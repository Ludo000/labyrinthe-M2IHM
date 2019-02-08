package com.example.sami.labyrinthem2ihm.Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Modele extends Observable {
    Bille bille;
    List<Level> levels;
    int currentLevelID;

    public Modele() {
        this.bille = new Bille();
        this.levels = new ArrayList<Level>();
        currentLevelID = 0;
    }

    public Bille getBille() {
        return bille;
    }

    public void setBille(Bille bille) {
        this.bille = bille;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }
    public void addLevel(Level level){
        this.levels.add(level);
    }

    public int getCurrentLevelID() {
        return currentLevelID;
    }

    public void setCurrentLevelID(int currentLevelID) {
        this.currentLevelID = currentLevelID;
    }

    public Level getCurrentLevel(){
        return this.getLevels().get(this.currentLevelID);
    }
}
