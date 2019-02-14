package com.example.sami.labyrinthem2ihm.Vue;

import com.example.sami.labyrinthem2ihm.Controller.Controleur;
import com.example.sami.labyrinthem2ihm.Modele.Modele;

import java.util.Observable;
import java.util.Observer;

public class Vue extends Observable implements Observer{

    private LevelsActivity levelsActivity;
    public LabyrintheView labyrintheView;

    // Le moteur physique du jeu
    public LabyrintheEngine mEngine;
    public Modele modele;

    public Controleur controleur;

    public Vue() {

        modele = new Modele();
        controleur = new Controleur(modele, this);
        this.levelsActivity = new LevelsActivity(this.controleur);
         mEngine = new LabyrintheEngine(this.levelsActivity, this.levelsActivity.mView.getCanvas(), this.controleur);

        this.addObserver(controleur.get_obsVue());

        modele.addObserver(controleur.get_obsMod());
        modele.addObserver(controleur.get_obsMod());

        controleur.addObserver(this);
        modele.notifyObservers(modele);
    }

    @Override
    public void update(Observable observable, Object arg) {
        this.modele = (Modele) arg;
    }
}
