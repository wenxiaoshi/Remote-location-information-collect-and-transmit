package com.bishe.zainali;
//���ǵڶ���ҳ��
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;

import java.util.Iterator;



import android.content.Intent;
import android.location.Criteria;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class FActivity extends Activity{
	
	 private Double homeLat=26.0673834d; //����γ��
	 private Double homeLon=119.3119936d; //���ᾭ��
	 private EditText editText = null;
	 private MyReceiver receiver=null; 
	 private final static String TAG=FActivity.class.getSimpleName();
		  
	  
	
	  
	

	private Button bt3;
	private Context mContext;
	private Button bt4;
	private Context mContext2;
	
	
	private EditText editText1;
	private EditText editText_phone;
	private LocationManager lm;
	private static final String FAG="FActivity"; 
	 PendingIntent paIntent;
	 SmsManager smsManager;
	   
	 
	   	
		@Override
	       public void onCreate(Bundle savedInstanceState) {
	           super.onCreate(savedInstanceState);
	           setContentView(R.layout.factivity);
	           
	   
	           editText=(EditText)findViewById(R.id.editText);
			   
	 		  //�ж�GPS�Ƿ����
	 		  Log.i(TAG, UtilTool.isGpsEnabled((LocationManager)getSystemService(Context.LOCATION_SERVICE))+"");
	 		  if(!UtilTool.isGpsEnabled((LocationManager)getSystemService(Context.LOCATION_SERVICE))){
	 		   Toast.makeText(FActivity.this, "GSP��ǰ�ѽ��ã���������ϵͳ������Ļ������", Toast.LENGTH_LONG).show();
	 		   Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);  
	 		   startActivity(callGPSSettingIntent);
	 		            return;
	 		  }   
	 		   
	 		  //��������
	 		  startService(new Intent(this, GpsService.class));
	 		  
	 		   
	 		  //ע��㲥
	 		  receiver=new MyReceiver();
	 		  IntentFilter filter=new IntentFilter();
	 		  filter.addAction("com.bishe.zainali.GpsService");
	 		  registerReceiver(receiver, filter);
	 		  
	 		  
	 		//���ŷ��ʹ�������
	           paIntent = PendingIntent.getBroadcast(this, 0, new Intent(), 0); 
	           smsManager = SmsManager.getDefault();
	           
	           bt3=(Button) findViewById(R.id.button_tellmylocation);
	           final EditText txtPhoneNo = (EditText) findViewById(R.id.editText_pnone);
	           final EditText txtMessage = (EditText) findViewById(R.id.editText);
	   		   final Context mContext=this;

	   			 
	   		   //�����֪�Է���ťʱʵ�ֶ��ŷ��ͣ�GPS�����Զ�Ƕ���ڶ���������
	           bt3.setOnClickListener(new MyClickListener() {
	   			@SuppressWarnings("deprecation")
				@Override
	   			public void onClick(View v) {
	   				String phoneNo =txtPhoneNo.getText().toString();
		             String message = txtMessage.getText().toString();
	   			
	                if (phoneNo.length()>0 && message.length()>0){                
	                	Log.v(FAG, "will begin sendSMS");
	                	Toast.makeText(FActivity.this, 
		                        "������ʼ����", 
		                        Toast.LENGTH_LONG).show();
	                	smsManager.sendTextMessage(phoneNo, null, message , paIntent, 
		                        null); 
	                	Toast.makeText(FActivity.this, 
		                        "���ͳɹ�", 
		                        Toast.LENGTH_LONG).show();
	                }
	                else
	                    Toast.makeText(FActivity.this, 
	                        "����������", 
	                        Toast.LENGTH_LONG).show();
	   			}
	   		});
	 	}
	 		  
	 		 //��ȡ�㲥����
	 		 private class MyReceiver extends BroadcastReceiver{
	 		  @Override
	 		  public void onReceive(Context context, Intent intent) {
	 		   Bundle bundle=intent.getExtras();      
	 		   String lon=bundle.getString("lon");    
	 		   String lat=bundle.getString("lat"); 
	 		   String alt=bundle.getString("alt");
	 		   String spe=bundle.getString("spe");
	 		   String bea=bundle.getString("bea");
	 		    if(lon!=null&&!"".equals(lon)&&lat!=null&&!"".equals(lat)){
	 		    double distance=getDistance(Double.parseDouble(lat), 
	 		      Double.parseDouble(lon), homeLat, homeLon);
	 		     
	 		    editText.setText("���� :"+lon+"γ�� :"+lat+"\n���� :"+alt+"m�ٶȣ�"+spe+"m/s"+"\n��λ�ǣ�"+bea+"��");
	 		   
	 		   }else{
	 		    editText.setText("���ȣ�"+lon+"\nγ�ȣ�"+lat+"\n����:"+alt+"m�ٶȣ�"+spe+"m/s"+"\n��λ�ǣ�"+bea+"��");/*+"\nAltitude:\n"+alt+"Speed"+spe*/
		 		 
	 		   }
	 		  }
	 		 }
	 		  
	 		 @Override
	 		 protected void onDestroy() {
	 		  //ע������
	 		  unregisterReceiver(receiver);
	 		  //��������������÷���һֱ���о�ע���˾�
	 		  stopService(new Intent(this, GpsService.class));
	 		  super.onDestroy();
	 		 }
	 		  
	 		 /**
	 		  * �Ѿ�γ�Ȼ���ɾ���
	 		  * 
	 		  * @param lat1 ��ʼγ��
	 		  * @param lon1 ��ʼ����
	 		  * @param lat2 ����γ��
	 		  * @param lon2 ��������
	 		  * @return
	 		  */
	 		 private double getDistance(double lat1, double lon1, double lat2, double lon2) {
	 		  float[] results = new float[1];
	 		  Location.distanceBetween(lat1, lon1, lat2, lon2, results);
	 		  return results[0];
	 		 }  
	 		

}
