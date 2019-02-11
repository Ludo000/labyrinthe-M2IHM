package com.example.sami.labyrinthem2ihm.Modele;

import com.example.sami.labyrinthem2ihm.Vue.VisiteurDessinMur;

import mesmaths.geometrie.base.Vecteur;

public abstract class Mur{
    private Vecteur position;
    private Block.Type pType;

    private double pX, pY;

    public Mur(Block.Type pType, double pX, double pY) {
        this.pX=pX;
        this.pY=pY;
        this.position = new Vecteur(this.pX, this.pY);
        this.pType= pType;
    }

    public abstract void accept(VisiteurDessinMur visiteurDessinMur);
}
