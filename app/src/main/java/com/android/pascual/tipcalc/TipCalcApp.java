package com.android.pascual.tipcalc;

import android.app.Application;

/**
 * Created by pascual on 6/1/2016.
 */
public class TipCalcApp extends Application {
    //agregar la url en variable
    private final static String ABOUT_URL = "https://plus.google.com/110138589980029092055";

    //agregar getter
    public String getAboutUrl() {
        return ABOUT_URL;
    }
}
