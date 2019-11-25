package com.ahmedelsayed.aboutmovies.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmedelsayed.aboutmovies.R;

public class HelperMethods {

    public static int Count(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (dpWidth / 150);
    }


    public static void NoConnectionDialog(final Context context, final Runnable fun) {

        if (context == null) {
            return;
        }

        final Dialog dialog = new Dialog(context, R.style.Theme_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.no_connection_dialog);
        ImageView imageView = dialog.findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.ic_no_connection);
        dialog.setCancelable(false);
        TextView retry = dialog.findViewById(R.id.retry_button);
        retry.setOnClickListener(view -> {
            fun.run();
            dialog.dismiss();
        });
        TextView exit = dialog.findViewById(R.id.exit);
        exit.setOnClickListener(view -> ((Activity) context).finish());
        dialog.show();
    }

    public static boolean IsConnected(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

    }
}
