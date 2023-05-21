package com.example.centralbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class SignatureActivity extends AppCompatActivity {

    private SignaturePad signaturePad;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        signaturePad = findViewById(R.id.signaturePad);
        btnSave = findViewById(R.id.btnSave);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signaturePad.isEmpty()) {
                    Toast.makeText(SignatureActivity.this, "No signature captured.", Toast.LENGTH_SHORT).show();
                } else {
                    saveSignature();
                    Intent intent = new Intent(getApplicationContext(), first_page_activity.class);


                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void saveSignature() {
        Bitmap signatureBitmap = signaturePad.getSignatureBitmap();
        String email = getIntent().getStringExtra("email");

        if (signatureBitmap != null) {
            // Convert the signature bitmap to a byte array or save it to a file
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            signatureBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] data = baos.toByteArray();

            // Create a Firebase Storage reference
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            StorageReference signatureRef = storageRef.child("signatures/" + email + ".png"); // Set a unique path for each user's signature image

            // Upload the signature image to Firebase Storage
            UploadTask uploadTask = signatureRef.putBytes(data);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // Signature image uploaded successfully, retrieve the download URL
                    signatureRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String imageUrl = uri.toString();


                            // Now you have the download URL of the signature image,
                            // proceed to save it in the real-time database
                            saveSignatureImageUrl(imageUrl);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error occurred while retrieving the download URL
                            // Handle the error appropriately
                            Toast.makeText(SignatureActivity.this, "Failed to retrieve signature image URL.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    // Error occurred while uploading the signature image
                    // Handle the error appropriately
                    Toast.makeText(SignatureActivity.this, "Failed to upload signature image.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "No signature captured.", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveSignatureImageUrl(String imageUrl) {
        String email = getIntent().getStringExtra("email");

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");

        usersRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DataSnapshot userSnapshot = dataSnapshot.getChildren().iterator().next();
                    DatabaseReference userRef = userSnapshot.getRef();
                    userRef.child("signatureImageUrl").setValue(imageUrl)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Signature image URL saved successfully
                                    Toast.makeText(SignatureActivity.this, "Signature saved successfully.", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // An error occurred while saving the signature image URL
                                    // Handle the error appropriately
                                    Toast.makeText(SignatureActivity.this, "Failed to save signature.", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error appropriately
            }
        });
    }
}
