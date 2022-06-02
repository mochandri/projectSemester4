package com.example.rizky;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class loginActivity extends AppCompatActivity {

    private EditText et_user, et_pass;
    private Button btn_login;
    private TextView tv_regis;

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
