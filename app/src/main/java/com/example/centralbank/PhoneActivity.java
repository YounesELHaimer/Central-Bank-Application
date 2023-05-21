package com.example.centralbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneActivity extends AppCompatActivity {

    private EditText etPhone;
    private Spinner spinner;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        progressBar=findViewById(R.id.probar1);

        etPhone = findViewById(R.id.phone);
        spinner = findViewById(R.id.spinner);

        String name = getIntent().getStringExtra("firstName");

        String lastName = getIntent().getStringExtra("lastName");

        TextView textView = findViewById(R.id.loginNow);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), first_page_activity.class);
                startActivity(intent);
                finish();
            }
        });

        Button btnNext = findViewById(R.id.btn_Nex);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone = etPhone.getText().toString().trim();
                String selectedOption = spinner.getSelectedItem().toString();

                if (TextUtils.isEmpty(phone)) {
                    etPhone.setError("Phone number is required");
                    return;
                }
                if (!etPhone.getText().toString().trim().isEmpty()){
                    if ((etPhone.getText().toString().trim()).length()==9)
                    {
                        progressBar.setVisibility(View.VISIBLE);
                        btnNext.setVisibility(View.INVISIBLE);
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+212" +etPhone.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                PhoneActivity.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressBar.setVisibility(View.GONE);
                                        btnNext.setVisibility(View.VISIBLE);

                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressBar.setVisibility(View.GONE);
                                        btnNext.setVisibility(View.VISIBLE);


                                        Toast.makeText(PhoneActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                        progressBar.setVisibility(View.GONE);
                                        btnNext.setVisibility(View.VISIBLE);

                                        Intent intent = new Intent(PhoneActivity.this, VerificationSms.class);
                                        intent.putExtra("phone", phone);
                                        intent.putExtra("selectedOption",selectedOption);
                                        intent.putExtra("firstName",name );
                                        intent.putExtra("lastName",lastName);
                                        intent.putExtra("backendotp",backendotp);
                                        startActivity(intent);

                                    }
                                }

                        );
                    }else {
                        Toast.makeText(PhoneActivity.this, "Please enter correct Number", Toast.LENGTH_SHORT).show();
                    }

                }else
                {
                    Toast.makeText(PhoneActivity.this, "Enter Mobile number", Toast.LENGTH_SHORT).show();
                }

            }





        });

        Button btnPrev = findViewById(R.id.btn_prev);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),firstpageRegister.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
