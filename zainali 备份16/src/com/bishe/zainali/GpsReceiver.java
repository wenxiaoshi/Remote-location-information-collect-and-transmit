package com.bishe.zainali;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.telephony.gsm.SmsManager;

public class GpsReceiver extends BroadcastReceiver{
	
		 float stringToFloat(String floatstr)
		   {
		     Float floatee;
		     floatee = Float.valueOf(floatstr);
		     return floatee.floatValue();
		   }
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
		  
		  float lonti=stringToFloat(lon);
		   System.out.println("经度:"+lon +"  纬度："+lat  +"  海拔："+alt+"  速度："+spe+"  方位角："+bea+new Date());
		  String message="经度:"+lon  +"  纬度："+lat  +"  海拔："+alt+"  速度："+spe+"  方位角："+bea+new Date();
	
		  System.out.println("经度浮点数是"+lonti);

		  if (lonti>10) {
			  
			  System.out.println("GpsReceiver经纬度信息，即将启动计时器：");
              context.startService(new Intent(context,SmsSender.class)); 
		  }
				  
			  
//			  SmsManager smsManager;
//				 smsManager = SmsManager.getDefault();
//				smsManager.sendTextMessage("5554", null,message , null, 
//		                null); 		
	//}
//		  long firstime=SystemClock.elapsedRealtime();
		  //开始时间，设置一个定时器，开始计数，以速度作为定时器阈值判断条件，
		  //比如，速度大于10，定时器达到1min时发送短信，然后归零从头开始,开始判断
		  //速度，然后设定时器阈值，然后开始定时。
		  //或者，先判断速度，然后设置定时器阈值，然后开始倒计时，为零时发送短信
		  //发完短信后判断速度，设置下一次定时器阈值，然后开始倒计时，使用循环实现，
		  //但是中途通过判断速度从而判断定时器阈值需要通过IF或者case条件实现。
		  //有一个额外注意的情况是，速度由零变为某个速度时，需要立即启动，并跳过计时器直接发送短信。
		  //如何在不同速度和加速度的情况下保持适当的采样频率，参看A律13折线方式。
		  
	  	  	  
		  
		//change the string type to the float type
		  
	  	
		  
		
	}
	
}
