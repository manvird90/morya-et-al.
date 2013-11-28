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
	
	private EditText etFirstName, etLastName, etDoctorId;
	private Button btnSearchDoctor;
	private String firstName, lastName;
	private int doctorId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receptionist_doctor_search);
		etFirstName = (EditText) findViewById(R.id.etDocFirstName);
		etLastName = (EditText) findViewById(R.id.etDocLastName);
		etDoctorId = (EditText) findViewById(R.id.etDoctorId);
		btnSearchDoctor = (Button) findViewById(R.id.btnSearchDoctor);
		
		final DatabaseHandler db = new DatabaseHandler(this);
		btnSearchDoctor.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View v) {
				Doctor doctor = null;
				if(!etDoctorId.getText().toString().trim().equals("")){
					try{
						doctorId = Integer.parseInt(etDoctorId.getText().toString());
						if (!etFirstName.getText().toString().trim().equals("")){
							firstName = etFirstName.getText().toString();
							if(!etLastName.getText().toString().trim().equals("")){
								lastName = etLastName.getText().toString();
							
								doctor = db.getDoctor(doctorId, firstName, lastName);
								
								if (doctor != null){
									Intent intent = new Intent(getBaseContext(), ReceptionistDoctorProfileView.class);
									Bundle bundle = new Bundle();
									bundle.putSerializable("doctor", doctor);
									intent.putExtras(bundle);
									startActivity(intent);
								} else {
									Toast.makeText(getBaseContext(), "No Doctor found having such information!", Toast.LENGTH_SHORT).show();
								}
																
								
							} else {
								Toast.makeText(getBaseContext(), "Last name is required !", Toast.LENGTH_SHORT).show();
							}
						} else {
							Toast.makeText(getBaseContext(), "First name is required !", Toast.LENGTH_SHORT).show();
						}
					}catch (NumberFormatException e){
						Toast.makeText(getBaseContext(), "Enter correct doctor ID!", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getBaseContext(), "Doctor ID is required!", Toast.LENGTH_SHORT).show();
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
