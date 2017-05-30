package com.javatest.RetailStore.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.javatest.RetailStore.domain.MapGeoCodeResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LocationInfoClient {

	private RestTemplate restTemplate;

	@Autowired
	public LocationInfoClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public MapGeoCodeResponse getResponseFromMaps() {

		log.info("Entering getResponseFromMaps");
		ResponseEntity<MapGeoCodeResponse> response = null;
		try {
			String url = "http://maps.googleapis.com/maps/api/geocode/json?address="
					+ "Sayaji Hotel, Near balewadi stadium, pune" + "&sensor=true";
			response = restTemplate.getForEntity(url, MapGeoCodeResponse.class);
			log.info("Exiting getResponseFromMaps");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		return response.getBody();

	}
}
