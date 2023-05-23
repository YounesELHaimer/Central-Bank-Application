package com.example.centralbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class page_rib extends AppCompatActivity {

    private ImageView home;
    private ImageView card;
    private ImageView power;
    private ImageView _transaction__2_;
    private ImageView invoice;
    private ImageView settings;
    private ImageView telecharger_btn;
    private ProgressDialog progressDialog;

    TextView code_banque,code_ville_chiffre,prefixe_chiffre,numero_de_compte_chiffre,chiffres_cles_chiffre;
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

        String email = getIntent().getStringExtra("email");
        String numeroDeCompte = getIntent().getStringExtra("numeroDeCompte");

        TextView rib = findViewById(R.id.numero_de_compte);
        rib.setText(numeroDeCompte);


        code_banque = findViewById(R.id.code_banque_chiffre);
        code_ville_chiffre = findViewById(R.id.code_ville_chiffre);
        prefixe_chiffre = findViewById(R.id.prefixe_chiffre);
        numero_de_compte_chiffre = findViewById(R.id.numero_de_compte_chiffre);
        chiffres_cles_chiffre = findViewById(R.id.chiffres_cles_chiffre);
        TextView name = findViewById(R.id.nom_client);

        DatabaseReference ribsRef = FirebaseDatabase.getInstance().getReference().child("ribs");

// Add a ValueEventListener to listen for changes in the RIB details
        ribsRef.orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ribSnapshot : dataSnapshot.getChildren()) {
                        // Retrieve the RIB object from the snapshot
                        RIB rib = ribSnapshot.getValue(RIB.class);

                        // Update the UI with the RIB details
                        if (rib != null) {
                            Log.d("TAG", "nonnull ");
                            code_banque.setText(rib.getCodeBanque());
                            code_ville_chiffre.setText(rib.getCodeVille());
                            prefixe_chiffre.setText(rib.getPrefixe());
                            numero_de_compte_chiffre.setText(rib.getNumeroDeCompte());
                            chiffres_cles_chiffre.setText(rib.getChiffresCles());
                            name.setText(rib.getUser());
                        } else {
                            Log.d("TAG", "null ");}
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors or interruptions in retrieving the RIB details
            }
        });



        home.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), page_home_activity.class);
                nextScreen.putExtra("email", email);
                nextScreen.putExtra("numeroDeCompte", numeroDeCompte);
                startActivity(nextScreen);


            }
        });


        _transaction__2_.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent nextScreen = new Intent(getApplicationContext(), page_virement_activity.class);
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

        telecharger_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                Intent nextScreen = new Intent(getApplicationContext(), rip_pdf.class);
                nextScreen.putExtra("email", email);
                nextScreen.putExtra("numeroDeCompte", numeroDeCompte);
                startActivity(nextScreen);
                progressDialog.dismiss();

            }
        });

    }
}