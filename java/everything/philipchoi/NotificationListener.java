package everything.philipchoi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashSet;

import static everything.philipchoi.Alarm.sharedPreferences;

public class NotificationListener extends NotificationListenerService {
        @Override
        public void onCreate() {
            System.out.println("LISTENED");
            Log.d("MYAPP", "Created");
        }

        @Override
        public IBinder onBind(Intent intent) {
            System.out.println("LISTENED");
            return super.onBind(intent);
        }

        @Override
        public void onNotificationPosted(StatusBarNotification sbn) {
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, Alarm.reminders);
                    Alarm.listView.setAdapter(adapter);
                    Alarm.edit = sharedPreferences.edit();
                    Alarm.set = new LinkedHashSet<>();
                    Alarm.set.addAll(Alarm.reminders);
                    Alarm.edit.putStringSet("Reminders", Alarm.set);
                    Alarm.edit.apply();
                }catch(Exception e){
                    e.printStackTrace();
                }
          //  }
        }

    }
