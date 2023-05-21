package com.example.centralbank;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class firstpageRegister extends AppCompatActivity {

    private EditText etFirstName, etLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage_register);

        etFirstName = findViewById(R.id.name);
        etLastName = findViewById(R.id.LastName);
        TextView textView = findViewById(R.id.loginNow);

        Button btnNext = findViewById(R.id.btn_Nex);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), first_page_activity.class);
                startActivity(intent);
                finish();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String firstName = etFirstName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                if (TextUtils.isEmpty(firstName)) {
                    etFirstName.setError("First name is required");
                    return;
                }
                if (TextUtils.isEmpty(lastName)) {
                    etLastName.setError("Last name is required");
                    return;
                }
                Intent intent = new Intent(firstpageRegister.this, PhoneActivity.class);
                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);
                startActivity(intent);
            }
        });

        Button btnPrev = findViewById(R.id.btn_prev);
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),first_page_activity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
