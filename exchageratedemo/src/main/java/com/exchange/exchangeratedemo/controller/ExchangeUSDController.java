package com.exchange.exchangeratedemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.exchangeratedemo.invo.ExchangeInput;
import com.exchange.exchangeratedemo.output.CurrencyLayer;
import com.exchange.exchangeratedemo.service.CurrencyLayerAPIService;
import com.exchange.exchangeratedemo.service.ExchangeService;

@RestController
public class ExchangeUSDController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CurrencyLayerAPIService currencyLayerAPIService;

	@Autowired
	ExchangeService exchangeService;
	
	@RequestMapping(value="/api/v1/exchange", method=RequestMethod.GET)
	public @ResponseBody CurrencyLayer getUSDExchage(){
		CurrencyLayer resp = null;
		try {
			resp = exchangeService.getRedisCurrencyLayer();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		return resp;
	}
	
	@RequestMapping(value="/api/v1/exchange", method=RequestMethod.POST)
	public @ResponseBody String getUSDCalExchage(@RequestBody ExchangeInput input){
		String resp =null;
		try {
			resp = exchangeService.calculateUSD(input);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return resp;
	}
	
}
