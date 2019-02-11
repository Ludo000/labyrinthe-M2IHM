package com.example.sami.labyrinthem2ihm.Controller;

import java.util.Observable;
import java.util.Observer;

public class ObserverVue implements Observer {

    Controleur controleur;

    public ObserverVue(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public void update(Observable observable, Object o) {
        this.controleur.updateVue(observable, o);
    }
}
