package com.example.psychologycounselingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
    private boolean validateEmail()
    {
        String emailInput = user_email.getText().toString();

        if(emailInput.isEmpty()){
            user_email.requestFocus();
            user_email.setError("Field cannot be empty!");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            user_email.requestFocus();
            user_email.setError("Please enter valid Email id");
            return false;

        }
        else{
            user_email.requestFocus();
            user_email.setError(null);
            return true;
        }
    }
    public boolean validatePhone() {
        String phone = user_mobile.getText().toString();

        if(phone.isEmpty()) {
            user_mobile.requestFocus();
            user_mobile.setError("Field must be filled.");
            return false;
        } else if(!Patterns.PHONE.matcher(phone).matches()) {
            user_mobile.requestFocus();
            user_mobile.setError("Enter valid phone.");
            return false;
        } else if(phone.length() < 10) {
            user_mobile.requestFocus();
            user_mobile.setError("Phone must 10 digit long.");
            return false;
        } else {
            user_mobile.requestFocus();
            return true;
        }
    }
    public boolean validatePassword() {
        String password = user_password.getText().toString();
        if(password.isEmpty()) {
            user_password.requestFocus();
            user_password.setError("Field must be filled.");
            return false;
        } else {
            user_password.requestFocus();
            return true;
        }
    }

    private boolean validateUname()
    {
        String unameInput = user_name.getText().toString();
        if(unameInput.isEmpty())
        {
            user_name.requestFocus();
            user_name.setError("Field cannot be empty!");
            return false;

        }
        else{
            user_name.requestFocus();
            user_name.setError(null);
            return true;
        }
    }

    private void Regist(){
        loading.setVisibility(View.VISIBLE);
        btn_regist.setVisibility(View.GONE);

        final String name = this.user_name.getText().toString().trim();
        final String email = this.user_email.getText().toString().trim();
        final String password = this.user_password.getText().toString().trim();
        final String mobile = this.user_mobile.getText().toString().trim();

        if(!validateUname() || !validatePhone() || !validateEmail() || !validatePassword()) {
            Toast.makeText(this, "PLease Enter All The Correct Details!!!", Toast.LENGTH_LONG);
            loading.setVisibility(View.GONE);
            btn_regist.setVisibility(View.VISIBLE);
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