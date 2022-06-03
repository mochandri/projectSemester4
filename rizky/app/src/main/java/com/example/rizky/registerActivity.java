package com.example.rizky;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class registerActivity extends AppCompatActivity implements View.OnClickListener {
        private EditText nama, user, pass;
        private FloatingActionButton btnRegis;
        private Button btnLogin;
        private String roleid;
        private static String URL_REGIST = "http://192.168.0.148/projectSemester4/ci3/api/user ";

        @Override
        protected void onCreate(Bundle savedIntanceState) {
            super.onCreate(savedIntanceState);
            setContentView(R.layout.register_main);

            nama =(EditText) findViewById(R.id.et_nama);
            user = (EditText) findViewById(R.id.et_username);
            pass = (EditText) findViewById(R.id.et_password);
            btnRegis = (FloatingActionButton) findViewById(R.id.floatRegis);
            btnLogin = (Button) findViewById(R.id.btnLogin);

            btnLogin.setOnClickListener(this);
            btnRegis.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        Intent goRegister = new Intent(registerActivity.this, loginActivity.class);
        startActivity(goRegister);
        finish();


        if(v == btnLogin){
            Intent intent = new Intent(registerActivity. this, loginActivity.class);
            startActivity(intent);
        }else if(v == btnRegis){
            String Nama = nama.getText().toString().trim();
            String username = user.getText().toString().trim();
            String password = pass.getText().toString().trim();

            if(!Nama.isEmpty() &&!username.isEmpty() && !password.isEmpty()){
            }else{
                nama.setError("Masukkan Nama");
                user.setError("Masukkan Username");
                pass.setError("Masukkan Password");
            }

        }
    }

    private void Register() {
            btnRegis.setVisibility(View.GONE);

            final String role_id = this.roleid.getBytes().toString().trim();
            final String Nama = this.nama.getText().toString().trim();
            final String username = this.user.getText().toString().trim();
            final String password = this.pass.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
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
                                btnRegis.setVisibility(View.VISIBLE);
                            } else {
                                Toast.makeText(registerActivity.this, "", Toast.LENGTH_SHORT).show();
                                btnRegis.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(registerActivity.this, "Registrasi Gagal!"+e.toString(), Toast.LENGTH_SHORT).show();
                            btnRegis.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(registerActivity.this, "Registrasi Erorr!" +error.toString(), Toast.LENGTH_SHORT).show();
                        btnRegis.setVisibility(View.VISIBLE);
                        error.printStackTrace();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
//                return (params != null || params.isEmpty())? params : super.getParams();

                params.put("nama",Nama);
                params.put("user",username);
                params.put("pass",password);
                params.put("roleid",role_id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
