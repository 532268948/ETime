package com.example.etime.View;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.example.etime.Presenter.LoginPresenter;
import com.example.etime.R;
import com.example.etime.Utils.UrlUtil;
import com.example.etime.View.findPassword.PasswordActivity;
import com.example.etime.View.register.Register1Activity;
import com.tencent.connect.common.Constants;
import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity implements LoginView{

    private TextView loginTextView;
    private CircleImageView headImage;
    private EditText acountText,passwordText;
    private CheckBox checkBox;
    private TextView findText;
    private TextView registerText;
    private ImageView qqLogin,weixinLogin,passwordStyle;
    private LoginPresenter loginPresenter;
    private String sign="2";
    private RelativeLayout loadingBg;
    private ImageView loadingImg;
    private ObjectAnimator animator2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter=new LoginPresenter(this,this,this);
//        View decorView = getWindow().getDecorView();
//        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//        decorView.setSystemUiVisibility(option);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        initView();
        initListener();
//        Log.d("ss","ss");
    }

    private void initView() {
        headImage=(CircleImageView)findViewById(R.id.person_image);
        acountText=(EditText)findViewById(R.id.account);
        passwordText=(EditText)findViewById(R.id.password);
        passwordStyle=(ImageView)findViewById(R.id.password_style);
        checkBox=(CheckBox)findViewById(R.id.check);
        findText=(TextView)findViewById(R.id.find_password);
        loginTextView=(TextView)findViewById(R.id.login);
        registerText=(TextView)findViewById(R.id.register);
        qqLogin=(ImageView)findViewById(R.id.login_qq);
        weixinLogin=(ImageView)findViewById(R.id.login_weixin);
        loadingBg=(RelativeLayout)findViewById(R.id.loading);
        loadingImg=(ImageView)findViewById(R.id.img);
    }

    private void initListener() {
        acountText.addTextChangedListener(mTextWatch);
        passwordStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordStyleChange();
            }
        });
        findText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {loginPresenter.toPasswordActivity();
            }
        });
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.Login();
            }
        });
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {loginPresenter.toRegisterActivity();
            }
        });
        qqLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.qqLogin();
            }
        });
        weixinLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.weixinLogin();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.e("MainActivity","listener1");
        if(requestCode == Constants.REQUEST_LOGIN&&resultCode==RESULT_OK){
            loginPresenter.qqResult(data);
//            Log.e("MainActivity","listener2");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void passwordStyleChange() {
        if(sign.equals("1")){
            sign="2";
            passwordStyle.setImageResource(R.drawable.showpassword);
            passwordText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }else {
            sign="1";
            passwordStyle.setImageResource(R.drawable.hidepassword);
            passwordText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    @Override
    public void finish() {
        super.finish();
        Log.d("LoginActivity","finish()");
    }

    @Override
    public void getHeadSuccess(String head) {
        if (Util.isOnMainThread())
            Glide.with(this).load(UrlUtil.getURL(head)).into(headImage);
    }

    @Override
    public void getHeadFalse() {
        if (Util.isOnMainThread())
            Glide.with(this).load(R.drawable.etime_logo).into(headImage);
    }

    @Override
    public String getUserName() {
        return acountText.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordText.getText().toString();
    }

    @Override
    public void toMainActivity() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void emptyError() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setNegativeButton("",null);
        builder.setPositiveButton("确定",null);
        builder.setTitle("信息错误");
        builder.setMessage("账号或者密码不能为空！！");
        builder.setIcon(R.drawable.error);
        builder.show();
    }

    @Override
    public void LoginFalse() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setNegativeButton("",null);
        builder.setPositiveButton("确定",null);
        builder.setTitle("登陆错误");
        builder.setMessage("账号或者密码错误！！");
        builder.setIcon(R.drawable.error);
        builder.show();
    }

    @Override
    public void toRegister1Activity() {
        startActivity(new Intent(this, Register1Activity.class));
    }

    @Override
    public void toPasswordActivity() {
        startActivity(new Intent(this, PasswordActivity.class));
    }

    @Override
    public void QQLogin() {

    }

    @Override
    public void WeiXinLogin() {

    }

    @Override
    public View remenber() {
        return checkBox;
    }

    @Override
    public void showPassword() {

    }

    @Override
    public void hidePassword() {

    }

    @Override
    public void changeHead() {

    }

    @Override
    public void showLoading() {
        loadingBg.setVisibility(View.VISIBLE);
        ObjectAnimator animator1=ObjectAnimator.ofFloat(loadingBg,"alpha",0f,1f);
        animator1.setDuration(500);
        animator1.start();
        animator2= ObjectAnimator.ofFloat(loadingImg,"rotation",0,360);
        animator2.setDuration(2000);
        animator2.setRepeatCount(ObjectAnimator.INFINITE);
        animator2.start();
    }

    @Override
    public void hideLoading() {
        animator2.cancel();
        loadingBg.setVisibility(View.GONE);
    }
    TextWatcher mTextWatch=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            loginPresenter.Head();
        }
    };
}
