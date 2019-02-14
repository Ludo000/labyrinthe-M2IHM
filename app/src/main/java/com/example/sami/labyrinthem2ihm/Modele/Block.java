package com.example.sami.labyrinthem2ihm.Modele;

import android.graphics.Paint;
import android.graphics.RectF;

import com.example.sami.labyrinthem2ihm.Vue.VisiteurCollision;
import com.example.sami.labyrinthem2ihm.Vue.VisiteurDessinMur;

import java.util.List;

import mesmaths.geometrie.base.Vecteur;

public class Block extends Mur{
    public enum  Type { TROU, DEPART, ARRIVEE };

    private float SIZE = Bille.RAYON * 4;

    private Type mType;
    private RectF mRectangle;
    private float hauteur;
    private float largeur;
    private float pX;
    private float pY;


    public Type getType() {
        return mType;
    }

    public void accept(VisiteurDessinMur visiteurDessinMur){
        visiteurDessinMur.dessin(this);
    }
    public void accept(VisiteurCollision visiteurCollision){
        visiteurCollision.collision(this);
    }

    public RectF getRectangle() {
        return mRectangle;
    }

    public Block(Type pType, int pX, int pY, float largeur, float hauteur) {
        super(pType,pX,pY);
        this.mType = pType;
        this.pX = pX;
        this.pY = pY;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.mRectangle = new RectF(pX * SIZE, pY * SIZE,largeur , hauteur);
    }

    public void getSegmentBas(Vecteur P0, Vecteur P1){
       P0 = new Vecteur(this.pX, this.pY-this.hauteur);
       P1 = new Vecteur(this.pX+this.largeur, this.pY-this.hauteur);
    }

    public void getSegmentHaut(Vecteur P0, Vecteur P1){
        P0 = new Vecteur(this.pX, this.pY);
        P1 = new Vecteur(this.pX+this.largeur, this.pY);
    }

    public void getSegmentGauche(Vecteur P0, Vecteur P1){
        P0 = new Vecteur(this.pX, this.pY-this.hauteur);
        P1 = new Vecteur(this.pX, this.pY);
    }

    public void getSegmentDroite(Vecteur P0, Vecteur P1){
        P0 = new Vecteur(this.pX+this.largeur, this.pY-this.hauteur);
        P1 = new Vecteur(this.pX+this.largeur, this.pY);
    }


}