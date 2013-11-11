package com.example.ezhealth;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class ReceptionistPatientProfileView extends Activity {
	
	private TextView displayName,displayContacts, displayAddress, displayInsuraceInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receptionist_patient_profile_view);
		
		Bundle bundle = getIntent().getExtras().getParcelable("patient");
		Patient patient = (Patient) bundle.getParcelable("patient");
		
		
		displayName = (TextView) findViewById(R.id.displayName);
		displayName.setText(patient.getFirstName().toUpperCase()+" "+patient.getLastName().toUpperCase());
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.receptionist_patient_profile_view,
				menu);
		return true;
	}

}
