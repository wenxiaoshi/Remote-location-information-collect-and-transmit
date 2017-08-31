package com.bishe.zainali;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;


import java.util.Date;
import java.text.SimpleDateFormat;




public class GpsService extends Service {
	 ArrayList<CellInfo> cellIds = null;
	 private Gps gps=null;
	 private boolean threadDisable=false; 
	 private final static String TAG=GpsService.class.getSimpleName();
	 
	 

	 
	 @Override
	 public void onCreate() {
	  super.onCreate();
	   
	  gps=new Gps(GpsService.this);
	  cellIds=UtilTool.init(GpsService.this);
	   
	  new Thread(new Runnable(){
	   @Override
	   public void run() {
	    while (!threadDisable) { 
	     try {
	      Thread.sleep(1000);
	     } catch (InterruptedException e) {
	      e.printStackTrace();
	     }
	      
	     if(gps!=null){ //����������ʱgpsΪ��
	      //��ȡ��γ��
	      Location location=gps.getLocation();
	      //���gps�޷���ȡ��γ�ȣ����û�վ��λ��ȡ
	      if(location==null){
	       Log.v(TAG, "gps location null"); 
	       //2.���ݻ�վ��Ϣ��ȡ��γ��
	       try {
	        location = UtilTool.callGear(GpsService.this, cellIds);
	       } catch (Exception e) {
	        location=null;
	        e.printStackTrace();
	       }
	       if(location==null){
	        Log.v(TAG, "cell location null"); 
	       }
	      }
	     
	      //���͹㲥
	      Intent intent=new Intent();
	      intent.setAction("com.bishe.zainali.GpsService");
	      intent.putExtra("lat", location==null?"":location.getLatitude()+""); 
	      intent.putExtra("lon", location==null?"":location.getLongitude()+""); 
	      intent.putExtra("alt", location==null?"":location.getAltitude()+""); 
	      intent.putExtra("spe", location==null?"":location.getSpeed()+""); 
	      intent.putExtra("bea", location==null?"":location.getBearing()+""); 
	      
	      intent.putExtra("lonti", location==null?0:location.getLongitude()+0);
	      
//	      PendingIntent sender=PendingIntent .getBroadcast(GpsService.this, 0, intent, 0);
//	    	        //��ʼʱ��
//	    	    long firstime=SystemClock.elapsedRealtime();
//
//	    	    AlarmManager am=(AlarmManager)getSystemService(ALARM_SERVICE);//5��һ�����ڣ���ͣ�ķ��͹㲥
//	    	    am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP
//	    	            , firstime, 1000, sender);
//	    	    System.out.println("����GPS�㲥��Ϣ:");
//	      
	     sendBroadcast(intent);
	     }
	 
	    }
	   }
	  }).start();
	   
	 }
	 
	 @Override
	 public void onDestroy() {
	  threadDisable=true;
	  if(cellIds!=null&&cellIds.size()>0){
	   cellIds=null;
	  }
	  if(gps!=null){
	   gps.closeLocation();
	   gps=null;
	  }
	  super.onDestroy();
	 }
	 
	 @Override
	 public IBinder onBind(Intent arg0) {
	  return null;
	 }
	 
	 
	}