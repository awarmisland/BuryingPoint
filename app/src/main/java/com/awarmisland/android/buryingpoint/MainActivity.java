package com.awarmisland.android.buryingpoint;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.awarmisland.android.buryingpoint.buryingPoint.DotComponent;
import com.awarmisland.aptannotation.RecordClick;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                DotComponent.getInstance().recordLifecycle(getClass().getName(),"");
            }
        });
        initButton();
    }

    protected void initButton(){
        Button btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View www) {
                requestUrl("www.baidu.com");
            }
        });
    }

    @RecordClick({"rel","urlStr"})
    private void requestUrl(String urlStr){
        String rel = "3d";
        int a = 1;
    }

    @Override
    public void onClick(View v) {
    }
}
