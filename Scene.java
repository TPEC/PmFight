package com.dc.pmfight;

import android.graphics.Canvas;

/**
 * Created by XIeQian on 2016/7/31.
 */
public interface Scene {
    void setScale(float sW,float sH);
    void drawSV(Canvas canvas);
    void logicSV();
    boolean setCommand(String cmd);
    String getBackcmd();
}
