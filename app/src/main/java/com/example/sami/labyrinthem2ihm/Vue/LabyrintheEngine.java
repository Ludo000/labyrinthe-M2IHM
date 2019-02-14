package com.example.sami.labyrinthem2ihm.Vue;

import android.app.Service;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.sami.labyrinthem2ihm.Modele.Bille;
import com.example.sami.labyrinthem2ihm.Modele.Block;
import com.example.sami.labyrinthem2ihm.Modele.Modele;

import java.util.ArrayList;
import java.util.List;

public class LabyrintheEngine {

    private LevelsActivity mActivity = null;

    private SensorManager mManager = null;
    private Sensor mAccelerometre = null;
    private EcouteurSensor ecouteurSensor;
    public Modele modele;


    public LabyrintheEngine(LevelsActivity pView, Modele modele) {
        mActivity = pView;
        mManager = (SensorManager) mActivity.getBaseContext().getSystemService(Service.SENSOR_SERVICE);
        mAccelerometre = mManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.modele= modele;

        ecouteurSensor= new EcouteurSensor(this.modele);
    }

    // Remet à zéro l'emplacement de la boule
    public void reset() {
        this.modele.getBille().reset();
    }

    // Arrête le capteur
    public void stop() {
        mManager.unregisterListener(ecouteurSensor, mAccelerometre);
    }

    // Redémarre le capteur
    public void resume() {
        mManager.registerListener(ecouteurSensor, mAccelerometre, SensorManager.SENSOR_DELAY_GAME);
    }


}
