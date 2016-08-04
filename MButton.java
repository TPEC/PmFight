package com.dc.pmfight;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by XIeQian on 2016/8/2.
 */
public class MButton {
    private Rect posR;
    private float scalerW=1,scalerH=1;
    private Bitmap[] bmpButtonBG;
    private Bitmap bmpButton;
    private Paint paint,paintGray;
    private boolean enabled;
    private boolean clickdown;
    private String title;
    private boolean password;


    public MButton(int px,int py,int pw,int ph){
        posR=new Rect(px,py,px+pw,py+ph);
        paint=new Paint();
        paintGray=new Paint();
        ColorMatrix cm=new ColorMatrix();
        cm.setSaturation(0);
        paintGray.setColorFilter(new ColorMatrixColorFilter(cm));
        bmpButtonBG=new Bitmap[2];
        title="";
        password=false;
    }

    public void setScale(float sW, float sH) {
        scalerW=sW;
        scalerH=sH;
    }

    public void setTitle(String strtitle){
        title=strtitle;
    }

    public String getTitle(){
        return title;
    }

    public void setPassword(boolean blnpassword){
        password=blnpassword;
    }

    public void setbmpButton(){

    }

    public void setEnabled(boolean blnEnabled){
        enabled=blnEnabled;
    }

    public boolean isEnabled(){
        return enabled;
    }

    public boolean onTouchEvent(MotionEvent event){
        int tx,ty;
        if(enabled) {
            tx = (int) (event.getX() / scalerW);
            ty = (int) (event.getY() / scalerH);
            if (posR.contains(tx, ty)) {
                if(clickdown) {
                    if(event.getAction()==MotionEvent.ACTION_UP){
                        clickdown=false;
                        return true;
                    }
                }else{
                    if(event.getAction()==MotionEvent.ACTION_DOWN){
                        clickdown=true;
                        return false;
                    }
                }
                return true;
            }else{
                if(clickdown && event.getAction()==MotionEvent.ACTION_UP)
                    clickdown=false;
                return false;
            }
        }
        return false;
    }

    public void drawButton(Canvas canvas){
        if(enabled) {
            if(clickdown){
                canvas.drawBitmap(bmpButtonBG[1], posR.left * scalerW, posR.top * scalerH, paint);
                canvas.drawBitmap(bmpButton, (posR.left+posR.width()/16) * scalerW, (posR.top+posR.height()/16) * scalerH, paint);
            }else {
                canvas.drawBitmap(bmpButtonBG[0], posR.left * scalerW, posR.top * scalerH, paint);
                canvas.drawBitmap(bmpButton, posR.left * scalerW, posR.top * scalerH, paint);
            }
        }else{
            canvas.drawBitmap(bmpButtonBG[0], posR.left * scalerW, posR.top * scalerH, paintGray);
            canvas.drawBitmap(bmpButton, posR.left * scalerW, posR.top * scalerH, paintGray);
        }
        canvas.save();
        canvas.scale(scalerW,scalerH);
        if(title!="") {
            if(password) {
                String pw="";
                for(int i=0;i<title.length();i++)
                    pw=pw + "*";
                canvas.drawText(title, posR.left + posR.height() / 16, posR.bottom - posR.height() / 16, paint);
            }else
                canvas.drawText(title, posR.left + posR.height() / 16, posR.bottom - posR.height() / 16, paint);
        }
        canvas.restore();
    }
}
