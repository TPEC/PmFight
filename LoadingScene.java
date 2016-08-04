package com.dc.pmfight;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by XIeQian on 2016/8/2.
 */
public class LoadingScene implements Scene {
    private float scalerW=1,scalerH=1;
    private Paint paint;
    private Bitmap bmpBG;
    private int sceneState;//00-null,01-loading
    private String backcmd;

    private MButton btnLogin;
    private MButton btnSignup;
    private MButton txtUsername;
    private MButton txtPassword;

    public LoadingScene(){
        btnLogin=new MButton(0,0,0,0);
        btnSignup=new MButton(0,0,0,0);
        txtUsername=new MButton(0,0,0,0);
        txtPassword=new MButton(0,0,0,0);
        txtPassword.setPassword(true);
        backcmd="";
        sceneState=0;
    }

    @Override
    public void setScale(float sW, float sH) {
        scalerW=sW;
        scalerH=sH;
    }

    @Override
    public void drawSV(Canvas canvas) {
        canvas.drawBitmap(bmpBG,0,0,paint);

    }

    @Override
    public boolean setCommand(String cmd){
        if(cmd.length()>0) {
            String[] c = cmd.split(":");
            switch (c[0]) {
                case "load":
                    sceneState=1;
                    break;
                case "login":
                    sceneState=11;
                    break;
                default:
                    return false;
            }
            return true;
        }else
            return false;
    }

    @Override
    public String getBackcmd(){
        String bcmd=backcmd;
        backcmd="";
        return bcmd;
    }

    @Override
    public void logicSV() {

    }
}
