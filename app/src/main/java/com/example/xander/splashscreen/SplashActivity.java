package com.example.xander.splashscreen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;



public class SplashActivity extends Activity {

    private static final int SPLASH_DISPLAY_LENGTH = 3000;
    private static final String PREFS_NAME = "splash_screen_pref";
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        settings = getSharedPreferences(PREFS_NAME, MODE_APPEND);
        boolean firstStart = settings.getBoolean("notFirstStart", false);

        if (!firstStart) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                    mainActivityIntent();
                    finish();
                    SharedPreferences.Editor prefEditor = settings.edit();
                    prefEditor.putBoolean("notFirstStart", true);
                    prefEditor.commit();
                }
            }, SPLASH_DISPLAY_LENGTH);
        } else {
            mainActivityIntent();
            finish();
        }


    }

    private void mainActivityIntent() {
        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
