package com.example.helloworld;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Intent;

public class MyAppWidgetProvider extends AppWidgetProvider {

    AppWidgetManager mAppWidgetManager = context.getSystemService(AppWidgetManager.class);
    ComponentName myProvider = new ComponentName(context, MyAppWidgetProvider.class);

    if(mAppWidgetManager.isRequestPinAppWidgetSupported()){
        //create the PendingIntent object only if your app needs to be notified that the user allowed the widget to be pinned
        //Note that, if the pinning operation fails, your app isn't notified
        Intent pinnedWidgetCallbackIntent = new Intent();

        //Configure the intent so that your app's broadcast receiver gets the callback successfully
        //This callback receives the ID of the newly-pinned widget(EXTRA_APPWIDGET_ID
        PendingIntent successCallback = PendingIntent.createBroadcast(content, 0, pinnedWidgetCallbackIntent);

        mAppWidgetManager.requestPinAppWidget(myProvider, null,successCallback);

    }
}
