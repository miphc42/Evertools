package everything.philipchoi;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.core.app.NotificationCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;

public class alertAlarm extends BroadcastReceiver {
    static NotificationHelper notificationHelper;
    static NotificationCompat.Builder nb;
    @Override
    public void onReceive(Context context, Intent intent) {
        notificationHelper = new NotificationHelper(context);
        nb = notificationHelper.getChannelNotification();
        Alarm alarm = new Alarm();
        System.out.println("AAAAAAAA");
        notificationHelper.getManager().notify(alarm.reminders2.indexOf((int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE)), alertAlarm.nb.build());
        Log.i("MYAPP", "Notification");
        // if(Alarm.c == Alarm.reminders.size()) {
        // System.out.println("LISTING"+Alarm.reminders);
        //Calendar date = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:00 z yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        String using = date.split("\\.")[0]+" "+date.split("\\.")[1].split(" ")[1]+date.split("\\.")[2];
        System.out.println("LISTENED");
        System.out.println(using);
        try {
            System.out.println(Alarm.reminders);
            int x = Alarm.reminders2.indexOf(using);
            System.out.println(x);
            Alarm.reminders.remove(x);
            Alarm.reminders2.remove(x);
            System.out.println("REMINDERS"+ Alarm.reminders);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, Alarm.reminders);
            Alarm.listView.setAdapter(adapter);
         //   Alarm.edit = sharedpreferences.edit();
            Alarm.set = new LinkedHashSet<>();
            Alarm.set.addAll(Alarm.reminders);
            Alarm.edit.putStringSet("Reminders", Alarm.set);
            Alarm.edit.apply();
        }catch(Exception e){
            e.printStackTrace();
        }
        //  }
        System.out.println("A"+(int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE));
        //  System.out.println(1);
    }
}
