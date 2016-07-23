package com.kb.mineactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kb.lifeknow.MainActivity;
import com.kb.lifeknow.R;


public class LoginActivity extends Activity implements View.OnClickListener {

    TextView txt_regiest,txt_forget_pwd,txt_fast_login;
    ImageView img_weixin,img_qq,img_sina,login_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    public void initView(){
        login_back=(ImageButton)findViewById(R.id.login_back);
        txt_regiest= (TextView) findViewById(R.id.txt_regist);
        txt_forget_pwd= (TextView) findViewById(R.id.txt_password);
        txt_fast_login= (TextView) findViewById(R.id.txt_fast_login);
        img_weixin= (ImageView) findViewById(R.id.img_weixin);
        img_qq= (ImageView) findViewById(R.id.img_qq);
        img_sina= (ImageView) findViewById(R.id.img_sina);

        login_back.setOnClickListener(this);
        txt_regiest.setOnClickListener(this);
        txt_forget_pwd.setOnClickListener(this);
        txt_fast_login.setOnClickListener(this);
        img_weixin.setOnClickListener(this);
        img_qq.setOnClickListener(this);
        img_sina.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_back:
                finish();
                break;
            case R.id.txt_regist:
                startActivity(new Intent(this,RegistActivity.class));
                break;
            case R.id.txt_password:
                startActivity(new Intent(this,ForgotPasswordActivity.class));
                break;
            case R.id.txt_fast_login:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.img_weixin://微信登錄
                break;
            case R.id.img_qq://QQ登錄
                break;
            case R.id.img_sina://微博登錄
                break;
        }
    }
}
