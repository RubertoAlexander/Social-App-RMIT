package com.sept.rest.webservices.restfulwebservices.lineitem;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineItemService {
	
	@Autowired
	private LineItemJpaRepository lineItemRepository;
	
	public List<LineItem> findAll() {
		return this.lineItemRepository.findAll();
	}
	
	public LineItem findProducts(Long id) {
		Optional<LineItem> optional = this.lineItemRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public LineItem create(LineItem lineItem) {
		return this.lineItemRepository.save(lineItem);
	}

	public void update(LineItem lineItem) {
		this.lineItemRepository.save(lineItem);
	}
	
	public boolean lineItemExist(Long id) {
		return this.lineItemRepository.existsById(id);
	}
	
}
