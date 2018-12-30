package com.exchange.exchangeratedemo.redis;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.exchange.exchangeratedemo.output.CurrencyLayer;
import com.exchange.exchangeratedemo.service.CurrencyLayerAPIService;

@Component
public class RedisRunner implements ApplicationRunner {

	@Autowired
	StringRedisTemplate redisTemplate;

	@Autowired
	CurrencyLayerAPIService currencyLayerAPIService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		CurrencyLayer cLayer = currencyLayerAPIService.getCurrencyLayerExchange();
		ValueOperations<String, String> values = redisTemplate.opsForValue();
		
	    for(Field field : cLayer.getQuotes().getClass().getDeclaredFields()) {
	    	if(field.getName().contains("usd")){
	    	field.setAccessible(true);
	    	values.set(field.getName().toUpperCase(), (String) field.get(cLayer.getQuotes()));
	    	}
	    }
		/*
		values.set("USDKRW", cLayer.getQuotes().getUsdkrw());
		values.set("USDJPY", cLayer.getQuotes().getUsdjpy());
		values.set("USDPHP", cLayer.getQuotes().getUdsphp());*/
		
	}

}