package everything.philipchoi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Notes extends Fragment {
    ListView notesList;
    ArrayList<String> notes = new ArrayList<>();
    Intent intent;
    SharedPreferences preferences;
    SharedPreferences.Editor edit;
    FloatingActionButton button;
    static int index;
    EditText input;
    Set<String> set;
    private void popUpEditText() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Notes");

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
                if(!(notes.contains(input.getText().toString()))) {
                    listAdapt();
                    intent.putExtra("noteid", input.getText().toString());
                    startActivity(intent);
                    notes.add(input.getText().toString());
                    permanent();
                    listAdapt();
                }
                else{
                    dialog.cancel();
                    Toast.makeText(getContext(), "Enter a new name for the note", Toast.LENGTH_SHORT ).show();
                }
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
    public void permanent(){
//        edit = preferences.edit();
//        set = new HashSet<>();
//        set.addAll(notes);
//        edit.putStringSet("SetNote", set);
//        edit.apply();
        Gson gson = new Gson();
        edit = preferences.edit();
        List<String> textList = new ArrayList<>(notes);
        String jsonText = gson.toJson(textList);
        System.out.println("JSON TEXT: "+ jsonText);
        edit.putString("key", jsonText);
        edit.apply();
        listAdapt();
    }
    public void listAdapt(){
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, notes);
        notesList.setAdapter(adapter);
        intent = new Intent(getContext(), NotesActivity.class);
        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {//setonitemclick not setonclick.
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent.putExtra("noteid", notesList.getItemAtPosition(i).toString());
                startActivity(intent);

            }

        } );
        notesList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
                new AlertDialog.Builder(adapterView.getContext())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete")
                        .setMessage("Remove Note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                    NotesActivity.sharedPreferences.edit().putString(notes.get(index), "Error").apply();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                notes.remove(index);
                                permanent();
                                adapter.notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });
    }
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        (getActivity()).setTitle("Notes");
        view = inflater.inflate(R.layout.notes, container, false);
        notesList = view.findViewById(R.id.notesList);
        getFragmentManager().findFragmentById(R.id.addNote);
        preferences = getContext().getSharedPreferences("com.example.everything", Context.MODE_PRIVATE);
        TreeSet<String> sett2 = new TreeSet<>();
        Set<String> set = preferences.getStringSet("SetNote", sett2);
        List<String> sample = new ArrayList<>(set);
        Gson gson = new Gson();
        String jsonText = preferences.getString("key", null);
        String[] text = gson.fromJson(jsonText, String[].class);
        if(text!=null){
            for (int i = 0; i < text.length; i++) {
                notes.add(text[i]);
            }
            listAdapt();
        }

        button = view.findViewById(R.id.addNote);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpEditText();
                System.out.println(notes);
            }
        });
        return view;

    }
}

