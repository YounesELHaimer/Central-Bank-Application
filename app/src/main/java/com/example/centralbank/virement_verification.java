package com.example.centralbank;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class virement_verification extends AppCompatActivity {

    private ImageView _power;
    private ImageView bell;
    private ImageView settings;
    private ImageView invoice;
    private ImageView card;
    private ImageView home10;
    EditText et1, et2, et3, et4, et5, et6;
    RelativeLayout btnsubmit;
    String getbackendotp;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.virement_verification);

        et1 = findViewById(R.id.inputotp1);
        et2 = findViewById(R.id.inputotp2);
        et3 = findViewById(R.id.inputotp3);
        et4 = findViewById(R.id.inputotp4);
        et5 = findViewById(R.id.inputotp5);
        et6 = findViewById(R.id.inputotp6);

        progressBar = findViewById(R.id.probar2);

        invoice = findViewById(R.id.invoice);
        home10 = findViewById(R.id.home10);
        _power = (ImageView) findViewById(R.id._power);
        bell = (ImageView) findViewById(R.id.bell);
        invoice = (ImageView) findViewById(R.id.invoice);
        card = findViewById(R.id.credit_card);
        settings = findViewById(R.id.gears);


        String email = getIntent().getStringExtra("email");
        String numeroDeCompte = getIntent().getStringExtra("numeroDeCompte");
        String numeroDeCompteBenef = getIntent().getStringExtra("numeroDeCompteBenef");
        String montantStr = getIntent().getStringExtra("montantStr");


        ///////////////////////////////////////// verification smsdebut
        //get mobile number from mainActivty to this
        TextView textView = findViewById(R.id.txtmobileno);
        textView.setText(String.format(
                "+212-%S",getIntent().getStringExtra("phone")
        ));

        getbackendotp = getIntent().getStringExtra("backendotp");


        btnsubmit = findViewById(R.id.btnsubmit);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!et1.getText().toString().trim().isEmpty() && !et2.getText().toString().trim().isEmpty()
                        && !et3.getText().toString().trim().isEmpty()
                        && !et4.getText().toString().trim().isEmpty()
                        && !et5.getText().toString().trim().isEmpty()
                        && !et6.getText().toString().trim().isEmpty()) {

                    // marging user's input in a string
                    String getuserotp = et1.getText().toString() +
                            et2.getText().toString() +
                            et3.getText().toString() +
                            et4.getText().toString() +
                            et5.getText().toString() +
                            et6.getText().toString();

                    if (getbackendotp != null) {

                        progressBar.setVisibility(View.VISIBLE);
                        btnsubmit.setVisibility(View.INVISIBLE);


                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(getbackendotp, getuserotp);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {


                                        progressBar.setVisibility(View.GONE);
                                        btnsubmit.setVisibility(View.VISIBLE);

                                        if (task.isSuccessful()) {
                                            float montant = Float.parseFloat(montantStr);
                                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                                            DatabaseReference usersRef = database.getReference("users");
                                            // Find the user with the specified numeroDeCompte
                                            usersRef.orderByChild("numeroDeCompte").equalTo(numeroDeCompteBenef).addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    if (dataSnapshot.exists()) {
                                                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                            String userId = snapshot.getKey();

                                                            // Get the current Solde value from the user account
                                                            float currentSolde = snapshot.child("Solde").getValue(Float.class);

                                                            // Calculate the new Solde value
                                                            float newSolde = currentSolde + montant;

                                                            // Set the new Solde value in the Firebase Realtime Database
                                                            usersRef.child(userId).child("Solde").setValue(newSolde)
                                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void aVoid) {
                                                                            // New Solde value added successfully for the user
                                                                            Toast.makeText(virement_verification.this, "New Solde value added successfully", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    })
                                                                    .addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {
                                                                            // An error occurred while adding the new Solde value for the user
                                                                            // Handle the error or show an error message here
                                                                        }
                                                                    });
                                                        }
                                                    } else {
                                                        // User with the specified numeroDeCompte does not exist
                                                        // Handle the case where user is not found
                                                    }

                                                    // Find the connected user based on the email
                                                    usersRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                            if (dataSnapshot.exists()) {
                                                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                                    String connectedUserId = snapshot.getKey();

                                                                    // Get the current Solde value from the connected user's account
                                                                    float connectedUserSolde = snapshot.child("Solde").getValue(Float.class);

                                                                    // Calculate the updated Solde value
                                                                    float updatedConnectedUserSolde = connectedUserSolde - montant;

                                                                    // Set the updated Solde value in the Firebase Realtime Database for the connected user
                                                                    usersRef.child(connectedUserId).child("Solde").setValue(updatedConnectedUserSolde)
                                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                @Override
                                                                                public void onSuccess(Void aVoid) {
                                                                                    // Updated Solde value for the connected user added successfully
                                                                                    Toast.makeText(virement_verification.this, "Updated Solde value for connected user added successfully", Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            })
                                                                            .addOnFailureListener(new OnFailureListener() {
                                                                                @Override
                                                                                public void onFailure(@NonNull Exception e) {
                                                                                    // An error occurred while adding the updated Solde value for the connected user
                                                                                    // Handle the error or show an error message here
                                                                                }
                                                                            });
                                                                }
                                                            } else {
                                                                // Connected user with the specified email does not exist
                                                                // Handle the case where connected user is not found
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                                            // An error occurred while querying the database for the connected user
                                                            // Handle the error or show an error message here
                                                        }
                                                    });
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                                    // An error occurred while querying the database for the user
                                                    // Handle the error or show an error message here
                                                }
                                            });
                                            usersRef.orderByChild("numeroDeCompte").equalTo(numeroDeCompteBenef).addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    if (dataSnapshot.exists()) {
                                                        // Retrieve the first matching user (assuming unique `numeroDeCompte`)
                                                        DataSnapshot userSnapshot = dataSnapshot.getChildren().iterator().next();

                                                        // Retrieve the nameBenef value
                                                        String name1 = userSnapshot.child("name").getValue(String.class);
                                                        String name2 = userSnapshot.child("lastName").getValue(String.class);
                                                        String nameBenef = "Mr " + name1 + " " + name2;

                                                        // Use the nameBenef value as needed
                                                        DatabaseReference virementsRef = FirebaseDatabase.getInstance().getReference().child("virements");

                                                        String name = getIntent().getStringExtra("name");
                                                        String typeDeTransaction = "Virement mobile";
                                                        Date currentDate = new Date();

                                                        Virement virement = new Virement(typeDeTransaction, montant, currentDate, name, numeroDeCompte , numeroDeCompteBenef ,nameBenef );

                                                        virementsRef.push().setValue(virement)
                                                                .addOnSuccessListener(aVoid -> {
                                                                    // Virement successfully written to the database
                                                                    // ...
                                                                })
                                                                .addOnFailureListener(e -> {
                                                                    // Error occurred while writing virement to the database
                                                                    // ...
                                                                });

                                                        Log.d("FirebaseData", "nameBenef: " + nameBenef);
                                                    } else {
                                                        Log.d("FirebaseData", "No user found with numeroDeCompteBenef: " + numeroDeCompteBenef);
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {
                                                    // Error handling
                                                    // ...
                                                }
                                            });

                                            Toast.makeText(virement_verification.this, "Success", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), page_home_activity.class);

                                            intent.putExtra("email", email);
                                            intent.putExtra("numeroDeCompte", numeroDeCompte);
                                            intent.putExtra("numeroDeCompteBenef", numeroDeCompteBenef);


                                            startActivity(intent);
