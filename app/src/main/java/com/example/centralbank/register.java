package com.example.centralbank;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {


    TextInputEditText   verifyPasswordEditText , editTextPassword;

    Button buttonReg;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;
    // @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            Intent intent = new Intent(getApplicationContext(),page_home_activity.class);
//            startActivity(intent);
//            finish();
//        }
//    }
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
        textView = findViewById(R.id.loginNow);

        String email = getIntent().getStringExtra("email");
        String password = editTextPassword.getText().toString();

        String name = getIntent().getStringExtra("firstName");


        String lastName = getIntent().getStringExtra("lastName");

        String phone = getIntent().getStringExtra("phone");


        String operator = getIntent().getStringExtra("selectedOption");

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), first_page_activity.class);
                startActivity(intent);
                finish();
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



                                        Toast.makeText(register.this, "Authentication succeed.",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Image.class);
                                        intent.putExtra("email", email);
                                        intent.putExtra("lastName", lastName);
                                        intent.putExtra("name", name);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(register.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
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
        Button btnPrev = findViewById(R.id.btn_prev);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PhoneActivity.class);
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

}
