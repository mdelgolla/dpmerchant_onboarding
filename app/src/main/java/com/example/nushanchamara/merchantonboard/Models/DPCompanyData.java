package com.example.nushanchamara.merchantonboard.Models;

import com.orm.SugarRecord;

/**
 * Created by user1 on 3/4/2018.
 */

public class DPCompanyData extends SugarRecord<DPMerchantImageUploads> {
    String companyName;
    String brNumber;
    String adentId;
    String appRegNo;
    String compEmail;
    String compAddress;
    String businessType;
    String tel;
    String imgBr;

    public DPCompanyData(){

    }
    public DPCompanyData(String companyName, String brNumber, String adentId, String appRegNo,String compEmail, String compAddress, String businessType,String tel,String imgBr){
        this.companyName=companyName;
        this.brNumber=brNumber;
        this.adentId=adentId;
        this.appRegNo=appRegNo;
        this.compEmail=compEmail;
        this.compAddress=compAddress;
        this.businessType=businessType;
        this.tel=tel;
        this.imgBr=imgBr;
    }
    public String getCompanyName(){return companyName;}
    public String getBrNumber(){return brNumber;}
    public String getAdentId(){return adentId;}
    public String getAppRegNo(){return appRegNo;}
    public String getCompEmail(){return compEmail;}
    public String getCompAddress(){return compAddress;}
    public String getBusinessType(){return businessType;}
    public String getTel(){return tel;}
    public String getImgBr(){return imgBr;}
}
