
	 
	/*
	 *	This content is generated from the API File Info.
	 *	(Alt+Shift+Ctrl+I).
	 *
	 *	@desc 		
	 *	@file 		first_page
	 *	@date 		Saturday 29th of April 2023 04:02:49 PM
	 *	@title 		First Page
	 *	@author 	
	 *	@keywords 	
	 *	@generator 	Export Kit v1.3.xd
	 *
	 */
	

package com.example.centralbank;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.text.method.HideReturnsTransformationMethod;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

	public class first_page_activity extends Activity {

		EditText editTextEmail, editTextPassword;

		FirebaseAuth mAuth;
		ProgressBar progressBar;

		SharedPreferences sharedPreferences;
		SharedPreferences.Editor editor;

		TextView textView;
		private View _bg__first_page_ek2;
		private ImageView groupe_d_filant_1;
		private View free_sample_by_wix;
		private ImageView free_sample_by_wix_ek1;
		private View rectangle_1;
		private TextView se_connecter;
		private View rectangle_1_ek1;
		private TextView creer_un_compte;
		private ImageView pngfind_com_contact_icon_png_666635;
		private TextView sos;
		private TextView agences;
		private ImageView maps_and_flags__2_;
		private TextView se_souvenir_de_moi;
		private TextView mot_de_passe_oubli___;
		private View rectangle_2;
		private View ellipse_1;
		private ImageView user__1_;
		private View rectangle_3;
		private View ellipse_1_ek1;
		private ImageView padlock;
		private ImageView hiddeneye;
		private ImageView eye;
		private EditText password;

		@Override
		public void onCreate(Bundle savedInstanceState) {

			super.onCreate(savedInstanceState);
			setContentView(R.layout.first_page);


			free_sample_by_wix = (View) findViewById(R.id.free_sample_by_wix);
			free_sample_by_wix_ek1 = (ImageView) findViewById(R.id.free_sample_by_wix_ek1);
			rectangle_1 = (View) findViewById(R.id.rectangle_1);
			se_connecter = (TextView) findViewById(R.id.se_connecter);
			rectangle_1_ek1 = (View) findViewById(R.id.rectangle_1_ek1);
			creer_un_compte = (TextView) findViewById(R.id.creer_un_compte);
			pngfind_com_contact_icon_png_666635 = (ImageView) findViewById(R.id.pngfind_com_contact_icon_png_666635);
			sos = (TextView) findViewById(R.id.sos);
			agences = (TextView) findViewById(R.id.agences);
			maps_and_flags__2_ = (ImageView) findViewById(R.id.maps_and_flags__2_);
			mot_de_passe_oubli___ = (TextView) findViewById(R.id.mot_de_passe_oubli___);
			rectangle_2 = (View) findViewById(R.id.rectangle_2);
			ellipse_1 = (View) findViewById(R.id.ellipse_1);
			user__1_ = (ImageView) findViewById(R.id.user__1_);
			rectangle_3 = (View) findViewById(R.id.rectangle_3);
			ellipse_1_ek1 = (View) findViewById(R.id.ellipse_1_ek1);
			padlock = (ImageView) findViewById(R.id.padlock);
			password = (EditText) findViewById(R.id.password);

			hiddeneye = findViewById(R.id.HiddenEye);


			textView = findViewById(R.id.creer_un_compte);

			mAuth = FirebaseAuth.getInstance();
			editTextEmail = findViewById(R.id.numero);
			editTextPassword = findViewById(R.id.password);

			progressBar = findViewById(R.id.progressBar);


			sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
			editor = sharedPreferences.edit();
			String savedUsername = sharedPreferences.getString("username", "");




//		se_souvenir_de_moi.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				String savedUsername = sharedPreferences.getString("username", "");
//				if (!savedUsername.equals("")) {
//					// If a username has been saved previously, set the EditText to display it.
//					editTextEmail.setText(savedUsername);
//				}
//			}
//		});

			textView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(getApplicationContext(), firstpageRegister.class);
					startActivity(intent);
					finish();
				}
			});



			rectangle_1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					progressBar.setVisibility(View.VISIBLE);
					String email,password;
					email = editTextEmail.getText().toString();
					password = editTextPassword.getText().toString();

					if(TextUtils.isEmpty(email)){
						Toast.makeText(first_page_activity.this,"Enter email",Toast.LENGTH_SHORT).show();
						return;
					}
					if(TextUtils.isEmpty(password)){
						Toast.makeText(first_page_activity.this,"Enter password",Toast.LENGTH_SHORT).show();
						return;
					}
					mAuth.signInWithEmailAndPassword(email, password)
							.addOnCompleteListener( new OnCompleteListener<AuthResult>() {
								@Override
								public void onComplete(@NonNull Task<AuthResult> task) {
									progressBar.setVisibility(View.GONE);
									if (task.isSuccessful()) {
										Toast.makeText(getApplicationContext(), "Login Succesful", Toast.LENGTH_SHORT).show();
										Intent intent = new Intent(getApplicationContext(),page_home_activity.class);
										startActivity(intent);
										finish();
									} else {
										// If sign in fails, display a message to the user.
										Toast.makeText(first_page_activity.this, "Authentication failed.",
												Toast.LENGTH_SHORT).show();

									}
								}
							});

					String username = email.trim();
					editor.putString("username", username);
					editor.apply();


				}
			});
			hiddeneye.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(password.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
						password.setTransformationMethod(PasswordTransformationMethod.getInstance());
						hiddeneye.setImageResource(R.drawable.eye_off);
					}
					else{
						password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
						hiddeneye.setImageResource(R.drawable.blue_eye_);
					}

				}
			});







			//custom code goes here

		}
	}
	
	