package com.example.currencychange.Model;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.currencychange.R;
import com.example.currencychange.View.MainActivity;

public class NotificationUtils {
    private static final String MAIN_NOTIFY_ID = "notify_id";
    private static final int NOTIFY_EACH_MIN = 60;

    public  static void createMainChannel(Context context){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            String channelName = context.getString(R.string.notify_name);
            String channelDesc = context.getString(R.string.notify_desc);
            NotificationChannel mainChan = new NotificationChannel(MAIN_NOTIFY_ID ,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT);
            mainChan.setDescription(channelDesc);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(mainChan);

        }//end if
    }//end createMainChannel

    public static  void notifyRate(Context context){
        Intent intent = new Intent(context ,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,MAIN_NOTIFY_ID);
        String desc= "Rate from "+ SharedPrefrencesHelper.getFrom(context)
                + " to " + SharedPrefrencesHelper.getTo(context);
        if (SharedPrefrencesHelper.getStatus(context)) {
            builder.setSmallIcon(R.drawable.ic_baseline_arrow_circle_up_24);
            desc+= " is rising up to " ;
        }else{
            builder.setSmallIcon(R.drawable.ic_baseline_arrow_circle_down_24);
            desc+= " is falling down to " ;
        }

        desc+= SharedPrefrencesHelper.getRate(context) ;

        builder.setContentTitle("Currency Rate Changed");
        builder.setContentText(desc);
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(0,notification);

    }//notifyRate
}//end class
