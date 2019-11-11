package com.ahmedelsayed.aboutmovies.view.customLayouts;

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;

public class CustomLayoutSeeAll extends GridLayoutManager {

    private ImageView laoder;
    Context context;

    public CustomLayoutSeeAll(Context context, int spanCount, int orientation, boolean reverseLayout, ImageView laoder) {
        super(context, spanCount, orientation, reverseLayout);
        this.laoder = laoder;
        this.context = context;
    }

    @Override
    public int getPaddingBottom() {
        return laoder.getHeight()+40;
    }
}
