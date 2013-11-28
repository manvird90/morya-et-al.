package com.example.ezhealth;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class ReceptionistDoctorProfileView extends Activity {
	private TextView docDisplayName, docDisplayContact, docSpecification;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receptionist_doctor_profile_view);
		docDisplayName = (TextView) findViewById(R.id.displayDocName);
		docDisplayContact = (TextView) findViewById(R.id.displayDocContact);
		
		Doctor doctor = new Doctor();
		doctor = (Doctor) getIntent().getSerializableExtra("doctor");
		final DatabaseHandler db = new DatabaseHandler(this);
		
		docDisplayName.setText(doctor.getFirstName().toUpperCase()+" "+doctor.getLastName().toUpperCase());
		
	docDisplayContact.setText("Phone: " + doctor.getPhone().toString()+
				"\nEmail: " + doctor.getEmail().toString());
		docSpecification = (TextView) findViewById(R.id.lblDocSpecification);
		docSpecification.setText("Doctor Profile ID: "+doctor.getDoctorId());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater()
				.inflate(R.menu.receptionist_doctor_profile_view, menu);
		return true;
	}

}

