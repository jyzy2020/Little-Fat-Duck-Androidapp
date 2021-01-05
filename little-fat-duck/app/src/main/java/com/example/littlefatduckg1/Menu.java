package com.example.littlefatduckg1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Menu extends AppCompatActivity {
    ImageView helpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        BottomNavigationView bottomNavigationView = findViewById(R.id.btm_navigation);

        bottomNavigationView.setSelectedItemId(R.id.navigation_menu);

        helpBtn = findViewById(R.id.helpbtn);

        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext()
                        , Help.class);
                startActivity(intent);
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_menu:
                        return true;
                    case R.id.navigation_outlet:
                        startActivity(new Intent(getApplicationContext()
                                , Outlet.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_order:
                        startActivity(new Intent(getApplicationContext()
                                ,Catering.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_register:
                        startActivity(new Intent(getApplicationContext()
                                , Register.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_promo:
                        startActivity(new Intent(getApplicationContext()
                                , Promotion.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}