package com.javatest.RetailStore.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class results {
	  private String formatted_address ;
	    private geometry geometry ;
	    private List<String> types;
	    private List<address_component> address_components;
	    private String place_id;
}
