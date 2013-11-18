package com.example.ezhealth;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class AdminAddDoctorSchedule extends Activity {
	
	private TextView profileTitle, displayName, displayContact, displayDepartment;
	private int departmentId;
	private Spinner spnDay;
	private TimePicker tpDutyStart, tpDutyEnd;
	private String day, dutyStart, dutyEnd; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_add_doctor_schedule);
		Doctor doctor = new Doctor();
		doctor = (Doctor) getIntent().getSerializableExtra("doctor");
		final DatabaseHandler db = new DatabaseHandler(this);
		profileTitle = (TextView) findViewById(R.id.doctorProfileTitle);
		displayName = (TextView) findViewById(R.id.displayName);
		displayContact = (TextView) findViewById(R.id.displayContact);
		spnDay = (Spinner) findViewById(R.id.spnDay);
		tpDutyStart = (TimePicker) findViewById(R.id.tpDutyStart);
		tpDutyEnd = (TimePicker) findViewById(R.id.tpDutyEnd);
		
		
		profileTitle.setText("Doctor Profile ID: "+doctor.getDoctorId());
				
		displayName.setText(doctor.getFirstName().toUpperCase()+" "+doctor.getLastName().toUpperCase());
		
		displayContact.setText("Phone: " + doctor.getPhone().toString()+
				"\nEmail: " + doctor.getEmail().toString());
		displayDepartment = (TextView) findViewById(R.id.displayDepartment);
		departmentId = doctor.getDepartmentId();
		displayDepartment.setText(db.getDepartmentName(departmentId));
		
		
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_add_doctor_schedule, menu);
		return true;
	}

}
