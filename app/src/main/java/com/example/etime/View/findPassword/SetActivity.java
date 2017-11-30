package com.example.etime.View.findPassword;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.etime.Presenter.findPassword.SetPresenter;
import com.example.etime.R;
import com.example.etime.Utils.AlertDialog;
import com.example.etime.View.LoginActivity;

public class SetActivity extends AppCompatActivity implements SetView{

    private SetPresenter presenter=new SetPresenter(this,this);
    private LinearLayout backLayout;
    private EditText password;
    private Button sure;
    private String phone;
    private RelativeLayout loadingBg;
    private ImageView loadingImg;
    private ObjectAnimator animator2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        Intent intent=getIntent();
        phone=intent.getStringExtra("phone");
        initView();
        initListener();
    }

    private void initView() {
        backLayout=(LinearLayout)findViewById(R.id.back);
        password=(EditText)findViewById(R.id.password);
        sure=(Button)findViewById(R.id.sure);
    }

    private void initListener() {
        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.Back();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.SetPassword();
            }
        });
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void emptyPassword() {
        AlertDialog alertDialog=new AlertDialog();
        alertDialog.getDialog2("提示","密码不能为空",this);
    }

    @Override
    public void errorPassword() {
        AlertDialog alertDialog=new AlertDialog();
        alertDialog.getDialog2("提示","密码不符合规范",this);
    }

    @Override
    public void setSuccess() {
        android.support.v7.app.AlertDialog.Builder builder=new android.support.v7.app.AlertDialog.Builder(this);
        builder.setNegativeButton("",null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.goLogin();
                    }
                })
                .setTitle("提示")
                .setMessage("密码修改成功")
                .show();
    }

    @Override
    public void setFalse() {
        AlertDialog alertDialog=new AlertDialog();
        alertDialog.getDialog2("提示","密码修改失败",this);
        password.setText("");
    }

    @Override
    public void Back() {
        finish();
    }

    @Override
    public void goLogin() {
        finish();
        startActivity(new Intent(this, LoginActivity.class));
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
}
