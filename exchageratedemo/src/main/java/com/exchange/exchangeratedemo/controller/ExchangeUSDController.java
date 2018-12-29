package com.exchange.exchangeratedemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.exchangeratedemo.output.CurrencyLayer;
import com.exchange.exchangeratedemo.service.CurrencyLayerAPIService;

@RestController
public class ExchangeUSDController {
	
	@Autowired
	CurrencyLayerAPIService currencyLayerAPIService;

	@RequestMapping(value="/api/v1/exchange", method=RequestMethod.GET)
	public @ResponseBody CurrencyLayer getUSDExchage(){
		CurrencyLayer resp = null;
		try {
			resp = currencyLayerAPIService.getCurrencyLayerExchange();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return resp;
	}
	
}
