package com.awarmisland.android.buryingpoint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButton();

    }

    private void initButton(){
        Button btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);



        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View www) {
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}
