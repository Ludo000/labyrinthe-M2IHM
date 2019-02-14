package com.example.sami.labyrinthem2ihm.Vue;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.sami.labyrinthem2ihm.Modele.Bille;
import com.example.sami.labyrinthem2ihm.Modele.Modele;
import com.example.sami.labyrinthem2ihm.Modele.Mur;
import com.example.sami.labyrinthem2ihm.R;
public class LabyrintheView extends SurfaceView implements SurfaceHolder.Callback {
    VisiteurDessinMur visiteurDessinMur;
    public Modele modele;

    SurfaceHolder mSurfaceHolder;
    DrawingThread mThread;

    Paint mPaint;

    public LabyrintheView(Context pContext, Modele modele) {
        super(pContext);
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        mThread = new DrawingThread();

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        this.modele= modele;
    }
    @Override
    protected void onDraw(Canvas pCanvas) {
        // Dessiner le fond de l'écran en premier
        Paint p= new Paint();
        visiteurDessinMur = new VisiteurDessinMur(pCanvas, mPaint);
        Bitmap btip=BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        p.setColor(Color.RED);
        pCanvas.drawBitmap(btip, 0, 0, p);

        for(Mur mur : modele.getCurrentLevel().getMurs()) {
            mur.accept(visiteurDessinMur);
        }

        // Dessiner la boule
        if(modele.getBille() != null) {

            mPaint.setColor(modele.getBille().getCouleur());
            pCanvas.drawCircle(modele.getBille().getX(), modele.getBille().getY(), Bille.RAYON, mPaint);
            Log.d("coor===>", (modele.getBille().getX() + ", "+ modele.getBille().getY()));
        }
        else {
            Log.d("TEST===>", "ERRORR");
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder pHolder, int pFormat, int pWidth, int pHeight) {
        //
    }

    @Override
    public void surfaceCreated(SurfaceHolder pHolder) {
        mThread.keepDrawing = true;
        mThread.start();
        // Quand on crée la boule, on lui indique les coordonnées de l'écran
        if(modele.getBille() != null ) {
            modele.getBille().setHeight(getHeight());
            modele.getBille().setWidth(getWidth());
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

    private class DrawingThread extends Thread {
        boolean keepDrawing = true;

        @Override
        public void run() {
            Canvas canvas;
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
