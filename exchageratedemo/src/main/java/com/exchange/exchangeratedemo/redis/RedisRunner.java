package com.exchange.exchangeratedemo.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.exchange.exchangeratedemo.service.RedisService;

@Component
public class RedisRunner implements ApplicationRunner {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RedisService redisService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		redisService.storeRedisCLayerAPI();
	}

}