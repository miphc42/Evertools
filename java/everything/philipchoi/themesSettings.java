package everything.philipchoi;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class themesSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes_settings);
    }
    public void changeTheme(View view){
        getTheme().applyStyle(R.style.AppTheme, true);
    }
}
