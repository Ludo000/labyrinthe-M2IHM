package com.example.sami.labyrinthem2ihm.Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import static com.example.sami.labyrinthem2ihm.Modele.Block.Type.TROU;

public class Modele extends Observable {
    private Bille bille;
    private List<Level> levels;
    private int currentLevelID;

    public Modele() {
        this.bille = new Bille(100,100);
        this.levels = new ArrayList<Level>();
        currentLevelID = 0;

        Level level1 = new Level();
        Level level2 = new Level();
        Level level3 = new Level();
        //  level1.addMur(new Block(new Vecteur(0,0)));
        Block block1 = new Block(TROU, 2,2);
        Block block2 = new Block(TROU, 4,4);
        Block block3 = new Block(TROU, 6,6);
        Block block4 = new Block(TROU, 106,106);

        level1.addMur(block1);
        level2.addMur(block2);
        level3.addMur(block3);
        level3.addMur(block4);

        this.addLevel(level1);
        this.addLevel(level2);
        this.addLevel(level3);
        this.setChanged();
    }

    public Bille getBille() {
        return bille;
    }

    public void setBille(Bille bille) {
        this.bille = bille;
    }

    private List<Level> getLevels() {
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
