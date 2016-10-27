package com.example.allen.fragmenttab;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Configuration cfg=getResources().getConfiguration();
        System.out.println(cfg.orientation);//系统屏幕方向
        System.out.println(cfg.mnc);//网络码
        System.out.println(cfg.mcc);//国家码
        System.out.println(cfg.navigation);//导航设备类型
        System.out.println(cfg.keyboard);//关联的键盘类型
        System.out.println(cfg.fontScale); //字体缩放因子
        System.out.println(cfg.locale);//用户位置
        System.out.println(cfg.keyboardHidden);//当前键盘是否可用
        System.out.println(cfg.touchscreen);//触摸方式


    }
}
