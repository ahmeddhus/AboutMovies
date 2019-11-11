package com.ahmedelsayed.aboutmovies.view.customLayouts;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;

public class CustomLayoutManager extends LinearLayoutManager {

    private int mParentWidth;
    private int mItemWidth;

    public CustomLayoutManager(Context context, int orientation, boolean reverseLayout, int mParentWidth, int mItemWidth) {
        super(context, orientation, reverseLayout);
        this.mParentWidth = mParentWidth;
        this.mItemWidth = mItemWidth;
    }

    @Override
    public int getPaddingLeft() {
        return mParentWidth/6  ;
    }

    @Override
    public int getPaddingRight() {
        return getPaddingLeft();
    }
}
