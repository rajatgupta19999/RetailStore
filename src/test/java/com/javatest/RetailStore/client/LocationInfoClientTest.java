package com.javatest.RetailStore.client;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import com.javatest.RetailStore.domain.MapGeoCodeResponse;
import com.javatest.RetailStore.domain.ShopAddress;
import com.javatest.RetailStore.domain.StoreLocation;


public class LocationInfoClientTest {

	
	private RestTemplate restTemplate = new RestTemplate();

	private String codeApiUrl = "http://maps.googleapis.com/maps/api/geocode/json?address=";

	private LocationInfoClient locationInfoClient;
	MockRestServiceServer mockServer;

	@Before
	public void setUp() {
		locationInfoClient = new LocationInfoClient(restTemplate, codeApiUrl);
		mockServer = MockRestServiceServer.createServer(restTemplate);
	}

	@Test
	public void shouldgetResponseFromMaps() {
		String shopName = "UttamSweets";
		int number = 62;	
		int postalCode = 160047;
		String inputUrl = codeApiUrl + shopName +"%20" +number + "%20"+postalCode + "&sensor=true";
		ShopAddress shopAdd = ShopAddress.builder().number(number).postalCode(postalCode).build();
		StoreLocation storeLocation = StoreLocation.builder().shopName(shopName).shopAddress(shopAdd).build();
		mockServer.expect(MockRestRequestMatchers.requestTo(inputUrl)).andRespond(MockRestResponseCreators
				.withStatus(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("{ \"status\" : \"OK\"}"));
		MapGeoCodeResponse response = locationInfoClient.getResponseFromMaps(storeLocation);
		assertEquals(response.getStatus(),"OK");
	}
}
