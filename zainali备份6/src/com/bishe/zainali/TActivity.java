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
	
	EditText etUserEmail,etUserPhone, etUserName1,etUserPass1 ,etUserPassResure ;//��¼��Ҫ������Ϣ���༭�����ʱ���õ�
	SharedPreferences pref;//��¼��Ҫ������Ϣ��ͬ��
    Editor editor;//��¼��Ҫ������Ϣ��ͬ��
	
	
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
					//���ø����onclick�¼�
					super.onClick(v);
					
					Intent intent=new Intent(mContext, MainActivity.class);//��ת����һҳ�����ͼ
					
					String email =etUserEmail.getText().toString().trim();
					String phone =etUserPhone.getText().toString().trim();
					String name =etUserName1.getText().toString().trim();//��¼��Ҫ������Ϣ��ȡ�û���
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
                     
                    	 Toast.makeText(TActivity.this, "ע��ɹ���������ת����¼���棬���Ժ�",Toast.LENGTH_LONG).show();  
                         startActivity(intent);
					
					
					
                     
                    
					
					
					//��¼��Ҫ������Ϣ��ȡ����
					
//                     else if(email==null||phone==null||name==null||pass==null||repass==null){
// 						Toast.makeText(TActivity.this, "ע��ʧ�ܣ�ע����Ϣ��δ��д��",Toast.LENGTH_LONG).show();
// 					}
// 					else if((pass).equals(repass)==false){
// 						Toast.makeText(TActivity.this, "ע��ʧ�ܣ�����������д��һ��",Toast.LENGTH_LONG).show();
// 						editor.remove("etUserPass1");
// 						editor.remove("etUserPassResure");
// 						editor.commit();
//					
					      //if (���������Ϣ���ݿ���û�У��绰��������䣬�û������������ݿ��в���������������ѡ�����ҵ����ע�ᰴťʱ) {
//						if (���ݿ���û�е绰����&&û������&&û���û���&&����������ѡ&&��⵽ע�ᰴť�����&&��������������ͬ) {
//							editor.putString("email", email);
//							editor.putString("phonenum", num);
//					       editor.putString("userName", name);
//					        editor.putString("password", password);
//					      	editor.commit();	
//							Toast.makeText(TActivity.this, "ע��ɹ�~\(�R���Q)/~������ת����¼����", 1).show();
//						} else { if(���ݿ����Ѿ��е绰����&&û���û���&&û������){
//				                    Toast.makeText(TActivity.this, "ע��ʧ�ܣ��绰�����ѱ�ע�ᣬ�����������Ϣ", 1).show();
//					editor.remove("userName");
					//editor.commit();
//				                	����ȫ���������ݿ��е������ʣ�����һһ�о٣�����ӡ�������ʾ��Ϣ)
//
//						}
					     //¼�벢�����û������绰���룬���䣬����
						//	editor.putString("userName", name);
						//	editor.commit();	
					   //����ӡ����ʾ��䣬ע��ɹ�����������¼���У�������ת����¼����
					//��ת����¼���棬����startActivity(intent)��ִ����ͼ����ʵ��ҳ����ת�����������¼��ʾ��Ϣ
	    	  }
	    	  });
		
		
		
		
	}

}
