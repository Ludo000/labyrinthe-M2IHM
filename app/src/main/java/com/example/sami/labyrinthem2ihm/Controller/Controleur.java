package com.example.sami.labyrinthem2ihm.Controller;

import android.app.Service;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.sami.labyrinthem2ihm.Modele.Bille;
import com.example.sami.labyrinthem2ihm.Modele.Modele;
import com.example.sami.labyrinthem2ihm.Modele.Mur;
import com.example.sami.labyrinthem2ihm.Vue.MainActivity;
import com.example.sami.labyrinthem2ihm.Vue.VisiteurDessinMur;
import com.example.sami.labyrinthem2ihm.Vue.Vue;

import java.util.List;
import java.util.Observable;

public class Controleur extends Observable{
    private Bille mBoule;
    private SensorManager mManager;
    private Sensor mAccelerometre;
    private Modele modele;
    private ObserverModele observerModele;
    private ObserverVue observerVue;
    private VisiteurDessinMur visiteurDessinMur;
    private SensorEventListener mSensorEventListener;
    private Vue vue;


    public Controleur(Modele modele, Vue vue) {

        this.modele = modele;
        this.vue = vue;
        mBoule = this.modele.getBille();
        observerModele = new ObserverModele(this);
        observerVue = new ObserverVue(this);


    }

    public void updateModele(Observable o, Object arg) {
        this.modele = (Modele) arg;
        this.setChanged();
        this.notifyObservers(arg);
    }

    public Modele get_modele(){ return this.modele;}

    public ObserverModele get_obsMod() {return observerModele;}
    public ObserverVue get_obsVue() {return observerVue;}

    public void updateVue(Observable o, Object arg) {
        this.vue.modele = (Modele) arg;
        this.setChanged();
        this.notifyObservers(arg);
    }

}
