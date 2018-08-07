package com.symatechlabs.demo.callbacks;

import com.symatechlabs.demo.models.Region;

import org.json.JSONObject;

import java.util.Map;

/**
 * this will be called when all the elements of have changed i.e the region has been set, the product selected and sku set
 */
public interface ProductChangeListener {
    //send the complete region back to implementation activity as a ma
    void onItemCompleteListener(Map<String,String> selectedValues);

}
