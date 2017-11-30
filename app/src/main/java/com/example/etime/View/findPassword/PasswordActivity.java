package com.example.etime.View.findPassword;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.etime.Presenter.findPassword.PasswordPresenter;
import com.example.etime.R;
import com.example.etime.Utils.AlertDialog;
import com.example.etime.View.register.Register1Activity;

import java.util.Timer;
import java.util.TimerTask;

public class PasswordActivity extends AppCompatActivity implements PasswordView{

    private LinearLayout backLayout;
    private EditText phone;
    private EditText identify;
    private TextView sendIdentify;
    private TextView sure;
    private PasswordPresenter presenter=new PasswordPresenter(this,this);
    private Boolean flag=false;
    private int second=60;
    private Timer timer;
    private TimerTask task;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        initView();
        initListener();
    }

    private void initView() {
        backLayout=(LinearLayout)findViewById(R.id.back);
        phone=(EditText)findViewById(R.id.phone);
        identify=(EditText)findViewById(R.id.identify);
        sendIdentify=(TextView)findViewById(R.id.send_identify);
        sure=(TextView)findViewById(R.id.sure);

    }

    private void initListener() {
        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.back();
            }
        });
        sendIdentify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    return;
                }
                presenter.sendIdentify();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.sure();
            }
        });
    }

    public void startTimer(){
        second=60;
        if(timer==null){
            timer=new Timer();
        }
        if(task==null){
            task=new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(second!=1){
                                --second;
                                presenter.setTime();
                            }else if(second==1){
                                stopTimer();
                                presenter.unsendIdentify();
                            }
                        }
                    });
                }
            };
        }
        flag=true;
//        presenter.setIdentityBtn();
        timer.schedule(task,0,1000);
    }
    public void stopTimer(){
        if(timer!=null){
            timer=null;
        }
        if(task!=null){
            task=null;
        }
        flag=false;
    }

    @Override
    public String getPhoneNum() {
        return phone.getText().toString();
    }

    @Override
    public String getIdentify() {
        return identify.getText().toString();
    }

    @Override
    public void unsendBtn() {
        sendIdentify.setText("获取验证码");
        sendIdentify.setBackground(getBaseContext().getResources().getDrawable(R.drawable.frame1));
    }

    @Override
    public void sendingBtn() {
        sendIdentify.setBackground(getBaseContext().getResources().getDrawable(R.drawable.bg2));
        startTimer();
    }

    @Override
    public void setTime() {
        sendIdentify.setText("重新发送("+second+")");
    }

    @Override
    public void back() {
        finish();
    }

    @Override
    public void emptyPhone() {
        AlertDialog dialog=new AlertDialog();
        dialog.getDialog1("提示","手机号不能为空！！",this);
    }

    @Override
    public void emptyIdentify() {
        AlertDialog dialog=new AlertDialog();
        dialog.getDialog1("提示","验证码不能为空！！",this);
    }

    @Override
    public void errorPhone() {
        AlertDialog dialog=new AlertDialog();
        dialog.getDialog1("提示","手机号不正确！！",this);
    }

    @Override
    public void goNext() {
        Intent intent=new Intent(this,SetActivity.class);
        intent.putExtra("phone",phone.getText().toString());
        startActivity(intent);
    }

    @Override
    public void exitPhone() {
        android.support.v7.app.AlertDialog.Builder builder=new android.support.v7.app.AlertDialog.Builder(this);
        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.goRegister();
            }
        })
                .setPositiveButton("是",null)
                .setTitle("该号码未注册")
                .setMessage("是否跳转到注册页面？")
                .show();
    }

    @Override
    public void goRegister() {
        startActivity(new Intent(this, Register1Activity.class));
        finish();
    }

}
