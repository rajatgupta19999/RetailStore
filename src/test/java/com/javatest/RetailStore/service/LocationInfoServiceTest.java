package com.javatest.RetailStore.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.javatest.RetailStore.client.LocationInfoClient;
import com.javatest.RetailStore.domain.MapGeoCodeResponse;
import com.javatest.RetailStore.domain.ShopAddress;
import com.javatest.RetailStore.domain.StoreLocation;
import com.javatest.RetailStore.domain.geometry;
import com.javatest.RetailStore.domain.location;
import com.javatest.RetailStore.domain.results;

@RunWith(MockitoJUnitRunner.class)
public class LocationInfoServiceTest {
	@Mock
	private LocationInfoClient locationInfoClient;

	private LocationInfoService locationInfoService;

	@Before
	public void setUp() {
		locationInfoService = new LocationInfoService(locationInfoClient);
	}

	@Test
	public void shouldGetLatLongForLocation() {
		String shopName = "UttamSweets";
		int number = 62;
		int postalCode = 160047;
		ShopAddress shopAdd = ShopAddress.builder().number(number).postalCode(postalCode).build();
		StoreLocation storeLocation = StoreLocation.builder().shopName(shopName).shopAddress(shopAdd).build();
		List<StoreLocation> listOfShops = new ArrayList<StoreLocation>();
		listOfShops.add(storeLocation);
		location loc = location.builder().lat(45.00).lng(39.00).build();
		geometry geo = geometry.builder().location(loc).build();
		results res = results.builder().geometry(geo).build();
		List<results> listOfResults = new ArrayList<results>();
		listOfResults.add(res);
		MapGeoCodeResponse mapGeoCodeResponse = MapGeoCodeResponse.builder().results(listOfResults).status("ok").build();
		Mockito.when(locationInfoClient.getResponseFromMaps(storeLocation)).thenReturn(mapGeoCodeResponse);
		location response = locationInfoService.getLangLongForLocation(listOfShops, storeLocation);
		assertEquals(response.getLat(), new Double(45.00));
		assertEquals(response.getLng(), new Double(39.00));
	}

	@Test
	public void shouldFindNearestStore(){
		String shopName = "UttamSweets";
		int number = 62;	
		int postalCode = 160047;
		ShopAddress shopAdd = ShopAddress.builder().number(number).postalCode(postalCode).build();
		StoreLocation storeLocation = StoreLocation.builder().shopName(shopName).shopAddress(shopAdd).shopLatitude(46.00).shopLongitude(39.00).build();
		List<StoreLocation> listOfShops = new ArrayList<StoreLocation>();
		listOfShops.add(storeLocation);
		StoreLocation response = locationInfoService.findNearestStore(45.00, 39.00, listOfShops);
		assertEquals(response.getShopLatitude(), new Double(46.00));
		assertEquals(response.getShopLongitude(), new Double(39.00));
	}
}
