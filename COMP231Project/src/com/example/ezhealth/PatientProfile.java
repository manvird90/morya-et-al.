package com.example.ezhealth;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class PatientProfile extends Activity {
	
	private TextView profileTitle, displayName,displayContact, displayAddress;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_profile);
		int patientLoginId = getIntent().getExtras().getInt("patientLoginId");
		final DatabaseHandler db = new DatabaseHandler(this);
		Patient patient = db.getPatientByLoginId(patientLoginId);
		profileTitle = (TextView) findViewById(R.id.patientProfileTitle);
		profileTitle.setText("Patient Profile ID: "+patient.getPatientId());
		
		displayName = (TextView) findViewById(R.id.displayName);
		displayName.setText(patient.getFirstName().toUpperCase()+" "+patient.getLastName().toUpperCase());
		displayContact = (TextView) findViewById(R.id.displayContact);
		displayContact.setText("Phone: " + patient.getPhone().toString()+
				"\nEmail: " + patient.getEmail().toString());
		displayAddress = (TextView) findViewById(R.id.displayAddress);
		displayAddress.setText(patient.getApartment().toString()+"\n"+
				patient.getStreet().toString()+"\n"+
				patient.getCity().toString()+"\n"+
				patient.getProvince().toString()+"\n"+
				patient.getCountry().toString());
		
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_profile, menu);
		return true;
	}

}
