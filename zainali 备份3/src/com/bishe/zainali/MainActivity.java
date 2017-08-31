package com.bishe.zainali;






import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button bt1;
	private Button bt2;
	private Context mContext;
	EditText etUserName,etUserPass;
	SharedPreferences pref;
    Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt1=(Button) findViewById(R.id.button_denglu);
		bt2=(Button) findViewById(R.id.button_zhuce);
		final Context mContext=this;
		
		
		etUserName=(EditText) findViewById(R.id.editText1);
		etUserPass=(EditText) findViewById(R.id.editText2);
		pref=getSharedPreferences("UserInfo", MODE_PRIVATE);
		editor=pref.edit();
		
     
		
		
		
		
		  bt1.setOnClickListener(new MyClickListener(){
	    	  public void onClick(View v) {
					//调用父类的onclick事件
					super.onClick(v);
					
					Intent intent=new Intent(mContext, FActivity.class);
					
					
					String name =etUserName.getText().toString().trim();
					String pass =etUserPass.getText().toString().trim();
					if ("admin".equals(name)&&"12345678".equals(pass)) {
						startActivity(intent);
						Toast.makeText(MainActivity.this, "哎呀好激动，登陆成功啦！", 1).show();
			}else {
				Toast.makeText(MainActivity.this, "好抱歉，您的信息错误，登录失败了呢，要不，先点击注个册？", 1).show();
			}
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