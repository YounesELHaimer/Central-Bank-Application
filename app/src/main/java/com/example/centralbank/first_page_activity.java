

package com.example.centralbank;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;


import android.text.TextUtils;

import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import  java.lang.Object.*;
import java.util.concurrent.Executor;

import androidx.biometric.FingerprintDialogFragment;

import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import java.security.KeyStore;
import java.util.concurrent.Executor;

import javax.crypto.Cipher;

// define a BiometricPrompt instance variable




public class first_page_activity extends FragmentActivity {



	EditText editTextEmail, editTextPassword;

	FirebaseAuth mAuth;
	ProgressBar progressBar;

	SharedPreferences sharedPreferences;
	SharedPreferences.Editor editor;

	TextView textView1;
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


	private static View emp;
	private static ImageView emp_icon;

	private FingerprintManager fingerprintManager;
	private KeyStore keyStore;
	private KeyguardManager keyguardManager;
	private Cipher cipher;
	private TextView textView;
	private static final String KEY_NAME = "example_key";

	private Executor executor;
	private BiometricPrompt biometricPrompt;
	private BiometricPrompt.PromptInfo promptInfo;


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


		textView1 = findViewById(R.id.creer_un_compte);

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

		textView1.setOnClickListener(new View.OnClickListener() {
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
				String numero = editTextEmail.getText().toString();
				password = editTextPassword.getText().toString();

				if(TextUtils.isEmpty(numero)){
					Toast.makeText(first_page_activity.this,"Enter account number",Toast.LENGTH_SHORT).show();
					return;
				}
				if(TextUtils.isEmpty(password)){
					Toast.makeText(first_page_activity.this,"Enter password",Toast.LENGTH_SHORT).show();
					return;
				}
				verifyUser(numero, password);
				progressBar.setVisibility(View.GONE);


//				mAuth.signInWithEmailAndPassword(email, password)
//						.addOnCompleteListener( new OnCompleteListener<AuthResult>() {
//							@Override
//							public void onComplete(@NonNull Task<AuthResult> task) {
//								progressBar.setVisibility(View.GONE);
//								if (task.isSuccessful()) {
//									Toast.makeText(getApplicationContext(), "Login Succesful", Toast.LENGTH_SHORT).show();
//									Intent intent = new Intent(getApplicationContext(),page_home_activity.class);
//									intent.putExtra("email", email);
//									startActivity(intent);
//									finish();
//								} else {
//									// If sign in fails, display a message to the user.
//									Toast.makeText(first_page_activity.this, "Authentication failed.",
//											Toast.LENGTH_SHORT).show();
//
//								}
//							}
//						});

//				String username = email.trim();
//				editor.putString("username", username);
//				editor.apply();
			}
		});


		hiddeneye = findViewById(R.id.HiddenEye);
		emp = findViewById(R.id.rectangle_emp);
		emp_icon = findViewById(R.id.emp_icon);





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




		BiometricManager biometricManager = BiometricManager.from(first_page_activity.this);
		int canAuthenticate = biometricManager.canAuthenticate();




		SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
		boolean switchState = prefs.getBoolean("switch_state", false);


		if (switchState==true) {
			emp.setVisibility(View.VISIBLE);
			emp_icon.setVisibility(View.VISIBLE);
				/*rectangle_1.setLayoutParams(new RelativeLayout.LayoutParams(275, 61));
				se_connecter.setLayoutParams(new RelativeLayout.LayoutParams(215, 34));*/
			rectangle_1.getLayoutParams().width = dpToPx(this,275);
			se_connecter.getLayoutParams().width = dpToPx(this,185);

			Executor executor = ContextCompat.getMainExecutor(first_page_activity.this);
			BiometricPrompt.AuthenticationCallback authenticationCallback = new BiometricPrompt.AuthenticationCallback() {
				@Override
				public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
					Intent nextScreen = new Intent(getApplicationContext(), page_home_activity.class);
					onStop();
					startActivity(nextScreen);
				}

				@Override
				public void onAuthenticationError(int errorCode, CharSequence errString) {
					// Handle authentication error
					Toast.makeText(getApplicationContext(), "Authentication error: " + errString, Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onAuthenticationFailed() {
					// Handle authentication failure
					Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
				}
			};

			if (canAuthenticate == BiometricManager.BIOMETRIC_SUCCESS) {
				//create a BiometricPrompt instance
				biometricPrompt = new BiometricPrompt(first_page_activity.this, executor, authenticationCallback);
				// create a prompt info object
				BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
						.setTitle("Empreinte digitale")
						.setSubtitle("Se connecter avec votre empreinte digitale")
						.setNegativeButtonText("ANNULER")
						.build();

				if (biometricPrompt != null && executor != null && authenticationCallback != null) {
					// Objects are initialized properly
					Log.d("BiometricPrompt", "Objects are initialized properly");
					emp.setOnClickListener(view -> {
						biometricPrompt.authenticate(promptInfo);
					});
				} else {
					// Objects are null or not properly initialized
					Log.e("BiometricPrompt", "Objects are null or not properly initialized");
				}
			}


		} else {
			emp.setVisibility(View.INVISIBLE);
			emp_icon.setVisibility(View.INVISIBLE);
				/*rectangle_1.setLayoutParams(new RelativeLayout.LayoutParams(340, 61));
				se_connecter.setLayoutParams(new RelativeLayout.LayoutParams(248, 34));*/
			rectangle_1.getLayoutParams().width = dpToPx(this,340);
			//se_connecter.getLayoutParams().width = dpToPx(this,248);

		}


	}

		/*public static void update(boolean ischecked) {
			if(ischecked==true){
				emp.setVisibility(View.VISIBLE);
				emp_icon.setVisibility(View.VISIBLE);
				rectangle_1.setLayoutParams(new RelativeLayout.LayoutParams(275, 61));
				se_connecter.setLayoutParams(new RelativeLayout.LayoutParams(215, 34));
			}
			else{
				emp.setVisibility(View.INVISIBLE);
				emp_icon.setVisibility(View.INVISIBLE);
				rectangle_1.setLayoutParams(new RelativeLayout.LayoutParams(340, 61));
				se_connecter.setLayoutParams(new RelativeLayout.LayoutParams(248, 34));
			}

		}*/

	public static int dpToPx(Context context, float dp) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		return (int) (dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
	}
	private void verifyUser(String numerodecompte, String password) {
		DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");

		// Query the database for the matching "numeroDeCompte"
		usersRef.orderByChild("numeroDeCompte").equalTo(numerodecompte).addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				if (dataSnapshot.exists()) {
					// Iterate over the matching users
					for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
						User user = userSnapshot.getValue(User.class);
						if (user != null && user.getPassword().equals(password)) {
							// NumeroDeCompte and password match, user is authenticated
							Toast.makeText(first_page_activity.this, "Authentication successful", Toast.LENGTH_SHORT).show();

							// Retrieve the email
							String email = user.getEmail();

							// Pass the email as an extra to the next activity
							Intent intent = new Intent(getApplicationContext(), page_home_activity.class);
							intent.putExtra("email", email);
							startActivity(intent);
							finish();
						} else {
							// Password does not match
							Toast.makeText(first_page_activity.this, "Invalid password", Toast.LENGTH_SHORT).show();
						}
					}
				} else {
					// NumeroDeCompte not found
					Toast.makeText(first_page_activity.this, "Invalid numeroDeCompte", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {
				// Handle any errors
				Toast.makeText(first_page_activity.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
			}
		});

	}




}

