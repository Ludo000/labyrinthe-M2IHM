package com.example.sami.labyrinthem2ihm.Modele;

import android.graphics.RectF;

import com.example.sami.labyrinthem2ihm.Vue.VisiteurDessinMur;

public class Block extends Mur{
    public enum  Type { TROU, DEPART, ARRIVEE };

    private double SIZE = Bille.RAYON * 4;

    private Type mType = null;
    private RectF mRectangle = null;

    public Type getType() {
        return mType;
    }

   public void accept(VisiteurDessinMur visiteurDessinMur){
        visiteurDessinMur.dessin(this);
   }

    public RectF getRectangle() {
        return mRectangle;
    }

    public Block(Type pType, double pX, double pY) {
        super(pType,pX,pY);
        this.mType = pType;
        this.mRectangle = new RectF((float) (pX * SIZE), (float) (pY * SIZE), (float) ((pX + 1) * SIZE), (float) ((pY + 1) * SIZE));
    }
}

