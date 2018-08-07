package com.symatechlabs.demo.models;

import java.util.ArrayList;
import java.util.List;

/**
 * region has name and one or more products
 */
public class Region {
    private String name;
    private List<Product> productsList=new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }

    @Override
    public String toString() {
        return name;
    }
}
