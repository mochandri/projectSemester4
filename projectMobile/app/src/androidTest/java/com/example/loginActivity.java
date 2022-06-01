package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rizky.R;

public class loginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.login_main);

        Button btnRegis = findViewById(R.id.btn_regis);
        btnRegis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent goRegister = new Intent(loginActivity.this, registerActivity.class);
                startActivity(goRegister);
                finish();

            }
        });
    }
}
