package com.example.littlefatduckg1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Help extends AppCompatActivity {

    ExpandableListViewAdapter expandableListViewAdapter;
    ExpandableListView expandableListView;
    List<String> faqList;
    HashMap<String, List<String>> faqDes;
    int lastExpendedPosition = -1;
    EditText helpTo, helpSubject, helpMessage;
    Button btSend;
    String sEmail, sPassword;
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        backBtn = findViewById(R.id.backbtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext()
                        , Promotion.class);
                startActivity(intent);
            }
        });

        expandableListView = (ExpandableListView) findViewById(R.id.faq_exlistview);
        showList();
        expandableListViewAdapter = new ExpandableListViewAdapter(this, faqList, faqDes);
        expandableListView.setAdapter(expandableListViewAdapter);
        setListViewHeightBasedOnChildren(expandableListView);
        expandableListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    expandableListView.requestDisallowInterceptTouchEvent(false);
                } else {
                    expandableListView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });

        //help function
        helpSubject = findViewById(R.id.help_subject);
        helpMessage = findViewById(R.id.help_msg);
        btSend = findViewById(R.id.send_mail);
        //outlet fab

        //sender email credential
        sEmail = "appdevsem6@gmail.com";
        sPassword = "diploma2020";

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Properties properties = new Properties();
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");

                Session session = Session.getDefaultInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sEmail, sPassword);
                    }
                });

                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(sEmail));
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("joelyzy2015@gmail.com"));

                    message.setSubject(helpSubject.getText().toString().trim());

                    message.setText(helpMessage.getText().toString().trim());

                    new SendMail().execute(message);

                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private class SendMail extends AsyncTask<Message, String, String> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(Help.this,
                    "Please Wait", "Sending Mail...", true, false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (s.equals("Success")) {

                Toast.makeText(Help.this, "Email sent successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext()
                        , Help.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext()
                        , "Something went wrong ?", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void showList() {
        faqList = new ArrayList<String>();
        faqDes = new HashMap<String, List<String>>();
        faqList.add("What is the minimum order?");
        faqList.add("What is the duration of the catering?");
        faqList.add("What are the charges for additional time?");
        faqList.add("How can I get a price quote?");
        faqList.add("How do I confirm a catering booking?");
        faqList.add("Can I cancel a confirmed booking?");
        faqList.add("Are there any additional charges?");

        List<String> des1 = new ArrayList<>();
        des1.add("RM 1,200. Mix & match any times on our menu to achieve this.");

        List<String> des2 = new ArrayList<>();
        des2.add("2 Hours.");

        List<String> des3 = new ArrayList<>();
        des3.add("RM50 per hour.");

        List<String> des4 = new ArrayList<>();
        des4.add("Fill up and submit the form to the left with all the required details. We will revert within 48 hours.");

        List<String> des5 = new ArrayList<>();
        des5.add("Once you accept our quotation, we will request for a 50% deposit. Payment should be by bank transfer. Upon receipt, your catering will be confirmed.");

        List<String> des6 = new ArrayList<>();
        des6.add("Yes. However, if cancellation is less than 48 hours before the catering date, the deposit will be forfeited.");

        List<String> des7 = new ArrayList<>();
        des7.add("Yes. there will be a transportation fee, starting from RM 50, depending on distance. Please fill in and submit the form on the left and we will revert within 48 hours with a price quote.");

        faqDes.put(faqList.get(0), des1);
        faqDes.put(faqList.get(1), des2);
        faqDes.put(faqList.get(2), des3);
        faqDes.put(faqList.get(3), des4);
        faqDes.put(faqList.get(4), des5);
        faqDes.put(faqList.get(5), des6);
        faqDes.put(faqList.get(6), des7);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                if (lastExpendedPosition != -1
                        && i != lastExpendedPosition) {
                    expandableListView.collapseGroup(lastExpendedPosition);
                }
                lastExpendedPosition = i;
            }
        });
    }

    public void setListViewHeightBasedOnChildren(ExpandableListView expandableListView) {
        ListAdapter listAdapter = expandableListView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            View listItem = listAdapter.getView(i, null, expandableListView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = expandableListView.getLayoutParams();
        params.height = totalHeight+ (expandableListView.getDividerHeight() * (listAdapter.getCount() - 1));
        expandableListView.setLayoutParams(params);
    }
}
