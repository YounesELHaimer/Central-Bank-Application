package com.example.centralbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class page_rib extends AppCompatActivity {

    private ImageView home;
    private ImageView card;
    private ImageView power;
    private ImageView _transaction__2_;
    private ImageView invoice;
    private ImageView settings;
    private ImageView telecharger_btn;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_rib);

        home = findViewById(R.id.home);
        power = findViewById(R.id._power);
        card = findViewById(R.id.credit_card);
        _transaction__2_ = (ImageView) findViewById(R.id._transaction__2_);
        invoice = findViewById(R.id.invoice);
        settings = findViewById(R.id.gears);
        telecharger_btn = findViewById(R.id.telecharger_btn);

        progressDialog = new ProgressDialog(page_rib.this);
        progressDialog.setMessage("Chargement...");
        progressDialog.setCancelable(false);


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

                AlertDialog.Builder adb = new AlertDialog.Builder(page_rib.this, R.style.CustomAlertDialogStyle);
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

        telecharger_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                Intent nextScreen = new Intent(getApplicationContext(), rip_pdf.class);
                startActivity(nextScreen);
                progressDialog.dismiss();

            }
        });

    }
}