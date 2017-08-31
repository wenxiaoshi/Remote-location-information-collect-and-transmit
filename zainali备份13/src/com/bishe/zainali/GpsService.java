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
	      
	     if(gps!=null){ //当结束服务时gps为空
	      //获取经纬度
	      Location location=gps.getLocation();
	      //如果gps无法获取经纬度，改用基站定位获取
	      if(location==null){
	       Log.v(TAG, "gps location null"); 
	       //2.根据基站信息获取经纬度
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
	     
	      //发送广播
	      Intent intent=new Intent();
	      intent.setAction("com.bishe.zainali.GpsService");
	      intent.putExtra("lat", location==null?"":location.getLatitude()+""); 
	      intent.putExtra("lon", location==null?"":location.getLongitude()+""); 
	      intent.putExtra("alt", location==null?"":location.getAltitude()+""); 
	      intent.putExtra("spe", location==null?"":location.getSpeed()+""); 
	      intent.putExtra("bea", location==null?"":location.getBearing()+""); 
	      
	      intent.putExtra("lonti", location==null?0:location.getLongitude()+0);
	      
//	      PendingIntent sender=PendingIntent .getBroadcast(GpsService.this, 0, intent, 0);
//	    	        //开始时间
//	    	    long firstime=SystemClock.elapsedRealtime();
//
//	    	    AlarmManager am=(AlarmManager)getSystemService(ALARM_SERVICE);//5秒一个周期，不停的发送广播
//	    	    am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP
//	    	            , firstime, 1000, sender);
//	    	    System.out.println("发送GPS广播信息:");
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