package com.example.psychologycounselingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements Home.OnFragmentInteractionListener, DSTfragment.OnFragmentInteractionListener, Userfragment.OnFragmentInteractionListener{
    BottomNavigationView bottomNavigationView;
    LinearLayout linearLayout;
    Home homeFragment = new Home();
    DSTfragment dstfragment = new DSTfragment();
    Userfragment userfragment = new Userfragment();
    Intent intent;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.main_frame);
        bottomNavigationView = findViewById(R.id.bottom_nav_menu);
        setFragment(homeFragment);
        sessionManager = new SessionManager(this);
        intent = new Intent(MainActivity.this, LoginActivity.class);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        setFragment(homeFragment);
                            return true;
                    case R.id.nav_DST:
                        setFragment(dstfragment);
                        return true;
                    case R.id.nav_user:
                        setFragment(userfragment);
                        return true;
                    default:
                        return false;
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}


