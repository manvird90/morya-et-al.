package com.example.ezhealth;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ReceptionistDoctorSearch extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receptionist_doctor_search);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.receptionist_doctor_search, menu);
		return true;
	}

}
