package com.ahmedelsayed.aboutmovies.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class HelperMethods {

    public static int Count(Context context){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (dpWidth / 150);
    }

}
