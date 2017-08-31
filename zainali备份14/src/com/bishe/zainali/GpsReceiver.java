package com.bishe.zainali;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.AvoidXfermode.Mode;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.telephony.gsm.SmsManager;

public class GpsReceiver extends BroadcastReceiver{
	 SharedPreferences Gpsinfo;
	    Editor editor;
	
	
		final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	
		 float stringToFloat(String floatstr)
		   {
		     Float floatee;
		     floatee = Float.valueOf(floatstr);
		     return floatee.floatValue();
		   }
	@Override
	public void onReceive(Context context, Intent intent) {
		
		
		// TODO 自动生成的方法存根
		System.out.println("GpsReceiver接收广播收到GPS广播信息:");
		 Bundle bundle=intent.getExtras();      
		   String lon=intent.getStringExtra("lon");    
		   String lat=intent.getStringExtra("lat"); 
		   String alt=intent.getStringExtra("alt");
		   String spe=intent.getStringExtra("spe");
		   String bea=intent.getStringExtra("bea");
		   
		 float lontitude=stringToFloat(lon);
		   float speed=stringToFloat(spe);
		   System.out.println("经度:"+lon +"  纬度："+lat  +"  海拔："+alt+"  速度："+spe+"  方位角："+bea+new Date());
		final  String message="经度:"+lon  +"  纬度："+lat  +"  海拔："+alt+"  速度："+spe+"  方位角："+bea+new Date();
		 
		//  if(lontitude>0){
		 //如果速度大于30，就开启计时器，计时时间为6s,6s后执行handler，发送一条短信到指定号码
			  System.out.println("GpsReceiver经纬度信息，即将启动计时器：");
              context.startService(new Intent(context,SmsSender.class)); ;
		  }

		  
		
	}
	
}
