package com.example.centralbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class page_card extends AppCompatActivity {
    private ImageView home;
    private ImageView card;
    private ImageView power;
    private ImageView _transaction__2_;
    private ImageView invoice;
    private ImageView settings;
    private Button copier_button;
    private TextView card_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_card);
        home = findViewById(R.id.home);
        power = findViewById(R.id._power);
        card = findViewById(R.id.credit_card);
        _transaction__2_ = (ImageView) findViewById(R.id._transaction__2_);
        invoice = findViewById(R.id.invoice);
        settings = findViewById(R.id.gears);
        copier_button = findViewById(R.id.copier_button);
        card_id = findViewById(R.id.card_id);

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

                AlertDialog.Builder adb = new AlertDialog.Builder(page_card.this, R.style.CustomAlertDialogStyle);
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

        copier_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToCopy = card_id.getText().toString();

                // Copy the text to the clipboard
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("TextView Text", textToCopy);
                clipboardManager.setPrimaryClip(clipData);

                // Show a toast message to indicate the text has been copied
                Toast.makeText(getApplicationContext(), "Texte copié dans le presse-papiers", Toast.LENGTH_SHORT).show();
            }
        });



    }
}