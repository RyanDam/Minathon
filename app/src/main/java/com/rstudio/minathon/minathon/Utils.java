package com.rstudio.minathon.minathon;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

/**
 * Created by Ryan on 1/8/17.
 */

public class Utils {

    public static String NAME = "";

    public static Dialog getWaitingDialog(Activity c) {
        Dialog ret = new Dialog(c);
        LayoutInflater inf = ((LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        View v = inf.inflate(R.layout.waiting_dialog, null);
        ret.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ret.setContentView(v);
        ret.setCanceledOnTouchOutside(false);
        ret.getWindow().setDimAmount(0.0f);
        ret.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return ret;
    }
}
