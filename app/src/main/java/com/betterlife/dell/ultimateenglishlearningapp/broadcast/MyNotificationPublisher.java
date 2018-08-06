package com.betterlife.dell.ultimateenglishlearningapp.broadcast;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.betterlife.dell.ultimateenglishlearningapp.helperclasses.FetchMeData;
import com.betterlife.dell.ultimateenglishlearningapp.R;
import com.betterlife.dell.ultimateenglishlearningapp.activities.datadetail;
import com.betterlife.dell.ultimateenglishlearningapp.modals.LevelData;

/**
 * Created by Dell on 7/17/2018.
 */

public class MyNotificationPublisher extends BroadcastReceiver {

    public static String NOTIFICATION_ID = "30";
    public static String NOTIFICATION = "notification";


    @Override
    public void onReceive(final Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = intent.getParcelableExtra(NOTIFICATION);
        int notificationId = intent.getIntExtra(NOTIFICATION_ID, 0);
        notificationManager.notify(notificationId, notification);
        long current_millisec = SystemClock.elapsedRealtime();
        Log.d("MYMSG", current_millisec + " courrent millisec");
        int notid = (int) (1 + (10000 - 1) * (Math.random()));
        scheduleNotification(context, (180*60000), notid);

    }

    public void scheduleNotification(Context context, long delay, int notificationId) {

        Intent intent = new Intent(context, datadetail.class);
        LevelData obj = new FetchMeData().fetchLevel(context);
        intent.putExtra("title", obj.title);
        intent.putExtra("id", obj.id);
        intent.putExtra("answer", obj.answer);
        intent.putExtra("additional", obj.additional);
        intent.putExtra("photo", obj.photo);
        intent.putExtra("fromnotification", "yes");



        Log.d("MYMSG", "in function");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle(obj.title)
                .setContentText(obj.answer)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher_round);

        PendingIntent activity = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(activity);
        Notification notification = builder.build();
        Intent notificationIntent = new Intent(context, MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, notificationId);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);


    }


}