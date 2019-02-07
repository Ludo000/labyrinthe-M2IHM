package com.example.sami.labyrinthem2ihm.Vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sami.labyrinthem2ihm.Controller.EcouteurBouttonLevel1;
import com.example.sami.labyrinthem2ihm.R;

public class MainActivity extends AppCompatActivity {

    Button btnLevel1;
    Button btnLevel2;
    Button btnLevel3;
    EcouteurBouttonLevel1 ecouteurBouttonLevel1;
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLevel1 = findViewById(R.id.btn_level1);
        btnLevel2 = findViewById(R.id.btn_level2);
        btnLevel3 = findViewById(R.id.btn_level3);
        this.ecouteurBouttonLevel1 = new EcouteurBouttonLevel1(this);
        btnLevel1.setOnClickListener(this.ecouteurBouttonLevel1);

    }
}
