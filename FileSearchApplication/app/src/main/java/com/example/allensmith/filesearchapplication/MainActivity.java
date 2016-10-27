package com.example.allensmith.filesearchapplication;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button;
    private EditText editKey; 
    private EditText editText;
    private String result;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    editText.setText((String)msg.obj);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }
    /**
    * 初始化事件
    * @author: allen
     */
    private void initEvent() {
          button.setOnClickListener(this);
    }

    /**
    * 初始化视图
    * @author:allen
     */
    private void initView() {
        button = (Button) findViewById(R.id.search);
        editKey = (EditText) findViewById(R.id.mKeyword);
        editText = (EditText) findViewById(R.id.et_show);
    }

    @Override
    public void onClick(View v) {
        String keyWord = "";
        switch (v.getId()) {
            case R.id.search:
                keyWord=editKey.getText().toString();
                if(keyWord.equals("")){
                    Toast.makeText(this,"关键字不能为空!",Toast.LENGTH_LONG).show();
                }else {
                    searchFile(keyWord);
                }
                break;
            default:
                break;
        }
    }

    private String searchFile(final String keyWord) {
        result="";
        new Thread(new Runnable() {
            @Override
            public void run() {
                File[] files=new File(Environment.getExternalStorageDirectory().getPath()+"/DCIM/Camera/").listFiles();
                Log.e("搜索的位置", Environment.getExternalStorageDirectory().getPath()+"/DCIM/Camera/");
//                MediaStore.Images.Media.
                for (File file : files) {
                    Log.e("文件位置", file.getAbsolutePath());
                    if (file.getName().contains(keyWord)) {
                        result += file.getPath() + "\n";
                    }
                }
                if(result.equals("")) {
                    result = "没有该文件!";
                }
                Message message=Message.obtain();
                message.what=0;
                message.obj= result;
                mHandler.sendMessage(message);

            }
        }).start();
        return result;


    }
}
