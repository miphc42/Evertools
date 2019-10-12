package everything.philipchoi;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Alarm extends Fragment implements TimePickerDialog.OnTimeSetListener {
    private TextView time;
    View view;
    static int count;
    static int i;
    EditText title;
    EditText input;
    static Calendar date;
    static SwipeMenuListView listView;
    static ArrayAdapter<String> adapter;
    static ArrayList<String> reminders = new ArrayList<>();
    static ArrayList<String> reminders2 = new ArrayList<>();
    static Set<String> set = new LinkedHashSet<>();
  //  ArrayList<String> reminders = new ArrayList<>();
    Switch s;
    static int c;
    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor edit;
    private SwipeMenuCreator creator;

// set creator

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.alarm, container, false);
        getActivity().setTitle("Reminders");
        Intent intent = new Intent(getContext(), NotificationListener.class);
        Objects.requireNonNull(getContext()).startService(intent);
        FloatingActionButton button = view.findViewById(R.id.start);
        ImageButton del = view.findViewById(R.id.deleteButton);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Swipe Left To Delete", Toast.LENGTH_SHORT).show();
            }
        });
        creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                /*
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(170);
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);
                */

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(250);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }

        };
        TreeSet<String> sett2 = new TreeSet<>();
        sharedPreferences = getContext().getSharedPreferences("com.example.everything", Context.MODE_PRIVATE);
        Set<String> set1 = sharedPreferences.getStringSet("Reminders", sett2);
        List<String> sample = new ArrayList<>(set1);
        listView = view.findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, reminders);
        if(!sample.isEmpty()){
            reminders.addAll(sample);
            listView.setAdapter(adapter);
            listView.setMenuCreator(creator);
            listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                    //   listView.removeFooterView(s);
                    System.out.println(index);
                    reminders.remove(index);
                    AlarmManager alarmManager = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE); //GETACTIIVTY OR CONTEXT
                    Intent intent = new Intent(getContext(), alertAlarm.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(),position/* (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE)*/,intent, PendingIntent.FLAG_ONE_SHOT);
                    alarmManager.cancel(pendingIntent);
                  //  time.setText("Alarm Canceled");
                    c = reminders.size();
                    adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, reminders);
                    listView.setAdapter(adapter);
                    edit = sharedPreferences.edit();
                    set = new LinkedHashSet<>();
                    set.addAll(reminders);
                    edit.putStringSet("Reminders", set);
                    edit.apply();
                   // cancelAlarm();
                    return false;
                }
                // false : close the menu; true : not close the menu

            });
        }
        time = view.findViewById(R.id.text);
        title = view.findViewById(R.id.title);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Reminder");

                input = new EditText(getContext());
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.width = 150;
                lp.height = 500;
                lp.x=-170;
                lp.y=100;
                input.setLayoutParams(lp);
                //  input.setWidth(10);
                builder.setView(input);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        mgr.hideSoftInputFromWindow(input.getWindowToken(), 0);
                            showDateTimePicker();
                        }
                    });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
        return view;
    }

    private void showDateTimePicker(){
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();
        new DatePickerDialog(Objects.requireNonNull(getContext()), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);
                        date.set(Calendar.SECOND, 0);
                        NotificationHelper.title = input.getText().toString();
                       // time.setText(date.getTime().toString());
                        s = new Switch(getContext());
                        reminders2.add(date.getTime().toString());
                        reminders.add("\n"+date.getTime().toString()+ "\n\n"+input.getText().toString()+"\n");
                        startAlarm(date);
                     //   s.setText("\n\n\n"+date.getTime().toString()+"\n\n\n");
                     //   listView.addFooterView(s);
                        listView.setMenuCreator(creator);
                        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                                        //   listView.removeFooterView(s);
                                i = index;
                                c = reminders.size();
                                System.out.println(index);
                                reminders.remove(position);
                                reminders2.remove(position);
                                AlarmManager alarmManager = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE); //GETACTIIVTY OR CONTEXT
                                Intent intent = new Intent(getContext(), alertAlarm.class);
                                System.out.println(index);
                                PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(),index/* (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE)*/,intent, PendingIntent.FLAG_ONE_SHOT);
                                alarmManager.cancel(pendingIntent);
                            //    time.setText("Alarm Canceled");
                                listView.setAdapter(adapter);
                             //   cancelAlarm();
                                return false;
                                }
                                // false : close the menu; true : not close the menu

                            });
                        System.out.println(date.getTime().toString());
                        listView.setAdapter(adapter);
                        edit = sharedPreferences.edit();
                        set = new LinkedHashSet<>();
                        set.addAll(reminders);
                        edit.putStringSet("Reminders", set);
                        edit.apply();
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void startAlarm(Calendar c){
        c.getTime();
        AlarmManager alarmManager = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE); //GETACTIIVTY OR CONTEXT
        Intent intent = new Intent(getContext(), alertAlarm.class);
        System.out.println(reminders2.indexOf(date.getTime().toString()));
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(),reminders2.indexOf(date.getTime().toString()) ,intent, PendingIntent.FLAG_ONE_SHOT);
        //alarmManager.set(AlarmManager.RTC, date.getTimeInMillis(), pendingIntent);
        assert alarmManager != null;
        System.out.println("ALARMMM START" +
                "" +
                "TEEEEEEEEEEEEEEEEEEEED");
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
       /* new NotificationListenerService() {
            @Override
            public void onNotificationPosted(StatusBarNotification sbn, RankingMap rankingMap) {
                System.out.println("AAAAAAA");
                Alarm.reminders2.remove((int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE));
                super.onNotificationPosted(sbn, rankingMap);
            }
        };
        */
        System.out.println("REMINDER LIST\t"+reminders);
        System.out.println("AA");
    }
    @SuppressLint("SetTextI18n")
    private void cancelAlarm(){
        AlarmManager alarmManager = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE); //GETACTIIVTY OR CONTEXT
        Intent intent = new Intent(getContext(), alertAlarm.class);
        //PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(),/* (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE)*/,intent, PendingIntent.FLAG_ONE_SHOT);
       // alarmManager.cancel(pendingIntent);
        time.setText("Alarm Canceled");
    }
}
