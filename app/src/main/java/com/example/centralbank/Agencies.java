package com.example.centralbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class Agencies extends AppCompatActivity {

    private Spinner spinnerCity;
    private Spinner spinnerAgency;

    private DatabaseReference agenciesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agencies);

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        agenciesRef = databaseRef.child("agencies");

        spinnerCity = findViewById(R.id.spinnerCity);
        spinnerAgency = findViewById(R.id.spinnerAgency);

        // Retrieve city names from the "agencies" table in the database
        retrieveCities();

        // Set up agency spinner based on selected city
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = spinnerCity.getSelectedItem().toString();
                retrieveAgencies(selectedCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
        // Replace the hardcoded email with your email string variable
        final String email = getIntent().getStringExtra("email");
        String name = getIntent().getStringExtra("name");


        String lastName = getIntent().getStringExtra("lastName");


        findViewById(R.id.btn_prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Image.class);
                startActivity(intent);
            }
        });


        RelativeLayout btnNext = findViewById(R.id.btn_Nex);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String selectedCity = spinnerCity.getSelectedItem().toString();
                final String selectedAgencyName = spinnerAgency.getSelectedItem().toString();

                DatabaseReference usersRef = databaseRef.child("users");
                Query query = usersRef.orderByChild("email").equalTo(email);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String userId = snapshot.getKey();
                            DatabaseReference userRef = usersRef.child(userId);
                            Agency selectedAgency = new Agency(selectedCity, selectedAgencyName);
                            userRef.child("agency").setValue(selectedAgency);

                        }
                        Toast.makeText(Agencies.this, "Authentication succeed.",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), SignatureActivity.class);
                        intent.putExtra("email", email);
                        intent.putExtra("lastName", lastName);
                        intent.putExtra("name", name);

                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle error
                    }
                });
            }
        });

    }

    private void retrieveCities() {
        agenciesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> citiesList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Agency agency = snapshot.getValue(Agency.class);
                    String city = agency.getCity();
                    if (!citiesList.contains(city)) {
                        citiesList.add(city);
                    }
                }
                // Set up adapter for city spinner
                ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(
                        Agencies.this,
                        android.R.layout.simple_spinner_item,
                        citiesList
                );
                cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCity.setAdapter(cityAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }

    private void retrieveAgencies(String selectedCity) {
        agenciesRef.orderByChild("city").equalTo(selectedCity).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> agenciesList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Agency agency = snapshot.getValue(Agency.class);
                    List<String> agencyNames = agency.getNames();
                    agenciesList.addAll(agencyNames);
                }
                // Set up adapter for agency spinner
                ArrayAdapter<String> agencyAdapter = new ArrayAdapter<>(
                        Agencies.this,
                        android.R.layout.simple_spinner_item,
                        agenciesList
                );
                agencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerAgency.setAdapter(agencyAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }

}
