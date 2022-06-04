package com.example.rizky;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class loginActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText user, pass;
    private FloatingActionButton floatingActionButtonLogin;
    private Button btnRegis;
    private static String URL_LOGIN = "http://192.168.0.148/projectSemester4/ci3/api/user/login ";


    @Override
    protected void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
        setContentView(R.layout.login_main);

        user = (EditText) findViewById(R.id.et_username);
        pass = (EditText) findViewById(R.id.et_password);
        floatingActionButtonLogin = (FloatingActionButton) findViewById(R.id.floatingLogin);
        btnRegis = (Button) findViewById(R.id.btn_regis);

        floatingActionButtonLogin.setOnClickListener(this);
        btnRegis.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if(v == floatingActionButtonLogin){
            String username = user.getText().toString().trim();
            String password = pass.getText().toString().trim();

            if(!username.isEmpty() && !password.isEmpty()){
                Login(username,password);
            }else{
                user.setError("Masukkan Username");
                pass.setError("Masukkan Password");
            }

        }else if(v== btnRegis){
            Intent intent = new Intent(loginActivity.this, registerActivity.class);
            startActivity(intent);
        }

    }

    private void Login(final String username, final String password) {
        floatingActionButtonLogin.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String logged_in = jsonObject.getString("logged_in");
                            String message = jsonObject.getString("message");
                            String id = jsonObject.getString("id");
                            if (logged_in.equals("1")) {
                                SharedPreferences sharedPreferences = loginActivity.this.getSharedPreferences("RizkyCakes",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(getString(R.string.pref_id), id);
                                editor.commit();

                                Toast.makeText(loginActivity.this, "Login Sukses", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(loginActivity.this, DashboardActivity.class);
                                startActivity(intent);

                                floatingActionButtonLogin.setVisibility(View.VISIBLE);

                            } else {
                                Toast.makeText(loginActivity.this, "Gagal Login"+message, Toast.LENGTH_SHORT).show();
                                floatingActionButtonLogin.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(loginActivity.this, "Login Erorr!"+e.toString(), Toast.LENGTH_SHORT).show();
                            floatingActionButtonLogin.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(loginActivity.this, "Erorr!" +error.toString(), Toast.LENGTH_SHORT).show();
                        floatingActionButtonLogin.setVisibility(View.VISIBLE);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("user",username);
                params.put("pass",password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
