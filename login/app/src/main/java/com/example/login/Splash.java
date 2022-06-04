package com.example.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final int welcomeScreenDisplay = 1000;
        Thread welcomeThread = new Thread(){
            int wait = 0;
            @Override
            public void run(){
                try {
                    super.run();
                    while (wait < welcomeScreenDisplay){
                        sleep(500);
                        wait += 500;
                    }
                }catch (Exception e){
                    System.out.println("EXc="+e);
                }finally {
                    Intent intent = new Intent(Splash.this, Login.class );
                    finish();
                    startActivity(intent);
                }
            }
        };
        welcomeThread.start();
    }
}
