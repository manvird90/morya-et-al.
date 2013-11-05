package com.example.ezhealth;

import java.sql.Date;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PatientRegistration extends Activity {
	
	final Calendar calendar=null;
	
	private RadioGroup rgGender;
	private Button btnPatientRegister;
	private RadioButton rbGender;
	private String gender;
	private Date dob;
	private EditText etFirstName, etLastName, etEmail, etPhone, etDateOfBirth, etApartment, etStreet, etCity, etProvince, etCountry, etPostalCode, etHealthPolicyNumber, etInsuranceCompany;
	private EditText etHeight, etWeight, etAllergy, etDiseaseHistory, etChronicDisease;
	
	
	final DatabaseHandler db = new DatabaseHandler(this);
	private int patientLoginId, patientId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_registration);
		Bundle bundle = this.getIntent().getExtras();
		patientLoginId = bundle.getInt("PatientUserId");
		
		
		//personalInformation
		etFirstName = (EditText) findViewById(R.id.txtFirstName);
		etLastName = (EditText) findViewById(R.id.txtLastName);
		etDateOfBirth = (EditText) findViewById(R.id.txtDateOfBirth);
		etEmail = (EditText) findViewById(R.id.txtEmail);
		etPhone = (EditText) findViewById(R.id.txtPhone);
		etApartment = (EditText) findViewById(R.id.txtApartment); 
		etStreet = (EditText) findViewById(R.id.txtStreet);
		etCity = (EditText) findViewById(R.id.txtCity);
		etProvince = (EditText) findViewById(R.id.txtProvince);
		etCountry = (EditText) findViewById(R.id.txtCountry);
		etPostalCode = (EditText) findViewById(R.id.txtPostalCode);
		etHealthPolicyNumber = (EditText) findViewById(R.id.txtInsurance);
		etInsuranceCompany = (EditText) findViewById(R.id.txtInsurComp);
		
		
		//Vital Information
		etHeight = (EditText) findViewById(R.id.txtHeight);
		etWeight = (EditText) findViewById(R.id.txtWeight);
	    etAllergy = (EditText) findViewById(R.id.txtAllergy);
	    etDiseaseHistory = (EditText) findViewById(R.id.txtDiseaseHistory);
	    etChronicDisease = (EditText) findViewById(R.id.txtChronicDisease);
	    
	    
		//RadioGroup Event
		rgGender = (RadioGroup) findViewById(R.id.rgGender);
		btnPatientRegister = (Button) findViewById(R.id.btnRegisterPatient);
		
		btnPatientRegister.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(validateField()){
				
				//Radio button listener setting
				if(rgGender.getCheckedRadioButtonId()!=-1){
				    int id= rgGender.getCheckedRadioButtonId();
				    View radioButton = rgGender.findViewById(id);
				    int radioId = rgGender.indexOfChild(radioButton);
				    rbGender = (RadioButton) rgGender.getChildAt(radioId);
				    gender = (String) rbGender.getText();
				} 
				
					
				
				Patient pt = new Patient();
				pt.setPatientLoginId(patientLoginId);
				pt.setFirstName(etFirstName.getText().toString());
				pt.setLastName(etLastName.getText().toString());
				pt.setGender(gender);
				pt.setDateOfBirth(etDateOfBirth.getText().toString());
				pt.setEmail(etEmail.getText().toString());
				pt.setPhone(etPhone.getText().toString());
				pt.setApartment(etApartment.getText().toString());
				pt.setStreet(etStreet.getText().toString());
				pt.setCity(etCity.getText().toString());
				pt.setProvince(etProvince.getText().toString());
				pt.setCountry(etCountry.getText().toString());
				pt.setPostalCode(etPostalCode.getText().toString());
				pt.setHealthPolicyNumber(etHealthPolicyNumber.getText().toString());
				pt.setInsuranceCompany(etInsuranceCompany.getText().toString());
				
				Log.d("Insert: " , "Patient" +etFirstName.getText().toString()+" "+ etLastName.getText().toString());
				db.addPatient(pt);
				patientId = db.getPatientId(etFirstName.getText().toString(), etLastName.getText().toString(), patientLoginId);
				
				PatientVital pv = new PatientVital();
				pv.setPatientId(patientId);
				pv.setHeight(Float.parseFloat(etHeight.getText().toString()));
				pv.setHeight(Float.parseFloat(etWeight.getText().toString()));
				pv.setAllergy(etAllergy.getText().toString());
				pv.setChronicDisease(etChronicDisease.getText().toString());
				pv.setDiseaseHistory(etDiseaseHistory.getText().toString());
				
				Log.d("Insert: " , "Vital information of Patient having Id:"+ patientId);
				db.addVitalInfo(pv);
				db.close();
				Toast.makeText(getBaseContext(),"Patient registration successful", Toast.LENGTH_SHORT).show();
				} else {

					Toast.makeText(getBaseContext(),"Not able to register patient", Toast.LENGTH_SHORT).show();
				}
				
				} 
				
			
		});
	
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_registration, menu);
		return true;
	}

	public boolean validateField(){
		
		if(etFirstName.getText().toString().equals("")){
			etFirstName.setError("First Name is required!");
		} else if (etLastName.getText().toString().equals("")){
			etLastName.setError("Last Name is required!");
		} else if (etEmail.getText().toString().equals("")){
			etEmail.setError("Email is required!");
		} else if (etPhone.getText().toString().equals("")){
			etPhone.setError("Phone number is required!");
		} else if (etLastName.getText().toString().equals("")){
			etLastName.setError("Last Name is required!");
		} else if (etHealthPolicyNumber.getText().toString().equals("")){
			etHealthPolicyNumber.setError("Policy Number is required!");
		} else if (etInsuranceCompany.getText().toString().equals("")){
			etInsuranceCompany.setError("Insurance company name is required!");
		}
		/*
		 else if(!etFirstName.getText().toString().equals("")){
		 
			Pattern p = Pattern.compile("[A-Za-z]+$");
			Matcher m = p.matcher(etFirstName.getText().toString());
			if(!m.matches()){
				etFirstName.setError("First Name must contains alphabetical only!");
			}
		} else if(!etLastName.getText().toString().equals("")){
			Pattern p = Pattern.compile("[A-Za-z]+$");
			Matcher m = p.matcher(etLastName.getText().toString());
			if(!m.matches()){
				etLastName.setError("Lst Name must contains alphabetical only!");
			}
		} else if(!etEmail.getText().toString().equals("")){
			if(!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches() ){
				etEmail.setError("Enter valid email address!");
			}
		} else if(!etPhone.getText().toString().equals("")){
			if(!android.util.Patterns.PHONE.matcher(etPhone.getText().toString()).matches()){
				etPhone.setError("Enter valid phone number!");
			}
		}*/
		else {
			return true;
		}
		
		return false;
	}
		
}

