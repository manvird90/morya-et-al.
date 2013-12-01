package com.example.ezhealth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PatientHomepage extends Activity {

	private TextView profileTitle, displayName,displayContact, displayAddress, displayInsuraceInfo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_homepage);
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
		displayInsuraceInfo = (TextView) findViewById(R.id.displayInsuranceInfo);
		displayInsuraceInfo.setText("Policy Number: "+patient.getHealthPolicyNumber().toString()+
				"\nInsurance Company: " + patient.getInsuranceCompany().toString());
		
		
	}
	public void action_logout(View v){
		finish();
		Intent z = null;
		z = new Intent(this, MainActivity.class);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.receptionist, menu);
		return true;
	}

}
