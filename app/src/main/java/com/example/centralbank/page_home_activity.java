package com.example.centralbank;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

	public class page_home_activity extends AppCompatActivity {


		private View _bg__page_home;
		private View rectangle_4;
		private View rectangle_5;
		private ImageView free_sample_by_wix;
		private View ellipse_2;
		private ImageView home__1_;
		private ImageView _transaction__2_;
		private ImageView home__1__ek2;
		private ImageView credit_card;
		private ImageView invoice;
		private ImageView _power;
		private ImageView menu;
		private ImageView bell;
		private ImageView gears;
		private View rectangle_6;
		private TextView compte_cheque;
		private ImageView account;
		private TextView rib;
		private TextView mes_derni_res__transactions;
		private TextView solde;
		private ImageView eye;
		private ImageView card;
		private ImageView settings;
		private DrawerLayout drawerLayout;
		private ListView L;

		private String email;

		@Override
		public void onCreate(Bundle savedInstanceState) {

			super.onCreate(savedInstanceState);
			setContentView(R.layout.page_home);


			_bg__page_home = (View) findViewById(R.id._bg__page_home);
			rectangle_4 = (View) findViewById(R.id.rectangle_4);
			rectangle_5 = (View) findViewById(R.id.rectangle_5);
			free_sample_by_wix = (ImageView) findViewById(R.id.free_sample_by_wix);

			_transaction__2_ = (ImageView) findViewById(R.id.transaction__2_);
			home__1__ek2 = (ImageView) findViewById(R.id.home10);
			invoice = (ImageView) findViewById(R.id.invoice);
			_power = (ImageView) findViewById(R.id._power);
			bell = (ImageView) findViewById(R.id.bell);
			rectangle_6 = (View) findViewById(R.id.rectangle_6);
			compte_cheque = (TextView) findViewById(R.id.compte_cheque);
			account = (ImageView) findViewById(R.id.account);
			rib = (TextView) findViewById(R.id.rib);
			mes_derni_res__transactions = (TextView) findViewById(R.id.mes_derni_res__transactions);
			solde =  (TextView) findViewById(R.id.solde1);
			card = findViewById(R.id.credit_card);
			settings = findViewById(R.id.gears);

			eye = (ImageView) findViewById(R.id.HiddenEye);
			solde.setText("*,**");
			drawerLayout = findViewById(R.id.drawer_layout);

			email = getIntent().getStringExtra("email");
			String numeroDeCompte = getIntent().getStringExtra("numeroDeCompte");
			String numeroDeCompteBenef = getIntent().getStringExtra("numeroDeCompteBenef");
			TextView rib = findViewById(R.id.rib);
			rib.setText(numeroDeCompte);

			L = findViewById(R.id.listView);

			ArrayList<HashMap<String, String>> Element = new ArrayList<>();

			DatabaseReference virementsRef = FirebaseDatabase.getInstance().getReference().child("virements");

			virementsRef.addValueEventListener(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot dataSnapshot) {
					Element.clear(); // Clear the existing data in Element ArrayList

					for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
						HashMap<String, String> map = new HashMap<>();

						String numeroDeCompteValue = snapshot.child("numeroDeCompte").getValue(String.class);
						String numeroDeCompteBenefValue = snapshot.child("numeroDeCompteBenef").getValue(String.class);

						// Fetch values from snapshot and add them to the map
						String typeDeTransaction = snapshot.child("typeDeTransaction").getValue(String.class);
						Float soldMadFloat = snapshot.child("montant").getValue(Float.class);
						String soldMad = String.valueOf(soldMadFloat); // Convert the Float value to String

						Date completeDate = snapshot.child("date").getValue(Date.class);

						// Create a Calendar instance and set it to the completeDate
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(completeDate);

						// Extract the day, month, and year from the Calendar
						int day = calendar.get(Calendar.DAY_OF_MONTH);
						int month = calendar.get(Calendar.MONTH) + 1; // Note: Month starts from 0, so add 1 to get the actual month value
						int year = calendar.get(Calendar.YEAR);

						// Create a formatted date string with only the day, month, and year
						String formattedDate = day + "/" + month + "/" + year;

						// Add the formatted date and other details to the map
						map.put("date", formattedDate);

						if (numeroDeCompteValue.equals(numeroDeCompte)) {
							map.put("type_de_la_transactions", " envoye a " + snapshot.child("nameBenef").getValue(String.class));
							map.put("sold_mad", "- " + soldMad + " MAD");
						} else if (numeroDeCompteBenefValue.equals(numeroDeCompte)) {
							map.put("type_de_la_transactions", " envoye par " + snapshot.child("name").getValue(String.class));
							map.put("sold_mad", "+ " + soldMad + " MAD");
						}

						Element.add(map);
					}

					// Call a method or update UI with the populated Element ArrayList
					// ...

					// Set up the adapter only after data retrieval is complete
					SimpleAdapter Adp = new SimpleAdapter(page_home_activity.this, Element,
							R.layout.affichage_listview, new String[]{"date", "type_de_la_transactions", "sold_mad"},
							new int[]{R.id.date, R.id.type_de_la_transactions, R.id.sold_mad}) {

						@Override
						public View getView(int position, View convertView, ViewGroup parent) {
							View view = super.getView(position, convertView, parent);

							TextView soldMadTextView = view.findViewById(R.id.sold_mad);
							String soldMadValue = Element.get(position).get("sold_mad");

							// Check if the soldMadValue is negative (starts with "-")
							if (soldMadValue.startsWith("-")) {
								soldMadTextView.setTextColor(Color.RED);
							}

							return view;
						}
					};

					L.setAdapter(Adp);

					Log.d("FirebaseData", "Data retrieval successful. Element size: " + Element.size());
				}

				@Override
				public void onCancelled(DatabaseError databaseError) {
					// Error handling
					// ...
				}
			});



			DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");
			Query query = usersRef.orderByChild("email").equalTo(email);

			query.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot dataSnapshot) {
					for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
						// Retrieve the user data
						Float solde1 = snapshot.child("Solde").getValue(Float.class);

						// Use the retrieved data as needed
						eye.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								if(solde.getText().toString() == "*,**"){
									solde.setText(solde1+" MAD");
									solde.setTextSize(18);
									eye.setImageResource(R.drawable.blue_eye_);
								}
								else{
									solde.setText("*,**");
									eye.setImageResource(R.drawable.red_eye_off);
								}

							}
						});

						// ...
					}
				}

				@Override
				public void onCancelled(DatabaseError databaseError) {
					// Error handling
					// ...
				}
			});




			card.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {

					Intent nextScreen = new Intent(getApplicationContext(), page_card.class);
					nextScreen.putExtra("email", email);
					nextScreen.putExtra("numeroDeCompte", numeroDeCompte);


					startActivity(nextScreen);

				}
			});

			_transaction__2_.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {

					Intent nextScreen = new Intent(getApplicationContext(), page_virement_activity.class);
					nextScreen.putExtra("email", email);
					nextScreen.putExtra("numeroDeCompte", numeroDeCompte);

					startActivity(nextScreen);


				}
			});

			_power.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {

					AlertDialog.Builder adb = new AlertDialog.Builder(page_home_activity.this, R.style.CustomAlertDialogStyle);
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
							nextScreen.putExtra("numeroDeCompte", numeroDeCompte);

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
					nextScreen.putExtra("numeroDeCompte", numeroDeCompte);

					startActivity(nextScreen);


				}
			});

			settings = findViewById(R.id.gears);
			settings.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {

					Intent nextScreen = new Intent(getApplicationContext(), page_settings.class);
					nextScreen.putExtra("email", email);
					nextScreen.putExtra("numeroDeCompte", numeroDeCompte);

					startActivity(nextScreen);


				}
			});


		}


	}
	
	