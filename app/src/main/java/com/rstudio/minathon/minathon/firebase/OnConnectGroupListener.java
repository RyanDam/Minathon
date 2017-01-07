package com.rstudio.minathon.minathon.firebase;

/**
 * Created by Ryan on 1/7/17.
 */

public interface OnConnectGroupListener {
    void onSuccessful(Group g);
    void onFailure(Exception e);
}
