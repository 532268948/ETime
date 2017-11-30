package com.example.etime.View.register;

import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.etime.Presenter.register.Register1Presenter;
import com.example.etime.R;

import java.util.Timer;
import java.util.TimerTask;

public class Register1Activity extends AppCompatActivity implements Register1View{

    private LinearLayout backLayout;
    private EditText phone;
    private EditText password;
    private ImageView passwordType;
    private EditText identity;
    private TextView btn_identity;
    private TextView register;
    private TextView loginBack;
    private String sign="2";
    private Boolean flag=false;
    private int second=60;
    private Timer timer;
    private TimerTask task;
    private Handler handler=new Handler();
    private Register1Presenter presenter=new Register1Presenter(this,this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        initView();
        initListener();
    }

    private void initView() {
        backLayout=(LinearLayout)findViewById(R.id.back);
        phone=(EditText)findViewById(R.id.phone);
        password=(EditText)findViewById(R.id.password);
        passwordType=(ImageView)findViewById(R.id.password_style);
        identity=(EditText)findViewById(R.id.identify);
        btn_identity=(TextView)findViewById(R.id.send_identify);
        register=(TextView)findViewById(R.id.register);
        loginBack=(TextView)findViewById(R.id.login_back);
    }

    private void initListener() {
        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.back();
            }
        });
        passwordType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.passwordType();
            }
        });
        btn_identity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag==true){
                    return;
                }
                presenter.sendIdentity();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.Register();
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
                                presenter.setTime(second);
                            }else if(second==1){
                                stopTimer();
                                flag=false;
                                presenter.backIdentity();
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
    public void back() {
        this.finish();
//        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public String getPhoneNum() {
        return phone.getText().toString();
    }

    @Override
    public void errorPhone() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setNegativeButton("",null);
        builder.setPositiveButton("确定",null);
        builder.setTitle("错误");
        builder.setMessage("手机号码错误！！");
        builder.setIcon(R.drawable.error);
        builder.show();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void passwordType() {
        if(sign.equals("1")){
            sign="2";
            passwordType.setImageResource(R.drawable.showpassword);
            password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }else {
            sign="1";
            passwordType.setImageResource(R.drawable.hidepassword);
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    @Override
    public String getIdentity() {
        return identity.getText().toString();
    }

    @Override
    public void toRegister2Activity() {

    }

    @Override
    public void identitySending() {

    }

    @Override
    public void setTime(int second) {
        btn_identity.setText("重新发送("+second+")");
    }

    @Override
    public void setIdentity() {
        btn_identity.setText("获取验证码");
        btn_identity.setBackground(getBaseContext().getResources().getDrawable(R.drawable.frame1));
        flag=false;
    }

    @Override
    public void setIdentityBtn() {
        btn_identity.setBackground(getBaseContext().getResources().getDrawable(R.drawable.bg2));
        startTimer();
    }

    @Override
    public void phoneExit() {

    }

    @Override
    public void register1Error() {

    }
}
