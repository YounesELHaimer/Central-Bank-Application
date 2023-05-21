package com.example.centralbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;
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
			L = findViewById(R.id.listView);

			ArrayList<HashMap<String, String>> Element = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map;

			map = new HashMap<String, String>();
			map.put("date", "17 Avril");
			map.put("type_de_la_transactions", "Type de la transaction");
			map.put("sold_mad", "+ 1394 MAD");
			Element.add(map);

			map = new HashMap<String, String>();
			map.put("date", "17 Avril");
			map.put("type_de_la_transactions", "Type de la transaction");
			map.put("sold_mad", "+ 14 MAD");
			Element.add(map);

			map = new HashMap<String, String>();
			map.put("date", "17 Avril 2013");
			map.put("type_de_la_transactions", "Type de la transaction");
			map.put("sold_mad", "+ 139384 MAD");
			Element.add(map);

			map = new HashMap<String, String>();
			map.put("date", "17 Avril");
			map.put("type_de_la_transactions", "Type de la transaction");
			map.put("sold_mad", "+ 1394 MAD");
			Element.add(map);

			map = new HashMap<String, String>();
			map.put("date", "17 Avril");
			map.put("type_de_la_transactions", "Type de la transaction");
			map.put("sold_mad", "+ 1394 MAD");
			Element.add(map);

			map = new HashMap<String, String>();
			map.put("date", "17 Avril");
			map.put("type_de_la_transactions", "Type de la transaction");
			map.put("sold_mad", "+ 1394 MAD");
			Element.add(map);

			map = new HashMap<String, String>();
			map.put("date", "17 Avril");
			map.put("type_de_la_transactions", "Type de la transaction");
			map.put("sold_mad", "+ 1394 MAD");
			Element.add(map);

			map = new HashMap<String, String>();
			map.put("date", "17 Avril");
			map.put("type_de_la_transactions", "Type de la transaction");
			map.put("sold_mad", "+ 1394 MAD");
			Element.add(map);

			map = new HashMap<String, String>();
			map.put("date", "17 Avril");
			map.put("type_de_la_transactions", "Type de la transaction");
			map.put("sold_mad", "+ 1394 MAD");
			Element.add(map);

			map = new HashMap<String, String>();
			map.put("date", "17 Avril");
			map.put("type_de_la_transactions", "Type de la transaction");
			map.put("sold_mad", "+ 1394 MAD");
			Element.add(map);



			SimpleAdapter Adp = new SimpleAdapter (this.getBaseContext(), Element,
					R.layout.affichage_listview, new String[] {"date", "type_de_la_transactions","sold_mad"},
					new int[] {R.id.date, R.id.type_de_la_transactions,R.id.sold_mad});
			L.setAdapter(Adp);

			eye.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(solde.getText().toString() == "*,**"){
						solde.setText("10000 MAD");
						eye.setImageResource(R.drawable.blue_eye_);
					}
					else{
						solde.setText("*,**");
						eye.setImageResource(R.drawable.red_eye_off);
					}

				}
			});

			card.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {

					Intent nextScreen = new Intent(getApplicationContext(), page_card.class);
					startActivity(nextScreen);

				}
			});

			_transaction__2_.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {

					Intent nextScreen = new Intent(getApplicationContext(), page_virement_activity.class);
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
					startActivity(nextScreen);


				}
			});

			settings = findViewById(R.id.gears);
			settings.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {

					Intent nextScreen = new Intent(getApplicationContext(), page_settings.class);
					startActivity(nextScreen);


				}
			});


		}


	}
	
	