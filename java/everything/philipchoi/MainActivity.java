package everything.philipchoi;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements customDialog.dialogListener, NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout draw;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, NotificationListener.class);
        this.startService(intent);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        draw = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, draw, toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        draw.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragments,
                    new openUp()).commit();
        }
    }
    static String goals;
    @Override
    public void applyText(String input) {
        goals = input;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.Home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments,
                        new openUp()).commit();
                break;
            case R.id.Calender:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments,
                        new Calender()).commit();
                break;
            case R.id.QRCode:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments,
                        new QRCode()).commit();
                break;
            case R.id.Notes:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragments,
                        new Notes()).commit();
               break;
            case R.id.Alarm:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments,
                        new Alarm()).commit();
                break;
            case R.id.Timer:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments,
                    new Timer()).commit();
                break;
            case R.id.Settings:
                Intent intent = new Intent(getApplicationContext(), settingsActivity.class);
                startActivity(intent);
        }
        draw.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if(draw.isDrawerOpen(GravityCompat.START)){
            draw.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }


}
