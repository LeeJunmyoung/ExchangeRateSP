package com.exchange.exchangeratedemo.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.exchange.exchangeratedemo.common.CommonUtil;
import com.exchange.exchangeratedemo.invo.ExchangeInput;
import com.exchange.exchangeratedemo.output.CurrencyLayer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ExchangeService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StringRedisTemplate redisTemplate;

	public CurrencyLayer getRedisCurrencyLayer() throws Exception {

		String cLayerStr = redisTemplate.opsForValue().get("exchange");

		ObjectMapper mapper = new ObjectMapper();

		CurrencyLayer resp = mapper.readValue(cLayerStr.toString(), new TypeReference<CurrencyLayer>() {
		});

		logger.info("[exchage]:" + CommonUtil.objectToString(resp));

		return resp;
	}

	public String calculateUSD(ExchangeInput input) throws Exception {
		String resp;
		CurrencyLayer cLayer = getRedisCurrencyLayer();
		String countryMoney = null;
		for (Field field : cLayer.getQuotes().getClass().getDeclaredFields()) {
			if (field.getName().contains(input.getDivision().toLowerCase())) {
				field.setAccessible(true);
				countryMoney = (String) field.get(cLayer.getQuotes());
				break;
			}
		}

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setRoundingMode(RoundingMode.DOWN);
		nf.setGroupingUsed(true);

		BigDecimal countryMoneyInt = new BigDecimal(nf.format(new BigDecimal(countryMoney)).replaceAll(",", ""));
		BigDecimal dollarInt = new BigDecimal(input.getDollar());

		logger.info("[dollar]       : " + dollarInt.toString());
		logger.info("[countryMoney] : " + countryMoneyInt.toString());

		DecimalFormat formatter = new DecimalFormat("###,###.00");
		
		resp = formatter.format(dollarInt.multiply(countryMoneyInt));
		logger.info("[cal] : "+resp);
		return resp;
	}

}
