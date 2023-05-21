package com.example.centralbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chaos.view.PinView;
import com.google.android.material.textfield.TextInputEditText;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;


import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Gmail extends AppCompatActivity {
    private static final String SMTP_SERVER = "smtp.gmail.com";
    private static final int SMTP_PORT = 587;
    TextInputEditText editText;
    Button button, confirmbtn;
    int code;
    PinView pinView;

    String stringReceiverEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gmail);

        Random random = new Random();
        code = random.nextInt(8999) + 1000;


        String phone = getIntent().getStringExtra("phone");
        String name = getIntent().getStringExtra("firstName");
        String lastName = getIntent().getStringExtra("lastName");
        String operator = getIntent().getStringExtra("selectedOption");

        editText = findViewById(R.id.signup_Email);
        button = findViewById(R.id.signup_btn);
        pinView = findViewById(R.id.firstPinView);
        confirmbtn = findViewById(R.id.signup_confirm);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String stringSenderEmail = "hpotterba422@gmail.com";
                     stringReceiverEmail = editText.getText().toString().trim();;
                    String stringPasswordSenderEmail = "byxvqhljrzsracor";


                    String stringHost = "smtp.gmail.com";

                    Properties properties = System.getProperties();

                    properties.put("mail.smtp.host", stringHost);
                    properties.put("mail.smtp.port", "465");
                    properties.put("mail.smtp.ssl.enable", "true");
                    properties.put("mail.smtp.auth", "true");

                    javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
                        }
                    });

                    MimeMessage mimeMessage = new MimeMessage(session);
                    mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));

                    mimeMessage.setSubject("Email Verification");
                    mimeMessage.setText("Hello this is ur code verification add it to the field "+code);

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Transport.send(mimeMessage);
                            } catch (MessagingException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    thread.start();
                    findViewById(R.id.LinearLayout_Email).setVisibility(View.GONE);
                    findViewById(R.id.LinearLayout_PinView).setVisibility(View.VISIBLE);



                } catch (AddressException e) {
                    e.printStackTrace();
                    Log.e("TAG", e.toString() );
                } catch (MessagingException e) {
                    e.printStackTrace();
                    Log.e("TAG1", e.toString() );
                }

            }

        });


        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputCode = pinView.getText().toString();
                if (inputCode.equals(String.valueOf(code))) {
                    Intent intent = new Intent(Gmail.this, register.class);
                    intent.putExtra("email", stringReceiverEmail);
                    intent.putExtra("phone", phone);
                    intent.putExtra("selectedOption", operator);
                    intent.putExtra("firstName", name);
                    intent.putExtra("lastName", lastName);
                    startActivity(intent);
                    Toast.makeText(Gmail.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Gmail.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



}

