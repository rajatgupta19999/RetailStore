package com.javatest.RetailStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatest.RetailStore.client.LocationInfoClient;
import com.javatest.RetailStore.domain.MapGeoCodeResponse;
import com.javatest.RetailStore.domain.StoreLocation;
import com.javatest.RetailStore.domain.results;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LocationInfoService {

	private LocationInfoClient locationInfoClient;

	@Autowired
	public LocationInfoService(LocationInfoClient locationInfoClient) {
		this.locationInfoClient = locationInfoClient;
	}

	public void getLangLongForLocation() {
		log.info("Entering getLangLongForLocation");
		MapGeoCodeResponse mapResponse = locationInfoClient.getResponseFromMaps();
		StoreLocation mapResponseToBeSaved = new StoreLocation();
		results res = mapResponse.getResults().get(0);
		mapResponseToBeSaved.setShopLatitude(res.getGeometry().getLocation().getLat());
		mapResponseToBeSaved.setShopLongitude(res.getGeometry().getLocation().getLng());
		log.info("Exiting  getLangLongForLocation");

	}
}