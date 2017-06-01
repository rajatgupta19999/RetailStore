package  com.javatest.RetailStore.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class StoreLocation {

    private String shopName ;
    private ShopAddress shopAddress;
    private Double shopLatitude ;
    private Double shopLongitude;
}
