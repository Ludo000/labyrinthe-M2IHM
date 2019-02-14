package com.example.sami.labyrinthem2ihm.Vue;

import android.app.Service;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.sami.labyrinthem2ihm.Controller.Controleur;
import com.example.sami.labyrinthem2ihm.Controller.EcouteurBouttonLevel3;
import com.example.sami.labyrinthem2ihm.Modele.Bille;
import com.example.sami.labyrinthem2ihm.Modele.Block;
import com.example.sami.labyrinthem2ihm.Modele.Modele;
import com.example.sami.labyrinthem2ihm.Modele.Mur;

import java.util.List;

public class LabyrintheEngine {
    public Vue vue;
    private Bille mBoule = null;
    private Controleur controleur;
    private VisiteurDessinMur visiteurDessinMur;
    private SensorManager mManager = null;
    private Sensor mAccelerometre = null;
    // Le labyrinthe
    private List<Block> mBlocks = null;
    private EcouteurSensor ecouteurSensor;

    public LabyrintheEngine(LevelsActivity activity, Canvas canvas, Controleur controleur) {
        this.controleur = controleur;
        mManager = (SensorManager) activity.getBaseContext().getSystemService(Service.SENSOR_SERVICE);
        mAccelerometre = mManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        visiteurDessinMur = new VisiteurDessinMur(canvas);
        this.ecouteurSensor = new EcouteurSensor(this.controleur);
        mManager.registerListener(this.ecouteurSensor, mAccelerometre, 1);
         }
        // Remet à zéro l'emplacement de la boule
        public void reset() {
         mBoule.reset();
        }

        // Arrête le capteur
        public void stop() {
         mManager.unregisterListener(this.ecouteurSensor, mAccelerometre);
        }

        // Redémarre le capteur
        public void resume() {
            mManager.registerListener(this.ecouteurSensor, mAccelerometre, SensorManager.SENSOR_DELAY_GAME);
        }
    }
