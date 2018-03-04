package com.example.nushanchamara.merchantonboard.Models;

import com.orm.SugarRecord;

/**
 * Created by user1 on 3/2/2018.
 */

public class DPMerchantImageUploads extends SugarRecord<DPMerchantImageUploads> {
    String companyName;
    String brNumber;
    String nicFrotimg;
    String nicBackimg;
    String imgBr;
    String imgOutlet;
    String imgOwner;

    public DPMerchantImageUploads(){

    }
    public DPMerchantImageUploads(String nicFrotimg, String nicBackimg, String imgBr,String imgOutlet, String imgOwner,String companyName, String brNumber){
        this.nicFrotimg = nicFrotimg;
        this.nicBackimg = nicBackimg;
        this.imgBr=imgBr;
        this.imgOutlet=imgOutlet;
        this.imgOwner=imgOwner;
        this.companyName=companyName;
        this.brNumber=brNumber;
    }
    public String getNicFrotimg(){
        return nicFrotimg;
    }
    public String getNicBackimg(){
        return nicBackimg;
    }
    public String getImgBr(){
        return imgBr;
    }
    public String getImgOutlet(){
        return imgOutlet;
    }
    public String getImgOwner(){
        return imgOwner;
    }
    public String getCompanyName(){return companyName;}
    public String getBrNumber(){return brNumber;}

    public void setNicFrotimg(){this.nicFrotimg=nicFrotimg;}
    public void setNicBackimg(){this.nicBackimg=nicBackimg;}
    public void setImgBr(){this.imgBr=imgBr;}
    public void setImgOutlet(){this.imgOutlet=imgOutlet;}
    public void setImgOwner(){this.imgOwner=imgOwner;}
    public void setCompanyName(){this.companyName=companyName;}
    public void setBrNumber(){this.brNumber=brNumber;}

}
