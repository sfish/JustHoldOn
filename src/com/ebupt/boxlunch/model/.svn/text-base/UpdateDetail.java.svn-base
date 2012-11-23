package com.ebupt.boxlunch.model;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;


public class UpdateDetail {
	private String TAG = "UpdateDetail";
	//public static final String Version = "1.0" ;
	public static final String Dtype = "android" ;
	public static final String UPDATE = "1";
	
	public String  upgradeversion ;
	public String  desc ;
	public String  durl ;
	public String  anotherurl;  //外网更新地址 cc 9-28
	//public String version ;
	
	public UpdateDetail(){
		this.upgradeversion = null;
		this.desc = null ;
		this.durl = null ;
		this.anotherurl = null;
		//this.version = null ;
	}

	public UpdateDetail(String netversion, String desc, String durl,String anotherurl){
		this.upgradeversion = netversion;
		this.desc = desc;
		this.durl = durl;
		this.anotherurl = anotherurl;
	}
	
	public boolean update(Context mContext){
		float networkVersion = 0;
        float current_version =1;
        try {
        	networkVersion = Float.parseFloat(upgradeversion);
			current_version = Float.parseFloat(mContext.getPackageManager().getPackageInfo("com.ebupt.boxlunch", 0).versionName);
		    Log.i(TAG,"current_version = "+current_version);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return false;
		} 
        if(current_version>=networkVersion){
        	return false;
        }
        else {
			return true;
		}
	}
}
