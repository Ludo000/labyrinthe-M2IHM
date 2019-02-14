package com.example.sami.labyrinthem2ihm.Modele;


import com.example.sami.labyrinthem2ihm.Vue.VisiteurDessinMur;

import mesmaths.geometrie.base.Vecteur;

public class Courbe extends Mur {
    private Vecteur debut;
    private Vecteur fin;

    public Courbe(Block.Type pType, int pX, int pY,Vecteur debut, Vecteur fin, Vecteur ctrlCourbe) {
        super(pType, pX, pY);
        this.debut = debut;
        this.fin = fin;
        CtrlCourbe = ctrlCourbe;
    }

    public Vecteur getDebut() {

        return debut;
    }

    public void accept(VisiteurDessinMur visiteurDessinMur){
        visiteurDessinMur.dessin(this);
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

    private Vecteur CtrlCourbe;

}
