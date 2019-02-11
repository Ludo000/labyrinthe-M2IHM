package com.example.sami.labyrinthem2ihm.Controller;

import com.example.sami.labyrinthem2ihm.Modele.Modele;

import java.util.Observable;
import java.util.Observer;

public class ObserverModele implements Observer {
    private Controleur controleur;

    public ObserverModele(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public void update(Observable observable, Object o) {
        this.controleur.updateModele(observable, o);
    }
}
