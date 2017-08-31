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
	
	
		final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	
		 float stringToFloat(String floatstr)
		   {
		     Float floatee;
		     floatee = Float.valueOf(floatstr);
		     return floatee.floatValue();
		   }
	@Override
	public void onReceive(Context context, Intent intent) {
		
		
		// TODO �Զ����ɵķ������
		System.out.println("GpsReceiver���չ㲥�յ�GPS�㲥��Ϣ:");
		 Bundle bundle=intent.getExtras();      
		   String lon=intent.getStringExtra("lon");    
		   String lat=intent.getStringExtra("lat"); 
		   String alt=intent.getStringExtra("alt");
		   String spe=intent.getStringExtra("spe");
		   String bea=intent.getStringExtra("bea");
		   
		 float lontitude=stringToFloat(lon);
		   float speed=stringToFloat(spe);
		   System.out.println("����:"+lon +"  γ�ȣ�"+lat  +"  ���Σ�"+alt+"  �ٶȣ�"+spe+"  ��λ�ǣ�"+bea+new Date());
		final  String message="����:"+lon  +"  γ�ȣ�"+lat  +"  ���Σ�"+alt+"  �ٶȣ�"+spe+"  ��λ�ǣ�"+bea+new Date();
		 
		//  if(lontitude>0){
		 //����ٶȴ���30���Ϳ�����ʱ������ʱʱ��Ϊ6s,6s��ִ��handler������һ�����ŵ�ָ������
			  System.out.println("GpsReceiver��γ����Ϣ������������ʱ����");
              context.startService(new Intent(context,SmsSender.class)); ;
		  }

		  
		
	}
	
}
