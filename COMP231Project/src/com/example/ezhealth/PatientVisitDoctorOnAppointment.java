package com.example.ezhealth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PatientVisitDoctorOnAppointment extends Activity {

	private EditText etAppointmentId, etPrescription, etSymptoms, etTreatment;
	private Button btnGetAppointment, btnAddTreatment;
	private TextView tvPatientName, tvDoctorName;
	int appointmentId = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_visit_doctor_on_appointment);
		etAppointmentId = (EditText) findViewById(R.id.etAppointmentId);
		etPrescription = (EditText) findViewById(R.id.etPrescription);
		etSymptoms = (EditText) findViewById(R.id.etSymptoms);
		etTreatment = (EditText) findViewById(R.id.etTreatment);
		btnGetAppointment = (Button) findViewById(R.id.btnGetAppointment);
		btnAddTreatment = (Button) findViewById(R.id.btnAddTreatment);
		tvPatientName =  (TextView) findViewById(R.id.tvPName);
		tvDoctorName =  (TextView) findViewById(R.id.tvDName);
		
		
		final DatabaseHandler db = new DatabaseHandler(this);
		btnGetAppointment.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
					Appointment appointment = null;
				    if(etAppointmentId.getText().toString()!=""){
				    	appointmentId = Integer.parseInt(etAppointmentId.getText().toString());
					appointment = db.getAppointment(appointmentId);
						if(appointment != null){
							Patient patient = db.getPatient(appointment.getPatientId());
							tvPatientName.setText(patient.getFirstName()+"\t"+patient.getLastName());
							Doctor doctor = db.getDoctor(appointment.getDoctorId());
							tvDoctorName.setText(doctor.getFirstName()+"\t"+doctor.getLastName());
						} else {
							Toast.makeText(getBaseContext(), "No such appointment found!!", Toast.LENGTH_SHORT).show();
						}
					
					} else {
						Toast.makeText(getBaseContext(), "Enter AppointmentId", Toast.LENGTH_SHORT).show();
					}
				} catch (NumberFormatException e){
					Toast.makeText(getBaseContext(), "Enter valid AppointmentId!!", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		btnAddTreatment.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(appointmentId!=0){
					if(etSymptoms.getText().toString()!="" ){
						Treatment trt = new Treatment();
						trt.setAppointmentId(appointmentId);
						trt.setSymptoms(etSymptoms.getText().toString());
						trt.setPrescription(etPrescription.getText().toString());
						trt.setTreatment(etTreatment.getText().toString());
						try{
						db.addTreatment(trt);
						Intent i = new Intent(getBaseContext(), DoctorHomePage.class);
						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(i);
						} catch (Exception e){
							Toast.makeText(getBaseContext(), "Erro while entering treatment", Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(getBaseContext(), "Enter Symptoms Atleast", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getBaseContext(), "Enter Appointment Id and Get Detail and Verify", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_visit_doctor_on_appointment,
				menu);
		return true;
	}

}
