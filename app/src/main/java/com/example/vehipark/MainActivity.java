package com.example.vehipark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    private Handler handler;
    private Runnable callback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        handler = new Handler();
        callback = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LogInActivity.class));
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        };
        handler.postDelayed(callback, 2000);
    }


    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(callback);
    }
}
