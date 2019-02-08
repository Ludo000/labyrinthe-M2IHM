package com.example.sami.labyrinthem2ihm.Vue;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.example.sami.labyrinthem2ihm.Modele.Bille;
import com.example.sami.labyrinthem2ihm.Modele.Block;
import com.example.sami.labyrinthem2ihm.Modele.Mur;
import com.example.sami.labyrinthem2ihm.R;

import java.util.List;
public class LabyrintheView extends SurfaceView implements SurfaceHolder.Callback {
    Bille mBoule;

    VisiteurDessinMur visiteurDessinMur;

    public Bille getBoule() {
        return mBoule;
    }

    public void setBoule(Bille pBoule) {
        this.mBoule = pBoule;
    }

    SurfaceHolder mSurfaceHolder;
    DrawingThread mThread;

    private List<Block> mBlocks = null;
    public List<Block> getBlocks() {
        return mBlocks;
    }

    public void setBlocks(List<Block> pBlocks) {
        this.mBlocks = pBlocks;
    }

    Paint mPaint;

    public LabyrintheView(Context pContext) {
        super(pContext);

        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        mThread = new DrawingThread();

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);

        mBoule = new Bille();
    }

    @Override
    protected void onDraw(Canvas pCanvas) {
        // Dessiner le fond de l'écran en premier
        Paint p=new Paint();
        visiteurDessinMur = new VisiteurDessinMur(pCanvas);
        Bitmap btip=BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        p.setColor(Color.RED);
        pCanvas.drawBitmap(btip, 0, 0, p);
       // pCanvas.drawPicture() .drawColor(Color.WHITE);
        // Pour tous les blocs du labyrinthe
        for(Mur mur : modele.getCurrentLevel().getMurs()) {
            // On crée un nouveau rectangle pour ne pas modifier celui du bloc
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
        //
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
