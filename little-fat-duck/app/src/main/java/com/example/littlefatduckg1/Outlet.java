package com.example.littlefatduckg1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Outlet extends AppCompatActivity {
    ImageView helpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlet);

        BottomNavigationView bottomNavigationView = findViewById(R.id.btm_navigation);

        bottomNavigationView.setSelectedItemId(R.id.navigation_outlet);

        helpBtn = findViewById(R.id.helpbtn);

        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext()
                        , Help.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),
                        MapsActivity.class);
                startActivity(i);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_menu:
                        startActivity(new Intent(getApplicationContext()
                                ,Menu.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_order:
                        startActivity(new Intent(getApplicationContext()
                                ,Catering.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_outlet:
                        return true;
                    case R.id.navigation_register:
                        startActivity(new Intent(getApplicationContext()
                                ,Register.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_promo:
                        startActivity(new Intent(getApplicationContext()
                                ,Promotion.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}