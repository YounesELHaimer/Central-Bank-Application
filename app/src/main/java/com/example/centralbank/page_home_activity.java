
	 
	/*
	 *	This content is generated from the API File Info.
	 *	(Alt+Shift+Ctrl+I).
	 *
	 *	@desc 		
	 *	@file 		page_home
	 *	@date 		Sunday 30th of April 2023 09:40:08 PM
	 *	@title 		First Page
	 *	@author 	
	 *	@keywords 	
	 *	@generator 	Export Kit v1.3.xd
	 *
	 */
	

package com.example.centralbank;

import android.app.Activity;
import android.os.Bundle;


import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.TextView;

public class page_home_activity extends Activity {

	
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
	private View rectangle_7;
	private View rectangle_12;
	private TextView _17_avril;
	private TextView _2023;
	private ImageView trac__1;
	private View rectangle_13;
	private TextView type_de_la_transactions;
	private View rectangle_14;
	private TextView ___10000_mad;
	private TextView solde;
	private ImageView eye;
	private ImageView card;

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
		credit_card = (ImageView) findViewById(R.id.credit_card);
		invoice = (ImageView) findViewById(R.id.invoice);
		_power = (ImageView) findViewById(R.id._power);
		menu = (ImageView) findViewById(R.id.menu);
		bell = (ImageView) findViewById(R.id.bell);
		gears = (ImageView) findViewById(R.id.gears);
		rectangle_6 = (View) findViewById(R.id.rectangle_6);
		compte_cheque = (TextView) findViewById(R.id.compte_cheque);
		account = (ImageView) findViewById(R.id.account);
		rib = (TextView) findViewById(R.id.rib);
		mes_derni_res__transactions = (TextView) findViewById(R.id.mes_derni_res__transactions);
		rectangle_7 = (View) findViewById(R.id.rectangle_7);
		rectangle_12 = (View) findViewById(R.id.rectangle_12);
		_17_avril = (TextView) findViewById(R.id.date);
		_2023 = (TextView) findViewById(R.id.annee);
		trac__1 = (ImageView) findViewById(R.id.trac__1);
		rectangle_13 = (View) findViewById(R.id.rectangle_13);
		type_de_la_transactions = (TextView) findViewById(R.id.type_de_la_transactions);
		rectangle_14 = (View) findViewById(R.id.rectangle_14);
		___10000_mad = (TextView) findViewById(R.id.sold_mad);
		solde =  (TextView) findViewById(R.id.solde1);
		card = findViewById(R.id.credit_card);

		eye = (ImageView) findViewById(R.id.HiddenEye);
		solde.setText("*,**");
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
				
				Intent nextScreen = new Intent(getApplicationContext(), first_page_activity.class);
				startActivity(nextScreen);
			
		
			}
		});

		invoice.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent nextScreen = new Intent(getApplicationContext(), page_facture.class);
				startActivity(nextScreen);


			}
		});
		
		
		//custom code goes here
	
	}
}
	
	