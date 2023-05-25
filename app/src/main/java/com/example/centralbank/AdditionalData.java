package com.example.centralbank;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AdditionalData extends AppCompatActivity {

    private EditText editTextAddress, editTextDOB, editTextFamilyStatus, editTextProfessionalStatus;
    private Spinner spinner_fam;
    private Spinner spinner_prof;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_data);

        // Initialize Firebase
        // Make sure you have properly initialized Firebase in your project before accessing the Realtime Database

        // Get references to EditText fields
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextDOB = findViewById(R.id.editTextDOB);
        //editTextFamilyStatus = findViewById(R.id.editTextFamilyStatus);
        //editTextProfessionalStatus = findViewById(R.id.editTextProfessionalStatus);
        spinner_fam = findViewById(R.id.spinner);
        spinner_prof = findViewById(R.id.spinner_prof);



        String[] options = getResources().getStringArray(R.array.options_fam);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_fam.setAdapter(adapter);


        String[] options_prof = getResources().getStringArray(R.array.options_prof);
        ArrayAdapter<String> adapter_prof = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options_prof);
        adapter_prof.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_prof.setAdapter(adapter_prof);





        String email = getIntent().getStringExtra("email");

        // Get a reference to the Firebase Realtime Database
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersRef = databaseRef.child("users");

        // Retrieve the user's email from the EditText field
        String userEmail = getIntent().getStringExtra("email");
        String name = getIntent().getStringExtra("name");


        String lastName = getIntent().getStringExtra("lastName");

        findViewById(R.id.btn_prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Image.class);
                startActivity(intent);
            }
        });

        // Update the user's additional data in the database
        RelativeLayout btnSaveData = findViewById(R.id.btn_Next);
        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = editTextAddress.getText().toString();
                String dob = editTextDOB.getText().toString();
                String selectedOptionFam = spinner_fam.getSelectedItem().toString();
                String selectedOptionProf = spinner_prof.getSelectedItem().toString();

                // Query the users based on the email field
                Query query = usersRef.orderByChild("email").equalTo(userEmail);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Loop through the query results (should be only one user)
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            // Get the reference to the user
                            DatabaseReference userRef = userSnapshot.getRef();

                            // Update the additional data fields
                            userRef.child("address").setValue(address);
                            userRef.child("dateOfBirth").setValue(dob);
                            userRef.child("familyStatus").setValue(selectedOptionFam);
                            userRef.child("professionalStatus").setValue(selectedOptionProf);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle any errors
                        Log.e("AdditionalData", "Failed to query users: " + databaseError.getMessage());
                    }
                });
                Intent intent = new Intent(getApplicationContext(), Agencies.class);
                intent.putExtra("email", email);
                intent.putExtra("lastName", lastName);
                intent.putExtra("name", name);

                startActivity(intent);
                finish();
            }
        });

    }
}
