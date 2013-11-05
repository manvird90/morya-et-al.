package com.example.ezhealth;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ReceptionistPatientSearch extends Activity {

	private EditText etFirstName, etLastName, etPatientId;
	private Button btnSearchPatient;
	private String firstName, lastName, patientId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receptionist_patient_search);
		etFirstName = (EditText) findViewById(R.id.etFirstName);
		etLastName = (EditText) findViewById(R.id.etLastName);
		etPatientId = (EditText) findViewById(R.id.etPatientId);
		btnSearchPatient = (Button) findViewById(R.id.btnSearchPatient);
		
		btnSearchPatient.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.receptionist_patient_search, menu);
		return true;
	}

}
