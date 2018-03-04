package com.example.nushanchamara.merchantonboard.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.nushanchamara.merchantonboard.R;

import java.util.ArrayList;

/**
 * Created by user1 on 3/4/2018.
 */

public class DPBankDetailsActivity extends AppCompatActivity implements View.OnClickListener{
    private RadioButton rb_primary,rb_secondary,rb_non_company,rb_savings,rb_current,rb_joint;
    private RadioGroup rg_priority_type,rg_accountType;
    private EditText et_branchid,et_nicnum,et_acc_number,et_acc_name;
    private Button btn_addBankData;
    private int priority,accountType;
    private String br_image,nic_frontimage,nic_backimage,outlet_image,owner_image;
    private AwesomeValidation awesomeValidation;
    private Toolbar toolbar;
    private ArrayList companyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dp_bank_details);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Bank Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initViews();
    }
    private void initViews(){
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            companyData=extras.getStringArrayList("companyData");

            nic_frontimage=extras.getString("nic_frontimage");
            nic_backimage=extras.getString("nic_backimage");
            outlet_image=extras.getString("outlet_image");
            owner_image=extras.getString("owner_image");
            Log.d("bundledata",companyData.toString()+"||"+nic_frontimage);
        }
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        RadioGroup rg_priority_type=(RadioGroup)findViewById(R.id.rg_priority_type);
        rg_priority_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch(checkedId){
                    case R.id.rb_primary:
                        priority=0;
                        break;
                    case R.id.rb_secondary:
                        priority=1;
                        break;
                    case R.id.rb_non_company:
                        priority=2;
                        break;

                }
            }

        });

        RadioGroup rg_accountType=(RadioGroup)findViewById(R.id.rg_accountType);
        rg_accountType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //rb_savings,rb_current,rb_joint
                // checkedId is the RadioButton selected
                switch(checkedId){
                    case R.id.rb_savings:
                        accountType=0;
                        break;
                    case R.id.rb_current:
                        accountType=1;
                        break;
                    case R.id.rb_joint:
                        accountType=2;
                        break;

                }
            }

        });

        et_branchid=(EditText)findViewById(R.id.et_branchid);
        et_nicnum=(EditText)findViewById(R.id.et_nicnum);
        et_acc_number=(EditText)findViewById(R.id.et_acc_number);
        et_acc_name=(EditText) findViewById(R.id.et_acc_name);
        btn_addBankData=(Button)findViewById(R.id.btn_addBankData);
        btn_addBankData.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addBankData:
                awesomeValidation.addValidation(this,R.id.et_branchid, RegexTemplate.NOT_EMPTY,R.string.branchid_err);
                awesomeValidation.addValidation(this,R.id.et_acc_number,RegexTemplate.NOT_EMPTY,R.string.account_err);
                awesomeValidation.addValidation(this, R.id.et_nicnum,"(^[4,5,6,7,8,9][0-9]{8}[V,X,v,x])|(^[1,2][0-9]{11})", R.string.nic_err);
                if (awesomeValidation.validate()) {
                    Intent intent = new Intent(DPBankDetailsActivity.this, DPMerchantDetails.class);
                    intent.putStringArrayListExtra("companyData", companyData);

                    intent.putExtra("priority", priority);
                    intent.putExtra("account_type", accountType);
                    intent.putExtra("branch_id", et_branchid.getText().toString().trim());
                    intent.putExtra("nic_num", et_nicnum.getText().toString().trim());
                    intent.putExtra("acc_number", et_acc_number.getText().toString().trim());
                    intent.putExtra("acc_name",et_acc_name.getText().toString().trim());

                    intent.putExtra("nic_frontimage", nic_frontimage);
                    intent.putExtra("nic_backimage", nic_backimage);
                    intent.putExtra("outlet_image", outlet_image);
                    intent.putExtra("owner_image", owner_image);
                    startActivity(intent);
                }

                break;
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
        Intent myIntent = new Intent(DPBankDetailsActivity.this,
                DPCompanyDetailsActivity.class);
        startActivity(myIntent);
    }
}

