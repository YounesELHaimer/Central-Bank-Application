package com.example.centralbank;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {


    EditText verifyPasswordEditText , editTextPassword;
    private ImageView eye2;
    private ImageView eye3;

    RelativeLayout buttonReg;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        editTextPassword = findViewById(R.id.password);
        verifyPasswordEditText = findViewById(R.id.verifypassword);

        buttonReg = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progressBar);
        eye2 = findViewById(R.id.HiddenEye2);
        eye3 = findViewById(R.id.HiddenEye3);

        String email = getIntent().getStringExtra("email");
        String password = editTextPassword.getText().toString();

        String name = getIntent().getStringExtra("firstName");


        String lastName = getIntent().getStringExtra("lastName");

        String phone = getIntent().getStringExtra("phone");


        String operator = getIntent().getStringExtra("selectedOption");

        eye2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideOrShow(eye2, editTextPassword);
            }
        });

        eye3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideOrShow(eye3, verifyPasswordEditText);
            }
        });


        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

//                String verifyPassword = verifyPasswordEditText.getText().toString();
//
//                String email = getIntent().getStringExtra("email");
//                Log.e("TAG", email );
//                String password = editTextPassword.getText().toString();
//                Log.e("TAG", password );

                String verifyPassword = verifyPasswordEditText.getText().toString();
                String email = getIntent().getStringExtra("email");
                String name = getIntent().getStringExtra("firstName");
                String lastName = getIntent().getStringExtra("lastName");

                String password = editTextPassword.getText().toString();



                if(TextUtils.isEmpty(verifyPassword)){
                    Toast.makeText(register.this,"Enter verification",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(register.this,"Enter password",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (password.equals(verifyPassword)) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {
                                        sendData();
                                        Intent intent = new Intent(getApplicationContext(), Image.class);
                                        intent.putExtra("email", email);
                                        intent.putExtra("lastName", lastName);
                                        intent.putExtra("name", name);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(register.this, "Opreation failed.",Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });

                } else {
                    Toast.makeText(register.this,"Password don't match",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

            }
        });

        findViewById(R.id.btn_prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Gmail.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void sendData() {

        String name = getIntent().getStringExtra("firstName");


        String lastName = getIntent().getStringExtra("lastName");

        String phone = getIntent().getStringExtra("phone");


        String operator = getIntent().getStringExtra("selectedOption");

        String email = getIntent().getStringExtra("email");

        String password = editTextPassword.getText().toString();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        User user = new User(name, lastName, phone, operator ,  email , password); // create a User object with the data



        String userId = myRef.push().getKey(); // generate a unique ID for the user
        myRef.child(userId).setValue(user);

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
