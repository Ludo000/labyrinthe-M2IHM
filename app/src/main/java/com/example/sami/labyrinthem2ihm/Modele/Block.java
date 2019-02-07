package com.example.sami.labyrinthem2ihm.Modele;

import mesmaths.geometrie.base.Vecteur;
import mesmaths.geometrie.formes.Polygone;

public class Block extends Polygone  implements Mur{
    public Block(Vecteur[] vecteurs) {
        super(vecteurs);
    }
}
