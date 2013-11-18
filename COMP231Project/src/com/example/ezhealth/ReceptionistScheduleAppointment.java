package com.example.ezhealth;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ReceptionistScheduleAppointment extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receptionist_schedule_appointment);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.receptionist_schedule_appointment,
				menu);
		return true;
	}

}
