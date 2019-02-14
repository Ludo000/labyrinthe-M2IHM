package com.example.sami.labyrinthem2ihm.Vue;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.sami.labyrinthem2ihm.Modele.Block;
import com.example.sami.labyrinthem2ihm.Modele.Courbe;


public class VisiteurDessinMur {
    private Canvas canvas;
    private Paint paint;

    public VisiteurDessinMur(Canvas canvas, Paint paint) {
        this.canvas = canvas;
        this.paint = paint;
        this.paint.setStyle(Paint.Style.FILL);
    }

    public void dessin(Block block){
        this.paint.setColor(Color.RED);
        this.canvas.drawRect(block.getRectangle(), this.paint);
        Log.d("dessin bloock ======", block.getRectangle().toString());
    }

    public void dessin(Courbe courbe){

    }
}