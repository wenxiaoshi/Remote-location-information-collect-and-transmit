package com.bishe.zainali;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class TActivity extends Activity {
	
	private Button bt1;
	private Context mContext;
	CheckBox chk1;
//	CheckBox chk_pass;
	
	EditText etUserEmail,etUserPhone, etUserName1,etUserPass1 ,etUserPassResure ;//登录需要的新信息，编辑存入的时候用到
	SharedPreferences pref;//登录需要的新信息，同上
    Editor editor;//登录需要的新信息，同上
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tactivity);
		bt1=(Button) findViewById(R.id.button_Zhuce);
		final Context mContext=this;
		chk1=(CheckBox) findViewById(R.id.checkBox_saveusername);
		//chk_pass=(CheckBox) findViewById(R.id.checkBox_savepassword);
		etUserEmail=(EditText) findViewById(R.id.editText_email);
		etUserPhone=(EditText) findViewById(R.id.editText_phone);
		etUserName1=(EditText) findViewById(R.id.editText_UserName);
		etUserPass1=(EditText) findViewById(R.id.editText_password);
		etUserPassResure=(EditText) findViewById(R.id.editText_passwordResure);
		pref=getSharedPreferences("UserInfo", MODE_PRIVATE);
		editor=pref.edit();
		
		 bt1.setOnClickListener(new MyClickListener(){
	    	  public void onClick(View v) {
					//调用父类的onclick事件
					super.onClick(v);
					
					Intent intent=new Intent(mContext, MainActivity.class);//调转到下一页面的意图
					
					String email =etUserEmail.getText().toString().trim();
					String phone =etUserPhone.getText().toString().trim();
					String name =etUserName1.getText().toString().trim();//登录需要的新信息，取用户名
					String pass =etUserPass1.getText().toString().trim();
					String repass =etUserPassResure.getText().toString().trim();
					
					
					 SharedPreferences preferences=getSharedPreferences("UserInfo",MODE_WORLD_WRITEABLE);  
                     Editor edit=preferences.edit();
                     editor.putString("etUserEmail", email);
                     editor.putString("etUserPhone", phone);
                     editor.putString("etUserName1", name);
                     editor.putString("etUserPass1", pass);
                     editor.putString("etUserPassResure", repass);
                     editor.commit();
                     
                    	 Toast.makeText(TActivity.this, "注册成功，即将跳转至登录界面，请稍候",Toast.LENGTH_LONG).show();  
                         startActivity(intent);
					
					
					
                     
                    
					
					
					//登录需要的新信息，取密码
					
//                     else if(email==null||phone==null||name==null||pass==null||repass==null){
// 						Toast.makeText(TActivity.this, "注册失败，注册信息有未填写项",Toast.LENGTH_LONG).show();
// 					}
// 					else if((pass).equals(repass)==false){
// 						Toast.makeText(TActivity.this, "注册失败，两次密码填写不一致",Toast.LENGTH_LONG).show();
// 						editor.remove("etUserPass1");
// 						editor.remove("etUserPassResure");
// 						editor.commit();
//					
					      //if (如果输入信息数据库中没有，电话号码和邮箱，用户名都不在数据库中并且免责申明被勾选，并且点击了注册按钮时) {
//						if (数据库中没有电话号码&&没有邮箱&&没有用户名&&免责申明勾选&&检测到注册按钮被点击&&两次密码输入相同) {
//							editor.putString("email", email);
//							editor.putString("phonenum", num);
//					       editor.putString("userName", name);
//					        editor.putString("password", password);
//					      	editor.commit();	
//							Toast.makeText(TActivity.this, "注册成功~\(≧▽≦)/~即将跳转到登录界面", 1).show();
//						} else { if(数据库中已经有电话号码&&没有用户名&&没有邮箱){
//				                    Toast.makeText(TActivity.this, "注册失败，电话号码已被注册，请更改您的信息", 1).show();
//					editor.remove("userName");
					//editor.commit();
//				                	除了全都不在数据库中的情况，剩下情况一一列举，并打印出相关提示信息)
//
//						}
					     //录入并保存用户名，电话号码，邮箱，密码
						//	editor.putString("userName", name);
						//	editor.commit();	
					   //并打印出提示语句，注册成功，数据正在录入中，即将跳转到登录界面
					//跳转到登录界面，运行startActivity(intent)；执行意图，即实现页面跳转，并且输出登录提示信息
	    	  }
	    	  });
		
		
		
		
	}

}
