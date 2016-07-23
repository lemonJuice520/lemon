package com.kb.mineactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kb.lifeknow.R;
import com.kb.utils.ReginstThread;
import com.kb.utils.RegistsThread;
import com.kb.utils.Utils;

import java.util.HashMap;
import java.util.Map;


public class RegistActivity extends Activity implements View.OnClickListener {

    ImageButton regis_back,img_more;
    EditText edt_name,edt_pwd,edt_pwd2,edt_auth;
    Button btn_send,btn_login;
    TextView txt_visiable,txt_pwd;

    String name,pwd,repwd,auth,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initView();
    }

    public void initView(){
        regis_back= (ImageButton) findViewById(R.id.img_back);
        img_more= (ImageButton) findViewById(R.id.img_more);
        txt_visiable= (TextView) findViewById(R.id.txt_visiable);
        txt_pwd= (TextView) findViewById(R.id.txt_pwd);
        txt_pwd.setVisibility(View.GONE);
        txt_visiable.setVisibility(View.GONE);
        img_more.setVisibility(View.GONE);
        regis_back.setOnClickListener(this);
        btn_send= (Button) findViewById(R.id.btn_send);
        btn_login= (Button) findViewById(R.id.btn_regist);
        btn_send.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        edt_name= (EditText) findViewById(R.id.edt_phone);
        edt_pwd= (EditText) findViewById(R.id.edt_pass);
        edt_pwd2= (EditText) findViewById(R.id.edt_repass);
        edt_auth= (EditText) findViewById(R.id.edt_auth1);
        pwd=edt_pwd.getText().toString().trim();
        repwd=edt_pwd2.getText().toString().trim();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_send:
                name=edt_name.getText().toString().trim();
                pwd=edt_pwd.getText().toString().trim();
                repwd=edt_pwd2.getText().toString().trim();
                url="http://192.168.1.108:8080/app/spring/createRandomVcode.action";
                if(!judgePhoneNums(name)){
                    return;
                }
                edt_pwd2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){
                        //获取焦点处理
                        txt_pwd.setVisibility(View.GONE);
                    }else{
                        if (!pwd.equals(repwd)){
                            txt_pwd.setVisibility(View.VISIBLE);
                            txt_pwd.setText("两次输入的密码不一致");
                            edt_pwd2.setText("");
                        }
                    }
                }
            });
//                System.out.print(url+">>"+name+">>"+pwd);
                RegistsThread(url,name,pwd);
                break;
            case R.id.btn_regist:
                auth=edt_auth.getText().toString().trim();
                judgeEditText(pwd,repwd,auth);
//                Thread thread=new Thread(subPhoneAndPass);
//                thread.start();
                break;
        }
    }

//    Runnable subPhoneAndPass=new Runnable() {
//        @Override
//        public void run() {
//            ReSmsToPost(auth);
//            handlerPAP.sendEmptyMessage(1);
//        }
//    };
//    Handler handlerPAP=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//        }
//    };
    /**
     * 收到短信后提交
     */
    private void ReSmsToPost(String strRecSmsMsg){
        //参数
        Map<String ,String >params=new HashMap<String ,String>();
        params.put("verifyCode",strRecSmsMsg);

        String url="http://192.168.1.108:8080/app/spring/userRegister.action";

        String strResult=Utils.submitPostData(url,params,"utf-8");
        txt_visiable.setVisibility(View.VISIBLE);
        txt_visiable.setText(strResult);

    }
    /**
     * 判断手机格式是否正确
     * @param phoneNums
     * @return
     */
    private boolean judgePhoneNums(String phoneNums){
        if (Utils.isMatchLenth(phoneNums,11)&&Utils.isMobileNo(phoneNums)){
            return true;
        }else
            Utils.showToast(this,"手机号码有误");
        return false;
    }

    private void judgeEditText(String pass,String repass,String auth){

       if (pass.equals("")||repass.equals("")||auth.equals("")) {
           txt_visiable.setVisibility(View.VISIBLE);
           txt_visiable.setText("请填写完整的信息");
       }else{
           txt_visiable.setVisibility(View.GONE);
       }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
