package com.example.sami.labyrinthem2ihm.Modele;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private List<Mur> murs;
    public Level(){
        murs = new ArrayList<Mur>();
    }
    public void addMur(Mur mur) {
        this.murs.add(mur);
    }

    public List<Mur> getMurs() {
        return murs;
    }

    public void setMurs(List<Mur> murs) {
        this.murs = murs;
    }
}
