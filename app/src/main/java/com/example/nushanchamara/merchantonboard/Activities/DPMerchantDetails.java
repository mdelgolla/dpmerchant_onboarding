package com.example.nushanchamara.merchantonboard.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.nushanchamara.merchantonboard.R;
import com.example.nushanchamara.merchantonboard.Utilities.PABMultipartEntity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user1 on 3/4/2018.
 */

public class DPMerchantDetails extends AppCompatActivity {
private EditText et_name,et_email,et_mobile,et_address,et_avg_revenue;
private ImageView img_nicfront,img_nicback,img_owner,img_outlet;
private Toolbar toolbar;
private String br_image,nic_frontimage,nic_backimage,outlet_image,owner_image,branch_id,nic_num,acc_number,acc_name;
private int priority,account_type;
private ArrayList companyData;
private long totalSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dp_merchant_details);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Merchant Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        initViews();
    }
    public void initViews(){
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            companyData=extras.getStringArrayList("companyData");
            Log.d("bumdledata_company",companyData.get(2).toString().trim());

            nic_frontimage=extras.getString("nic_frontimage");
            nic_backimage=extras.getString("nic_backimage");
            outlet_image=extras.getString("outlet_image");
            owner_image=extras.getString("owner_image");

            priority=extras.getInt("priority");
            account_type=extras.getInt("account_type");
            branch_id=extras.getString("branch_id");
            nic_num=extras.getString("nic_num");
            acc_number=extras.getString("acc_number");
            acc_name=extras.getString("acc_name");

            Log.d("bundledata",companyData.toString()+"||"+nic_frontimage+"||"+priority);

            img_nicfront=(ImageView)findViewById(R.id.img_nicfront);
            Bitmap myBitmap = BitmapFactory.decodeFile(nic_frontimage);
            img_nicfront.setImageBitmap(myBitmap);

            img_nicback=(ImageView)findViewById(R.id.img_nicback);
            Bitmap nicBitmap = BitmapFactory.decodeFile(nic_backimage);
            img_nicback.setImageBitmap(nicBitmap);

            img_owner=(ImageView) findViewById(R.id.img_owner);
            Bitmap ownerBitmap = BitmapFactory.decodeFile(owner_image);
            img_owner.setImageBitmap(ownerBitmap);

            img_outlet=(ImageView)findViewById(R.id.img_outlet);
            Bitmap outletBitmap = BitmapFactory.decodeFile(outlet_image);
            img_outlet.setImageBitmap(outletBitmap);
        }

        et_name=(EditText)findViewById(R.id.et_name);
        et_email=(EditText)findViewById(R.id.et_email);
        et_mobile=(EditText)findViewById(R.id.et_mobile);
        et_address=(EditText)findViewById(R.id.et_address);
        et_avg_revenue=(EditText)findViewById(R.id.et_avg_revenue);

    }
    private class UploadFileToServer extends AsyncTask<Void, Integer, String> {
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {

        }


        @Override
        protected String doInBackground(Void... params) {

            return uploadFile();
        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {
            Log.i("upload", ":");
            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(getString(R.string.SERVER_URL)+getString(R.string.ONBOARD_MERCHANT));

            try {
                PABMultipartEntity entity = new PABMultipartEntity(
                        new PABMultipartEntity.ProgressListener() {

                            @Override
                            public void transferred(long num) {
                                publishProgress((int) ((num / (float) totalSize) * 100));
                            }
                        });
//                filePath = fileUri.getPath();
                Log.i("filePath", ":" + nic_frontimage);

                File merchant_nic_front = new File(nic_frontimage);
                File merchant_nic_back = new File(nic_backimage);
                File merchant_photo = new File(owner_image);
                File branchStoreFront = new File(outlet_image);
                File brPhoto=new File(companyData.get(8).toString().trim());

                entity.addPart("agent",new StringBody(companyData.get(2).toString().trim()));
                entity.addPart("companyName",new StringBody(companyData.get(0).toString().trim()));
                entity.addPart("companyEmail",new StringBody(companyData.get(4).toString().trim()));
                entity.addPart("companyAddress",new StringBody(companyData.get(5).toString().trim()));
                entity.addPart("companyBusinessType",new StringBody(companyData.get(6).toString().trim()));
                entity.addPart("businessRegNumber",new StringBody(companyData.get(1).toString().trim()));
                entity.addPart("companyTelephone",new StringBody(companyData.get(7).toString().trim()));
                entity.addPart("CombankAvaTypes",new StringBody(String.valueOf(priority)));
                entity.addPart("comBankPrimaryType",new StringBody(String.valueOf(account_type)));
                entity.addPart("comBankPrimaryBranch",new StringBody(branch_id));
                entity.addPart("comBankPrimaryNic",new StringBody(nic_num));
                entity.addPart("comBankPrimaryAccountNumber",new StringBody(acc_number));
                entity.addPart("comBankPrimaryAccountName",new StringBody(acc_name));
                entity.addPart("comBankPrimarySms1",new StringBody("+444444444"));
                entity.addPart("merchant-name",new StringBody(et_name.getText().toString().trim()));
                entity.addPart("merchant-email",new StringBody(et_email.getText().toString().trim()));
                entity.addPart("merchant-mobile",new StringBody(et_mobile.getText().toString().trim()));
                entity.addPart("merchant-addres",new StringBody(et_address.getText().toString().trim()));
                entity.addPart("merchant-revenue",new StringBody(et_avg_revenue.getText().toString().trim()));


                // Adding file data to http body
                entity.addPart("merchant-nic-front", new FileBody(merchant_nic_front));
                entity.addPart("merchant-nic-back", new FileBody(merchant_nic_back));
                entity.addPart("merchant-photo", new FileBody(merchant_photo));
                entity.addPart("branchStoreFront", new FileBody(branchStoreFront));
                entity.addPart("brPhoto", new FileBody(brPhoto));


                totalSize = entity.getContentLength();
                httppost.setEntity(entity);
                Log.d("communication_service",entity+"");

                // Making server call
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity r_entity = response.getEntity();
                Log.d("communication_service",response);

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    // Server response
                    responseString = EntityUtils.toString(r_entity);
                } else {
                    responseString = "Error occurred! Http Status Code: "
                            + statusCode;
                }

            } catch (ClientProtocolException e) {
                responseString = e.toString();
                Log.i("responce", ":" + responseString);
            } catch (IOException e) {
                responseString = e.toString();
                Log.i("responce:1", ":" + responseString);
            }

            return responseString;

        }

        @Override
        protected void onPostExecute(String result) {
            //viewErrorValidation("Upload Success", "Image Uploading Success", 202);
            super.onPostExecute(result);
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
        Intent myIntent = new Intent(DPMerchantDetails.this,
                DPBankDetailsActivity.class);
        startActivity(myIntent);
    }
}
