package com.bishe.zainali;

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

public class FActivity extends Activity{
	
		  
	  
	
	  
	

	private Button bt3;
	private Context mContext;
	private Button bt4;
	private Context mContext2;
	
	private EditText editText;
	private EditText editText1;
	private EditText editText_phone;
	private LocationManager lm;
	private static final String FAG="FActivity"; 
	
	 PendingIntent paIntent;
	 SmsManager smsManager;
	   
	  @Override
	   protected void onDestroy() {
	     // TODO Auto-generated method stub
	     super.onDestroy();
	     lm.removeUpdates(locationListener);
	   }
	   	
		@Override
	       public void onCreate(Bundle savedInstanceState) {
	           super.onCreate(savedInstanceState);
	           setContentView(R.layout.factivity);
	           
	           paIntent = PendingIntent.getBroadcast(this, 0, new Intent(), 0); 
	           smsManager = SmsManager.getDefault();
	           
	           bt3=(Button) findViewById(R.id.button_tellmylocation);
	           final EditText txtPhoneNo = (EditText) findViewById(R.id.editText_pnone);
	           final EditText txtMessage = (EditText) findViewById(R.id.editText);
	   		   final Context mContext=this;
	   		   
	           bt3.setOnClickListener(new MyClickListener() {
	   			@SuppressWarnings("deprecation")
				@Override
	   			public void onClick(View v) {
	   				//�Զ����Ͷ��ſ��Գɹ�����θı����ݺͺ��룿
	   			 String phoneNo =txtPhoneNo.getText().toString();
		   			
	             String message = txtMessage.getText().toString();
	            // String result = editText.getText().toString();   
	           //���Ӧ�ó���ʵ��   
	            
	           //��ȫ�ֱ�����ֵ   
	           
	   				
//	   				smsManager.sendTextMessage(phoneNo, null, message , paIntent, 
//	                        null); 
//                 super.onClick(v);
//				Intent intent=new Intent(mContext, SActivity.class);
//				startActivity(intent);
	   				// ���ڰ�ť�ļ����ǿռ�������ʵ����ת��Ҳ��ʵ�ֶ��ŷ���
//	   				
//   			 String phoneNo =txtPhoneNo.getText().toString();
//	   			
//             String message = txtMessage.getText().toString();                 
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
	   	//��ť����ת�ͼ���������
	         
	           
	           
	           editText=(EditText)findViewById(R.id.editText);
	           lm=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
	           
	           //�ж�GPS�Ƿ���������
	           if(!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)){
	               Toast.makeText(this, "�뿪��GPS����...", Toast.LENGTH_LONG).show();
	               //���ؿ���GPS�������ý��棬�ο�����������תҳ��
	               Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);   
	               startActivityForResult(intent,0); 
	               return;
	           }
	           
	           //Ϊ��ȡ����λ����Ϣʱ���ò�ѯ����
	           String bestProvider = lm.getBestProvider(getCriteria(), true);
	           //��ȡλ����Ϣ
	           //��������ò�ѯҪ��getLastKnownLocation��������Ĳ���ΪLocationManager.GPS_PROVIDER
	           Location location= lm.getLastKnownLocation(bestProvider);    
	           updateView(location);
	           //����״̬
	           lm.addGpsStatusListener(listener);
	           //�󶨼�������4������    
	           //����1���豸����GPS_PROVIDER��NETWORK_PROVIDER����
	           //����2��λ����Ϣ�������ڣ���λ����    
	           //����3��λ�ñ仯��С���룺��λ�þ���仯������ֵʱ��������λ����Ϣ    
	           //����4������    
	           //��ע������2��3���������3��Ϊ0�����Բ���3Ϊ׼������3Ϊ0����ͨ��ʱ������ʱ���£�����Ϊ0������ʱˢ��   
	           
	           // 1�����һ�Σ�����Сλ�Ʊ仯����1�׸���һ�Σ�
	           //ע�⣺�˴�����׼ȷ�ȷǳ��ͣ��Ƽ���service��������һ��Thread����run��sleep(10000);Ȼ��ִ��handler.sendMessage(),����λ��
	           lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
	       }
	       
	     

		//λ�ü���
	       private LocationListener locationListener=new LocationListener() {
	           
	           /**
	            * λ����Ϣ�仯ʱ����
	            */
	           public void onLocationChanged(Location location) {
	               updateView(location);
	               Log.i(FAG, "ʱ�䣺"+location.getTime()); 
	               Log.i(FAG, "���ȣ�"+location.getLongitude()); 
	               Log.i(FAG, "γ�ȣ�"+location.getLatitude()); 
	               Log.i(FAG, "���Σ�"+location.getAltitude()); 
	           }
	           
	           /**
	            * GPS״̬�仯ʱ����
	            */
	           public void onStatusChanged(String provider, int status, Bundle extras) {
	               switch (status) {
	               //GPS״̬Ϊ�ɼ�ʱ
	               case LocationProvider.AVAILABLE:
	                   Log.i(FAG, "��ǰGPS״̬Ϊ�ɼ�״̬");
	                   break;
	               //GPS״̬Ϊ��������ʱ
	               case LocationProvider.OUT_OF_SERVICE:
	                   Log.i(FAG, "��ǰGPS״̬Ϊ��������״̬");
	                   break;
	               //GPS״̬Ϊ��ͣ����ʱ
	               case LocationProvider.TEMPORARILY_UNAVAILABLE:
	                   Log.i(FAG, "��ǰGPS״̬Ϊ��ͣ����״̬");
	                   break;
	               }
	           }
	       
	           /**
	            * GPS����ʱ����
	            */
	           public void onProviderEnabled(String provider) {
	               Location location=lm.getLastKnownLocation(provider);
	               updateView(location);
	           }
	       
	           /**
	            * GPS����ʱ����
	            */
	           public void onProviderDisabled(String provider) {
	               updateView(null);
	           }

	       
	       };
	       
	       //״̬����
	       GpsStatus.Listener listener = new GpsStatus.Listener() {
	           public void onGpsStatusChanged(int event) {
	               switch (event) {
	               //��һ�ζ�λ
	               case GpsStatus.GPS_EVENT_FIRST_FIX:
	                   Log.i(FAG, "��һ�ζ�λ");
	                   break;
	               //����״̬�ı�
	               case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
	                   Log.i(FAG, "����״̬�ı�");
	                   //��ȡ��ǰ״̬
	                   GpsStatus gpsStatus=lm.getGpsStatus(null);
	                   //��ȡ���ǿ�����Ĭ�����ֵ
	                   int maxSatellites = gpsStatus.getMaxSatellites();
	                   //����һ�������������������� 
	                   Iterator<GpsSatellite> iters = gpsStatus.getSatellites().iterator();
	                   int count = 0;     
	                   while (iters.hasNext() && count <= maxSatellites) {     
	                       GpsSatellite s = iters.next();     
	                       count++;     
	                   }   
	                   System.out.println("��������"+count+"������");
	                   break;
	               //��λ����
	               case GpsStatus.GPS_EVENT_STARTED:
	                   Log.i(FAG, "��λ����");
	                   break;
	               //��λ����
	               case GpsStatus.GPS_EVENT_STOPPED:
	                   Log.i(FAG, "��λ����");
	                   break;
	               }
	           };
	       };
	       
	       /**
	        * ʵʱ�����ı�����
	        * 
	        * @param location
	        */
	       private void updateView(Location location){
	           if(location!=null){
//	        	  
//	               editText.append(String.valueOf("\n"+location.getLongitude()+"\n"));
//	               
//	               editText.append(String.valueOf(location.getLatitude()+"\n"));
//	               
//	               editText.append(String.valueOf(location.getAltitude())+"m\n");
//	               
//	               editText.append(String.valueOf(location.getSpeed())+"m/s");
//	               
//	               
	               editText.setText("�豸λ����Ϣ:\n\n���ȣ�");
	               editText.append(String.valueOf(location.getLongitude()));
	               editText.append("\nγ�ȣ�");
	               editText.append(String.valueOf(location.getLatitude()));
	               editText.append("\n���Σ�");
	               editText.append(String.valueOf(location.getAltitude())+"��");
	               editText.append("\n�ٶȣ�");
	               editText.append(String.valueOf(location.getSpeed())+"m/s");
	               editText.append("\nʱ�䣺");
	               editText.append(String.valueOf(location.getTime()));
	               
	           }else{
	               //���EditText����
	               editText.getEditableText().clear();
	           }
	       }
	       
	       /**
	        * ���ز�ѯ����
	        * @return
	   */
	       private Criteria getCriteria(){
	           Criteria criteria=new Criteria();
	           //���ö�λ��ȷ�� Criteria.ACCURACY_COARSE�Ƚϴ��ԣ�Criteria.ACCURACY_FINE��ȽϾ�ϸ 
	           criteria.setAccuracy(Criteria.ACCURACY_FINE);    
	           //�����Ƿ�Ҫ���ٶ�
	           criteria.setSpeedRequired(true);
	           // �����Ƿ�������Ӫ���շ�  
	           criteria.setCostAllowed(true);
	           //�����Ƿ���Ҫ��λ��Ϣ
	           criteria.setBearingRequired(true);
	           //�����Ƿ���Ҫ������Ϣ
	           criteria.setAltitudeRequired(true);
	           // ���öԵ�Դ������  
	           criteria.setPowerRequirement(Criteria.POWER_LOW);
	           return criteria;
	       }
	   


//	       @SuppressWarnings("deprecation")
//			protected void sendSMS(String txtPhoneNo, String txtMessage) {
//		    	   PendingIntent pi = PendingIntent.getActivity(this, 0,
//		    	            new Intent(this, FActivity.class), 0);   
//	    	        Log.v(FAG, "will init SMS Manager");
//		    	        SmsManager sms = SmsManager.getDefault();
//		    	        
//		    	        Log.v(FAG, "will send SMS");
//		    	        sms.sendTextMessage(txtPhoneNo, null, txtMessage, pi, null); 
//				
//			}	     

}
