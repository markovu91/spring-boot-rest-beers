package com.marko.springrestbeers.controller;


import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marko.springrestbeers.entity.Beer;
import com.marko.springrestbeers.exception.BeerNotFoundException;
import com.marko.springrestbeers.service.BeerService;

@RestController
public class BeerRestController {

	@Autowired
	BeerService beerService;
	
	
	@RequestMapping("/hello")
	public String Home() {
		return "hello!";
	}
	
	@GetMapping(path="listBeers",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<Beer> beers() {
		List<Beer>beers=beerService.getBeers();
		beerService.meanTemperature(beers);
		return beers;
	}
	
	
	@GetMapping(path="/beers/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Beer getBeerById(@PathVariable(value = "id") Integer id) {
		
		//check id against list size
		if(id >= beers().size() || id < 0) {
			
			throw new BeerNotFoundException("Beer with id - "+ id + " not found!");
		}
		
		return beerService.getBeerById(id);
		
	}
	
	@DeleteMapping("/beers/{id}")
	public void deleteBeerById(@PathVariable(value = "id") Integer id) {
		//check id against list size
		if(id >= beers().size() || id < 0) {
			
			throw new BeerNotFoundException("Beer with id - "+ id + " not found! Can't delete beer!");
		}
		
		beerService.deleteBeerById(id);
		
	}
	
	
	@PutMapping("/beers/{id}")
	public ResponseEntity<Beer> updateBeer(@RequestBody Beer beer, @PathVariable Integer id) {
		try {
		Beer existBeer = beerService.getBeerById(id);
		beerService.saveBeer(beer);
		return new ResponseEntity<Beer>(HttpStatus.OK);
		}catch(NoSuchElementException exc) {
			return new ResponseEntity<Beer>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	
	@PostMapping("/beers")
	public void addBeer(@RequestBody Beer beer) {
		beerService.saveBeer(beer);
	}

	
}
