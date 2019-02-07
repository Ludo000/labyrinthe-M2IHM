package com.example.sami.labyrinthem2ihm.Modele;

import android.graphics.RectF;

public class Block extends Mur{
    public enum  Type { TROU, DEPART, ARRIVEE };

    private float SIZE = Bille.RAYON * 4;

    private Type mType = null;
    private RectF mRectangle = null;

    public Type getType() {
        return mType;
    }

    public RectF getRectangle() {
        return mRectangle;
    }

    public Block(Type pType, int pX, int pY) {
        super(pType,pX,pY);
        this.mType = pType;
        this.mRectangle = new RectF(pX * SIZE, pY * SIZE, (pX + 1) * SIZE, (pY + 1) * SIZE);
    }
}

