package com.symatechlabs.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.symatechlabs.demo.adapters.MyAdapter;
import com.symatechlabs.demo.callbacks.ProductChangeListener;
import com.symatechlabs.demo.models.Product;
import com.symatechlabs.demo.models.Region;
import com.symatechlabs.demo.models.UserOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Demo extends AppCompatActivity implements ProductChangeListener, View.OnClickListener {

    FloatingActionButton fab;
    List<Region> regions = new ArrayList<>();
    List<UserOption> userOptions=new ArrayList<>();

    MyAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        //create recycler view that will receive the selections
        recyclerView=findViewById(R.id.recycler_view);
        adapter=new MyAdapter(this,userOptions,this);
        recyclerView.setAdapter(adapter);
        //generate fake regions data
        mockRegions();

        //when the fab is clicked we create a new user options in the recycler view
        //call adapter.notify item inserted

        fab.setOnClickListener(this);



    }

    public void mockRegions() {
        regions=new ArrayList<>();
        /**
         * skus
         * am creating two types of skus as template skus to re use in products
         */
        List<String> sku1 = new ArrayList<>();
        sku1.add("50 ml");
        sku1.add("100 ml");
        sku1.add("150 ml");
        sku1.add("200 ml");

        List<String> sku2 = new ArrayList<>();
        sku2.add("50 g");
        sku2.add("100 g");
        sku2.add("150 g");
        sku2.add("200 g");
        /**
         * products
         * am creating 11 products as templates to re use in 3 regions
         */

        Product product=new Product();
        product.setName("Product 1");
        product.setSkus(sku1);

        Product product1=new Product();
        product1.setName("Product 2");
        product1.setSkus(sku2);

        Product product2=new Product();
        product2.setSkus(sku1);
        product2.setName("Product 3");

        Product product3=new Product();
        product3.setName("Product 4");
        product3.setSkus(sku2);

        Product product4=new Product();
        product4.setName("Product 5");
        product4.setSkus(sku1);

        Product product5=new Product();
        product5.setName("Product 6");
        product5.setSkus(sku2);

        Product product6=new Product();
        product6.setName("Product 7");
        product6.setSkus(sku1);

        Product product7=new Product();
        product7.setSkus(sku2);
        product7.setName("Product 8");

        Product product8=new Product();
        product8.setName("Product 9");
        product8.setSkus(sku1);

        Product product9=new Product();
        product9.setSkus(sku2);
        product9.setName("Product 10");

        Product product10=new Product();
        product10.setName("Product 11");
        product10.setSkus(sku1);

        /**
         * 3 demo regions
         *
         */
        List<Product> products=new ArrayList<>();
        Region region1=new Region();
        region1.setName("Region A");
        products.add(product);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        region1.setProductsList(products);

        regions.add(region1);

        Region region2=new Region();
        region2.setName("Region B");
        products=new ArrayList<>();
        products.add(product5);
        products.add(product6);
        products.add(product7);
        products.add(product8);
        region2.setProductsList(products);

        regions.add(region2);

        Region region3=new Region();
        region3.setName("Region C");
        products=new ArrayList<>();
        products.add(product9);
        products.add(product10);
        products.add(product);
        products.add(product3);
        region3.setProductsList(products);

        regions.add(region3);


    }

    //this is our call back
    //we receive data from the recycler view adapter here
    @Override
    public void onItemCompleteListener(Map<String, String> selectedValues) {
        //the fully completed user selection from the listener in the recycler view
        Toast.makeText(this, "Selection: "+selectedValues.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        UserOption userOption=new UserOption();
        userOption.setRegions(regions);
        userOptions.add(userOption);
        //tell the recycler view that a new item has been added at the end
        adapter.notifyItemInserted(userOptions.size()-1);

    }
}
