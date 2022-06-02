package com.example.rizky;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class registerActivity extends AppCompatActivity {

    private EditText Username, Password;
    private Button Login;
    private TextView Register;
    private static String URL_REGIST =" http://10.209.58.41/projectSemester4/ci3/api/user";

    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.register_main);

        Username = (EditText)findViewById(R.id.et_username);
        Password = (EditText)findViewById(R.id.et_password);
        Login = (Button) findViewById(R.id.btnLogin);
        Register = (TextView)findViewById(R.id.tv_title);


        Button btnRegis = findViewById(R.id.btnLogin);
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == Register) {
                    Intent goRegister = new Intent(registerActivity.this, loginActivity.class);
                    startActivity(goRegister);
                    finish();
                } else if (v == btnRegis) {
                    String username = Username.getText().toString().trim();
                    String password = Password.getText().toString().trim();

                }
                Username.setError("Masukkan Username");
                Password.setError("Masukkan Password");

            }

        });
    }
    private void Register() {
        Register.setVisibility(View.GONE);

        final String Username = this.Username.getText().toString().trim();
        final String Password= this.Password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String value = jsonObject.getString("value");
                            String pesan = jsonObject.getString("pesan");
                            if (value.equals("1")) {
                                Toast.makeText(registerActivity.this, pesan, Toast.LENGTH_SHORT).show();
                                Intent berhasil = new Intent(registerActivity.this, loginActivity.class);
                                startActivity(berhasil);
                                Register.setVisibility(View.VISIBLE);
                            } else {
                                Toast.makeText(registerActivity.this, pesan, Toast.LENGTH_SHORT).show();
                                Register.setVisibility(View.VISIBLE);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(registerActivity.this, "Register Error!" + e.toString(), Toast.LENGTH_SHORT).show();
                            Register.setVisibility(View.VISIBLE);
                        }
                    }
                }, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(registerActivity.this, "Register Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                Register.setVisibility(View.VISIBLE);
            }
        }){
            @Override
            protected Map<String,String> getParams()throws AuthFailureError{
                Map<String, String> params= new HashMap<>();
                params.put("Username",Username);
                params.put("Password",Password);
               return params;
            }

        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
