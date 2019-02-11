package com.example.sami.labyrinthem2ihm.Vue;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sami.labyrinthem2ihm.Controller.Controleur;
import com.example.sami.labyrinthem2ihm.Modele.Bille;
import com.example.sami.labyrinthem2ihm.Modele.Block;
import com.example.sami.labyrinthem2ihm.Modele.Level;
import com.example.sami.labyrinthem2ihm.Modele.Modele;
import com.example.sami.labyrinthem2ihm.Modele.Mur;

import java.util.List;

import static com.example.sami.labyrinthem2ihm.Modele.Block.Type.TROU;

public class LevelsActivity extends AppCompatActivity {
    // Identifiant de la boîte de dialogue de victoire
    public static final int VICTORY_DIALOG = 0;
    // Identifiant de la boîte de dialogue de défaite
    public static final int DEFEAT_DIALOG = 1;

    // Le moteur graphique du jeu
    private LabyrintheView mView = null;
    // Le moteur physique du jeu
    private Controleur controleur = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mView = new LabyrintheView(this);
        setContentView(mView);

        Bille b = new Bille();
        mView.setBoule(b);
        controleur.setBoule(b);

        List<Mur> mList = controleur.get_modele().getCurrentLevel().getMurs();
        mView.setBlocks(mList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        controleur.resume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        controleur.stop();
    }

    @Override
    public Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (id) {
            case VICTORY_DIALOG:
                builder.setCancelable(false)
                        .setMessage("Bravo, vous avez gagné !")
                        .setTitle("T'es un Champion :D !")
                        .setNeutralButton("Recommencer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // L'utilisateur peut recommencer s'il le veut
                                controleur.reset();
                                controleur.resume();
                            }
                        });
                break;

            case DEFEAT_DIALOG:
                builder.setCancelable(false)
                        .setMessage("Réessaye encore une fois.")
                        .setTitle("Oh Nooooo !")
                        .setNeutralButton("Recommencer", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                controleur.reset();
                                controleur.resume();
                            }
                        });
        }
        return builder.create();
    }

    @Override
    public void onPrepareDialog(int id, Dialog box) {
        // A chaque fois qu'une boîte de dialogue est lancée, on arrête le moteur physique
        controleur.stop();
    }
}

