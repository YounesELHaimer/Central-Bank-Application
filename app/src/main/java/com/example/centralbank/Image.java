package com.example.centralbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Image extends AppCompatActivity {
    TextView captureTxt;

    private ImageView captureImage, captureImage2, captureImage3;
    private static final int REQUEST_CAMERA_PERMISSION = 100;
    private static final int REQUEST_IMAGE_CAPTURE = 200;
    private static final int REQUEST_IMAGE_PICK = 300;

    private AlertDialog alertDialog;
    private File photoFile;
    private Uri photoUri;
    private int imageCount = 0;

    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        FirebaseApp.initializeApp(this);

        // Get a reference to the 'users' node in the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        usersRef = database.getReference("users");
        String email = getIntent().getStringExtra("email");


        captureTxt = findViewById(R.id.textView3);
        captureImage = findViewById(R.id.imageView2);
        captureImage2 = findViewById(R.id.imageView4);
        captureImage3 = findViewById(R.id.imageView7);

        Button btnNext = findViewById(R.id.save);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdditionalData.class);
                intent.putExtra("email", email);
                startActivity(intent);
                finish();

            }
        });


        captureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageCount = 1;
                Log.d("ImageCount", "imageCount: " + imageCount);
                showImagePickerDialog();
            }
        });

        captureImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageCount = 2;
                Log.d("ImageCount", "imageCount: " + imageCount);
                showImagePickerDialog();
            }
        });

        captureImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageCount = 3;
                Log.d("ImageCount", "imageCount: " + imageCount);
                showImagePickerDialog();
            }
        });
    }

    private void showImagePickerDialog() {
        final String[] options = {"Take Photo", "Choose from Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an option");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        // Check camera permission
                        if (ContextCompat.checkSelfPermission(
                                Image.this,
                                Manifest.permission.CAMERA
                        ) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(
                                    Image.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    REQUEST_CAMERA_PERMISSION
                            );
                        } else {
                            openCamera();
                        }
                        break;
                    case 1:
                        openGallery();
                        break;
                }
            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }

    private void validateImage() {
        if (photoUri != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
                switch (imageCount) {
                    case 1:
                        captureImage.setImageBitmap(bitmap);
                        break;
                    case 2:
                        captureImage2.setImageBitmap(bitmap);
                        break;
                    case 3:
                        captureImage3.setImageBitmap(bitmap);
                        break;
                }

                saveImageToFirebaseStorage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveImageToFirebaseStorage(Bitmap bitmap) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        String email = getIntent().getStringExtra("email");
        String folderName = email.replace(".", "_"); // Replace '.' with '_' in email for folder name

        StorageReference folderRef = storageRef.child(folderName);

        // Convert bitmap to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageData = baos.toByteArray();

        // Generate a unique image filename
        String imageFileName = "android_image_" + imageCount + ".jpg";

        StorageReference imageRef = folderRef.child(imageFileName);

        // Upload the image byte array to Firebase Storage
        UploadTask uploadTask = imageRef.putBytes(imageData);

        uploadTask.addOnSuccessListener(taskSnapshot -> {
            // Image uploaded successfully
            // Retrieve the download URL
            imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                String imageURL = uri.toString();
                saveImageURLToDatabase(imageURL);
            }).addOnFailureListener(e -> {
                // Handle the error appropriately
            });
        }).addOnFailureListener(e -> {
            // Image upload failed
            // Handle the error appropriately
        });
    }


    private void saveImageURLToDatabase(String imageURL) {
        String email = getIntent().getStringExtra("email");
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");

        usersRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DataSnapshot userSnapshot = dataSnapshot.getChildren().iterator().next();
                    DatabaseReference userRef = userSnapshot.getRef();

                    if (imageCount == 1) {
                        userRef.child("frontCIN").setValue(imageURL);
                    } else if (imageCount == 2) {
                        userRef.child("backCIN").setValue(imageURL);
                    } else if (imageCount == 3) {
                        userRef.child("face").setValue(imageURL);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error appropriately
            }
        });
    }



    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = createPhotoFile();
            if (photoFile != null) {
                photoUri = FileProvider.getUriForFile(this, "com.example.centralbank.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createPhotoFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {
            File photoFile = File.createTempFile(imageFileName, ".jpg", storageDir);
            return photoFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void openGallery() {
        Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhotoIntent, REQUEST_IMAGE_PICK);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE_CAPTURE:
                    validateImage();
                    break;
                case REQUEST_IMAGE_PICK:
                    if (data != null && data.getData() != null) {
                        photoUri = data.getData();
                        validateImage();
                    }
                    break;
            }
        }
    }
}
