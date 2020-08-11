package com.example.psychologycounselingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

import static java.security.AccessController.getContext;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

      public double result=0;
      public Button b1,b2;
      public TextView result1,resultScore;
      public Intent in;
      public SessionManager sessionManager;
      Button saveButton, homeButton;
      String getMobile, age;
      String resultScoreFinal;
    //Todo: Change URL when integrate on server
      private static String URL_REGIST = "http://192.168.0.105/insert_test.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        result = intent.getDoubleExtra("result",0.00);
        age = intent.getStringExtra("age");
        result1 = findViewById(R.id.result);
        resultScore = findViewById(R.id.score_result);
        saveButton = findViewById(R.id.btnSave);
        homeButton = findViewById(R.id.btnHome);
        print(result);
        Toast.makeText(this, "Test done.", Toast.LENGTH_LONG).show();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager = new SessionManager(ResultActivity.this);
                sessionManager.checkLogin();
                HashMap<String, String> user = sessionManager.getUserDetail();
                getMobile = user.get(SessionManager.MOBILE);
                resultScoreFinal = resultScore.getText().toString().trim();

                insertTest();
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void insertTest(){
//        final String name = this.user_name.getText().toString().trim();
//        final String email = this.user_email.getText().toString().trim();
//        final String password = this.user_password.getText().toString().trim();
//        final String mobile = this.user_mobile.getText().toString().trim();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");

                                 if(success.equals("1")){
                                    Toast.makeText(ResultActivity.this, "Stored!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ResultActivity.this, "Stored!", Toast.LENGTH_SHORT).show();
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(ResultActivity.this, "Not Stored, login again. " + e.toString(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ResultActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ResultActivity.this, "Not Stored! " + error.toString(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ResultActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    })

            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("age", age);
                    params.put("score", resultScoreFinal);
                    params.put("mobile", getMobile);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
    }

    void print(double result)
    {

        if(result>100)
        {
            result1.setText("Above Average");
            String finalResult =  String.format("%.2f", result);
            resultScore.setText(finalResult);
        }
        else if(result==100)
        {
            result1.setText("Average");
            String finalResult =  String.format("%.2f", result);
            resultScore.setText(finalResult);
        }else
        {
            result1.setText("Below Average");
            String finalResult =  String.format("%.2f", result);
            resultScore.setText(finalResult);
        }

    }

    @Override
    public void onClick(View v) {
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                in=new Intent(ResultActivity.this,DSTfragment.class);
            }
        });
        {

        }

    }
}