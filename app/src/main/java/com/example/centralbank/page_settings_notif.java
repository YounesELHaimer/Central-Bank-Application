package com.example.centralbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class page_settings_notif extends AppCompatActivity {

    private ImageView arrow_back;
    private ImageView power;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_settings_notif);

        arrow_back = findViewById(R.id.arrow_back);
        power = (ImageView) findViewById(R.id._power);

        arrow_back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                onBackPressed();


            }
        });

        power.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                AlertDialog.Builder adb = new AlertDialog.Builder(page_settings_notif.this, R.style.CustomAlertDialogStyle);
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
    }
}