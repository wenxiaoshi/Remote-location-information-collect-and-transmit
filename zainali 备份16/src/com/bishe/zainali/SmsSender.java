package com.bishe.zainali;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class SmsSender extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO �Զ����ɵķ������
		return null;
	}
	
	
	@Override
	 public void onCreate() {
	  super.onCreate();
	 

	final Handler handler  = new Handler(){
		    public void handleMessage(Message msg) {
		        super.handleMessage(msg);
		        if(msg.what == 1){
		        	 
		        		//todo something...
		 	        	 System.out.println("hander��ִ������to do something,");
		 	        	System.out.println("���Ͷ��Ű���"+new Date());
//		 				  SmsManager smsManager;
//		 					 smsManager = SmsManager.getDefault();
//		 					smsManager.sendTextMessage("5554", null,"����5556�Զ������Ķ���", null, 
//		 			                null);
		        		  }

		        	}
		        
		};
		 
		 
		Timer timer = new Timer(true);
		 
		//����
		TimerTask task = new TimerTask() {
		  public void run() {
		    Message msg = new Message();
		    msg.what = 1;
		    handler.sendMessage(msg);
		    System.out.println("timer��handler������Ϣ");
		  }
		};
		 
		//������ʱ��
		timer.schedule(task, 0, 6*1000);
		 System.out.println("��ʱ������");

	 }

}
