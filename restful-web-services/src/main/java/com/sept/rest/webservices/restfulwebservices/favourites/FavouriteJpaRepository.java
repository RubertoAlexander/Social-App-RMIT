package com.sept.rest.webservices.restfulwebservices.favourites;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteJpaRepository extends JpaRepository<Favourite, Long> {
	List<Favourite> findFavouriteByUserId(long userId);
	List<Favourite> findAll();
}
