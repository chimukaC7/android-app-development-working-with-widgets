package com.example.helloworld;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.content.Intent;
import android.app.PendingIntent;
import java.util.Random;

/**
 * Implementation of App Widget functionality.
 * The widget provider is where the logic and settings of the widgets are done.
 * In other words, in order to make your widget function you need to work with the provider classes.
 */
public class Test extends AppWidgetProvider {

    /*
    * widgets are the extensions of your application that you can install almost anywhere in your app to get a summarized view of an application.
    * They can be moved anywhere, and be resized as the users see fit.
    * Android documentation speaks of a few different types of widgets you can decide to develop.
    *  So let's explore these different categories.
    *
    * You have the information type, which provides a summary of information from the main application.
    * A weather app is a good example.
    *
    * Then you have control widgets, which allow you to do specific task in the widget.
    * For example, a to-do list widget would allow you to quickly add new to-do items.
    *
    * You also have a collection widget, which allows you to see a list of items inside of a widget.
    * For example, the list of to-dos or your emails.
    *
    * And in most cases, your widgets will be a combination of those.
    * So a to-do application widget most likely would have the current list of to-dos, and then also be able to add new items to the list with a button, therefor a hybrid widget as they call it.
    * */

    /*
    * you want to make sure you are improving the experience of the user by adding this widget
    * A widget can only support the touch and vertical swipe gestures. If your intended widget needs to do more types of gestures, then your idea isn't a good candidate for a widget, and you're better off investing in development time to the main application
    * */

    /*
    * The guidelines set forth by Android suggest the following items,
    *
    * the first one is content.
    * A widget's purpose is to present information about a application and promoting new content as it is updated on the application is crucial. But how you present this information needs to be well designed so the user is motivated to click on the app or leverage this information to make quick decision
    * Android uses the label of a snack versus meal approach. Whereas the widget provides a snack, the main application presents the meal
    *
    * The second one is navigation or functions.
    * As mentioned just now, providing the main actions of the application is important in the widget.
    * You want to provide some room in your design for this purpose.
    * You need to provide actions such as an Add New To Do button for you to do application, or a Play and Tap Next Song for your music application as well
    *
    * The third one is layout.
    * You need to think about the numerous different devices you need to support when creating your design.
    * What is shown at what sizes and allowing the user to resize your widget will go a long way into making your widget as flexible as possible with as many scenarios as possible.
    * Define your resize options and looks early on
    *
    * the final one is configuration.
    * Does your widget pull specific information that needs a setup of any kind in order to provide some information?
    * For example, what city you live in for a weather app.
    * Consider the initial steps of getting into your main application and if the user installs the widget and tries to use it before your application is set up properly, what are those steps required?
    * So you don't fail the user experience from the get-go.
    * */


    //this class is called to update the widget based on the milliseconds you set in the info file
    //update the widget
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int i = 0; i < appWidgetIds.length; i++) {
            String number = String.format("%03d", (new Random().nextInt(500)));//
            int appWidgetId = appWidgetIds[i];

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.test);
            views.setTextViewText(R.id.appwidget_text, number);

            //broadcasts intents i.e  indicate an action has been done and that we should update our widget
            Intent intent = new Intent(context, Test.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);//updates the widget, pretty much like what we had for the methods.
            //intent.setAction(AppWidgetManager.ACTION_APPWIDGET_ENABLED);//called when the widget is created.
            //intent.setAction(AppWidgetManager.ACTION_APPWIDGET_DISABLED);//called when a widget is removed.
            //intent.setAction(AppWidgetManager.ACTION_APPWIDGET_OPTIONS_CHANGED);//called when the widget is resized or placed on the screen,
            //intent.setAction(AppWidgetManager.ACTION_APPWIDGET_DELETED);//called when widget is deleted
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.button, pendingIntent);

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    /*
    * called the first time the widget is created.called when the widget is created
    * So whatever code you put in there will be executed when the user place a new widget on the screen
    * */
    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    /*
    *  which is when a user removes a widget from its screen
    * called when a widget is removed
    * */
    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        //For example, if you create a mini database or some (mumbles) data, upon creation, this is when you'd remove these items and use this method to do so
    }

    /*
    *this is a method that is called whenever the widget is resized or when placed on the screen
    * called when widget is resized or placed on the screen
    * */
//    @Override
//    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
//        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
//    }
}

