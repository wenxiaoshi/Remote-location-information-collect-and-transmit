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
		// TODO �Զ����ɵķ������
		System.out.println("�յ�GPS�㲥��Ϣ:");
		 Bundle bundle=intent.getExtras();      
		   String lon=intent.getStringExtra("lon");    
		   String lat=intent.getStringExtra("lat"); 
		   String alt=intent.getStringExtra("alt");
		   String spe=intent.getStringExtra("spe");
		   String bea=intent.getStringExtra("bea");
		  
		  float lonti=stringToFloat(lon);
		   System.out.println("����:"+lon +"  γ�ȣ�"+lat  +"  ���Σ�"+alt+"  �ٶȣ�"+spe+"  ��λ�ǣ�"+bea+new Date());
		  String message="����:"+lon  +"  γ�ȣ�"+lat  +"  ���Σ�"+alt+"  �ٶȣ�"+spe+"  ��λ�ǣ�"+bea+new Date();
	
		  System.out.println("���ȸ�������"+lonti);

		  if (lonti>10) {
			  
			  System.out.println("GpsReceiver��γ����Ϣ������������ʱ����");
              context.startService(new Intent(context,SmsSender.class)); 
		  }
				  
			  
//			  SmsManager smsManager;
//				 smsManager = SmsManager.getDefault();
//				smsManager.sendTextMessage("5554", null,message , null, 
//		                null); 		
	//}
//		  long firstime=SystemClock.elapsedRealtime();
		  //��ʼʱ�䣬����һ����ʱ������ʼ���������ٶ���Ϊ��ʱ����ֵ�ж�������
		  //���磬�ٶȴ���10����ʱ���ﵽ1minʱ���Ͷ��ţ�Ȼ������ͷ��ʼ,��ʼ�ж�
		  //�ٶȣ�Ȼ���趨ʱ����ֵ��Ȼ��ʼ��ʱ��
		  //���ߣ����ж��ٶȣ�Ȼ�����ö�ʱ����ֵ��Ȼ��ʼ����ʱ��Ϊ��ʱ���Ͷ���
		  //������ź��ж��ٶȣ�������һ�ζ�ʱ����ֵ��Ȼ��ʼ����ʱ��ʹ��ѭ��ʵ�֣�
		  //������;ͨ���ж��ٶȴӶ��ж϶�ʱ����ֵ��Ҫͨ��IF����case����ʵ�֡�
		  //��һ������ע�������ǣ��ٶ������Ϊĳ���ٶ�ʱ����Ҫ������������������ʱ��ֱ�ӷ��Ͷ��š�
		  //����ڲ�ͬ�ٶȺͼ��ٶȵ�����±����ʵ��Ĳ���Ƶ�ʣ��ο�A��13���߷�ʽ��
		  
	  	  	  
		  
		//change the string type to the float type
		  
	  	
		  
		
	}
	
}
