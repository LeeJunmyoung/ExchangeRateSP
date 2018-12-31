
package com.exchange.exchangeratedemo.common;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class CommonUtil {

	protected static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

	public static JSONObject serviceInterfaceRequest(String urlFullStr, Object reqParam, HttpMethod sendType)
			throws IOException, JSONException {

		String strParam = "";

		if (reqParam != null) {
			// ObjectParameter를 String형으로 변환
			strParam = objectToString(reqParam);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
		HttpEntity jsonBody = new HttpEntity(strParam, headers);

		RestTemplate restTemplate = new RestTemplate();

		logger.info("[INTERFACE REQUEST URL] :" + objectToString(urlFullStr));
		logger.info("[INTERFACE REQUEST BODY] :" + objectToString(jsonBody));

		String statusCode = "";
		String responseObjResultBody = "";

		try {

			ResponseEntity<String> responseResult = restTemplate.exchange(urlFullStr, sendType, jsonBody, String.class);

			statusCode = responseResult.getStatusCode().toString();
			if (responseResult.getBody() != null) {
				responseObjResultBody = responseResult.getBody().toString();
			}

		} catch (RestClientException ex) {
			throw new RestClientException(ex.getMessage());
		}

		JSONObject result;

		// Object
		logger.info("[INTERFACE RESPONSE BODY] :" + responseObjResultBody);

		result = new JSONObject(responseObjResultBody);
		return result;
	}

	public static JSONArray serviceInterfaceListRequest(String urlStr, Object reqParam, HttpMethod sendType)
			throws IOException, JSONException {
		// ObjectParameter를 String형으로 변환

		String strParam = "";

		if (reqParam != null) {
			// ObjectParameter를 String형으로 변환
			strParam = objectToString(reqParam);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));

		HttpEntity jsonBody = new HttpEntity(strParam, headers);

		RestTemplate restTemplate = new RestTemplate();

		logger.info("[INTERFACE REQUEST URL] :" + urlStr);
		logger.info("[INTERFACE REQUEST BODY] :" + strParam);

		String statusCode = "";
		String responseObjResultBody = null;
		try {

			ResponseEntity<String> responseResult = restTemplate.exchange(urlStr, sendType, jsonBody, String.class);
			statusCode = responseResult.getStatusCode().toString();
			if (responseResult.getBody() != null) {
				responseObjResultBody = responseResult.getBody().toString();
			}

		} catch (RestClientException ex) {
			if (!"200".equals(statusCode)) {
				throw new RestClientException(ex.getMessage());
			}
		}

		// Object
		logger.info("[INTERFACE RESPONSE BODY] :" + responseObjResultBody);

		String resultString = responseObjResultBody;
		JSONArray respArray = new JSONArray(resultString);

		JSONArray resultArray = new JSONArray();
		JSONObject resultObj = new JSONObject();

		for (int i = 0; i < respArray.length(); i++) {
			resultObj = new JSONObject();
			resultObj = (JSONObject) respArray.get(i);
			resultArray.put(resultObj);
		}

		return resultArray;
	}
	
	public static String objectToString(Object vo) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		try {
			if (vo.getClass() == JSONObject.class) {
				return vo.toString();
			}
			return objectMapper.writeValueAsString(vo);

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

}
