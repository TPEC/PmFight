package com.dc.pmfight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by XIeQian on 2016/7/27.
 */
public class MainSV extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder sh;
    private Thread th;
    private boolean flag;
    private Paint paint;
    private int FPS;
    private float scalerW,scalerH;
    private Canvas canvas;
    private int SceneState;//1-loading.loading,2-loading.login,3-loading.Select,11-main

    private Socket socket;
    private SocketAddress socketAddress;


    public MainSV(Context context){
        super(context);
        sh=this.getHolder();
        sh.addCallback(this);
        paint=new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(20);

        try {
            socket = new Socket();
            socketAddress = new InetSocketAddress("127.0.0.1", 8070);
        }catch (Exception e){

        }

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        flag=true;
        th=new Thread(this);
        th.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        scalerW=width/1280;
        scalerH=height/720;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag=false;
    }

    public void logicSV(){

    }

    public void drawSV(){
        try{
            canvas=sh.lockCanvas();
            if(canvas!=null){
                canvas.save();
                canvas.scale(scalerW,scalerH);
                canvas.drawText("FPS:"+String.valueOf(FPS)+"  "+String.valueOf(scalerW)+"*"+String.valueOf(scalerH),0,30,paint);
                canvas.restore();
            }
        } catch (Exception e){

        } finally {
            if(canvas!=null)
                sh.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void run() {
        while(flag){
            long tinterval=System.currentTimeMillis();
            logicSV();
            drawSV();
            tinterval=System.currentTimeMillis()-tinterval;
            try{
                if(tinterval<33){
                    FPS=30;
                    th.sleep(33-tinterval);
                }
                else{
                    FPS=1000/(int)tinterval;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
