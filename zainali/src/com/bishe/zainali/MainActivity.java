package com.bishe.zainali;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button bt1;
	private Button bt2;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt1=(Button) findViewById(R.id.button_denglu);
		bt2=(Button) findViewById(R.id.button_zhuce);
		final Context mContext=this;
		
		
       bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(mContext, FActivity.class);
				startActivity(intent);
				
			}
		});
       
      bt2.setOnClickListener(new MyClickListener(){
    	  public void onClick(View v) {
				//调用父类的onclick事件
				super.onClick(v);
				Toast.makeText(MainActivity.this, "注册中,请稍候", 1).show();
		}
      });
    		
	}
	
}
class MyClickListener implements OnClickListener{

	@Override
	public void onClick(View v) {
		
	}}