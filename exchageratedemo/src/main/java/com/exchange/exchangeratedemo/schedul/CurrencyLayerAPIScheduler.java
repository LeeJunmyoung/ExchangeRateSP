package com.exchange.exchangeratedemo.schedul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.exchange.exchangeratedemo.service.RedisService;

@Component
public class CurrencyLayerAPIScheduler {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RedisService redisService;
	
	@Scheduled(cron="0 0 * * * *")
	private void schedulingCLayerAPI() {
		try {
			logger.info("[SCHEDULE CURRENCT LAYER API START]");
			redisService.storeRedisCLayerAPI();
			logger.info("[SCHEDULE CURRENCT LAYER API END]");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}
}
