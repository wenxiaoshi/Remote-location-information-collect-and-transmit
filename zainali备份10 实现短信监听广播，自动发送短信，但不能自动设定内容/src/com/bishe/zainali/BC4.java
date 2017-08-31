package com.bishe.zainali;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.telephony.gsm.SmsMessage;
import android.widget.EditText;

public class BC4 extends  BroadcastReceiver{
	Intent intent2;
	

	private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
	@Override public void onReceive(Context context, Intent intent) {
		System.out.println("reveiver4收到短信广播消息：");
		
		
	if (intent.getAction().equals(SMS_RECEIVED)) {
		
		
	SmsManager sms = SmsManager.getDefault();
	Bundle bundle = intent.getExtras();
	if (bundle != null) {
	Object[] pdus = (Object[]) bundle.get("pdus");
	SmsMessage[] messages = new SmsMessage[pdus.length];
	for (int i = 0; i < pdus.length; i++) messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
	for (SmsMessage message : messages){
	//String msg = message.getMessageBody();
	//String to = message.getOriginatingAddress();
	sms.sendTextMessage("5554", null, "This is an auto message", null, null);
	
	}
	}
	}
	}

}
