package com.exchange.exchangeratedemo.service;

import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.exchange.exchangeratedemo.common.CommonUtil;
import com.exchange.exchangeratedemo.output.CurrencyLayer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CurrencyLayerAPIService {

	public CurrencyLayer getCurrencyLayerExchange() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		String url = "http://apilayer.net/api/live?access_key=76a447f947d9909090e8e829871df566";

		JSONObject responseObj = CommonUtil.serviceInterfaceRequest(url, null, HttpMethod.GET);

		CurrencyLayer resp = mapper.readValue(responseObj.toString(), new TypeReference<CurrencyLayer>() {});

		return resp;
	}

}
