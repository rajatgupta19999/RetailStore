package com.javatest.RetailStore.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.javatest.RetailStore.domain.StoreLocation;
import com.javatest.RetailStore.domain.location;
import com.javatest.RetailStore.service.LocationInfoService;

@RunWith(MockitoJUnitRunner.class)
public class LocationInfoControllerTest {

	private LocationInfoController locationInfoController;
	
	@Mock
	private LocationInfoService locationInfoService;
	
	@Before
	public void setUp() {
		locationInfoController = new LocationInfoController(locationInfoService);
	}
	
	@Test
	public void shouldGetStoreLatLong(){
		StoreLocation strLoc = StoreLocation.builder().build();
		List<StoreLocation> listOfShops = new ArrayList<StoreLocation>();
		location loc = location.builder().build();
		Mockito.when(locationInfoService.getLangLongForLocation(listOfShops, strLoc)).thenReturn(loc);
		ResponseEntity<?> response= locationInfoController.getStoreLatLong(strLoc);
		assertEquals(response.getStatusCodeValue(),200);
	}
	
	@Test
	public void shouldFetchShopsNearMe(){
		StoreLocation strLoc = StoreLocation.builder().build();
		List<StoreLocation> listOfShops = new ArrayList<StoreLocation>();
		Mockito.when(locationInfoService.findNearestStore(45.00, 39.00, listOfShops)).thenReturn(strLoc);
		ResponseEntity<?> response= locationInfoController.fetchShopsNearMe(45.00, 39.00);
		assertEquals(response.getStatusCodeValue(),200);
		
	}
	
}
