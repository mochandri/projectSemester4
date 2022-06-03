package com.example.rizky;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class loginActivity extends AppCompatActivity {


    private EditText et_user, et_pass;
    private Button btn_login;
    private TextView tv_regis;
    private EditText Username, Password;
    private Button Register;
    private TextView Login;
    private static String URL_LOGIN = " ";


    @Override
    protected void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
        setContentView(R.layout.login_main);

        Username = (EditText) findViewById(R.id.et_username);
        Password = (EditText) findViewById(R.id.et_password);
        Register = (Button) findViewById(R.id.btnLogin);
        Login = (TextView) findViewById(R.id.tv_login);

        Register.setOnClickListener((View.OnClickListener) this);
        Login.setOnClickListener((View.OnClickListener) this);

        Button btnRegis = findViewById(R.id.btn_regis);
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == Register) {
                    Intent goRegister = new Intent(loginActivity.this, registerActivity.class);
                    startActivity(goRegister);
                    finish();

                } else if (v == btnRegis) {
                    String username = Username.getText().toString().trim();
                    String password = Password.getText().toString().trim();

                }
                Username.setError("Masukkan Username");
                Password.setError("Masukkan Password");

            }  });
        }

        private void Login ( final String user, final String password){
            Register.setVisibility(View.GONE);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String value = jsonObject.getString("value");
                                String pesan = jsonObject.getString("pesan");
                                if (value.equals("1")) {
                                    SharedPreferences sharedPreferences = loginActivity.this.getSharedPreferences("toko_online", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString(getString(R.string.pref_username), String.valueOf(Username));
                                    editor.commit();

                                    Toast.makeText(loginActivity.this, "Login Sukses", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(loginActivity.this, MainActivity.class);
                                    startActivity(intent);

                                    Login.setVisibility(View.VISIBLE);

                                } else {
                                    Toast.makeText(loginActivity.this, "Gagal Login" + pesan, Toast.LENGTH_SHORT).show();
                                    Login.setVisibility(View.VISIBLE);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(loginActivity.this, "Register Error!" + e.toString(), Toast.LENGTH_SHORT).show();
                                Login.setVisibility(View.VISIBLE);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(loginActivity.this, "Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                            Register.setVisibility(View.VISIBLE);
                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError{
                    Map<String, String> params = new HashMap<>();
                    params.put("user", user);
                    params.put("pass",password);
                    return params;
                }

            };


            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }




