package com.sept.rest.webservices.restfulwebservices.dbfile;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sept.rest.webservices.restfulwebservices.exceptions.DataNotFoundException;
import com.sept.rest.webservices.restfulwebservices.exceptions.FileStorageException;
import com.sept.rest.webservices.restfulwebservices.products.Product;

@Service
public class DBFileService {

    @Autowired
    private DBFileRepository dbFileRepository;

    public DBFile storeFile(MultipartFile file, Product product) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes(), product);

            
            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
        }
    }

    public DBFile getFile(String fileId) {
    	Optional<DBFile> optional = dbFileRepository.findById(fileId);
    	if (optional.isPresent()) {
    		return optional.get();
    	} else {
    		throw new DataNotFoundException("File not found with id " + fileId);
    	}
    }
}
