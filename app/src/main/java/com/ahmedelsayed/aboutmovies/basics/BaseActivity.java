package com.ahmedelsayed.aboutmovies.basics;

import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.instabug.library.InstabugTrackingDelegate;

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //To allow instabug to track user steps
        // and also add touches to screen recording
        InstabugTrackingDelegate.notifyActivityGotTouchEvent(ev, this);
        return super.dispatchTouchEvent(ev);
    }
}
