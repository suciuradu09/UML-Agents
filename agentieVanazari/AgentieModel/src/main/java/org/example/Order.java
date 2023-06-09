package org.example;

import java.util.List;

public class Order extends Entity<Long>{

    private String name;

    private String adress;

    private String city;

    private String country;

    private String postalCode;

    private List<Produs> productList;

    public Order( String name, String adress, String city, String country, String postalCode) {
        this.name = name;
        this.adress = adress;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }

    public void setProductList(List<Produs> productList){
        this.productList = productList;
    }

}
