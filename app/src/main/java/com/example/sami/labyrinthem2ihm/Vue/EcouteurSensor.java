package com.example.sami.labyrinthem2ihm.Vue;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import com.example.sami.labyrinthem2ihm.Modele.Bille;
import com.example.sami.labyrinthem2ihm.Modele.Modele;
import com.example.sami.labyrinthem2ihm.Modele.Mur;

import java.util.Observable;

import mesmaths.cinematique.Collisions;
import mesmaths.geometrie.base.Vecteur;

public class EcouteurSensor extends Observable implements SensorEventListener  {


    public Modele modele;
    private Collisions collisions;
    private VisiteurCollision visiteurCollision;

    EcouteurSensor(Modele modele) {
        this.modele = modele;
        this.collisions = new Collisions();
        this.visiteurCollision = new VisiteurCollision(this.modele.getBille());
    }

    @Override
    public void onSensorChanged(SensorEvent pEvent) {
        float x = pEvent.values[0];
        float y = pEvent.values[1];

        Bille mBoule =  modele.getBille();
        modele.getBille().putXAndY(x, y);
        if(mBoule != null) {
            for ( Mur mur : this.modele.getCurrentLevel().getMurs()) {
                 mur.accept(this.visiteurCollision);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor pSensor, int pAccuracy) {

    }
}