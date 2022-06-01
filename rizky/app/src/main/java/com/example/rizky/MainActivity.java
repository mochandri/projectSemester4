package com.example.rizky;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int welcomeScreenDisplay = 1000;
        Thread welcomeThread = new Thread(){
            int wait = 0;
            @Override
            public void run(){
                try {
                    super.run();
                    while (wait < welcomeScreenDisplay){
                        sleep(100);
                        wait += 100;
                    }
                }catch (Exception e){
                    System.out.println("EXc="+e);
                }finally {
                    Intent intent = new Intent(MainActivity.this, loginActivity.class );
                    finish();
                    startActivity(intent);
                }
            }
        };
        welcomeThread.start();
    }
}
