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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
        String email = getIntent().getStringExtra("email");
        String numeroDeCompte = getIntent().getStringExtra("numeroDeCompte");






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
                        nextScreen.putExtra("email", email);
                        nextScreen.putExtra("numeroDeCompte", numeroDeCompte);
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
                nextScreen.putExtra("email", email);
                nextScreen.putExtra("numeroDeCompte", numeroDeCompte);
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

                AlertDialog alert = adb.create();
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
                        hideOrShow(eye1, ancien_pwd);
                    }
                });

                eye2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideOrShow(eye2, nv_pwd);
                    }
                });

                eye3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hideOrShow(eye3, confirmer_pwd);
                    }
                });

                button_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = "badralqaraoui@gmail.com";  // Email to search for in the database
                        String oldPassword = ancien_pwd.getText().toString();
                        String newPassword = nv_pwd.getText().toString();
                        String confirmPassword = confirmer_pwd.getText().toString();

                        // Check if the new password and confirmation match
                        if (!newPassword.equals(confirmPassword)) {
                            Toast.makeText(page_settings.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // Retrieve the user from the database
                        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
                        Query query = usersRef.orderByChild("email").equalTo(email);
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                    User user = userSnapshot.getValue(User.class);
                                    if (user != null && user.getPassword().equals(oldPassword)) {
                                        // Old password is correct, update the password
                                        userSnapshot.getRef().child("password").setValue(newPassword)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(page_settings.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                                                        alert.dismiss();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(page_settings.this, "Failed to change password: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                        return;
                                    }
                                }

                                // No user found with the specified email or incorrect old password
                                Toast.makeText(page_settings.this, "Invalid email or old password", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(page_settings.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
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
                nextScreen.putExtra("email", email);
                nextScreen.putExtra("numeroDeCompte", numeroDeCompte);
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