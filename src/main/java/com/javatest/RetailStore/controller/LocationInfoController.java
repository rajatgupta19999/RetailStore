package com.javatest.RetailStore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javatest.RetailStore.Exception.InvalidAddressException;
import com.javatest.RetailStore.domain.StoreLocation;
import com.javatest.RetailStore.domain.location;
import com.javatest.RetailStore.service.LocationInfoService;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/location")
@Slf4j
public class LocationInfoController {
	@Autowired
	private LocationInfoService locationInfoService;

	List<StoreLocation> listOfShops = new ArrayList<StoreLocation>();
	
	
	public LocationInfoController(LocationInfoService locationInfoService){
		this.locationInfoService =  locationInfoService;
	}
	

	@RequestMapping(value = "/getInfo", method = { RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<?> getStoreLatLong(@RequestBody StoreLocation storeLocation) {
		try {
			location loc = locationInfoService.getLangLongForLocation(listOfShops, storeLocation);
			log.info("Total Shops in Array:" + listOfShops.size());
			return new ResponseEntity<location>(loc, HttpStatus.OK);
		} catch (InvalidAddressException inv) {
			return new ResponseEntity<String>(inv.getMessage(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@RequestMapping(value = "/fetching-shops/lattitude/{lattitude}/longitude/{longitude}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> fetchShopsNearMe(@PathVariable Double lattitude, @PathVariable Double longitude) {
		StoreLocation strLoc = locationInfoService.findNearestStore(lattitude, longitude, listOfShops);
		if (null != strLoc)
			return new ResponseEntity<StoreLocation>(strLoc, HttpStatus.OK);
		else
			return new ResponseEntity<String>("No Nearest Store Present", HttpStatus.OK);

	}


}
