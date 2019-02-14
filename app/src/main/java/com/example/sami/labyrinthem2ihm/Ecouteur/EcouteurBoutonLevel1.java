package com.example.sami.labyrinthem2ihm.Ecouteur;

import android.content.Intent;
import android.view.View;

import com.example.sami.labyrinthem2ihm.Vue.LevelsActivity;
import com.example.sami.labyrinthem2ihm.Vue.MainActivity;

public class EcouteurBoutonLevel1 implements View.OnClickListener {

    MainActivity mainActivity;

    public EcouteurBoutonLevel1(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this.mainActivity, LevelsActivity.class);
        this.mainActivity.startActivity(intent);
    }
}
