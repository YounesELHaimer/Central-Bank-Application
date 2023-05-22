
	 
	/*
	 *	This content is generated from the API File Info.
	 *	(Alt+Shift+Ctrl+I).
	 *
	 *	@desc 		
	 *	@file 		page_virement
	 *	@date 		Sunday 30th of April 2023 10:06:42 PM
	 *	@title 		First Page
	 *	@author 	
	 *	@keywords 	
	 *	@generator 	Export Kit v1.3.xd
	 *
	 */
	

package com.example.centralbank;

	import android.app.Activity;
	import android.content.Intent;
	import android.os.Bundle;
	import android.view.View;
	import android.widget.EditText;
	import android.widget.ImageView;
	import android.widget.RadioButton;
	import android.widget.RadioGroup;
	import android.widget.RelativeLayout;
	import android.widget.Toast;

	import androidx.annotation.NonNull;
	import androidx.appcompat.app.AlertDialog;

	import com.google.android.gms.tasks.OnFailureListener;
	import com.google.android.gms.tasks.OnSuccessListener;
	import com.google.firebase.database.DataSnapshot;
	import com.google.firebase.database.DatabaseError;
	import com.google.firebase.database.DatabaseReference;
	import com.google.firebase.database.FirebaseDatabase;
	import com.google.firebase.database.ValueEventListener;

	public class page_virement_activity extends Activity {

	
	private View _bg__page_virement;
	private View rectangle_4;
	private View rectangle_5;
	private ImageView free_sample_by_wix;
	private View ellipse_2;
	private ImageView home__1_;
	private ImageView transaction__3_;
	private ImageView _home__3_;
	private ImageView _power;
	private ImageView menu;
	private ImageView bell;
	private ImageView settings;
	private ImageView credit_card;
	private ImageView invoice;
	private ImageView card;
	private ImageView home10;
	private RadioButton radioButton1;
	private RadioButton radioButton2;
	private RadioGroup radioGroup;
	private RelativeLayout relativeLayout_Central_bank;
	private RelativeLayout relativeLayout_autre_banque;
	private RelativeLayout button;
	private RelativeLayout button_autre_bank;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_virement);

		invoice = findViewById(R.id.invoice);
		home10 = findViewById(R.id.home10);
		String email = getIntent().getStringExtra("email");

		rectangle_4 = (View) findViewById(R.id.rectangle_4);
		rectangle_5 = (View) findViewById(R.id.rectangle_5);
		free_sample_by_wix = (ImageView) findViewById(R.id.free_sample_by_wix);
		_power = (ImageView) findViewById(R.id._power);
		bell = (ImageView) findViewById(R.id.bell);
		credit_card = (ImageView) findViewById(R.id.credit_card);
		invoice = (ImageView) findViewById(R.id.invoice);
		card = findViewById(R.id.credit_card);
		settings = findViewById(R.id.gears);
		radioButton1 = findViewById(R.id.RadioButton1);
		radioButton2 = findViewById(R.id.RadioButton2);
		radioGroup = findViewById(R.id.RadioGroup);
		Boolean RadioButtonState1 = radioButton1.isChecked();
		Boolean RadioButtonState2 = radioButton2.isChecked();
		relativeLayout_Central_bank = findViewById(R.id.central_bank_layout);
		relativeLayout_autre_banque = findViewById(R.id.autre_banque_layout);
		button = findViewById(R.id.button);
		button_autre_bank = findViewById(R.id.button_autre_bank);

		EditText numeroDeCompteEditText = findViewById(R.id.edit_numero_de_compte);
		String numeroDeCompte = numeroDeCompteEditText.getText().toString();

		EditText nomEditText = findViewById(R.id.nom);
		String nomBeneficiaire = nomEditText.getText().toString();

		EditText montantEditText = findViewById(R.id.montant);






		home10.setOnClickListener(new View.OnClickListener() {
		
			public void onClick(View v) {
				
				Intent nextScreen = new Intent(getApplicationContext(), page_home_activity.class);
				nextScreen.putExtra("email", email);
				startActivity(nextScreen);
			
		
			}
		});

		card.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent nextScreen = new Intent(getApplicationContext(), page_card.class);
				nextScreen.putExtra("email", email);
				startActivity(nextScreen);

			}
		});
		
		
		_power.setOnClickListener(new View.OnClickListener() {
		
			public void onClick(View v) {

				AlertDialog.Builder adb = new AlertDialog.Builder(page_virement_activity.this, R.style.CustomAlertDialogStyle);
				View view = getLayoutInflater().inflate(R.layout.logout_alert_dialog, null);
				RelativeLayout button_ok = view.findViewById(R.id.button_OK);
				RelativeLayout button_annuler = view.findViewById(R.id.button_Annuler);

				adb.setView(view);
				adb.setCancelable(false);

				AlertDialog alert= adb.create();
				alert.show();

				button_ok.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent nextScreen = new Intent(getApplicationContext(), first_page_activity.class);
						nextScreen.putExtra("email", email);
						startActivity(nextScreen);
					}
				});

				button_annuler.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						alert.dismiss();
					}
				});
			
		
			}
		});

		invoice.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent nextScreen = new Intent(getApplicationContext(), page_rib.class);
				nextScreen.putExtra("email", email);
				startActivity(nextScreen);


			}
		});


		settings.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent nextScreen = new Intent(getApplicationContext(), page_settings.class);
				nextScreen.putExtra("email", email);
				startActivity(nextScreen);

			}
		});

		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				Boolean RadioButtonState1 = radioButton1.isChecked();
				Boolean RadioButtonState2 = radioButton2.isChecked();
				if (RadioButtonState2){
					relativeLayout_autre_banque.setVisibility(View.VISIBLE);
					relativeLayout_Central_bank.setVisibility(View.INVISIBLE);
				}else {
					relativeLayout_autre_banque.setVisibility(View.INVISIBLE);
					relativeLayout_Central_bank.setVisibility(View.VISIBLE);
				}
			}
		});

		radioButton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				radioButton1.setChecked(true);
			}
		});

		radioButton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				radioButton2.setChecked(true);
			}
		});

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String email = getIntent().getStringExtra("email");
				String numeroDeCompte = numeroDeCompteEditText.getText().toString();



				String montantStr = montantEditText.getText().toString();

