package com.example.sami.labyrinthem2ihm.Controller;

import android.app.Activity;
import android.app.Service;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.sami.labyrinthem2ihm.Modele.Bille;
import com.example.sami.labyrinthem2ihm.Modele.Block;
import com.example.sami.labyrinthem2ihm.Modele.Level;
import com.example.sami.labyrinthem2ihm.Modele.Modele;
import com.example.sami.labyrinthem2ihm.Modele.Mur;
import com.example.sami.labyrinthem2ihm.Vue.LevelsActivity;
import com.example.sami.labyrinthem2ihm.Vue.MainActivity;
import com.example.sami.labyrinthem2ihm.Vue.VisiteurDessinMur;

import java.util.Observable;

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
    private Modele modele;
    private ObserverModele observerModele;
    private ObserverVue observerVue;
    private VisiteurDessinMur visiteurDessinMur;
    private SensorEventListener mSensorEventListener;



    public Controleur(Modele modele, MainActivity activity) {

        this.modele = modele;
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

        observerModele = new ObserverModele(this);
        observerVue = new ObserverVue(this);
        mManager = (SensorManager) activity.getBaseContext().getSystemService(Service.SENSOR_SERVICE);

        mAccelerometre = mManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorEventListener = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent pEvent) {
                float x = pEvent.values[0];
                float y = pEvent.values[1];

                if(mBoule != null) {
                    // On met à jour les coordonnées de la boule
                    RectF hitBox = mBoule.putXAndY(x, y);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor pSensor, int pAccuracy) {

            }
        };
    }


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

    public void updateModele(Observable o, Object arg) {
        this.setChanged();
        this.notifyObservers(arg);
    }

    public Modele get_modele() {return modele;}

    public ObserverModele get_obsMod() {return observerModele;}
    public ObserverVue get_obsVue() {return observerVue;}

    public void updateVue(Observable o, Object arg) {
        this.setChanged();
        this.notifyObservers(arg);
    }

}
