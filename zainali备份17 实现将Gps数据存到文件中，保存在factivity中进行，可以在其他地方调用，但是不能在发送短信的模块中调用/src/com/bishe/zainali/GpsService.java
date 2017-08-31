package com.bishe.zainali;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
	 
	 SharedPreferences preferen;//为测试是否可以在GPS服务中，将数据保存到文件中而定义的变量
	    Editor editor3;  

	 
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
	      SharedPreferences gpsinfo = getSharedPreferences( "GpsInfo", 0);
      	String GPSInfo = gpsinfo.getString("gpsinfo", "为空为空为空");
      	System.out.println("GpsServicer中读取文件"+GPSInfo);
	      //发送广播
	      Intent intent=new Intent();
	      intent.setAction("com.bishe.zainali.GpsService");
	      intent.putExtra("lat", location==null?"0":location.getLatitude()+""); 
	      intent.putExtra("lon", location==null?"0":location.getLongitude()+""); 
	      intent.putExtra("alt", location==null?"0":location.getAltitude()+""); 
	      intent.putExtra("spe", location==null?"0":location.getSpeed()+""); 
	      intent.putExtra("bea", location==null?"0":location.getBearing()+""); 
	      
	      intent.putExtra("lonti", location==null?0:location.getLongitude()+0);
	      
//             下面是在service中尝试保存数据至文件，在发送短信时好调用，先测试FActivity中保存文件是否成功，再测试此处是否成功
	     // 如果此处可以成功保存，后面发送短信可以自动调用，就删除FActivity中的保存项
//	      preferen=getSharedPreferences("3", MODE_PRIVATE);
//			editor3=preferen.edit();
//			
//			 preferen=getSharedPreferences("GpsInfoService", MODE_PRIVATE);
//				editor3=preferen.edit();
//				SharedPreferences preferences=getSharedPreferences("GpsInfoService",MODE_WORLD_WRITEABLE);  
//             Editor edit=preferences.edit();
//             editor3.putString("gpsinfo", "文实：经度："+location.getLatitude()+"纬度："+location.getLongitude()+
//            		 "海拔："+location.getAltitude()+"速度："+location.getSpeed()+"方位角："+location.getBearing());
//             
//             editor3.commit();
	      
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