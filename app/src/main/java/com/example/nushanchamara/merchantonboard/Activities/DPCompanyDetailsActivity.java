package com.example.nushanchamara.merchantonboard.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.nushanchamara.merchantonboard.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user1 on 3/2/2018.
 */

public class DPCompanyDetailsActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_companyName,tv_brnumber;
    private EditText et_agentid,et_app_reg_no,et_comp_email,et_comp_address,et_br_number,et_tel;
    private Button btn_save_companyData;
    private Spinner sp_business_type;
    private ImageView img_br_upload;
    private Toolbar toolbar;
    private String br_image,nic_frontimage,nic_backimage,outlet_image,owner_image;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dp_company_details);

        initViews();
    }
    public void initViews(){
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        tv_companyName=(TextView)findViewById(R.id.tv_companyName);
        tv_brnumber=(TextView)findViewById(R.id.tv_brnumber);
        et_agentid=(EditText)findViewById(R.id.et_agentid);
        et_app_reg_no=(EditText)findViewById(R.id.et_app_reg_no);
        et_comp_email=(EditText)findViewById(R.id.et_comp_email);
        et_comp_address=(EditText)findViewById(R.id.et_comp_address);
        et_br_number=(EditText)findViewById(R.id.et_br_number);
        et_tel=(EditText)findViewById(R.id.et_tel);

        sp_business_type=(Spinner) findViewById(R.id.sp_business_type);
        addBusinessTypesOnSpinner();

        img_br_upload=(ImageView)findViewById(R.id.img_br_upload);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Company Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btn_save_companyData=(Button) findViewById(R.id.btn_save_companyData);
        btn_save_companyData.setOnClickListener(this);

        Intent intent = getIntent();
        String company_name=intent.getStringExtra("company_name");
        String br_number=intent.getStringExtra("br_number");
         br_image=intent.getStringExtra("br_image");
        nic_frontimage=intent.getStringExtra("nic_frontimage");
        nic_backimage=intent.getStringExtra("nic_backimage");
        outlet_image=intent.getStringExtra("outlet_image");
        owner_image=intent.getStringExtra("owner_image");

        tv_companyName.setText(company_name);
        tv_brnumber.setText(br_number);

        Bitmap myBitmap = BitmapFactory.decodeFile(br_image);
        img_br_upload.setImageBitmap(myBitmap);
    }

    public void addBusinessTypesOnSpinner(){
        ArrayAdapter<String> adapter;
        List<String> list;

        list = new ArrayList<String>();
        list.add("Retail");
        list.add("Food and Beverage");
        list.add("FMCG");
        list.add("Stationary");
        list.add("Fashion Retail");
        list.add("Restaurant");
        list.add("Health Care");
        list.add("Hardware");
        list.add("Education");
        adapter = new ArrayAdapter<String>(DPCompanyDetailsActivity.this,
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_business_type.setAdapter(adapter);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save_companyData:
                awesomeValidation.addValidation(this,R.id.et_agentid, RegexTemplate.NOT_EMPTY,R.string.agentid_err);
                awesomeValidation.addValidation(this,R.id.et_app_reg_no,RegexTemplate.NOT_EMPTY,R.string.app_reg_err);
                awesomeValidation.addValidation(this, R.id.et_comp_email, Patterns.EMAIL_ADDRESS, R.string.error_invalid_email);
                awesomeValidation.addValidation(this,R.id.et_comp_address,RegexTemplate.NOT_EMPTY,R.string.address_err);
                awesomeValidation.addValidation(this,R.id.et_br_number,RegexTemplate.NOT_EMPTY,R.string.br_error);
                awesomeValidation.addValidation(this,R.id.et_tel, Patterns.PHONE,R.string.tel_error);

                if (awesomeValidation.validate()){
                    Intent intent = new Intent(DPCompanyDetailsActivity.this, DPBankDetailsActivity.class);

                    ArrayList<String> companyData = new ArrayList<String>();
                    companyData.add(tv_companyName.getText().toString());
                    companyData.add(tv_brnumber.getText().toString());
                    companyData.add(et_agentid.getText().toString().trim());
                    companyData.add(et_app_reg_no.getText().toString().trim());
                    companyData.add(et_comp_email.getText().toString().trim());
                    companyData.add(et_comp_address.getText().toString().trim());
                    companyData.add(sp_business_type.getSelectedItem().toString());
                    companyData.add(et_tel.getText().toString().trim());
                    companyData.add(br_image);

                    intent.putStringArrayListExtra("companyData",companyData);
                    intent.putExtra("nic_frontimage",nic_frontimage);
                    intent.putExtra("nic_backimage",nic_backimage);
                    intent.putExtra("outlet_image",outlet_image);
                    intent.putExtra("owner_image",owner_image);

//                    companyData=new DPCompanyData(tv_companyName.getText().toString(),tv_brnumber.getText().toString(),et_agentid.getText().toString().trim(),et_app_reg_no.getText().toString().trim(),
//                            et_comp_email.getText().toString().trim(),et_comp_address.getText().toString().trim(),sp_business_type.getSelectedItem().toString(),et_tel.getText().toString().trim(),br_image);
//                    companyData.save();
                    startActivity(intent);
                }

        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent myIntent = new Intent(DPCompanyDetailsActivity.this,
                DPMerchantListActivity.class);
        startActivity(myIntent);
    }
}
