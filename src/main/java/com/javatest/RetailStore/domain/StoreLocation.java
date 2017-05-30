package  com.javatest.RetailStore.domain;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StoreLocation {

    private String shopName ;
    private Map<String, ShopAddress> shopAddress;
    private String shopLatitude ;
    private String shopLongitude;
}
@Getter
@Setter
class ShopAddress{
    private int number;
    private int postalCode;
}