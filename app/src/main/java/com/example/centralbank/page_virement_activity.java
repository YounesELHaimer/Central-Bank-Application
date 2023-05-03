
	 
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
import android.media.Image;
import android.os.Bundle;


import android.view.View;
import android.widget.ImageView;
import android.content.Intent;

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
	private ImageView gears;
	private ImageView credit_card;
	private ImageView invoice;
	private ImageView card;
	private ImageView home10;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_virement);

		invoice = findViewById(R.id.invoice);
		home10 = findViewById(R.id.home10);
		rectangle_4 = (View) findViewById(R.id.rectangle_4);
		rectangle_5 = (View) findViewById(R.id.rectangle_5);
		free_sample_by_wix = (ImageView) findViewById(R.id.free_sample_by_wix);
		_power = (ImageView) findViewById(R.id._power);
		menu = (ImageView) findViewById(R.id.menu);
		bell = (ImageView) findViewById(R.id.bell);
		gears = (ImageView) findViewById(R.id.gears);
		credit_card = (ImageView) findViewById(R.id.credit_card);
		invoice = (ImageView) findViewById(R.id.invoice);
		card = findViewById(R.id.credit_card);
		
		home10.setOnClickListener(new View.OnClickListener() {
		
			public void onClick(View v) {
				
				Intent nextScreen = new Intent(getApplicationContext(), page_home_activity.class);
				startActivity(nextScreen);
			
		
			}
		});

		card.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent nextScreen = new Intent(getApplicationContext(), page_card.class);
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
	
	