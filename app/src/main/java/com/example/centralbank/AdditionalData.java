package com.example.centralbank;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_data);

        // Initialize Firebase
        // Make sure you have properly initialized Firebase in your project before accessing the Realtime Database

        // Get references to EditText fields
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextDOB = findViewById(R.id.editTextDOB);
        editTextFamilyStatus = findViewById(R.id.editTextFamilyStatus);
        editTextProfessionalStatus = findViewById(R.id.editTextProfessionalStatus);

        String email = getIntent().getStringExtra("email");

        // Get a reference to the Firebase Realtime Database
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersRef = databaseRef.child("users");

        // Retrieve the user's email from the EditText field
        String userEmail = getIntent().getStringExtra("email");

        // Update the user's additional data in the database
        Button btnSaveData = findViewById(R.id.btn_Next);
        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = editTextAddress.getText().toString();
                String dob = editTextDOB.getText().toString();
                String familyStatus = editTextFamilyStatus.getText().toString();
                String professionalStatus = editTextProfessionalStatus.getText().toString();

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
                            userRef.child("familyStatus").setValue(familyStatus);
                            userRef.child("professionalStatus").setValue(professionalStatus);
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
                startActivity(intent);
                finish();
            }
        });

    }
}
