package com.marko.springrestbeers.service;


import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.marko.springrestbeers.entity.Beer;
import com.marko.springrestbeers.entity.MashTemp;
import com.marko.springrestbeers.repository.BeerRepository;

@Service
public class BeerServiceImpl implements BeerService {
	
	@Autowired
	BeerRepository beerRepository;
	

	@Autowired
	RestTemplate restTemp;
	
	@Autowired
	HttpHeaders httpHeaders;
	
	public static final String BEER_URI = "https://api.punkapi.com/v2/beers/random";
	public static final int NUMBER_OF_ROWS = 9;
	
	//method for loading beers, @PostConstruct to initialize database before user requests
	
	@PostConstruct
	@Override
	public void loadBeers() {
		try {
				
			//sending request until the database is populated with 10 rows
			while(beerRepository.count()<=NUMBER_OF_ROWS) {
				
			httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			httpHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36");
	        HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
	       
	        //json response from api
	        ResponseEntity <String> response = restTemp.exchange(BEER_URI,HttpMethod.GET,entity, String.class);

	        //creating object mapper and mapping json values to java POJO
	        ObjectMapper mapper = new ObjectMapper();
	        String strBody = null;
	        if(response.getStatusCodeValue()==200) {
	        	strBody = response.getBody();
	        }
	        List<Beer> beers = Arrays.asList(mapper.readValue(strBody, Beer[].class));
	        System.out.println("Size of list is: "+ beers.size());
	        for (int i = 0; i < beers.size(); i++) {
	       	 	System.out.println("beer id: "+beers.get(i).getId());
				System.out.println("beer name: "+beers.get(i).getName());
				System.out.println("beer description: "+beers.get(i).getDescription());
				System.out.println("Size of the mash temps list is: "+beers.get(i).getMethod().getMash_temp().size());
				
				//persisting beer entity to database
				if(beers.get(i)!=null) {
					beerRepository.save(beers.get(i));
				}
	        }
		}
	    	
			}catch(JsonProcessingException exc) {}
	}

	//method for calculating temperature mean value
	
	public double meanTemperature(List<Beer>beers) {
		double meanTemp=0;
		for (int i = 0; i < beers.size(); i++) {
			List<MashTemp>mashTemps = beers.get(i).getMethod().getMash_temp();
			int sum=0;
			for(int j = 0; j < mashTemps.size(); j++) {
				int temp = mashTemps.get(j).getTemp().getValue();
				sum += temp;
			}
			System.out.println("sum of mash temps is: "+sum);
			meanTemp = sum/mashTemps.size();
			System.out.println("Mean value is: "+meanTemp);
			beers.get(i).setMeanValue(meanTemp);
		}
		return meanTemp;
	}
	
	
	@Override
	public List<Beer> getBeers() {
		return beerRepository.findAll();
	}

	@Override
	public void saveBeer(Beer beer) {
		beerRepository.save(beer);
		
	}

	@Override
	public Beer getBeerById(Integer beerId) {

		Beer beer = beerRepository.findById(beerId).get();
		List<MashTemp> mashTemp = beer.getMethod().getMash_temp();
		int sum =0;
		for(int i =0;i<mashTemp.size();i++) {
			int temp = mashTemp.get(i).getTemp().getValue();
			sum+=temp;
		}
		double meanTemp = sum/mashTemp.size();
		beer.setMeanValue(meanTemp);
		return beer;
	}

	@Override
	public void deleteBeerById(Integer beerId) {
		
		beerRepository.deleteById(beerId);
		
	}





}
