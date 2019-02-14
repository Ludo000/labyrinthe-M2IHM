package com.example.sami.labyrinthem2ihm.Vue;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.sami.labyrinthem2ihm.Controller.Controleur;
import com.example.sami.labyrinthem2ihm.Modele.Bille;
import com.example.sami.labyrinthem2ihm.Modele.Block;
import com.example.sami.labyrinthem2ihm.Modele.Modele;
import com.example.sami.labyrinthem2ihm.Modele.Mur;
import com.example.sami.labyrinthem2ihm.R;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class LabyrintheView extends SurfaceView implements SurfaceHolder.Callback, Observer {
    Bille mBoule;

    VisiteurDessinMur visiteurDessinMur;

    public Modele modele;
    SurfaceHolder mSurfaceHolder;
    DrawingThread mThread;
    Canvas canvas;

    public List<Mur> murs;
    public Controleur controleur;

    Paint mPaint;

    public LabyrintheView(Context pContext, Controleur controleur) {
       super(pContext);
        this.controleur = controleur;
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        mThread = new DrawingThread();

        modele = new Modele();

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);

        mBoule = modele.getBille();
    }

    @Override
    protected void onDraw(Canvas pCanvas) {
        // Dessiner le fond de l'écran en premier
        Paint p= new Paint();
        visiteurDessinMur = new VisiteurDessinMur(pCanvas);
        Bitmap btip=BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        p.setColor(Color.RED);
        pCanvas.drawBitmap(btip, 0, 0, p);
       // pCanvas.drawPicture() .drawColor(Color.WHITE);
        // Pour tous les blocs du labyrinthe
        // On crée un nouveau rectangle pour ne pas modifier celui du bloc
        for(Mur mur : modele.getCurrentLevel().getMurs()) {
            mur.accept(visiteurDessinMur);
        }

        // Dessiner la boule
        if(mBoule != null) {

            mPaint.setColor(mBoule.getCouleur());
            pCanvas.drawCircle(mBoule.getX(), mBoule.getY(), Bille.RAYON, mPaint);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder pHolder, int pFormat, int pWidth, int pHeight) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder pHolder) {
        mThread.keepDrawing = true;
        mThread.start();
        // Quand on crée la boule, on lui indique les coordonnées de l'écran
        if(mBoule != null ) {
            this.mBoule.setHeight(getHeight());
            this.mBoule.setWidth(getWidth());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder pHolder) {
        mThread.keepDrawing = false;
        boolean retry = true;
        while (retry) {
            try {
                mThread.join();
                retry = false;
            } catch (InterruptedException e) {}
        }

    }

    @Override
    public void update(Observable observable, Object o) {
    }

    public Canvas getCanvas(){
        return canvas;
    }

    private class DrawingThread extends Thread {
        boolean keepDrawing = true;

        @Override
        public void run() {
            while (keepDrawing) {
                canvas = null;

                try {
                    canvas = mSurfaceHolder.lockCanvas();
                    synchronized (mSurfaceHolder) {
                        onDraw(canvas);
                    }
                } finally {
                    if (canvas != null)
                        mSurfaceHolder.unlockCanvasAndPost(canvas);
                }

                // Pour dessiner à 50 fps
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {}
            }
        }
    }
}
