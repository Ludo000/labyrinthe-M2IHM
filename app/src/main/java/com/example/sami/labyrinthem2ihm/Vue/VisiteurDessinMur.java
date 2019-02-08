package com.example.sami.labyrinthem2ihm.Vue;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.sami.labyrinthem2ihm.Modele.Block;
import com.example.sami.labyrinthem2ihm.Modele.Courbe;


public class VisiteurDessinMur {
    private Canvas canvas;
    private Paint paint;

    public VisiteurDessinMur(Canvas canvas) {
        this.canvas = canvas;
        this.paint = new Paint();
        this.paint.setStyle(Paint.Style.FILL);
    }

    public void dessinBlock(Block block){

        this.canvas.drawRect(block.getRectangle(), this.paint);
    }

    public void dessinCourbe(Courbe courbe){

    }
}
