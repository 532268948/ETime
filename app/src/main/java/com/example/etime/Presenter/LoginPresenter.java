package com.example.etime.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.etime.Bean.qqUser;
import com.example.etime.Model.dao.HeadDao;
import com.example.etime.Model.dao.LocalMessageDao;
import com.example.etime.Model.dao.LoginDao;
import com.example.etime.Model.dao.SaveDao;
import com.example.etime.Model.dao.isQqExit;
import com.example.etime.Model.dao.listener.Listener;
import com.example.etime.Model.dao.listener.RequestListener;
import com.example.etime.Model.impl.LoginDaoImpl;
import com.example.etime.Model.impl.QqExitDaoImpl;
import com.example.etime.Model.impl.RemenberDaoImpl;
import com.example.etime.View.LoginView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 53226 on 2017/11/7.
 */

public class LoginPresenter {

    private Context context;
    private LoginDao loginDao;
    private HeadDao headDao;
    private SaveDao saveDao;
    private LocalMessageDao remenberDao;
    private LoginView loginView;
    private Activity activity;
    private Handler handler=new Handler();
    private QqListener mUIListener;
    private Tencent tencent;

    public LoginPresenter(LoginView loginView, Context context, Activity activity){
        this.loginView=loginView;
        this.loginDao=new LoginDaoImpl();
        this.remenberDao=new RemenberDaoImpl();
        this.headDao=new LoginDaoImpl();
        this.saveDao=new LoginDaoImpl();
        this.context=context;
        this.activity=activity;
    }

    public void Login(){
        if(((CheckBox)loginView.remenber()).isChecked()){
            remenberDao.save(loginView.getUserName(),loginView.getPassword(),context);
        }else{
            remenberDao.change(context);
        }
        if(TextUtils.isEmpty(loginView.getUserName())||TextUtils.isEmpty(loginView.getPassword())){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    loginView.emptyError();
                }
            });
        }else{
            loginView.showLoading();
            loginDao.Login(loginView.getUserName(), loginView.getPassword(), new RequestListener() {
                @Override
                public void Success(final String id){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            remenberDao.saveId(id,context);
                            loginView.hideLoading();
                            loginView.toMainActivity();
                        }
                    },3000);
                }

                @Override
                public void False() {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loginView.hideLoading();
                            loginView.LoginFalse();
                        }
                    },4000);
                }
            });
        }
    }

    public void Head(){
         headDao.getHead(loginView.getUserName(), new RequestListener() {
            @Override
            public void Success(final String head) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.getHeadSuccess(head);
                    }
                });
            }

            @Override
            public void False() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.getHeadFalse();
                    }
                });
            }
        });
    }

    public void toPasswordActivity(){
        loginView.toPasswordActivity();
    }

    public void toRegisterActivity(){
        loginView.toRegister1Activity();
    }

    public void qqLogin(){

        tencent=Tencent.createInstance("1106494874",context);
        mUIListener=new QqListener(context,tencent);
        tencent.login(activity, "all",mUIListener);
    }

    public void weixinLogin(){
        loginView.WeiXinLogin();
    }

    public void qqResult(Intent data){
        tencent.handleLoginData(data, mUIListener);

    }

    class QqListener implements IUiListener {
        private Context mContext;
        private Tencent mTencent;
        private UserInfo mUserInfo;
        private isQqExit exitDao;
        public QqListener(Context mContext, Tencent mTencent){
            this.mContext=mContext;
            this.mTencent=mTencent;
            this.exitDao=new QqExitDaoImpl();
        }
        @Override
        public void onComplete(Object response) {
            try {
//                Toast.makeText(mContext, "授权成功", Toast.LENGTH_SHORT).show();
                Log.e("LoginActivity", "response:" + response);
                JSONObject obj = (JSONObject) response;
                final String openID = obj.getString("openid");
                final String accessToken = obj.getString("access_token");
                final String expires = obj.getString("expires_in");
                exitDao.isExit(openID, new RequestListener() {
                    @Override
                    public void Success(final String id) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                remenberDao.saveId(id,context);
                                loginView.toMainActivity();
                            }
                        });
                    }

                    @Override
                    public void False() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                mTencent.setOpenId(openID);
                                mTencent.setAccessToken(accessToken,expires);
                                QQToken qqToken = mTencent.getQQToken();
                                mUserInfo = new UserInfo(mContext.getApplicationContext(),qqToken);
                                mUserInfo.getUserInfo(new IUiListener() {
                                    @Override
                                    public void onComplete(Object response) {

                                        try {
                                            Log.e("LoginActivity","登录成功"+response.toString());
//                                        qqUser qqUser = (new Gson()).fromJson(response.toString(), new TypeToken<qqUser>() {}.getType());
//                                        Log.e("LoginActivityhh","ss"+qqUser.getFigureurl_qq_2());
                                            JSONObject obj2= (JSONObject) response;
//                                        final String nick = obj.getString("openid");
//                                        final String accessToken = obj.getString("access_token");
//                                        final String expires = obj.getString("expires_in");
                                            List<String> list=new ArrayList<>();
                                            list.add(openID);
                                            list.add(obj2.getString("nickname"));
                                            list.add(obj2.getString("figureurl_qq_2"));
                                            if((obj2.getString("gender")).equals("男")){
                                                list.add("1");
                                            }else {
                                                list.add("0");
                                            }
                                            saveDao.Save(list, new Listener() {
                                                @Override
                                                public void Success() {
                                                    handler.post(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            loginView.toMainActivity();
                                                        }
                                                    });
                                                }

                                                @Override
                                                public void False() {
                                                    handler.post(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Toast.makeText(mContext,"登录出错",Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }
                                            });
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

//                                        new Thread(new Runnable() {
//                                            @Override
//                                            public void run() {
//
//                                            }
//                                        }).start();
                                    }

                                    @Override
                                    public void onError(UiError uiError) {
                                        Log.e("LoginActivity","登录失败"+uiError.toString());
                                    }

                                    @Override
                                    public void onCancel() {
                                        Log.e("LoginActivity","登录取消");

                                    }
                                });
                            }
                        });
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(mContext, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(mContext, "授权取消", Toast.LENGTH_SHORT).show();

        }
    }
}
