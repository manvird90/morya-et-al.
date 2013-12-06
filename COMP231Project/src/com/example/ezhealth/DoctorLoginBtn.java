package com.example.ezhealth;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

//Manvir K dhaliwal: doctor home page----new

public class DoctorLoginBtn extends Activity {
	private Button btnProfile, btnSchedule, btnAppointment,btnPatient,btnAppInfo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_login_btn);
		final int doctorLoginId = getIntent().getExtras().getInt("doctorLoginId");
		
		btnProfile = (Button) findViewById(R.id.btnProfile);
		btnProfile.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getBaseContext(), DoctorHomePage.class);
				i.putExtra("doctorLoginId", doctorLoginId);
				startActivity(i);
			}
		});
		btnPatient = (Button) findViewById(R.id.btnViewPatient);
		btnPatient.setOnClickListener(new View.OnClickListener()
		{
		@Override
				public void onClick(View v) {
					Intent i = new Intent(getBaseContext(), ReceptionistPatientSearch.class);
					startActivity(i);
				}
			});
		btnAppInfo = (Button) findViewById(R.id.btnAppInfo);
		btnAppInfo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getBaseContext(), PatientVisitDoctorOnAppointment.class);
				startActivity(i);
			}
		});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.doctor_login_btn, menu);
		return true;
	}
	 public boolean onOptionsItemSelected(MenuItem item) 
	    {    
	    	Intent intent= new Intent(this, MainActivity.class);
	    	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    	startActivity(intent);
	    	return true;
	    }

}
