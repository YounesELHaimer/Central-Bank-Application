package com.example.centralbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class page_facture extends AppCompatActivity {

    private ImageView home;
    private ImageView card;
    private ImageView power;
    private ImageView _transaction__2_;
    private ImageView invoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_facture);

        home = findViewById(R.id.home);
        power = findViewById(R.id._power);
        card = findViewById(R.id.credit_card);
        _transaction__2_ = (ImageView) findViewById(R.id._transaction__2_);
        invoice = findViewById(R.id.invoice);


        home.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), page_home_activity.class);
                startActivity(nextScreen);


            }
        });


        _transaction__2_.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), page_virement_activity.class);
                startActivity(nextScreen);


            }
        });

        card.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), page_card.class);
                startActivity(nextScreen);

            }
        });


        power.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), first_page_activity.class);
                startActivity(nextScreen);


            }
        });

        invoice.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), page_facture.class);
                startActivity(nextScreen);


            }
        });
    }
}