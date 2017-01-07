package com.rstudio.minathon.minathon.firebase;

/**
 * Created by Ryan on 1/7/17.
 */

public interface OnListenToGroup {
    void onGroupEnd(Group g);
    void onGroupAlarm(Group g);
    void onError(Exception e);
}
