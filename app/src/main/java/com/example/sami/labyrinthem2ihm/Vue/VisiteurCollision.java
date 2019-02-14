package com.example.sami.labyrinthem2ihm.Vue;

import android.util.Log;

import com.example.sami.labyrinthem2ihm.Modele.Bille;
import com.example.sami.labyrinthem2ihm.Modele.Block;
import com.example.sami.labyrinthem2ihm.Modele.Courbe;

import mesmaths.cinematique.Collisions;
import mesmaths.geometrie.base.Vecteur;

public class VisiteurCollision {
    private Collisions collisions;
    private Bille bille;
    public VisiteurCollision(Bille bille) {
        this.collisions = new Collisions();
        this.bille = bille;
    }


    public boolean collision(Block block){
        Vecteur P0 = new Vecteur();
        Vecteur P1 = new Vecteur();
        block.getSegmentBas(P0, P1);
        if(this.collisions.collisionBilleSegmentAvecRebond(new Vecteur(bille.getX(), bille.getY()), Bille.RAYON, bille.getVecteurVitesse(), P0,P1)){
            //si collision avec le bas du block
            Log.d("collision","bas");
        }
        block.getSegmentHaut(P0, P1);
        if(this.collisions.collisionBilleSegmentAvecRebond(new Vecteur(bille.getX(), bille.getY()), Bille.RAYON, bille.getVecteurVitesse(), P0,P1)){
            //si collision avec le haut du block
            Log.d("collision","haut");


        }
        block.getSegmentDroite(P0, P1);
        if(this.collisions.collisionBilleSegmentAvecRebond(new Vecteur(bille.getX(), bille.getY()), Bille.RAYON, bille.getVecteurVitesse(), P0,P1)){
            //si collision avec la droite du block
            Log.d("collision","droite");

        }
        block.getSegmentGauche(P0, P1);
        if(this.collisions.collisionBilleSegmentAvecRebond(new Vecteur(bille.getX(), bille.getY()), Bille.RAYON, bille.getVecteurVitesse(), P0,P1)){
            //si collision avec la gauche du block
            Log.d("collision","gauche");

        }
        return true;
    }

    public boolean collision(Courbe courbe){
        return true;
    }
}
