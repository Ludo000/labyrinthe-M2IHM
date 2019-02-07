package com.example.sami.labyrinthem2ihm.Controller;

import android.content.Intent;
import android.view.View;

import com.example.sami.labyrinthem2ihm.Vue.LevelsActivity;
import com.example.sami.labyrinthem2ihm.Vue.MainActivity;

public class EcouteurBouttonLevel1 implements View.OnClickListener {
    MainActivity mainActivity;

    public EcouteurBouttonLevel1(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this.mainActivity, LevelsActivity.class);
        this.mainActivity.startActivity(intent);
    }
}
