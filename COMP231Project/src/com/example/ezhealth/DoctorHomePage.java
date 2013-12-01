package com.example.ezhealth;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class DoctorHomePage extends Activity {
	
	private TextView profileTitle, displayName,displayContact, displayAddress;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_home_page);
		int doctorLoginId = getIntent().getExtras().getInt("doctorLoginId");
		final DatabaseHandler db = new DatabaseHandler(this);
		Doctor doctor = db.getDoctorByLoginId(doctorLoginId);
		profileTitle = (TextView) findViewById(R.id.doctorProfileTitle);
		profileTitle.setText("Doctor Profile ID: "+doctor.getDoctorId());
		
		displayName = (TextView) findViewById(R.id.displayName);
		displayName.setText(doctor.getFirstName().toUpperCase()+" "+doctor.getLastName().toUpperCase());
		displayContact = (TextView) findViewById(R.id.displayContact);
		displayContact.setText("Phone: " + doctor.getPhone().toString()+
				"\nEmail: " + doctor.getEmail().toString());
		displayAddress = (TextView) findViewById(R.id.displayAddress);
		displayAddress.setText(doctor.getApartment().toString()+"\n"+
				doctor.getStreet().toString()+"\n"+
				doctor.getCity().toString()+"\n"+
				doctor.getProvince().toString()+"\n"+
				doctor.getCountry().toString());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.receptionist, menu);
		return true;
	}

}
