package everything.philipchoi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

public class You extends Fragment {
    static SharedPreferences prefs;
    static SharedPreferences.Editor edit;
    static ListView listView;
    static CheckBox checkBox;
    ImageButton addButton;
    static ImageButton deleteButton;
    static ArrayList<String> l;
    static Set<String> set1;
//    static TreeSet<String> set3;
    Boolean delete = false;
    static CheckBox[] boxes;
    static SharedPreferences preferences;
    ArrayList<CheckBox> checks;
    int temp;
    boolean check;
    static Set<String> set2;
    static View view;
    static ArrayAdapter<String> adapter;
    static CheckBox[] boxes2;
    static ArrayList<CheckBox> boxes3 = new ArrayList<>();
    static CompoundButton q;
    static TreeMap<Integer, Boolean> delMap;
    ArrayList<String> newList;
    static CheckBox c;

    public void listAdapt() {

    }

    public void allChange() {

    }

    public void update() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.you, container, false);
        Intent intent = new Intent(getContext(), NotificationListener.class);
        Objects.requireNonNull(getContext()).startService(intent);
      /*  Button b = view.findViewById(R.id.to);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.fragments,
                        new Alarm()).commit();
            }
        });*/
        prefs = getContext().getSharedPreferences("com.example.everything", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonText = prefs.getString("key", null);
        String[] text = gson.fromJson(jsonText, String[].class);
        set2 = new LinkedHashSet<>();
        l = new ArrayList<>();
        ArrayList<String> sample = new ArrayList<>();
        if(text!=null) {
            for (int i = 0; i < text.length; i++) {
                l.add(text[i]);
            }
       /*
        Set<String> set = prefs.getStringSet("Set", set2);
        System.out.println("SETTT "+set);*/
            sample = new ArrayList<>(l);
            l.clear();
        }
        if (!sample.isEmpty()) {
            l.addAll(sample);
            System.out.println("SIZE: --> " + l.size());
        } else {
            //l.add("\n" + "\n" + "Your Goals" + "\n" + "\n");
        }
        preferences = getContext().getSharedPreferences("com.example.everything", Context.MODE_PRIVATE);
        getActivity().setTitle("You");
        listView = view.findViewById(R.id.listView);
        addButton = view.findViewById(R.id.addGoal);
        deleteButton = view.findViewById(R.id.deleteGoal);
        delMap = new TreeMap<>();
        boxes = new CheckBox[l.size()+1];
        boxes2 = new CheckBox[l.size()+1];
        for (int i = 0; i < l.size(); i++) {
            check = preferences.getBoolean(Integer.toString(i), false);
            checkBox = new CheckBox(getContext());
            checkBox.setText(l.get(i));
            checkBox.setTag(i);
            checkBox.setId(i);
            boxes[i] = checkBox;
            boxes2[i] = checkBox;
            boxes3.add(checkBox);
          //  boxes2.add(checkBox);
            delMap.put(checkBox.getId(), check);
            listView.addFooterView(checkBox);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(final CompoundButton compoundButton, boolean b) {
                    System.out.println("ID:" + compoundButton.getId());
                    if (compoundButton.isChecked()) {
                        delMap.put(compoundButton.getId(), true);
                        System.out.println("CHECKED");
                        preferences.edit().putBoolean(Integer.toString(compoundButton.getId()), true).apply();
                    } else {
                        delMap.put(compoundButton.getId(), false);
                        System.out.println("THETHE THE");
                        preferences.edit().putBoolean(Integer.toString(compoundButton.getId()), false).apply();
                    }
                    // listView.removeAllViewsInLayout();
                                    /*
                                    for (int i = 0; i < l.size(); i++) {
                                        check = preferences.getBoolean(Integer.toString(i), false);
                                        checkBox = new CheckBox(getContext());
                                        checkBox.setText(l.get(i));
                                        checkBox.setId(i);
                                        if (!check)
                                            delMap.put(checkBox.getId(), false);
                                        else
                                            delMap.put(checkBox.getId(), true);
                                        listView.addFooterView(checkBox);
                                        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                            @Override
                                            public void onCheckedChanged(final CompoundButton compoundButton, boolean b) {
                                                System.out.println("ID:" + compoundButton.getId());
                                                if (compoundButton.isChecked()) {
                                                    delMap.put(compoundButton.getId(), true);
                                                    System.out.println("CHECKED");
                                                    preferences.edit().putBoolean(Integer.toString(compoundButton.getId()), true).apply();
                                                    //listView.removeFooterView(compoundButton);
                                                }
                                            }
                                        });
                                    }*/
                                    /*
                    System.out.println("SIZE2" + l.size());
                    edit = preferences.edit();
                    set1 = new HashSet<>();
                    System.out.println("list" + l);
                    set1.addAll(l);
                    edit.putStringSet("Set", set1);
                    edit.apply();
                    */
                }
                });
            if (check) {
                checkBox.setChecked(true);
            } else {
                //System.out.println(checkBox.getId());
                checkBox.setChecked(false);
            }
         //   listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        }
        System.out.println("MAP"+delMap);
        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_multiple_choice);
        listView.setAdapter(adapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpEditText();
            }
        });
        newList = new ArrayList<>();
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("clc");
                try {
                    int counter = 0;
                    int size = l.size();
                    for (int c = 0; c < l.size(); c++) {
                        boolean x = delMap.get(c);
                        //System.out.println(c);
                        listView.removeFooterView(boxes[c]);
                        System.out.println("LIST ITEM"+c+l.get(counter));
                        if (!x) {
                                System.out.println("C      " + c);
                                System.out.println("BEfore" + l.size());
                                newList.add(l.get(c));
                                l.trimToSize();
                                System.out.println("LIST SIZE CURR" + l.size());
                                System.out.println("After" + l.size());
                                System.out.println("SIZE:" + l.size());
                                System.out.println("yee" + c);
                            }
                    }
                    System.out.println("LIST: " + newList);
                    for(int i = 0; i < newList.size(); i++){
                        System.out.println("THE NEW SIZE  "+newList.size());
                        c = new CheckBox(getContext());
                        c.setText(newList.get(i));
                       // boxes[i] = c;
                        listView.addFooterView(c);
                        c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (compoundButton.isChecked()) {
                                    delMap.put(compoundButton.getId(), true);
                                    System.out.println("CHECKED");
                                    preferences.edit().putBoolean(Integer.toString(compoundButton.getId()), true).apply();
                                } else {
                                    delMap.put(compoundButton.getId(), false);
                                    System.out.println("THETHE THE");
                                    preferences.edit().putBoolean(Integer.toString(compoundButton.getId()), false).apply();
                                }
                            }
                        });
                    }
                   // adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_multiple_choice);
                   // listView.setAdapter(adapter);
                    /*
                    edit = preferences.edit();
                    set1 = new LinkedHashSet<>();
                    System.out.println("list" + newList);

                    set1.addAll(newList);
                    edit.putStringSet("Set", set1);
                    edit.apply();*/
                    l = new ArrayList<>(newList);
                    Gson gson = new Gson();
                    edit = preferences.edit();
                    List<String> textList = new ArrayList<>(newList);
                    System.out.println(newList);
                    String jsonText = gson.toJson(textList);
                    System.out.println("JSON TEXT: "+ jsonText);
                    edit.putString("key", jsonText);
                    edit.apply();
                }catch (Exception e){
                    e.printStackTrace();}
            }
        });

        return view;
    }
    private void popUpEditText() {
        customDialog customDialog = new customDialog();
        customDialog.show(getFragmentManager(), "Dialog");
    }
}

        //AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        /*
        builder.setTitle("Goal");
        input = new EditText(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        input.setLayoutParams(lp);
        builder.setView(input);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                l.add("\n" + "\n" + input.getText().toString() + "\n" + "\n");
                checkBox = new CheckBox(getContext());
                checkBox.setText(l.get(l.size() - 1));
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        System.out.println("ID:" + (l.size() - 1));
                        if (checkBox.isChecked()) {
                            System.out.println("CHECKED PERMA");
                            preferences.edit().putBoolean(Integer.toString(l.size() - 1), true).apply();
                        } else {
                            System.out.println("CHECKED DEL");
                            preferences.edit().putBoolean(Integer.toString(compoundButton.getId()), false).apply();
                        }
                    }
                });
                listView.addFooterView(checkBox);
                edit = prefs.edit();
                set = new HashSet<>();
                set.addAll(l);
                edit.putStringSet("Set", set);
                edit.apply();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
        */


/*
        deleteButton.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        System.out.println("clc");
        try {
        for(int c = 0; c<l.size(); c++){
        boolean x = delMap.get(c);
        System.out.println(c);
        if(x){
        l.remove(c);
        l.trimToSize();
        System.out.println("SIZE:"+l.size());
        System.out.println("yee"+c);
        listView.removeFooterView(compoundButton);
        }
        }
*/



