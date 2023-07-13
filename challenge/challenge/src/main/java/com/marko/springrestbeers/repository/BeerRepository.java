package com.marko.springrestbeers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marko.springrestbeers.entity.Beer;


public interface BeerRepository extends JpaRepository<Beer, Integer>{

}
