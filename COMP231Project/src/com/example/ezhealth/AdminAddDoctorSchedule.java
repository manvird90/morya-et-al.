package com.example.ezhealth;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AdminAddDoctorSchedule extends Activity {
	
	private TextView profileTitle, displayName, displayContact, displayDepartment, tvCurrentTime;
	private int departmentId;
	private TimePicker tpDutyStart, tpDutyEnd;
	private String day; 
	private int startHour, startMinute, endHour, endMinute;
	private Button btnAddSchedule, btnDone;
	private String currentDay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_add_doctor_schedule);
		final Doctor doctor = (Doctor) getIntent().getSerializableExtra("doctor");
		final DatabaseHandler db = new DatabaseHandler(this);
		profileTitle = (TextView) findViewById(R.id.doctorProfileTitle);
		displayName = (TextView) findViewById(R.id.displayName);
		displayContact = (TextView) findViewById(R.id.displayContact);
		final Spinner spnDay = (Spinner) findViewById(R.id.spnDay);
		tpDutyStart = (TimePicker) findViewById(R.id.tpDutyStart);
		tpDutyEnd = (TimePicker) findViewById(R.id.tpDutyEnd);
		btnAddSchedule = (Button) findViewById(R.id.btnAddSchedule);
		btnDone = (Button) findViewById(R.id.btnDone);
		
		
		
		profileTitle.setText("Doctor Profile ID: "+doctor.getDoctorId());
				
		displayName.setText(doctor.getFirstName().toUpperCase()+" "+doctor.getLastName().toUpperCase());
		
		displayContact.setText("Phone: " + doctor.getPhone().toString()+
				"\nEmail: " + doctor.getEmail().toString());
		displayDepartment = (TextView) findViewById(R.id.displayDepartment);
		departmentId = doctor.getDepartmentId();
		displayDepartment.setText(db.getDepartmentName(departmentId));
		
		setCurrentTimeOnView();
		
		btnAddSchedule.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView day = (TextView) spnDay.getSelectedView();
				if(!day.getText().toString().equals("Select Day")){
					DoctorSchedule ds = new DoctorSchedule();
					ds.setDoctorId(doctor.getDoctorId());
					ds.setDay(day.getText().toString());
					ds.setDutyStartTime(tpDutyStart.getCurrentHour()+":"+tpDutyStart.getCurrentMinute());
					ds.setDutyEndTime(tpDutyEnd.getCurrentHour()+":"+tpDutyEnd.getCurrentMinute());
					db.addDoctorSchedule(ds);
					setCurrentTimeOnView();
					Toast.makeText(getBaseContext(), "Schedule of doctor id "+ds.getDoctorId()+" is added for "+ ds.getDay()+
							"start from "+ ds.getDutyStartTime()+ " to "+ds.getDutyEndTime(), Toast.LENGTH_LONG).show();
					spnDay.setSelection(0);
					setCurrentTimeOnView();
				} else {
					Toast.makeText(getBaseContext(), "Day selection is required!", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		btnDone.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), HospitalAdmin.class);
				startActivity(intent);
			}
		});
		
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_add_doctor_schedule, menu);
		return true;
	}
	
	public void setCurrentTimeOnView() {
		 
		int hour, minute;
 
		final Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
 
		tpDutyStart.setCurrentHour(hour);
		tpDutyEnd.setCurrentHour(hour);
		tpDutyStart.setCurrentMinute(minute);
		tpDutyEnd.setCurrentMinute(minute);
 
	}


}
