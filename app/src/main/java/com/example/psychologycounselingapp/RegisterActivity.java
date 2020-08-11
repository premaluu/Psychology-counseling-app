package com.example.psychologycounselingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {
    Intent intent;
    private EditText user_name, user_mobile,user_email, user_password;
    private Button btn_regist;
    private ProgressBar loading;
    //Todo: Change URL when integrate on server
    private static String URL_REGIST = "http://192.168.0.105/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user_name = findViewById(R.id.name);
        user_email = findViewById(R.id.email);
        user_mobile = findViewById(R.id.mobile);
        user_password = findViewById(R.id.password);
        btn_regist = findViewById(R.id.btn_regist);
        loading = findViewById(R.id.loading);
        intent = new Intent(RegisterActivity.this, LoginActivity.class);
        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
            }
        });
    }

    private void Regist(){
        loading.setVisibility(View.VISIBLE);
        btn_regist.setVisibility(View.GONE);

        final String name = this.user_name.getText().toString().trim();
        final String email = this.user_email.getText().toString().trim();
        final String password = this.user_password.getText().toString().trim();
        final String mobile = this.user_mobile.getText().toString().trim();

        if(name.equals("null") || mobile.equals("null") || email.equals("null") || email.equals("null") || password.equals("null")) {
            Toast.makeText(this, "PLease Enter All The Details!!!", Toast.LENGTH_LONG);
        }
        else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");

                                if (success.equals("2")) {
                                    Toast.makeText(RegisterActivity.this, "User Already Exist.", Toast.LENGTH_SHORT).show();
                                    loading.setVisibility(View.GONE);
                                    btn_regist.setVisibility(View.VISIBLE);
                                } else if(success.equals("1")){
                                    Toast.makeText(RegisterActivity.this, "Register Success!", Toast.LENGTH_SHORT).show();
                                    loading.setVisibility(View.GONE);
                                    btn_regist.setVisibility(View.VISIBLE);
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Register Success!", Toast.LENGTH_SHORT).show();
                                    loading.setVisibility(View.GONE);
                                    btn_regist.setVisibility(View.VISIBLE);
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(RegisterActivity.this, "User Already Exist. " + e.toString(), Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                btn_regist.setVisibility(View.VISIBLE);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RegisterActivity.this, "Register Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btn_regist.setVisibility(View.VISIBLE);
                        }
                    })

            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("user_name", name);
                    params.put("user_mobile", mobile);
                    params.put("user_email", email);
                    params.put("user_password", password);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }
    public void viewLoginClicked(View v) {
        if(v.getId() == R.id.txtAlreadyAccount) {
            startActivity(intent);
        }
    }
    public void viewRegisterClose(View v) {
        finish();
    }
}