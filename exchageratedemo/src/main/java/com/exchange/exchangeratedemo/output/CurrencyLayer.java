package com.exchange.exchangeratedemo.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyLayer {

	public CurrencyLayer() {
		quotes = new Quotes();
	}
	
	@JsonProperty
	private String success;
	
	@JsonProperty
	private String terms;
	
	@JsonProperty
	private String timestamp;
	
	@JsonProperty
	private String source;
	
	@JsonProperty
	private Quotes quotes;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Quotes getQuotes() {
		return quotes;
	}

	public void setQuotes(Quotes quotes) {
		this.quotes = quotes;
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public class Quotes{
		@JsonProperty("USDKRW")
		private String usdkrw;
		
		@JsonProperty("USDJPY")
		private String usdjpy;
		
		@JsonProperty("USDPHP")
		private String udsphp;

		public String getUsdkrw() {
			return usdkrw;
		}

		public void setUsdkrw(String usdkrw) {
			this.usdkrw = usdkrw;
		}

		public String getUsdjpy() {
			return usdjpy;
		}

		public void setUsdjpy(String usdjpy) {
			this.usdjpy = usdjpy;
		}

		public String getUdsphp() {
			return udsphp;
		}

		public void setUdsphp(String udsphp) {
			this.udsphp = udsphp;
		}

	}
	
}


