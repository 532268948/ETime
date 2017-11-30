package com.example.etime.View;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import com.example.etime.Other.CustomImageView;
import com.example.etime.Presenter.SplashPresenter;
import com.example.etime.R;
import com.example.etime.Utils.StatusBarCompat;

public class SplashActivity extends AppCompatActivity implements SplashView{

    private SharedPreferences pref;
    private CustomImageView imageView;
    private SplashPresenter splashPresenter=new SplashPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StatusBarCompat.compat(this, Color.WHITE);

        imageView=(CustomImageView)findViewById(R.id.sign);
        float Y=imageView.getTranslationY();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "translationY",Y+100f, Y);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(animator1).with(animator2);
        animSet.setDuration(1500);
        animSet.start();
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                pref= getSharedPreferences("data",MODE_PRIVATE);
                                final boolean isRemember=pref.getBoolean("remember_password",false);
                                if(isRemember){
                                    splashPresenter.Login();
                                }else {
                                    splashPresenter.goLogin();
                                }
                            }
                        }).start();
                    }
                }, 1000);
            }
        });
    }

    @Override
    public String getUserName() {
        return (String)pref.getString("account","");
    }

    @Override
    public String getPassword() {
        return (String)pref.getString("password","");
    }

    @Override
    public void toMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void toLoginActivity() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
