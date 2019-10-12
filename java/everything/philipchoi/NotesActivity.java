package everything.philipchoi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NotesActivity extends AppCompatActivity {
    EditText noteText;
    static SharedPreferences sharedPreferences;
    String noteContent;
    String idNote;
    Intent intent;

    public void textWatcher() {
        noteText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                sharedPreferences.edit().putString(idNote, noteText.getText().toString()).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        intent = getIntent();
        idNote = intent.getStringExtra("noteid");
        sharedPreferences = this.getSharedPreferences("com.example.everything", Context.MODE_PRIVATE);
        noteText = findViewById(R.id.noteText);
        textWatcher();
        noteContent = sharedPreferences.getString(idNote, "Error");
        if (!noteContent.equals("Error")) {
            noteText.setText(noteContent);
        }
    }
}
