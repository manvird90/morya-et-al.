package com.example.ezhealth;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminDoctorSearchToAddSchedule extends Activity {
	private EditText etFirstName, etLastName, etDoctorId;
	private Button btnSearchDoctor;
	private String firstName, lastName;
	private int doctorId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_doctor_search_to_add_schedule);
		
		etFirstName = (EditText) findViewById(R.id.etFirstName);
		etLastName = (EditText) findViewById(R.id.etLastName);
		etDoctorId = (EditText) findViewById(R.id.etDoctorId);
		btnSearchDoctor = (Button) findViewById(R.id.btnSearchDoctor);
		

		final DatabaseHandler db = new DatabaseHandler(this);
		btnSearchDoctor.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				doctorId = Integer.parseInt(etDoctorId.getText().toString());
				firstName = etFirstName.getText().toString();
				lastName = etLastName.getText().toString();
				
				//Patient instance
				Doctor doctor = new Doctor();
				//checking all three inputs do have values
				if (doctorId!=0){
					if (firstName != ""){
						if(lastName != ""){
							doctor = db.getDoctor(doctorId, firstName, lastName);
						} else {
							Toast.makeText(getBaseContext(), "Last name is required !", Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(getBaseContext(), "First name is required !", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getBaseContext(), "Correct doctor id is required !", Toast.LENGTH_SHORT).show();
				}
				if (doctor.getDoctorId() != 0){
					
					Intent intent = new Intent(getBaseContext(), AdminAddDoctorSchedule.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("doctor", doctor);
					intent.putExtras(bundle);
					startActivity(intent);
				} else {
					Toast.makeText(getBaseContext(), "No doctor found having such information!", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_doctor_search_to_add_schedule,
				menu);
		return true;
	}

	
}
