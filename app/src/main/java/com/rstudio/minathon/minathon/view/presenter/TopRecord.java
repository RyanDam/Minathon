package com.rstudio.minathon.minathon.view.presenter;

/**
 * Created by dkkbg_000 on 07/01/2017.
 */

public class TopRecord {
    private String Time;
    private String Date;

    public String getTime() {
        return Time;
    }

    public String getDate() {
        return Date;
    }

    public TopRecord(String time, String date) {

        Time = time;
        Date = date;
    }
}
