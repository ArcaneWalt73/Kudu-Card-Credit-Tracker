package com.example.kuducredittracker.AppEntrance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kuducredittracker.R;

public class Splash extends AppCompatActivity {

    public static int SPLASH_SCREEN = 3000;

    Animation topAnim, bottomAnim;
    ImageView witsLogo;
    TextView kudu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        witsLogo = findViewById(R.id.splash_wits_logo);
        kudu = findViewById(R.id.kudu);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        witsLogo.setAnimation(topAnim);
        kudu.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent login = new Intent(Splash.this, com.example.kuducredittracker.AppEntrance.login.class);
                startActivity(login);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}