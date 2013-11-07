package com.example.ezhealth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReceptionistPatientSearch extends Activity {

	private EditText etFirstName, etLastName, etPatientId;
	private Button btnSearchPatient;
	private String firstName, lastName;
	private int patientId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receptionist_patient_search);
		etFirstName = (EditText) findViewById(R.id.etFirstName);
		etLastName = (EditText) findViewById(R.id.etLastName);
		etPatientId = (EditText) findViewById(R.id.etPatientId);
		btnSearchPatient = (Button) findViewById(R.id.btnSearchPatient);
		
		
		final DatabaseHandler db = new DatabaseHandler(this);
		btnSearchPatient.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				patientId = Integer.parseInt(etPatientId.getText().toString());
				firstName = etFirstName.getText().toString();
				lastName = etLastName.getText().toString();
				
				//Patient instance
				Patient patient = new Patient();
				//checking all three inputs do have values
				if (patientId==0){
					if (firstName == ""){
						if(lastName == ""){
							patient = db.getPatient(patientId, firstName, lastName);
						} else {
							Toast.makeText(getBaseContext(), "Last name is required !", Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(getBaseContext(), "First name is required !", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getBaseContext(), "Correct patient id is required !", Toast.LENGTH_SHORT).show();
				}
				
				if (patient.getPatientId() == 0){
					
					Intent intent = new Intent(getBaseContext(), ReceptionistPatientProfileView.class);
					Bundle bundle = new Bundle();
					bundle.putParcelable("patient", (Parcelable) patient);
					intent.putExtras(bundle);
				} else {
					Toast.makeText(getBaseContext(), "No patient id found having such information!", Toast.LENGTH_SHORT).show();
				}
				
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
