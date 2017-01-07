package com.rstudio.minathon.minathon.view.custom;

/**
 * Created by Ryan on 1/7/17.
 */

public interface CircleTimeListener {
    void onClockStart();
    void onClockEnd();
    void onClockUpdate(float progress);
}
