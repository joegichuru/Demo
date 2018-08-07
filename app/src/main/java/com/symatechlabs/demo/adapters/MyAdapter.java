package com.symatechlabs.demo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.symatechlabs.demo.R;
import com.symatechlabs.demo.callbacks.ProductChangeListener;
import com.symatechlabs.demo.models.Product;
import com.symatechlabs.demo.models.Region;
import com.symatechlabs.demo.models.UserOption;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * the recycler view adapter
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context mContext;
    private List<UserOption> userOptions = new ArrayList<>();
    private ProductChangeListener productChangeListener;

    private Map<String,String> selectedValues=new HashMap<>();

    public MyAdapter(Context mContext, List<UserOption> userOptions, ProductChangeListener productChangeListener) {
        this.mContext = mContext;
        this.userOptions = userOptions;
        this.productChangeListener = productChangeListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.user_selection_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Spinner regionSpinner = holder.regionSpinner;
        final Spinner productSpinner = holder.productSpinner;
        final Spinner skuSpinner = holder.skuSpinner;
        UserOption userOption = userOptions.get(position);
        final List<Region> regions = userOption.getRegions();
        //add logic to populate spinners and control which views are enabled and disabled
        //populate regions spinner
        ArrayAdapter<Region> regionsAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_dropdown_item, regions);
        regionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regionSpinner.setAdapter(regionsAdapter);

        //when a region is clicked update the products spinner to be as the region products
        //also keep track of the region. i am using a map to store the values
        regionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //populate products spinner
                Region selectedRegion = regions.get(position);
                //keep track
                selectedValues.put("region",selectedRegion.getName());

                //populate the products spinner
                final List<Product> productsList = selectedRegion.getProductsList();
                ArrayAdapter<Product> productArrayAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_dropdown_item, productsList);
                productArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                productSpinner.setAdapter(productArrayAdapter);
                productSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Product selectedProduct= productsList.get(position);
                        //keep track of the selected product
                        selectedValues.put("product",selectedProduct.getName());

                        //populate skus spinner
                        final List<String> skus=selectedProduct.getSkus();
                        ArrayAdapter<String> skuArrayAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_dropdown_item, skus);
                        skuArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        skuSpinner.setAdapter(skuArrayAdapter);
                        skuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                selectedValues.put("sku",skus.get(position));
                                //call the listener and pass the values back to the activity
                                //todo i have added this such that when a user selects an sku the complete data is submitted
                                //you can add a button which will show up only when all values are selected and when the user clicks the
                                //button the content is submitted
                                //the activity hosting the recycler view must implement this listener to be able to receive the values send in
                                //this case in form of a map
                                productChangeListener.onItemCompleteListener(selectedValues);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //fancy animations
        //this will make the row to slide from left when being added
        holder.itemView.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.slide_in_from_left));
    }

    //it can take a regions then extract the products
    //alternatively you can change the method signature then just take the list of products as parameter

    @Override
    public int getItemCount() {
        return userOptions.size();
    }
    //the view holder to host a single row

    public class MyViewHolder extends RecyclerView.ViewHolder {

        Spinner regionSpinner;
        Spinner productSpinner;
        Spinner skuSpinner;

        public MyViewHolder(View itemView) {
            super(itemView);
            regionSpinner = itemView.findViewById(R.id.region_spinner);
            productSpinner = itemView.findViewById(R.id.product_spinner);
            skuSpinner = itemView.findViewById(R.id.sku_spinner);
        }
    }
}
