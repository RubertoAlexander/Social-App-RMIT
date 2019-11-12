package com.sept.rest.webservices.restfulwebservices.lineitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemJpaRepository extends JpaRepository<LineItem, Long>{

}
