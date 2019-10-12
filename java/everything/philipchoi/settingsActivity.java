package everything.philipchoi;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;



public class settingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#373737")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
    public void next(View view){
        Intent intent = new Intent(this, themesSettings.class);
        startActivity(intent);
    }

}
