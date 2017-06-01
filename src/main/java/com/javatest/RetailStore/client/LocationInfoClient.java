package com.javatest.RetailStore.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.javatest.RetailStore.domain.MapGeoCodeResponse;
import com.javatest.RetailStore.domain.ShopAddress;
import com.javatest.RetailStore.domain.StoreLocation;

@Component
public class LocationInfoClient {

	@Autowired
	private RestTemplate restTemplate;

	private String codeApiUrl;

	
	public LocationInfoClient(RestTemplate restTemplate, @Value("${url.codeapi}") String codeApiUrl) {
		this.restTemplate = restTemplate;
		this.codeApiUrl = codeApiUrl;
	}

	public MapGeoCodeResponse getResponseFromMaps(StoreLocation storeLocation) {
		ResponseEntity<MapGeoCodeResponse> response = null;
			ShopAddress shopAddress = storeLocation.getShopAddress();
			String url = codeApiUrl + storeLocation.getShopName() + " " + shopAddress.getNumber() + " "
					+ shopAddress.getPostalCode() + "&sensor=true";
			response = restTemplate.getForEntity(url, MapGeoCodeResponse.class);
		return response.getBody();

	}
}