// Convert the montant string to float

				float montant = Float.parseFloat(montantStr);
				FirebaseDatabase database = FirebaseDatabase.getInstance();
				DatabaseReference usersRef = database.getReference("users");
				// Find the user with the specified numeroDeCompte
				usersRef.orderByChild("numeroDeCompte").equalTo(numeroDeCompte).addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
						if (dataSnapshot.exists()) {
							for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
								String userId = snapshot.getKey();

								// Get the current Solde value from the user account
								float currentSolde = snapshot.child("Solde").getValue(Float.class);

								// Calculate the new Solde value
								float newSolde = currentSolde + montant;

								// Set the new Solde value in the Firebase Realtime Database
								usersRef.child(userId).child("Solde").setValue(newSolde)
										.addOnSuccessListener(new OnSuccessListener<Void>() {
											@Override
											public void onSuccess(Void aVoid) {
												// New Solde value added successfully for the user
												Toast.makeText(page_virement_activity.this, "New Solde value added successfully", Toast.LENGTH_SHORT).show();
											}
										})
										.addOnFailureListener(new OnFailureListener() {
											@Override
											public void onFailure(@NonNull Exception e) {
												// An error occurred while adding the new Solde value for the user
												// Handle the error or show an error message here
											}
										});
							}
						} else {
							// User with the specified numeroDeCompte does not exist
							// Handle the case where user is not found
						}

						// Find the connected user based on the email
						usersRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
								if (dataSnapshot.exists()) {
									for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
										String connectedUserId = snapshot.getKey();

										// Get the current Solde value from the connected user's account
										float connectedUserSolde = snapshot.child("Solde").getValue(Float.class);

										// Calculate the updated Solde value
										float updatedConnectedUserSolde = connectedUserSolde - montant;

										// Set the updated Solde value in the Firebase Realtime Database for the connected user
										usersRef.child(connectedUserId).child("Solde").setValue(updatedConnectedUserSolde)
												.addOnSuccessListener(new OnSuccessListener<Void>() {
													@Override
													public void onSuccess(Void aVoid) {
														// Updated Solde value for the connected user added successfully
														Toast.makeText(page_virement_activity.this, "Updated Solde value for connected user added successfully", Toast.LENGTH_SHORT).show();
													}
												})
												.addOnFailureListener(new OnFailureListener() {
													@Override
													public void onFailure(@NonNull Exception e) {
														// An error occurred while adding the updated Solde value for the connected user
														// Handle the error or show an error message here
													}
												});
									}
								} else {
									// Connected user with the specified email does not exist
									// Handle the case where connected user is not found
								}
							}

							@Override
							public void onCancelled(@NonNull DatabaseError databaseError) {
								// An error occurred while querying the database for the connected user
								// Handle the error or show an error message here
							}
						});
					}

					@Override
					public void onCancelled(@NonNull DatabaseError databaseError) {
						// An error occurred while querying the database for the user
						// Handle the error or show an error message here
					}
				});
				Intent nextScreen = new Intent(getApplicationContext(), virement_verification.class);
				nextScreen.putExtra("email", email);

				startActivity(nextScreen);
			}
		});

		button_autre_bank.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent nextScreen = new Intent(getApplicationContext(), virement_verification.class);
				nextScreen.putExtra("email", email);

				startActivity(nextScreen);
			}
		});

	}
}
	
	