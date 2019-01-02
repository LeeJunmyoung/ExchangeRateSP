package com.exchange.exchangeratedemo.controller;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;




import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExchangeUSDControllerTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void getUSDExchageTest() throws Exception {
		mockMvc.perform(get("/api/v1/exchange"))
			   .andExpect(status().isOk())
			   .andDo(print());
	}

	@Test
	public void getUSDCalExchageTest() throws Exception {
		String json = "{\"dollar\":\"100\",\"division\":\"KRW\"}";
		mockMvc.perform(post("/api/v1/exchange")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json))
				.andExpect(status().isOk())
				.andDo(print());
	}

}
