package com.example.ezhealth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PatientHomepage extends Activity {
		private Button btnProfile,btnScheduleAppointment, btnViewAppointment;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_patient_homepage);
			final int patientLoginId = getIntent().getExtras().getInt("patientLoginId");
			final DatabaseHandler db = new DatabaseHandler(this);
			final Patient patient = db.getPatientByLoginId(patientLoginId);
			
			btnProfile = (Button) findViewById(R.id.btnProfile);
			btnProfile.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent i = new Intent(getBaseContext(), PatientProfile.class);
					i.putExtra("patientLoginId", patientLoginId);
					startActivity(i);
				}
			});
		btnScheduleAppointment = (Button) findViewById(R.id.btnScheduleAppointment);
			btnScheduleAppointment.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent i = new Intent(getBaseContext(), ReceptionistScheduleAppointment.class);
					i.putExtra("patientId", patient.getPatientId());
					startActivity(i);
				}
			}); 
			
			btnViewAppointment = (Button) findViewById(R.id.btnViewAppointment);
			btnViewAppointment.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent i = new Intent(getBaseContext(), PatientViewAppointments.class);
					i.putExtra("patientId", patient.getPatientId());
					startActivity(i);
				}
			}); 
			
			
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.receptionist, menu);
		return true;
	}

}
