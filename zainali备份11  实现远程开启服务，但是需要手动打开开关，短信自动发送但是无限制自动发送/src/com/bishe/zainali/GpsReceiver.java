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
		// TODO �Զ����ɵķ������
		System.out.println("�յ�GPS�㲥��Ϣ:");
		 Bundle bundle=intent.getExtras();      
		   String lon=intent.getStringExtra("lon");    
		   String lat=intent.getStringExtra("lat"); 
		   String alt=intent.getStringExtra("alt");
		   String spe=intent.getStringExtra("spe");
		   String bea=intent.getStringExtra("bea");
		   System.out.println("����:"+lon  +"  γ�ȣ�"+lat  +"  ���Σ�"+alt+"  �ٶȣ�"+spe+"  ��λ�ǣ�"+bea);
		  String message="����:"+lon  +"  γ�ȣ�"+lat  +"  ���Σ�"+alt+"  �ٶȣ�"+spe+"  ��λ�ǣ�"+bea;
		 SmsManager smsManager;
		 smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage("5554", null,message , null, 
                null); 
		 abortBroadcast();
		
	}
	
}