//                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(virement_verification.this, "Enter corrent SMS", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                    } else {
                        Toast.makeText(virement_verification.this, "Please check internet", Toast.LENGTH_SHORT).show();
                    }

                    //Toast.makeText(MainActivity2.this, "OTP Verify", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(virement_verification.this, "Please fill all number", Toast.LENGTH_SHORT).show();
                }


                // movenumtonext();



            }
        });

        findViewById(R.id.sendotp_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+212" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        virement_verification.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {


                                Toast.makeText(virement_verification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {


                                getbackendotp = newbackendotp;
                                Toast.makeText(virement_verification.this, "OTP Send Sucessfuly", Toast.LENGTH_SHORT).show();


                            }
                        }

                );

            }
        });

        movenumtonext(); //move num to next


        ///////////////////////////////////////// verification smsfin







        home10.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), page_home_activity.class);
                nextScreen.putExtra("email", email);
                nextScreen.putExtra("numeroDeCompte", numeroDeCompte);

                startActivity(nextScreen);


            }
        });

        card.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), page_card.class);
                nextScreen.putExtra("email", email);
                nextScreen.putExtra("numeroDeCompte", numeroDeCompte);

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
                        nextScreen.putExtra("email", email);

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
                nextScreen.putExtra("email", email);
                nextScreen.putExtra("numeroDeCompte", numeroDeCompte);

                startActivity(nextScreen);


            }
        });


        settings.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), page_settings.class);
                nextScreen.putExtra("email", email);
                nextScreen.putExtra("numeroDeCompte", numeroDeCompte);

                startActivity(nextScreen);

            }
        });
    }

    private void movenumtonext() {



        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()) {
                    et2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()) {
                    et3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()) {
                    et4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()) {
                    et5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()) {
                    et6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

}