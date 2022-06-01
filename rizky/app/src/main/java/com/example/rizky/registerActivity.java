package com.example.rizky;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class registerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.register_main);

        Button btnRegis = findViewById(R.id.btnLogin);
        btnRegis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent goRegister = new Intent(registerActivity.this, loginActivity.class);
                startActivity(goRegister);
                finish();

            }
        });
    }
}
