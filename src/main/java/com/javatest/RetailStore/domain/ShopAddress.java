package com.javatest.RetailStore.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ShopAddress{
    private int number;
    private int postalCode;
}
