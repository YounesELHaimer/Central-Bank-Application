package com.example.centralbank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class page_empreinte extends AppCompatActivity {

    private ImageView arrow_back;
    private ImageView power;
    private Switch switch_emp;
    private View se_connecter;
    private View emp;
    private TextView string_se_connecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_empreinte);

        arrow_back = findViewById(R.id.arrow_back);
        power = (ImageView) findViewById(R.id._power);
        switch_emp = findViewById(R.id.switch_emp);



        arrow_back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                onBackPressed();


            }
        });

        power.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                AlertDialog.Builder adb = new AlertDialog.Builder(page_empreinte.this, R.style.CustomAlertDialogStyle);
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

        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
        boolean switchState = prefs.getBoolean("switch_state", false);
        switch_emp.setChecked(switchState);


        switch_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder adb = new AlertDialog.Builder(page_empreinte.this);
                View view = getLayoutInflater().inflate(R.layout.emp_alert_dialog, null);
                RelativeLayout button_ok = view.findViewById(R.id.button_OK);
                RelativeLayout button_annuler = view.findViewById(R.id.button_Annuler);
                TextView title = view.findViewById(R.id.dialog_title);

                if (switch_emp.isChecked()) {

                    adb.setView(view);
                    adb.setCancelable(false);

                    AlertDialog alert= adb.create();
                    alert.show();

                    button_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            FingerprintManager fingerprintManager = null;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                fingerprintManager = (FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);
                                switch_emp.setChecked(true);
                                SharedPreferences.Editor editor = getSharedPreferences("my_prefs", MODE_PRIVATE).edit();
                                editor.putBoolean("switch_state", switch_emp.isChecked());
                                editor.apply();
                            }

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                if (!fingerprintManager.isHardwareDetected()) {
                                    // Fingerprint sensor not available
                                    Toast.makeText(page_empreinte.this,"No hardware detected",Toast.LENGTH_LONG).show();
                                    switch_emp.setChecked(false);
                                    SharedPreferences.Editor editor = getSharedPreferences("my_prefs", MODE_PRIVATE).edit();
                                    editor.putBoolean("switch_state", switch_emp.isChecked());
                                    editor.apply();
                                    alert.dismiss();
                                    return;
                                }
                            }


                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                if (!fingerprintManager.hasEnrolledFingerprints()) {
                                    // No fingerprints registered
                                    Toast.makeText(page_empreinte.this,"Aucune empreinte enregistrer !",Toast.LENGTH_LONG).show();
                                    switch_emp.setChecked(false);
                                    SharedPreferences.Editor editor = getSharedPreferences("my_prefs", MODE_PRIVATE).edit();
                                    editor.putBoolean("switch_state", switch_emp.isChecked());
                                    editor.apply();
                                    alert.dismiss();
                                    return;
                                }
                            }
                            alert.dismiss();
                        }
                    });

                    button_annuler.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch_emp.setChecked(false);
                            SharedPreferences.Editor editor = getSharedPreferences("my_prefs", MODE_PRIVATE).edit();
                            editor.putBoolean("switch_state", switch_emp.isChecked());
                            editor.apply();
                            alert.dismiss();
                        }
                    });
                }
                if(switch_emp.isChecked()==false) {

                    title.setText("Voulez-vous disassocier votre empreinte à votre compte ?");
                    adb.setView(view);
                    adb.setCancelable(false);

                    AlertDialog alert= adb.create();
                    alert.show();

                    button_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch_emp.setChecked(false);
                            SharedPreferences.Editor editor = getSharedPreferences("my_prefs", MODE_PRIVATE).edit();
                            editor.putBoolean("switch_state", switch_emp.isChecked());
                            editor.apply();
                            alert.dismiss();
                        }
                    });

                    button_annuler.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch_emp.setChecked(true);
                            SharedPreferences.Editor editor = getSharedPreferences("my_prefs", MODE_PRIVATE).edit();
                            editor.putBoolean("switch_state", switch_emp.isChecked());
                            editor.apply();
                            alert.dismiss();
                        }
                    });

                }
            }
        });
        /*switch_emp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switch_emp.isChecked()) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(page_empreinte.this);
                    adb.setMessage("Voulez-vous associer votre empreinte à votre compte ?");
                    adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface arg0, int arg1) {

                            SharedPreferences.Editor editor = getSharedPreferences("my_prefs", MODE_PRIVATE).edit();
                            editor.putBoolean("switch_state", isChecked);
                            editor.apply();
                            switch_emp.setChecked(true);
                        }
                    });
                    adb.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface arg0, int arg1) {
                            switch_emp.setChecked(false);
                        }
                    });
                    AlertDialog alert= adb.create(); alert.show();
                }
                else {
                    AlertDialog.Builder adb = new AlertDialog.Builder(page_empreinte.this);
                    adb.setMessage("Voulez-vous disassocier votre empreinte à votre compte ?");
                    adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface arg0, int arg1) {


                            SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putBoolean("switchState", switch_emp.isChecked());
                            editor.apply();
                            switch_emp.setChecked(false);

                        }
                    });
                    adb.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface arg0, int arg1) {
                            switch_emp.setChecked(true);
                        }
                    });
                    AlertDialog alert= adb.create(); alert.show();
                }
            }
        });*/



    }
}