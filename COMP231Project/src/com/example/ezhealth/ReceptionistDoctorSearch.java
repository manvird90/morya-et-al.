package com.example.ezhealth;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReceptionistDoctorSearch extends Activity {
	
	private EditText etDoctorFN, etDoctorLN, etDoctorID;
	private Button btnSearchDoctor;
	private String firstName, lastName;
	private int doctorID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receptionist_doctor_search);
		etDoctorFN = (EditText) findViewById(R.id.etDocFirstName);
		etDoctorLN = (EditText) findViewById(R.id.etDocLastName);
		etDoctorID = (EditText) findViewById(R.id.etDoctorId);
		btnSearchDoctor = (Button) findViewById(R.id.btnSearchDoctor);
		
		final DatabaseHandler db = new DatabaseHandler(this);
		btnSearchDoctor.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View v) {
				doctorID = Integer.parseInt(etDoctorID.getText().toString());
				firstName = etDoctorFN.getText().toString();
				lastName = etDoctorLN.getText().toString();
				Toast.makeText(getBaseContext(), doctorID, Toast.LENGTH_SHORT).show();
				//Patient instance
				Doctor doctor = new Doctor();
//				//checking all three inputs do have values
				if (doctorID!=0){
					if (firstName != ""){
						if(lastName != ""){
							doctor = db.getDoctor(doctorID, firstName, lastName);
						} else {
							Toast.makeText(getBaseContext(), "Last name is required !", Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(getBaseContext(), "First name is required !", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getBaseContext(), "Correct Doctor id is required !", Toast.LENGTH_SHORT).show();
				}
				
				if (doctor.getDoctorId() != 0){
//				
					Intent intent = new Intent(getBaseContext(), ReceptionistDoctorProfileView.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("doctor", doctor);
					intent.putExtras(bundle);
					startActivity(intent);
				} else {
					Toast.makeText(getBaseContext(), "No doctor id found having such information!", Toast.LENGTH_SHORT).show();
				}
			}
		
		
	});}
			
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.receptionist_doctor_search, menu);
		return true;
	}

}
