package com.example.centralbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class page_card extends AppCompatActivity {
    private ImageView home;
    private ImageView card;
    private ImageView power;
    private ImageView _transaction__2_;
    private ImageView invoice;
    private ImageView settings;
    private Button copier_button;
    private TextView card_id,id_name,date_exp;

    DatabaseReference cardRef;

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
        id_name = findViewById(R.id.id_name);
        date_exp = findViewById(R.id.date_exp);
        cardRef = FirebaseDatabase.getInstance().getReference().child("cards");
        Switch switchBlockage = findViewById(R.id.switch_blocage);
        Switch switchPaiementSansContact = findViewById(R.id.switch_paiement_sans_contact);

        String email = getIntent().getStringExtra("email");


// Add a ValueEventListener to listen for changes in the card details

// Get the reference to the "cards" node in the database
        cardRef = FirebaseDatabase.getInstance().getReference().child("cards");

// Add a ValueEventListener to listen for changes in the card details
        cardRef.orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot cardSnapshot : dataSnapshot.getChildren()) {
                        // Retrieve the card object from the snapshot
                        Card card = cardSnapshot.getValue(Card.class);

                        // Update the UI with the card details
                        if (card != null) {
                            card_id.setText(card.getAccountNumber());
                            id_name.setText("Mr " +card.getUser());
                            date_exp.setText(card.getDateExp());

                            // Update the switches based on the card details
                            switchBlockage.setChecked(card.isBlockage());
                            switchPaiementSansContact.setChecked(card.isSansContact());

                            // Add an OnCheckedChangeListener to switchBlockage
                            switchBlockage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    // Update the blockage field in the card object
                                    card.setBlockage(isChecked);

                                    // Save the updated card object back to the database
                                    cardSnapshot.getRef().setValue(card);
                                }
                            });

                            // Add an OnCheckedChangeListener to switchPaiementSansContact
                            switchPaiementSansContact.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    // Update the sansContact field in the card object
                                    card.setSansContact(isChecked);

                                    // Save the updated card object back to the database
                                    cardSnapshot.getRef().setValue(card);
                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors or interruptions in retrieving the card details
            }
        });



        home.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), page_home_activity.class);
                nextScreen.putExtra("email", email);
                startActivity(nextScreen);


            }
        });


        _transaction__2_.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), page_virement_activity.class);
                nextScreen.putExtra("email", email);
                startActivity(nextScreen);


            }
        });

        card.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), page_card.class);
                nextScreen.putExtra("email", email);
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
                startActivity(nextScreen);


            }
        });

        settings.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), page_settings.class);
                nextScreen.putExtra("email", email);
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