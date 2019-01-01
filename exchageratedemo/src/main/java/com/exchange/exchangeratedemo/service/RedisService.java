package com.exchange.exchangeratedemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.exchange.exchangeratedemo.common.CommonUtil;
import com.exchange.exchangeratedemo.output.CurrencyLayer;

@Service
public class RedisService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StringRedisTemplate redisTemplate;

	@Autowired
	CurrencyLayerAPIService currencyLayerAPIService;
	
	public void storeRedisCLayerAPI() throws Exception{
		CurrencyLayer cLayer = currencyLayerAPIService.getCurrencyLayerExchange();
		ValueOperations<String, String> values = redisTemplate.opsForValue();
		
		values.set("exchange", CommonUtil.objectToString(cLayer));
		
	    logger.info("[exchage]:"+CommonUtil.objectToString(cLayer));
	}
	
}
