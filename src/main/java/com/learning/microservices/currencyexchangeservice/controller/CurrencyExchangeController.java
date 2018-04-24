package com.learning.microservices.currencyexchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learning.microservices.currencyexchangeservice.model.ExchangeValue;
import com.learning.microservices.currencyexchangeservice.repo.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	CurrencyExchangeRepository repository;
	
	@Autowired
	private Environment enviroment; 
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue getCurrencyExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
		exchangeValue.setPort(Integer.parseInt(enviroment.getProperty("local.server.port")));
		
		return exchangeValue;
		
	}

}
