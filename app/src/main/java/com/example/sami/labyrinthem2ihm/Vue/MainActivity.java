package com.example.sami.labyrinthem2ihm.Vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sami.labyrinthem2ihm.Controller.Controleur;
import com.example.sami.labyrinthem2ihm.Controller.EcouteurBouttonLevel1;
import com.example.sami.labyrinthem2ihm.Controller.EcouteurBouttonLevel2;
import com.example.sami.labyrinthem2ihm.Controller.EcouteurBouttonLevel3;
import com.example.sami.labyrinthem2ihm.Controller.ObserverVue;
import com.example.sami.labyrinthem2ihm.Modele.Block;
import com.example.sami.labyrinthem2ihm.Modele.Level;
import com.example.sami.labyrinthem2ihm.Modele.Modele;
import com.example.sami.labyrinthem2ihm.Modele.Mur;
import com.example.sami.labyrinthem2ihm.R;

import mesmaths.geometrie.base.Vecteur;

import static com.example.sami.labyrinthem2ihm.Modele.Block.Type.TROU;

public class MainActivity extends AppCompatActivity {

    Button btnLevel1;
    Button btnLevel2;
    Button btnLevel3;
    EcouteurBouttonLevel1 ecouteurBouttonLevel1;
    EcouteurBouttonLevel2 ecouteurBouttonLevel2;
    EcouteurBouttonLevel3 ecouteurBouttonLevel3;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Modele modele = new Modele();

        Level level1 = new Level();
        Level level2 = new Level();
        Level level3 = new Level();
      //  level1.addMur(new Block(new Vecteur(0,0)));
        Block block1 = new Block(TROU, 2,2);
        Block block2 = new Block(TROU, 4,4);
        Block block3 = new Block(TROU, 6,6);

        level1.addMur(block1);
        level2.addMur(block2);
        level3.addMur(block3);

        modele.addLevel(level1);
        modele.addLevel(level2);
        modele.addLevel(level3);



        btnLevel1 = findViewById(R.id.btn_level1);
        btnLevel2 = findViewById(R.id.btn_level2);
        btnLevel3 = findViewById(R.id.btn_level3);


        this.ecouteurBouttonLevel1 = new EcouteurBouttonLevel1(this);
        btnLevel1.setOnClickListener(this.ecouteurBouttonLevel1);

        this.ecouteurBouttonLevel2 = new EcouteurBouttonLevel2(this);
        btnLevel2.setOnClickListener(this.ecouteurBouttonLevel2);

        this.ecouteurBouttonLevel3 = new EcouteurBouttonLevel3(this);
        btnLevel3.setOnClickListener(this.ecouteurBouttonLevel3);

        Controleur controleur = new Controleur(modele);

    }
}
