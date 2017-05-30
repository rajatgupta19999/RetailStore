package com.javatest.RetailStore.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapGeoCodeResponse {
	private String status ;
    private List<results> results ;
}

@Getter
@Setter
 class bounds {
     private location northeast ;
     private location southwest ;
 }



@Getter
@Setter
 class address_component{
    private String long_name;
    private String short_name;
    private List<String> types ;
}

