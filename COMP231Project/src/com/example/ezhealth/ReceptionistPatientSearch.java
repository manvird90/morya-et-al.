package com.example.ezhealth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
				//Patient instance
				Patient patient = null;
				//checking all three inputs do have values
				
				if(!etPatientId.getText().toString().trim().equals("")){
					try{
						patientId = Integer.parseInt(etPatientId.getText().toString());
						if (!etFirstName.getText().toString().trim().equals("")){
							firstName = etFirstName.getText().toString();
							if(!etLastName.getText().toString().trim().equals("")){
								lastName = etLastName.getText().toString();
							
								patient = db.getPatient(patientId, firstName, lastName);
								
								if (patient != null){
									Intent intent = new Intent(getBaseContext(), ReceptionistPatientProfileView.class);
									Bundle bundle = new Bundle();
									bundle.putSerializable("patient", patient);
									intent.putExtras(bundle);
									startActivity(intent);
								} else {
									Toast.makeText(getBaseContext(), "No patient found having such information!", Toast.LENGTH_SHORT).show();
								}
																
								
							} else {
								Toast.makeText(getBaseContext(), "Last name is required !", Toast.LENGTH_SHORT).show();
							}
						} else {
							Toast.makeText(getBaseContext(), "First name is required !", Toast.LENGTH_SHORT).show();
						}
					}catch (NumberFormatException e){
						Toast.makeText(getBaseContext(), "Enter correct patient ID!", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getBaseContext(), "Patient ID is required!", Toast.LENGTH_SHORT).show();
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
