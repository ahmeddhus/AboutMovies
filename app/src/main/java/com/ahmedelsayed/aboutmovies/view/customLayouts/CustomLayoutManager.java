package com.ahmedelsayed.aboutmovies.view.customLayouts;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;

public class CustomLayoutManager extends LinearLayoutManager {

    private int mParentWidth;

    public CustomLayoutManager(Context context, int orientation, boolean reverseLayout, int mParentWidth) {
        super(context, orientation, reverseLayout);
        this.mParentWidth = mParentWidth;
    }

    @Override
    public int getPaddingLeft() {
        return mParentWidth/4  ;
    }

    @Override
    public int getPaddingRight() {
        return getPaddingLeft();
    }
}
