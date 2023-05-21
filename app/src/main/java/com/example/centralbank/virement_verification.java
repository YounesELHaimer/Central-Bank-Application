package com.example.centralbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class virement_verification extends AppCompatActivity {

    private ImageView _power;
    private ImageView bell;
    private ImageView settings;
    private ImageView invoice;
    private ImageView card;
    private ImageView home10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.virement_verification);

        invoice = findViewById(R.id.invoice);
        home10 = findViewById(R.id.home10);
        _power = (ImageView) findViewById(R.id._power);
        bell = (ImageView) findViewById(R.id.bell);
        invoice = (ImageView) findViewById(R.id.invoice);
        card = findViewById(R.id.credit_card);
        settings = findViewById(R.id.gears);


        home10.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), page_home_activity.class);
                startActivity(nextScreen);


            }
        });

        card.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), page_card.class);
                startActivity(nextScreen);

            }
        });


        _power.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                AlertDialog.Builder adb = new AlertDialog.Builder(virement_verification.this, R.style.CustomAlertDialogStyle);
                View view = getLayoutInflater().inflate(R.layout.logout_alert_dialog, null);
                RelativeLayout button_ok = view.findViewById(R.id.button_OK);
                RelativeLayout button_annuler = view.findViewById(R.id.button_Annuler);

                adb.setView(view);
                adb.setCancelable(false);

                AlertDialog alert= adb.create();
                alert.show();

                button_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent nextScreen = new Intent(getApplicationContext(), first_page_activity.class);
                        startActivity(nextScreen);
                    }
                });

                button_annuler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.dismiss();
                    }
                });


            }
        });

        invoice.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), page_rib.class);
                startActivity(nextScreen);


            }
        });


        settings.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), page_settings.class);
                startActivity(nextScreen);

            }
        });
    }
}