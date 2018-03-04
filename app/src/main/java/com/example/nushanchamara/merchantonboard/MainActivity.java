package com.example.nushanchamara.merchantonboard;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nushanchamara.merchantonboard.Activities.DPMerchantListActivity;
import com.example.nushanchamara.merchantonboard.Activities.DPSelectionMenuActivity;
import com.example.nushanchamara.merchantonboard.Models.DPMerchantImageUploads;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button btnselectnic;
    private Button buttonnicback;
    private Button buttonOutletphoto;
    private Button buttonBRphoto;
    private Button buttonOwnerphoto;
    private Button btnsubmit;

    private EditText editTextsshopname;
    private EditText editTextemail;
    private EditText editTextPhoneNumber;
    private EditText editTextPrimarybankname;
    private EditText editTextBranchname;
    private EditText editTextAccountnumber;
    private EditText et_companyName,et_businessrreg;

    private ImageView imageViewnic;
    private ImageView imageViewnicback;
    private ImageView imageViewOutletphoto;
    private ImageView imageViewBRphoto;
    private ImageView imageViewOwnerphoto;
    private String userChoosenTask,filepath_nicfront = null,filepath_nicback = null,filepath_br = null,filepath_outlet = null,filepath_owner = null;
    private File destination,destination_nicback,destination_br,destination_outlet,destination_owner;
    DPMerchantImageUploads merchantImageUploads;

    private int REQUEST_CAMERA;
    private int PICK_IMAGE_REQUEST = 1;
    private int PICK_IMAGE_REQUEST2 = 2;
    private int PICK_IMAGE_REQUEST3 = 3;
    private int PICK_IMAGE_REQUEST4 = 4;
    private int PICK_IMAGE_REQUEST5 = 5;

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dp_merchant_main_activity);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register Merchant");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnselectnic = (Button)findViewById(R.id.buttonnic);
        buttonnicback = (Button)findViewById(R.id.buttonnicback);
        buttonOutletphoto = (Button)findViewById(R.id.buttonOutletphoto);
        buttonBRphoto = (Button)findViewById(R.id.buttonBRphoto);
        buttonOwnerphoto = (Button)findViewById(R.id.buttonOwnerphoto);
        btnsubmit = (Button)findViewById(R.id.btnsubmit);

//        editTextsshopname = (EditText) findViewById(R.id.editTextsshopname);
//        editTextemail = (EditText) findViewById(R.id.editTextemail);

//        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
//        editTextPrimarybankname = (EditText) findViewById(R.id.editTextPrimarybankname);
//        editTextBranchname = (EditText) findViewById(R.id.editTextBranchname);
//        editTextAccountnumber = (EditText) findViewById(R.id.editTextAccountnumber);
        et_companyName=(EditText)findViewById(R.id.et_companyName);
        et_businessrreg=(EditText) findViewById(R.id.et_businessrreg);

        imageViewnic = (ImageView)findViewById(R.id.imageViewnic);
        imageViewnicback = (ImageView)findViewById(R.id.imageViewnicback);
        imageViewOutletphoto = (ImageView)findViewById(R.id.imageViewOutletphoto);
        imageViewBRphoto = (ImageView)findViewById(R.id.imageViewBRphoto);
        imageViewOwnerphoto = (ImageView)findViewById(R.id.imageViewOwnerphoto);


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitMerchantData();
            }
        });

        btnselectnic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                selectImage();
                Intent intentnic = selectimg(1);
            }
        });
        buttonnicback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentnicback = selectimg(2);
            }
        });
        buttonOutletphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentOutletphoto = selectimg(3);
            }
        });
        buttonBRphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBRphoto = selectimg(4);
            }
        });
        buttonOwnerphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentOwnerphoto = selectimg(5);
            }
        });
    }

    private Intent selectimg(int IMAGE_REQUEST_CODE)
    {
        getpermission();

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, IMAGE_REQUEST_CODE);

