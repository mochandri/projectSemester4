package com.example.rizky;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class registerActivity extends AppCompatActivity {

    private EditText Username, Password;
    private Button Login;
    private TextView Register;

    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.register_main);

        Username = (EditText)findViewById(R.id.et_username);
        Password = (EditText)findViewById(R.id.et_password);
        Login = (Button) findViewById(R.id.btnLogin);
        Register = (TextView)findViewById(R.id.tv_title);

        Button btnRegis = findViewById(R.id.btnLogin);
        btnRegis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(v==Register){
                    Intent goRegister = new Intent(registerActivity.this, loginActivity.class);
                    startActivity(goRegister);
                    finish();
                }else if(v==btnRegis){
                    String username = Username.getText().toString().trim();
                    String password = Password.getText().toString().trim();
                if(!username.isEmpty()&& !password.isEmpty()){
                    if (equals(password))Register();
                    else password.setError("konfirmasi password salahh");

                }
                Username.setError("Masukkan Username");
                Password.setError("Masukkan Password");

                }


            }
        });
    }

    private void Register() {
        loading.setVisibilty(View.VISIBLE);
        Register.setVisibility(View.GONE);

    }
}
