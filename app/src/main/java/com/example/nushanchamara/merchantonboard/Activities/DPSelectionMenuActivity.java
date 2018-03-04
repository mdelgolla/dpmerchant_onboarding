package com.example.nushanchamara.merchantonboard.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.nushanchamara.merchantonboard.MainActivity;
import com.example.nushanchamara.merchantonboard.R;

/**
 * Created by user1 on 3/3/2018.
 */

public class DPSelectionMenuActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_reg_newCompany,btn_submit_companyData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dp_selection_menu_activity);

        btn_reg_newCompany=(Button)findViewById(R.id.btn_reg_newCompany);
        btn_reg_newCompany.setOnClickListener(this);

        btn_submit_companyData=(Button)findViewById(R.id.btn_submit_companyData);
        btn_submit_companyData.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reg_newCompany:
                Intent intent = new Intent(DPSelectionMenuActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_submit_companyData:
                Intent intent1=new Intent(DPSelectionMenuActivity.this, DPMerchantListActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                finish();
                break;
                default:
                    break;
        }
    }
}
