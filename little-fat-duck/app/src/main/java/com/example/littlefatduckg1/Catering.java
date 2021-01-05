package com.example.littlefatduckg1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Catering extends AppCompatActivity {
    private static final String TAG = "Catering";
    EditText bilName, catAddress, email, phoneNum;
    EditText cBolognese, bBolognese, cCarbonara, bCarbonara, cMushMornay, sSBechamel, cConfit;
    EditText cChop, cMeatball, mAndCheese, Pudding, iceLemonTea, sevenUp;
    EditText AandW, coke, addReq;
    Button submit;
    TextView catDate, catTime;
    FirebaseFirestore fStore;
    FirebaseUser user;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    ImageView backBtn, helpBtn;
    int hour, tminute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering);

        bilName = findViewById(R.id.billing_name);
        catAddress = findViewById(R.id.catering_address);
        catDate = (TextView) findViewById(R.id.date);
        helpBtn = findViewById(R.id.helpbtn);

        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext()
                        ,Help.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.btm_navigation);

        bottomNavigationView.setSelectedItemId(R.id.navigation_order);

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
                    case R.id.navigation_order:
                        return true;
                    case R.id.navigation_promo:
                        startActivity(new Intent(getApplicationContext()
                                ,Promotion.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_register:
                        startActivity(new Intent(getApplicationContext()
                                ,Register.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        catDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Catering.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                catDate.setText(date);
            }
        };

        catTime = findViewById(R.id.time);

        catTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Catering.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                hour = hourOfDay;
                                tminute = minute;

                                String time = hour + ":" + tminute;

                                SimpleDateFormat f24hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24hours.parse(time);

                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    catTime.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                timePickerDialog.updateTime(hour,tminute);

                timePickerDialog.show();
            }
        });

        email = findViewById(R.id.email);
        phoneNum = findViewById(R.id.phone);
        cBolognese = findViewById(R.id.bol_chicken);
        bBolognese = findViewById(R.id.bol_beef);
        cCarbonara = findViewById(R.id.car_chicken);
        bCarbonara = findViewById(R.id.car_beef);
        cMushMornay = findViewById(R.id.chicken_mushroom_mornay);
        sSBechamel = findViewById(R.id.sausage);
        cConfit = findViewById(R.id.chicken_confit);
        cChop = findViewById(R.id.chicken_chop);
        cMeatball = findViewById(R.id.meatball);
        mAndCheese = findViewById(R.id.mac_cheese);
        Pudding = findViewById(R.id.pudding);
        iceLemonTea = findViewById(R.id.ice_lemon_tea);
        sevenUp = findViewById(R.id.sevenUp);
        AandW = findViewById(R.id.aAndW);
        coke = findViewById(R.id.coke);
        addReq = findViewById(R.id.additional_request);
        submit = findViewById(R.id.submit);

        user = FirebaseAuth.getInstance().getCurrentUser();
        fStore = FirebaseFirestore.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bilname = bilName.getText().toString();
                String cataddress = catAddress.getText().toString();
                String catdate = catDate.getText().toString();
                String cattime = catTime.getText().toString();
                String uEmail = email.getText().toString();
                String phonenum = phoneNum.getText().toString();
                String cbolognese = cBolognese.getText().toString();
                String bbolognese = bBolognese.getText().toString();
                String ccarbonara = cCarbonara.getText().toString();
                String bcarbonara = bCarbonara.getText().toString();
                String cmushMornay = cMushMornay.getText().toString();
                String ssBechamel = sSBechamel.getText().toString();
                String cconfit = cConfit.getText().toString();
                String cchop = cChop.getText().toString();
                String cmeatball = cMeatball.getText().toString();
                String mandCheese = mAndCheese.getText().toString();
                String pudding = Pudding.getText().toString();
                String icelemonTea = iceLemonTea.getText().toString();
                String sevenup = sevenUp.getText().toString();
                String aandW = AandW.getText().toString();
                String Coke = coke.getText().toString();
                String addreq = addReq.getText().toString();

                if (bilname.isEmpty() || cataddress.isEmpty() || catdate.equals("Required (dd/mm/yy)") || cattime.equals("Required (h:m)") || uEmail.isEmpty() || phonenum.isEmpty()) {
                    Toast.makeText(Catering.this, "Must fill in required fields", Toast.LENGTH_LONG).show();
                    return;
                }

                DocumentReference docref = fStore.collection("Catering Service").document();
                Map<String, Object> catering = new HashMap<>();
                catering.put("Billing Name", bilname);
                catering.put("Catering Address", cataddress);
                catering.put("Catering Date", catdate);
                catering.put("Catering Time", cattime);
                catering.put("Email", uEmail);
                catering.put("Phone Number", phonenum);
                catering.put("Chicken Bolognaise", cbolognese);
                catering.put("Beef Bolognaise", bbolognese);
                catering.put("Chicken Carbonara", ccarbonara);
                catering.put("Beef Carbonara", bcarbonara);
                catering.put("Chicken Mushroom Mornay", cmushMornay);
                catering.put("Sausage Spinach Bechamel", ssBechamel);
                catering.put("Chicken Confit", cconfit);
                catering.put("Chicken Chop", cchop);
                catering.put("Chicken Meatball", cmeatball);
                catering.put("Mac and Cheese", mandCheese);
                catering.put("Pudding", pudding);
                catering.put("Ice Lemon Tea", icelemonTea);
                catering.put("Seven up", sevenup);
                catering.put("A & W", aandW);
                catering.put("Coke", Coke);
                catering.put("Additional Request", addreq);

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        sendMailUsingSendGrid("appdevsem6@gmail.com", "joelyzy2015@gmail.com",
                                "Catering service",
                                "One new order!");
                    }
                });

                docref.set(catering).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Catering.this, "Order received. We will contact you soon.", Toast.LENGTH_LONG).show();
                        return;
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Catering.this, "Error saving, try again", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void sendMailUsingSendGrid(String from, String to, String subject, String mailBody){
        Hashtable<String, String> params = new Hashtable<>();
        params.put("to", to);
        params.put("from", from);
        params.put("subject", subject);
        params.put("text", mailBody);

        SendGridAsyncTask email = new SendGridAsyncTask();
        try{
            email.execute(params);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}