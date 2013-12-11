package com.example.ezhealth;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class AppointmentDetail extends Activity {

	TextView taid, tdid, ttid, tdate, ttime;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appointment_detail);
		taid = (TextView) findViewById(R.id.taid);
		tdid = (TextView) findViewById(R.id.tdid);
		ttid = (TextView) findViewById(R.id.ttid);
		tdate = (TextView) findViewById(R.id.tdate);
		ttime = (TextView) findViewById(R.id.ttime);
		
		Appointment a = new Appointment();
		a = (Appointment) getIntent().getSerializableExtra("appointment");
		
		taid.setText(a.getAppointmentId()+"");
		tdid.setText(a.getDoctorId()+"");
		ttid.setText(a.getDepartmentId()+"");
		tdate.setText(a.getDate());
		ttime.setText(a.getTime());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.appointment_detail, menu);
		return true;
	}

}
