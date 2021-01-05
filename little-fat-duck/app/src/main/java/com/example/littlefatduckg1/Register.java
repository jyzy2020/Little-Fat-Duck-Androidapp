
    package com.example.littlefatduckg1;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.Button;
    import android.widget.CheckBox;
    import android.widget.EditText;
    import android.widget.ImageView;
    import android.widget.ProgressBar;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;

    import com.google.android.gms.tasks.OnFailureListener;
    import com.google.android.gms.tasks.OnSuccessListener;
    import com.google.android.material.bottomnavigation.BottomNavigationView;
    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.auth.FirebaseUser;
    import com.google.firebase.firestore.DocumentReference;
    import com.google.firebase.firestore.FirebaseFirestore;

    import java.util.HashMap;
    import java.util.Map;

    public class Register extends AppCompatActivity {
        FirebaseFirestore fStore;
        EditText username, dateOfBirth, email;
        CheckBox checkBox;
        TextView pdpa, registerNow;
        ImageView logo;
        Button getItNow;
        ProgressBar progressBar;
        FirebaseUser user;
        ImageView helpBtn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            logo = findViewById(R.id.logoXML);
            registerNow = findViewById(R.id.reg_now);

            username = findViewById(R.id.userName);
            dateOfBirth = findViewById(R.id.user_dob);
            email = findViewById(R.id.userEmail);

            checkBox = findViewById(R.id.checkBoxXML);
            pdpa = findViewById(R.id.pdpaXML);
            getItNow = findViewById(R.id.createAccount);

            helpBtn = findViewById(R.id.helpbtn);

            helpBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext()
                            , Help.class);
                    startActivity(intent);
                }
            });

            pdpa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext()
                    , PDPA.class);
                    startActivity(intent);
                }
            });

            progressBar = findViewById(R.id.progressBar2);
            user = FirebaseAuth.getInstance().getCurrentUser();

            fStore = FirebaseFirestore.getInstance();

            BottomNavigationView bottomNavigationView = findViewById(R.id.btm_navigation);

            bottomNavigationView.setSelectedItemId(R.id.navigation_register);

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.navigation_menu:
                            startActivity(new Intent(getApplicationContext()
                                    ,Menu.class));
                            overridePendingTransition(0,0);
                            return true;
                        case R.id.navigation_outlet:
                            startActivity(new Intent(getApplicationContext()
                                    ,Outlet.class));
                            overridePendingTransition(0,0);
                            return true;
                        case R.id.navigation_register:
                            return true;
                        case R.id.navigation_order:
                            startActivity(new Intent(getApplicationContext()
                                    ,Catering.class));
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

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((CheckBox) v).isChecked()) {
                        getItNow.setEnabled(true);
                    }
                    else {
                        getItNow.setEnabled(false);
                    }
                }
            });

            getItNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String uName = username.getText().toString();
                    String uDOB = dateOfBirth.getText().toString();
                    String uEmail = email.getText().toString();

                    if (uName.isEmpty() || uDOB.isEmpty() || uEmail.isEmpty()) {
                        Toast.makeText(Register.this, "All Text Field are required", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    progressBar.setVisibility(View.VISIBLE);

                    DocumentReference docref = fStore.collection("registration").document(user.getUid()).collection("myRegistration").document();
                    Map<String, Object> registration = new HashMap<>();
                    registration.put("Username", uName);
                    registration.put("Date of Birth", uDOB);
                    registration.put("Email", uEmail);

                    docref.set(registration).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Register.this, "Account registered, Check your email to get birthday promotion!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            return;
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Register.this, "Error saving, try again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }
            });
        }

    }
