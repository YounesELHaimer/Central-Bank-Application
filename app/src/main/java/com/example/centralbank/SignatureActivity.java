package com.example.centralbank;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.Random;

public class SignatureActivity extends AppCompatActivity {

    private SignaturePad signaturePad;
    RelativeLayout btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        signaturePad = findViewById(R.id.signaturePad);
        btnSave = findViewById(R.id.btnSave);

        RelativeLayout clearButton = findViewById(R.id.effacer);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signaturePad.clear();
            }
        });

        findViewById(R.id.btn_prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AdditionalData.class);
                startActivity(intent);
                finish();
            }
        });



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signaturePad.isEmpty()) {
                    Toast.makeText(SignatureActivity.this, "No signature captured.", Toast.LENGTH_SHORT).show();
                } else {
                    saveSignature();
                    Random random = new Random();

                    int num1 = random.nextInt(8999) + 1000;
                    int num2 = random.nextInt(8999) + 1000;
                    int num3 = random.nextInt(8999) + 1000;

                    // Create the account number string with the specified format
                    String accountNumber = "5321  " + num1 + "  " + num2 + "  " + num3 ;

                    String email = getIntent().getStringExtra("email");

                    // Set the dateExp
                    String dateExp = "      01/28";

                    // Get the user email
                    String user =  getIntent().getStringExtra("name") +" "+ getIntent().getStringExtra("lastName");

                    // Set the boolean attributes
                    boolean isBlocked = true;
                    boolean isSansContactEnabled = true;

                    // Create a new Card object
                    Card card = new Card(email,accountNumber, dateExp, user, isBlocked, isSansContactEnabled);

                    // Create a reference to the 'cards' node in the database
                    DatabaseReference cardsRef = FirebaseDatabase.getInstance().getReference().child("cards");

                    // Push the card to the database
                    String cardId = cardsRef.push().getKey(); // Generate a unique key for the card
                    DatabaseReference cardRef = cardsRef.child(cardId);
                    cardRef.setValue(card)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Card saved successfully
                                    Toast.makeText(SignatureActivity.this, "Card saved successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Failed to save card
                                    Toast.makeText(SignatureActivity.this, "Failed to save card", Toast.LENGTH_SHORT).show();
                                }
                            });

                    // Generate random values for the RIB fields

                    Float Solde = Float.valueOf(1000);
                    String codeBanque = "112";
                    String codeVille = "112";
                    String prefixe = "3428";
                    String numeroDeCompte = String.format("%012d", random.nextInt(1000000000));
                    String chiffresCles = String.format("%02d", random.nextInt(100));

// Create a new RIB object with the generated values
                    RIB rib = new RIB(email, codeBanque, codeVille, prefixe, numeroDeCompte, chiffresCles, user);

// Get the reference to the "ribs" node in the database
                    DatabaseReference ribRef = FirebaseDatabase.getInstance().getReference().child("ribs");

// Generate a new unique key for the RIB entry
                    String ribKey = ribRef.push().getKey();

// Save the RIB object to the database
                    ribRef.child(ribKey).setValue(rib)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // RIB data saved successfully
                                    Toast.makeText(SignatureActivity.this, "RIB saved successfully", Toast.LENGTH_SHORT).show();

                                    // You can perform any additional actions or show a success message here
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // An error occurred while saving the RIB data
                                    // Handle the error or show an error message here
                                }
                            });

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference usersRef = database.getReference("users");

                    usersRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    String userId = snapshot.getKey();
                                    usersRef.child(userId).child("numeroDeCompte").setValue(numeroDeCompte)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    // Numerodecompte added successfully for the user
                                                    Toast.makeText(SignatureActivity.this, "Numerodecompte added successfully", Toast.LENGTH_SHORT).show();


                                                    // Proceed with your desired action
                                                    // e.g., start a new activity or perform additional operations
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    // An error occurred while adding numerodecompte for the user
                                                    // Handle the error or show an error message here
                                                }
                                            });
                                }
                            } else {
                                // User with the specified email does not exist
                                // Handle the case where user is not found
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // An error occurred while querying the database
                            // Handle the error or show an error message here
                        }
                    });
// Assuming you have already initialized your Firebase Database reference
// and other necessary variables

                    usersRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    String userId = snapshot.getKey();

                                    // Convert the Solde string to float
                                    float soldeValue = Float.parseFloat(String.valueOf(Solde));

                                    // Set the value of the "Solde" attribute as float
                                    usersRef.child(userId).child("Solde").setValue(soldeValue)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    // Solde added successfully for the user
                                                    Toast.makeText(SignatureActivity.this, "Solde added successfully", Toast.LENGTH_SHORT).show();


                                                    // Proceed with your desired action
                                                    // e.g., start a new activity or perform additional operations
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    // An error occurred while adding Solde for the user
                                                    // Handle the error or show an error message here
                                                }
                                            });
                                }
                            } else {
                                // User with the specified email does not exist
                                // Handle the case where user is not found
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // An error occurred while querying the database
                            // Handle the error or show an error message here
                        }
                    });


                    Intent intent = new Intent(getApplicationContext(), last_page_register.class);


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
