package com.marko.springrestbeers.service;

import java.util.List;

import com.marko.springrestbeers.entity.Beer;
import com.marko.springrestbeers.entity.MashTemp;


public interface BeerService {

	void loadBeers();
	List<Beer>getBeers();
	void saveBeer(Beer beer);
	Beer getBeerById(Integer beerId);
	void deleteBeerById(Integer beerId);
	
	
	double meanTemperature(List<Beer>beers);
	

	
}
