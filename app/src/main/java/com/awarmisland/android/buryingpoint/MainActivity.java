package com.awarmisland.android.buryingpoint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.awarmisland.android.buryingpoint.buryingPoint.DotComponent;
import com.awarmisland.aptannotation.RecordClick;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    @RecordClick("rel")
    private void requestUrl(String urlStr){
        String rel = "3d";
        int a = 1;
    }

    @Override
    public void onClick(View v) {

    }
}
