package com.example.littlefatduckg1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loadingScreen extends AppCompatActivity {
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadingscreen);

        ProgressBar progressBar;

        fAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar3);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (fAuth.getCurrentUser() != null){
                    startActivity(new Intent(getApplicationContext(), Promotion.class));
                    finish();
                } else {
                    fAuth.signInAnonymously().addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            startActivity(new Intent(getApplicationContext(), Promotion.class));
                            finish();
                        }
                    });
                }
            }
        }, 2000);
    }
}