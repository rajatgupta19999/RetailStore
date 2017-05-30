package com.javatest.RetailStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javatest.RetailStore.service.LocationInfoService;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Controller
@RequestMapping("/location")
public class LocationInfoController {
	@Autowired
	private LocationInfoService locationInfoService;
	
	@RequestMapping(value = "/getInfo",method={RequestMethod.POST})
	public void getStoreLatLong(){
		locationInfoService.getLangLongForLocation();
	}
}
