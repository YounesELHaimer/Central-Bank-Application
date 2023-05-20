package com.example.centralbank;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class page_settings extends AppCompatActivity {
    private RelativeLayout emp_container;
    private ImageView arrow_back;
    private ImageView power;
    private RelativeLayout password_container;
    private RelativeLayout notif_container;
    private ImageView eye1;
    private ImageView eye2;
    private ImageView eye3;
    private EditText ancien_pwd;
    private EditText nv_pwd;
    private EditText confirmer_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_settings);
        emp_container = findViewById(R.id.empreinte_container);
        arrow_back = findViewById(R.id.arrow_back);
        power = (ImageView) findViewById(R.id._power);
        password_container = findViewById(R.id.password_container);
        notif_container = findViewById(R.id.notif_container);





        arrow_back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                onBackPressed();


            }
        });

        power.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                AlertDialog.Builder adb = new AlertDialog.Builder(page_settings.this, R.style.CustomAlertDialogStyle);
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

        emp_container.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), page_empreinte.class);
                startActivity(nextScreen);


            }
        });

        password_container.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(page_settings.this);
                View view = getLayoutInflater().inflate(R.layout.pwd_alert_dialog, null);
                RelativeLayout button_ok = view.findViewById(R.id.button_OK);
                RelativeLayout button_annuler = view.findViewById(R.id.button_Annuler);
                TextView title = findViewById(R.id.dialog_title);
                adb.setView(view);
                adb.setCancelable(false);

                AlertDialog alert= adb.create();
                alert.show();

                eye1 = view.findViewById(R.id.HiddenEye1);
                eye2 = view.findViewById(R.id.HiddenEye2);
                eye3 = view.findViewById(R.id.HiddenEye3);
                ancien_pwd = view.findViewById(R.id.mon_ancien_pwd);
                nv_pwd = view.findViewById(R.id.mon_nv_password);
                confirmer_pwd = view.findViewById(R.id.confirmer_password);

                eye1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideOrShow(eye1,ancien_pwd);
                    }
                });

                eye2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideOrShow(eye2,nv_pwd);
                    }
                });

                eye3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideOrShow(eye3,confirmer_pwd);
                    }
                });

                button_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alert.dismiss();
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

        notif_container.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), page_settings_notif.class);
                startActivity(nextScreen);


            }
        });
    }



    public void hideOrShow(ImageView hiddeneye, EditText password){
        if(password.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            hiddeneye.setImageResource(R.drawable.eye_off);
        }
        else{
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            hiddeneye.setImageResource(R.drawable.blue_eye_);
        }
    }
}