package com.example.nushanchamara.merchantonboard.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.example.nushanchamara.merchantonboard.Models.DPMerchantImageUploads;
import com.example.nushanchamara.merchantonboard.R;

import java.util.ArrayList;

/**
 * Created by user1 on 3/2/2018.
 */

public class DPMerchantListActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private ListView list_view_1;
    private ArrayList<DPMerchantImageUploads> listImageUploades;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dp_merchant_list_activity);
        initViews();
    }
    public void initViews(){
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Unregistered Merchant Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_list_merchantdata);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        listParkedVehicles = new ArrayList<>();
        //Finally initializing our adapter
        listImageUploades = (ArrayList<DPMerchantImageUploads>)DPMerchantImageUploads.listAll(DPMerchantImageUploads.class);
        Log.d("image_data",listImageUploades.size()+" ");
        if (listImageUploades.size()>0){
            adapter = new DPMechantListAdapter(listImageUploades,DPMerchantListActivity.this);
            recyclerView.setAdapter(adapter);
        }
//        listImageUploades = DPMerchantImageUploads.findWithQuery(DPMerchantImageUploads.class, "Select * from DPMerchantImageUploads");


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent myIntent = new Intent(DPMerchantListActivity.this,
                DPSelectionMenuActivity.class);
        startActivity(myIntent);
    }
}
