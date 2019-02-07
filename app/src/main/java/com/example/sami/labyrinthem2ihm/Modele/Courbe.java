package com.example.sami.labyrinthem2ihm.Modele;


import mesmaths.geometrie.base.Vecteur;

public class Courbe implements Mur {
    Vecteur debut;
    Vecteur fin;

    public Courbe(Vecteur debut, Vecteur fin, Vecteur ctrlCourbe) {
        this.debut = debut;
        this.fin = fin;
        CtrlCourbe = ctrlCourbe;
    }

    public Vecteur getDebut() {

        return debut;
    }

    public void setDebut(Vecteur debut) {
        this.debut = debut;
    }

    public Vecteur getFin() {
        return fin;
    }

    public void setFin(Vecteur fin) {
        this.fin = fin;
    }

    public Vecteur getCtrlCourbe() {
        return CtrlCourbe;
    }

    public void setCtrlCourbe(Vecteur ctrlCourbe) {
        CtrlCourbe = ctrlCourbe;
    }

    Vecteur CtrlCourbe;

}
