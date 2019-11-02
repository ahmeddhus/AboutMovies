package com.ahmedelsayed.aboutmovies.basics;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.instabug.bug.BugReporting;
import com.instabug.library.Instabug;
import com.instabug.library.InstabugColorTheme;
import com.instabug.library.InstabugCustomTextPlaceHolder;
import com.instabug.library.invocation.InstabugInvocationEvent;
import com.instabug.library.ui.onboarding.WelcomeMessage;

import java.util.Locale;

public class SampleApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        new Instabug.Builder(this, "65d4395f58b8107a67f66c7638753787")
                .setInvocationEvents(InstabugInvocationEvent.SHAKE, InstabugInvocationEvent.SCREENSHOT,
                        InstabugInvocationEvent.FLOATING_BUTTON, InstabugInvocationEvent.TWO_FINGER_SWIPE_LEFT)
                .build();

        Instabug.setColorTheme(InstabugColorTheme.InstabugColorThemeLight);

        Instabug.setWelcomeMessageState(WelcomeMessage.State.LIVE);

        Instabug.setLocale(Locale.ENGLISH);

        //To show instabug debug logs
        Instabug.setDebugEnabled(true);

        //Settings custom strings to replace instabug's strings
        InstabugCustomTextPlaceHolder placeHolder = new InstabugCustomTextPlaceHolder();
        placeHolder.set(InstabugCustomTextPlaceHolder.Key.REPORT_FEEDBACK, "Send Feedback");
        placeHolder.set(InstabugCustomTextPlaceHolder.Key.REPORT_BUG, "Send Bug Report");

        Instabug.setCustomTextPlaceHolders(placeHolder);

        //setting user attributes
        Instabug.setUserAttribute("USER_TYPE", "instabug user");

        BugReporting.setAutoScreenRecordingEnabled(true);
    }
}
