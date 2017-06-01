package com.javatest.RetailStore.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class geometry {
   
        private bounds bounds;
        private String location_type ;
        private location location;
        private bounds viewport;
    }


