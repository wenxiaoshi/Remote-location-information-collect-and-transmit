package com.bishe.zainali;

import java.util.Iterator;
import java.util.Set;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;

public class GpsReceiver extends BroadcastReceiver{
	
	 
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO 自动生成的方法存根
		System.out.println("收到GPS广播信息:");
		 Bundle bundle=intent.getExtras();      
		   String lon=intent.getStringExtra("lon");    
		   String lat=intent.getStringExtra("lat"); 
		   String alt=intent.getStringExtra("alt");
		   String spe=intent.getStringExtra("spe");
		   String bea=intent.getStringExtra("bea");
		   System.out.println("经度:"+lon  +"  纬度："+lat  +"  海拔："+alt+"  速度："+spe+"  方位角："+bea);
		  String message="经度:"+lon  +"  纬度："+lat  +"  海拔："+alt+"  速度："+spe+"  方位角："+bea;
		 SmsManager smsManager;
		 smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage("5554", null,message , null, 
                null); 
		 abortBroadcast();
		
	}
	
}
