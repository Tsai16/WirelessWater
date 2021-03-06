package com.cph2020.wirelesswater;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;

public class SplashActivity extends AppCompatActivity {

    Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseApp.initializeApp(this);

        settingsButton =  findViewById(R.id.settings_button);

        AlphaAnimation fadeIn = new AlphaAnimation(1.0f , 0.0f ) ;
        AlphaAnimation fadeOut = new AlphaAnimation( 0.0f , 1.0f ) ;
        settingsButton.startAnimation(fadeIn);
        settingsButton.startAnimation(fadeOut);
//        fadeIn.setDuration(1000);
//        fadeIn.setFillAfter(true);
        fadeOut.setDuration(1500);
        fadeOut.setFillAfter(true);
//        fadeOut.setStartOffset(4200+fadeIn.getStartOffset());

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this,EmailPasswordActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_up, R.anim.fade_out);
                finish();

            }
        });

    }
}
