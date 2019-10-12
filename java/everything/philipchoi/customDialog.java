package everything.philipchoi;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class customDialog extends AppCompatDialogFragment {
    static EditText input;
    ArrayAdapter<String> adapter;
    private dialogListener listner;
    static String goal;
    static int p;
    int t[];
    You you;
    CheckBox[] cb;
    CheckBox c;
    int x;
    int z;
    int index;
    int counter = 0;
    //CheckBox boxes[];
    ArrayList<String> newList;
    private Context mContext;

    // Initialise it from onAttach()

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listner =(dialogListener) context;
            mContext = context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+ "must implement dialog listener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = getActivity().getLayoutInflater();
      //  index = You.boxes2.size();
        final View view = inflater.inflate(R.layout.custom_dialog, null);
        You.set2 = new LinkedHashSet<>();
        You.prefs = getContext().getSharedPreferences("com.example.everything", Context.MODE_PRIVATE);
        Set<String> set = You.prefs.getStringSet("Set", You.set2);
        ArrayList<String> sample = new ArrayList<>(set);
        if (!sample.isEmpty()) {
            You.l.addAll(sample);
            System.out.println("SIZE: --> " + You.l.size());
        } else {
            //l.add("\n" + "\n" + "Your Goals" + "\n" + "\n");
        }
        input = view.findViewById(R.id.goal_input);
        x = 0;
       // counter = 0;
        builder.setView(view)
                .setTitle("Title")
                .setMessage("Message")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getDialog().cancel();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @SuppressLint("CommitPrefEdits")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        t = new int[You.l.size()+2];
                       // You.boxes = new CheckBox[You.l.size()+1];
                        you = new You();
                        System.out.println();
                       // System.out.println("SIZE ON CLICK"+ boxes);
                        for(int y = 0; y < You.l.size(); y++){
                            x++;
                            if(You.l.size()==0){
                                x=0;
                            }
                            else if(You.l.size()==1){
                                x = 1;
                            }
                        }
                        for(int y = 0; y < counter; y++){
                            z++;
                            if(You.l.size()==0){
                                z=0;
                            }
                        }
                        counter++;
                        You.l.add("\n" + "\n" + input.getText().toString() + "\n" + "\n");
                        System.out.println("LIST : : :  :"+ You.l);
                       // boolean check = You.preferences.getBoolean(Integer.toString(x), false);
                        System.out.println(x);
                        System.out.println("SIZE"+ You.l.size());
                        t[x] = x;
                        /*
                        if(check) {
                            You.delMap.put(t[x], true);
                            System.out.println("No");
                        }
                        else{
                        */

                        System.out.println("correct");
                        You.checkBox = new CheckBox(getContext());
                        You.checkBox.setText(You.l.get(You.l.size() - 1));
                        System.out.println("T IS" + t[x]);
                        System.out.println("X IS" + x);
                        System.out.println("L IS" + You.l.size());
                       // You.boxes[x] = You.checkBox;
                        You.listView.addFooterView(You.checkBox);
                      //  You.boxes.add(You.checkBox);
                      //  You.boxes2.add(You.checkBox);
                    //    You.boxes2[x] = You.checkBox;
                        You.boxes3.add(You.checkBox);
                        You.delMap.put(t[x], false);
                        System.out.println(You.l);
                        System.out.println(You.delMap);
                        //System.out.println("ARRAY IS : " + You.boxes2+ You.boxes2.size());
                      //  System.out.println("SIZE"+You.boxes2.size());
                        You.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (compoundButton.isChecked()) {
                                         compoundButton.setId(t[x]);
                                    System.out.println("ID FIRST: "+compoundButton.getId());
                                        System.out.println("True" + (t[x]));
                                        You.delMap.put(t[x], true);
                                    System.out.println("T OF X: "+t[x]);
                                    System.out.println(You.delMap);
                                        You.preferences.edit().putBoolean(Integer.toString(t[x]), true).apply();
                                        /*
                                    You.deleteButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            System.out.println("clc");
                                            try {
                                                for(int c = 0; c<You.l.size(); c++) {
                                                    boolean o = You.delMap.get(c);
                                                    System.out.println(c);
                                                    if (o) {

                                                        System.out.println("Pos" + c);
                                                        You.l.remove(c);
                                                        You.l.trimToSize();
                                                        System.out.println("SIZE:" + You.l.size());
                                                        You.listView.removeAllViewsInLayout();

                                                    }
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });/
                        */
                                } else {
                                    System.out.println("CHECKED DEL");
                                    You.delMap.put(t[x], false);
                                    System.out.println("T OF X: "+t[x]);
                                    System.out.println(You.delMap);
                                    You.preferences.edit().putBoolean(Integer.toString(t[x]), false).apply();
                                }
                            }
                        });
                        newList = new ArrayList<>();
                You.deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println("clc");
                     //   System.out.println("ARRAY SIZE"+ You.boxes2.size());
                        try {
                            int counter = 1;
                            int size = You.l.size();
                            System.out.println("MAP"+ You.delMap);
                            /*
                            if(You.listView.getFooterViewsCount()==1){
                                You.delMap.clear();
                                newList.clear();
                                You.l.clear();
                                System.out.println("DONE");
                                You.listView.removeFooterView(You.boxes2.get(0));
                                You.boxes2.clear();
                                System.out.println(You.listView.getFooterViewsCount());
                            }
                            else {
                            */  int count = You.l.size();
                            if(!newList.isEmpty())
                                newList.clear();

                          //  ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_list_item_1);
                                for (int c = 0; c < count; c++) {
                                    System.out.println(You.delMap.size());
                                    boolean x = You.delMap.get(c);
                                    System.out.println("BOOLEAN" + x);
                                    System.out.println("L SIZE" + "\t" + You.l.size());
                                    System.out.println("BOX SIZE" + "\t" + You.boxes3.size());
                                    You.listView.removeFooterView(You.boxes3.get(c));
                                    if (!x) {
                                        System.out.println("NOWW" + You.l.get(c));
                                        newList.add(You.l.get(c));
                                    }
                                }
                            System.out.println("MAP"+ You.delMap);
                            System.out.println("LIST"+ You.l+ You.l.size());
                            You.boxes3.clear();
                            You.l.clear();
                            You.delMap.clear();
                            You.adapter.notifyDataSetChanged();
                         //   adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
                            You.listView.setAdapter(adapter);

                            System.out.println("NEWLIST: " + newList);

                          //  cb = new CheckBox[newList.size()];
                            for(int i = 0; i < newList.size(); i++){
                                System.out.println("THE NEW SIZE  "+ You.l.size());
                                CheckBox c = new CheckBox(mContext);
                                c.setText(newList.get(i));
                                System.out.println("IIIIIIIII"+newList.get(i));
                                c.setId(i);
                                You.boxes3.add(c);
                                You.delMap.put(i, false);
                                You.l.add(newList.get(i));
                                //cb[i] = c;
                                You.listView.addFooterView(c);

                                c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                        if (compoundButton.isChecked()) {
                                            System.out.println("ID: "+ compoundButton.getId());
                                            You.delMap.put(compoundButton.getId(), true);
                                            System.out.println(You.delMap);
                                            System.out.println("CHECKED AFTER");
                                            You.preferences.edit().putBoolean(Integer.toString(compoundButton.getId()), true).apply();
                                        } else {
                                            You.delMap.put(compoundButton.getId(), false);
                                            System.out.println("UNCHECK AFTER");
                                            System.out.println("ID: "+ compoundButton.getId());
                                            System.out.println(You.delMap);
                                            You.preferences.edit().putBoolean(Integer.toString(compoundButton.getId()), false).apply();
                                        }
                                    }
                                });
                            }
                            // adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_multiple_choice);
                            // listView.setAdapter(adapter);
                            /*
                            System.out.println("ADDING NEWLIST TO SHARED\t"+newList);
                            You.listView.setAdapter(You.adapter);
                            You.edit = You.preferences.edit();
                            You.set1 = new LinkedHashSet<>();
                            System.out.println("list" + newList);
                            You.set1.addAll(newList);
                            System.out.println("ADDEDDDDDDDDDDDDDDDDDD: "+ You.set1);
                            You.edit.putStringSet("Set", You.set1);
                            You.edit.apply();*/
                            Gson gson = new Gson();
                            You.edit = You.preferences.edit();
                            List<String> textList = new ArrayList<>(newList);
                            System.out.println(newList);
                            String jsonText = gson.toJson(textList);
                            System.out.println("JSON TEXT: "+ jsonText);
                            You.edit.putString("key", jsonText);
                            You.edit.apply();
                         //   System.out.println(You.boxes2.size());
                        }catch (Exception e){
                            e.printStackTrace();}
                    }
                });
                       /*
                        You.edit = You.prefs.edit();
                        You.set1 = new HashSet<>();
                        You.set1.addAll(You.l);
                        You.edit.putStringSet("Set", You.set1);
                        You.edit.apply();*/
                        Gson gson = new Gson();
                        You.edit = You.preferences.edit();
                        List<String> textList = new ArrayList<>(You.l);
                        System.out.println("!)!)!)");
                        System.out.println(You.l);
                        String jsonText = gson.toJson(textList);
                        System.out.println("JSON TEXT: "+ jsonText);
                        You.edit.putString("key", jsonText);
                        You.edit.apply();
                        /*
                        You.edit = You.preferences.edit();
                        You.set1 = new LinkedHashSet<>();
                        You.set1.addAll(You.l);
                        System.out.println("SET!!!!!!!!!!!!!!!!!!!"+You.set1);
                        You.edit.putStringSet("Set", You.set1);
                        You.edit.apply();
                        */
                                }
                            });

        return builder.create();
    }
    public interface dialogListener{
        void applyText(String input);
    }
}
