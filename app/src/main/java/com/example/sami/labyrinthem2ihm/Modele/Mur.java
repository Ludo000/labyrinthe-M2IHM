package com.example.sami.labyrinthem2ihm.Modele;

import com.example.sami.labyrinthem2ihm.Vue.VisiteurDessinMur;

import mesmaths.geometrie.base.Vecteur;

public abstract class Mur{
    Vecteur position = null;
    Block.Type pType;

    public Mur(Block.Type pType, int pX, int pY) {
        this.position = new Vecteur(pX, pY);
        this.pType= pType;
    }

    public abstract void accept(VisiteurDessinMur visiteurDessinMur);
}
