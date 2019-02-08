package com.example.sami.labyrinthem2ihm.Controller;

import android.app.Service;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.sami.labyrinthem2ihm.Modele.Bille;
import com.example.sami.labyrinthem2ihm.Modele.Block;
import com.example.sami.labyrinthem2ihm.Modele.Modele;
import com.example.sami.labyrinthem2ihm.Modele.Mur;
import com.example.sami.labyrinthem2ihm.Vue.LevelsActivity;
import com.example.sami.labyrinthem2ihm.Vue.VisiteurDessinMur;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import static com.example.sami.labyrinthem2ihm.Modele.Block.Type.ARRIVEE;
import static com.example.sami.labyrinthem2ihm.Modele.Block.Type.DEPART;
import static com.example.sami.labyrinthem2ihm.Modele.Block.Type.TROU;

public class Controleur extends Observable{
    private Bille mBoule = null;
    public Bille getBoule() {
        return mBoule;
    }

    public void setBoule(Bille pBoule) {
        this.mBoule = pBoule;
    }

    private SensorManager mManager = null;
    private Sensor mAccelerometre = null;
    Modele modele;
    ObserverModele observerModele;
    ObserverVue observerVue;
    VisiteurDessinMur visiteurDessinMur;
    public Controleur(Modele modele) {
        this.modele = modele;
        observerModele = new ObserverModele();
        observerVue = new ObserverVue();
        mAccelerometre = mManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    SensorEventListener mSensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent pEvent) {
            float x = pEvent.values[0];
            float y = pEvent.values[1];

            if(mBoule != null) {
                // On met à jour les coordonnées de la boule
                RectF hitBox = mBoule.putXAndY(x, y);

                // Pour tous les blocs du labyrinthe
                for(Mur mur : modele.getCurrentLevel().getMurs()) {
                    // On crée un nouveau rectangle pour ne pas modifier celui du bloc
                  mur.accept(visiteurDessinMur);
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor pSensor, int pAccuracy) {

        }
    };

    // Remet à zéro l'emplacement de la boule
    public void reset() {
        mBoule.reset();
    }

    // Arrête le capteur
    public void stop() {
        mManager.unregisterListener(mSensorEventListener, mAccelerometre);
    }

    // Redémarre le capteur
    public void resume() {
        mManager.registerListener(mSensorEventListener, mAccelerometre, SensorManager.SENSOR_DELAY_GAME);
    }

}
