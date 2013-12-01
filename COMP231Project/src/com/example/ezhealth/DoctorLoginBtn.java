package com.example.ezhealth;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

//Manvir K dhaliwal: doctor home page----new

public class DoctorLoginBtn extends Activity {
	private Button btnProfile, btnSchedule, btnAppointment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_login_btn);
		btnProfile = (Button) findViewById(R.id.btnProfile);
		btnProfile.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getBaseContext(), DoctorHomePage.class);
				int doctorLoginId = getIntent().getExtras().getInt("doctorLoginId");
				Doctor doctor = db.getDoctorByLoginId(doctorLoginId);
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

}
