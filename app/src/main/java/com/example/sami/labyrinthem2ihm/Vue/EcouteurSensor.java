package com.example.sami.labyrinthem2ihm.Vue;

import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import com.example.sami.labyrinthem2ihm.Controller.Controleur;
import com.example.sami.labyrinthem2ihm.Modele.Bille;
import com.example.sami.labyrinthem2ihm.Modele.Mur;

import java.util.Observable;

public class EcouteurSensor extends Observable implements SensorEventListener  {
    private Controleur controleur;
    public EcouteurSensor(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public void onSensorChanged(SensorEvent pEvent) {
        float x = pEvent.values[0];
        float y = pEvent.values[1];

        Bille mBoule = this.controleur.get_modele().getBille();
        if(mBoule != null) {
            // On met à jour les coordonnées de la boule
            mBoule.putXAndY(x, y);
            Log.d("Bille : ", Float.toString(mBoule.getX()) + "," + Float.toString(mBoule.getY()));
            this.controleur.get_modele().setBille(mBoule);
            // Pour tous les blocs du labyrinthe
            this.controleur.get_obsMod().update(this, this.controleur.get_modele());
            this.controleur.get_obsVue().update(this, this.controleur.get_modele());
            Log.d("ModeleBille : ", Float.toString(this.controleur.get_modele().getBille().getX()) + "," + Float.toString(this.controleur.get_modele().getBille().getY()));


        }
    }

    @Override
    public void onAccuracyChanged(Sensor pSensor, int pAccuracy) {

    }
}
