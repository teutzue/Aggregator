package test;
import static org.junit.Assert.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import core.AggregatorService;

public class AggregatorTest {

	
	@Test
	public void testAggregatorService() throws JSONException {
		String quotes = "[{\"interestRate\":3.6399999999999997,\"bank\":\"NordeaBank\",\"ssn\":1234566543},"
				+ "{\"interestRate\":5.5,\"bank\":\"BankOfNorrebro\",\"ssn\":1605789787}]";
		
		JSONObject returnedQuote=null;
		AggregatorService as = new AggregatorService();
		returnedQuote=as.getBestQuote(quotes);
		
		assertTrue(3.6399999999999997==returnedQuote.getDouble("interestRate"));
		assertEquals("1234566543",returnedQuote.getString("ssn"));
		
	}

}