//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent,IMAGE_REQUEST_CODE);
        //startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMG_REQUEST);
        return intent;
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent myIntent = new Intent(MainActivity.this,
                DPSelectionMenuActivity.class);
        startActivity(myIntent);
    }

    private void submitMerchantData()
    {

        try {
            filepath_nicfront=destination.getAbsolutePath();
            filepath_nicback=destination_nicback.getAbsolutePath();
            filepath_outlet=destination_outlet.getAbsolutePath();
            filepath_owner=destination_owner.getAbsolutePath();
            filepath_br=destination_br.getAbsolutePath();
            Log.d("image_path",filepath_nicfront+"||"+filepath_nicback+"||"+filepath_outlet+"||"+filepath_owner+"||"+filepath_br);
        }catch (Exception e){
            Log.e("image_path_1",e.getMessage());
            Toast.makeText(getApplicationContext(), "Please Upload All The Requested Images", Toast.LENGTH_LONG).show();
        }

        merchantImageUploads=new DPMerchantImageUploads(filepath_nicfront,filepath_nicback,filepath_br,filepath_outlet,filepath_owner,et_companyName.getText().toString().trim(),et_businessrreg.getText().toString().trim());
        merchantImageUploads.save();

        Intent intent=new Intent(MainActivity.this, DPMerchantListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

//        editTextsshopname.setError(null);
//
//        String shopname = editTextsshopname.getText().toString();
//        String email = editTextemail.getText().toString();
//        String phoneno = editTextPhoneNumber.getText().toString();
//        String bankname = editTextPrimarybankname.getText().toString();
//        String branchname = editTextBranchname.getText().toString();
//        String accno = editTextAccountnumber.getText().toString();
//
//
//        boolean cancel = false;
//        View focusView = null;

//        if (TextUtils.isEmpty(accno)) {
//            editTextAccountnumber.setError(getString(R.string.error_field_required));
//            focusView = editTextAccountnumber;
//            cancel = true;
//        }
//        if (TextUtils.isEmpty(branchname)) {
//            editTextBranchname.setError(getString(R.string.error_field_required));
//            focusView = editTextBranchname;
//            cancel = true;
//        }
//        if (TextUtils.isEmpty(bankname)) {
//            editTextPrimarybankname.setError(getString(R.string.error_field_required));
//            focusView = editTextPrimarybankname;
//            cancel = true;
//        }
//        if (TextUtils.isEmpty(phoneno)) {
//            editTextPhoneNumber.setError(getString(R.string.error_field_required));
//            focusView = editTextPhoneNumber;
//            cancel = true;
//        }
//        if (TextUtils.isEmpty(email)) {
//            editTextemail.setError(getString(R.string.error_field_required));
//            focusView = editTextemail;
//            cancel = true;
//        }
//        if (TextUtils.isEmpty(shopname)) {
//            editTextsshopname.setError(getString(R.string.error_field_required));
//            focusView = editTextsshopname;
//            cancel = true;
//        }
//
//        if (cancel) {
//            // There was an error; don't attempt login and focus the first
//            // form field with an error.
//            focusView.requestFocus();
//        } else {
//            // Show a progress spinner, and kick off a background task to
//            // perform the user login attempt.
//            //showProgress(true);
//            //mAuthTask = new AgentRegistration.UserLoginTask(email, password);
//            //mAuthTask.execute((Void) null);
//        }
    }

    private void getpermission()
    {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }
//    private void selectImage() {
//        final CharSequence[] items = { "Take Photo", "Cancel" };
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setTitle("Capture Vehicle Plate");
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int item) {
//                boolean result= DPUtilities.checkPermission(MainActivity.this);
//                if (items[item].equals("Take Photo")) {
//                    userChoosenTask="Take Photo";
//                    if(result)
//                        cameraIntent();
////                } else if (items[item].equals("Choose from Library")) {
////                    userChoosenTask="Choose from Library";
////                    if(result)
////                        galleryIntent();
//                } else if (items[item].equals("Cancel")) {
//                    dialog.dismiss();
//                }
//            }
//        });
//        builder.show();
//    }

//    private void cameraIntent(REQUEST_CAMERA)
//    {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, REQUEST_CAMERA);
//
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST){
                onCaptureImageResultNicFront(data);
            }
            else if (requestCode == PICK_IMAGE_REQUEST2){
                onCaptureImageResultNicBack(data);
            }
            else if(requestCode==PICK_IMAGE_REQUEST3){
                onCaptureImageResultOutlet(data);
            }
            else if(requestCode==PICK_IMAGE_REQUEST4){
                onCaptureImageResultBr(data);
            }
            else if(requestCode==PICK_IMAGE_REQUEST5){
                onCaptureImageResultOwner(data);
            }
        }
    }

    private void onCaptureImageResultNicFront(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

         destination = new File(Environment.getExternalStorageDirectory(),
                "IMG_NICDRONT"+System.currentTimeMillis() + ".jpg");

//        destination = new File(Environment.getExternalStorageDirectory(),
//                "IMG_" + number_plate + "_" + zoneId + "_" + keeper_id + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        btnselectnic.setVisibility(View.GONE);
        imageViewnic.setVisibility(View.VISIBLE);
        imageViewnic.setImageBitmap(thumbnail);

    }
    private void onCaptureImageResultNicBack(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        destination_nicback = new File(Environment.getExternalStorageDirectory(),
                "IMG_NICBACK"+System.currentTimeMillis() + ".jpg");

//        destination = new File(Environment.getExternalStorageDirectory(),
//                "IMG_" + number_plate + "_" + zoneId + "_" + keeper_id + ".jpg");

        FileOutputStream fo;
        try {
            destination_nicback.createNewFile();
            fo = new FileOutputStream(destination_nicback);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        buttonnicback.setVisibility(View.GONE);
        imageViewnicback.setVisibility(View.VISIBLE);
        imageViewnicback.setImageBitmap(thumbnail);

    }

    private void onCaptureImageResultOutlet(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        destination_outlet = new File(Environment.getExternalStorageDirectory(),
                "IMG_OUTLET"+System.currentTimeMillis() + ".jpg");

//        destination = new File(Environment.getExternalStorageDirectory(),
//                "IMG_" + number_plate + "_" + zoneId + "_" + keeper_id + ".jpg");

        FileOutputStream fo;
        try {
            destination_outlet.createNewFile();
            fo = new FileOutputStream(destination_outlet);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        buttonOutletphoto.setVisibility(View.GONE);
        imageViewOutletphoto.setVisibility(View.VISIBLE);
        imageViewOutletphoto.setImageBitmap(thumbnail);

    }
    private void onCaptureImageResultBr(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        destination_br = new File(Environment.getExternalStorageDirectory(),
                "IMG_BR"+System.currentTimeMillis() + ".jpg");

//        destination = new File(Environment.getExternalStorageDirectory(),
//                "IMG_" + number_plate + "_" + zoneId + "_" + keeper_id + ".jpg");

        FileOutputStream fo;
        try {
            destination_br.createNewFile();
            fo = new FileOutputStream(destination_br);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        buttonBRphoto.setVisibility(View.GONE);
        imageViewBRphoto.setVisibility(View.VISIBLE);
        imageViewBRphoto.setImageBitmap(thumbnail);

    }
    private void onCaptureImageResultOwner(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        destination_owner = new File(Environment.getExternalStorageDirectory(),
                "IMG_OWNER"+System.currentTimeMillis() + ".jpg");

//        destination = new File(Environment.getExternalStorageDirectory(),
//                "IMG_" + number_plate + "_" + zoneId + "_" + keeper_id + ".jpg");

        FileOutputStream fo;
        try {
            destination_owner.createNewFile();
            fo = new FileOutputStream(destination_owner);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        buttonOwnerphoto.setVisibility(View.GONE);
        imageViewOwnerphoto.setVisibility(View.VISIBLE);
        imageViewOwnerphoto.setImageBitmap(thumbnail);

    }
}
