package com.symatechlabs.demo.models;

import java.util.ArrayList;
import java.util.List;

/**
 * a product has name and one or more skus
 * i have represented SKUS as string
 */
public class Product {
    private String name;
    private List<String> skus=new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSkus() {
        return skus;
    }

    public void setSkus(List<String> skus) {
        this.skus = skus;
    }

    //must override this method because we are using this model in the ArrayAdapter
    @Override
    public String toString() {
        return name;
    }
}
