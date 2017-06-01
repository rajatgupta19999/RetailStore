package com.javatest.RetailStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatest.RetailStore.Exception.InvalidAddressException;
import com.javatest.RetailStore.client.LocationInfoClient;
import com.javatest.RetailStore.domain.MapGeoCodeResponse;
import com.javatest.RetailStore.domain.StoreLocation;
import com.javatest.RetailStore.domain.location;
import com.javatest.RetailStore.domain.results;
import com.javatest.RetailStore.util.DistanceUtil;




@Service
public class LocationInfoService {

	@Autowired
	private LocationInfoClient locationInfoClient;

	
	public LocationInfoService(LocationInfoClient locationInfoClient) {
		this.locationInfoClient = locationInfoClient;
	}

	public location getLangLongForLocation(List<StoreLocation> listOfShops, StoreLocation storeLocation) {
		MapGeoCodeResponse mapResponse = locationInfoClient.getResponseFromMaps(storeLocation);
		if(mapResponse.getStatus().equalsIgnoreCase("ZERO_RESULTS"))
			throw new InvalidAddressException("Invalid Addtess");
		results res = mapResponse.getResults().get(0);
		location loc = res.getGeometry().getLocation();
		Double lat = loc.getLat();
		Double lng = loc.getLng();
		storeLocation.setShopLatitude(lat);
		storeLocation.setShopLongitude(lng);
		addShopToListOfShops(listOfShops, lat, lng, storeLocation);
		
		return loc;
	}

	private void addShopToListOfShops(List<StoreLocation> listOfShops, Double lat, Double lng,
			StoreLocation storeLocation) {
		boolean shopExists = false;
		for (StoreLocation st : listOfShops) {
			shopExists = checkIfShopExists(lat, lng, st);
		}
		if (!shopExists) {
			listOfShops.add(storeLocation);
		}
	}

	private boolean checkIfShopExists(Double lat, Double lng , StoreLocation st) {
		if (st.getShopLatitude().equals(lat) && st.getShopLongitude().equals(lng)) {
			return true;
		}
		return false;
	}

	public StoreLocation findNearestStore(Double lat, Double lng, List<StoreLocation> listOfShops) {
		Double smallestDistance = -1.00;
		StoreLocation nearestLoc = null;
		for (StoreLocation strLoc : listOfShops) {
			if(!checkIfShopExists(lat,lng,strLoc)){
			Double distance = DistanceUtil.getdistanceBetweenTwopoints(strLoc.getShopLatitude(),
					strLoc.getShopLongitude(), lat, lng,0.00,0.00);
			if (smallestDistance == -1 || distance < smallestDistance) {
				nearestLoc = strLoc;
				smallestDistance = distance;
			}}
		}
		return nearestLoc;
	}
}