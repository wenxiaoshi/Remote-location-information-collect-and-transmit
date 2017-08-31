package com.bishe.zainali;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FActivity extends Activity{
	
	private Button bt4;
	
	private Context mContext2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.factivity);
	
        bt4=(Button) findViewById(R.id.button_mylocation);
		final Context mContext2=this;
        bt4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(mContext2, TActivity.class);
				startActivity(intent);
				
			}
		});
	}

}
