package com.caknow.android.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.caknow.android.R;
import com.caknow.android.constant.Action;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import net.gtr.framework.util.Loger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by caroline on 2017/12/10.
 */

public class SplashActivity extends BaseActivity {
    @BindView(R.id.banner)
    Banner banner;
    private CallbackManager callbackManager;

    //LoginButton
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        List<Integer> images = new ArrayList<>();
        images.add(R.mipmap.splash_1_image_2x);
        images.add(R.mipmap.splash_2_image_2x);
        images.add(R.mipmap.splash_3_image_2x);
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
        //banner.setBannerTitles(images);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(6000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Loger.d(loginResult.getAccessToken().toString());
                        Toast.makeText(SplashActivity.this, "onSuccess", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Toast.makeText(SplashActivity.this, "onCancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Toast.makeText(SplashActivity.this, "onError "+exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private boolean isLogin(){
        boolean loggedIn = AccessToken.getCurrentAccessToken() != null;
        return loggedIn;
    }
    @OnClick({R.id.enter_phone_hint,R.id.login_facebook,R.id.login_google})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.enter_phone_hint:
                Intent intent = new Intent(Action.Register.value);
                startActivity(intent);
                break;
            case R.id.login_facebook:
                Loger.d("login_facebook start");
                if (isLogin()){
                    Loger.d(" is logged");
                }else {
                    LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile","user_friends"));
                    Loger.d("login_facebook logInWithReadPermissions");
                }
                break;
            case R.id.login_google:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Loger.d("onActivityResult ----");

        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Glide.with(context).load((Integer) path).fitCenter().into(imageView);
        }
    }
}
