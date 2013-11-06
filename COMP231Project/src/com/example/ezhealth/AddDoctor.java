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

public class AddDoctor extends Activity {
	
	final Calendar calendar=null;
	
	private RadioGroup rgGender;
	private Button btnAddDoctor;
	private RadioButton rbGender;
	private String gender;
	private Date dob;
	private EditText etFirstName, etLastName, etEmail, etPhone, etDateOfBirth, etApartment, etStreet, etCity, etProvince, etCountry, etPostalCode, etExp, etSpeciality;
	
	
	
	final DatabaseHandler db = new DatabaseHandler(this);
	private int doctorLoginId, doctorId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_doctor);
		Bundle bundle = this.getIntent().getExtras();
		doctorLoginId = bundle.getInt("DoctorUserId");
		
		
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
		etExp = (EditText) findViewById(R.id.txtExp);
		etSpeciality = (EditText) findViewById(R.id.txtSpeciality);
		
		
		//RadioGroup Event
		rgGender = (RadioGroup) findViewById(R.id.rgGender);
		btnAddDoctor = (Button) findViewById(R.id.btnAddDoctor);
		
		btnAddDoctor.setOnClickListener(new View.OnClickListener() {
			
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
				
					
				
				Doctor dt = new Doctor();
				dt.setDoctorLoginId(doctorLoginId);
				dt.setFirstName(etFirstName.getText().toString());
				dt.setLastName(etLastName.getText().toString());
				dt.setGender(gender);
				dt.setDateOfBirth(etDateOfBirth.getText().toString());
				dt.setEmail(etEmail.getText().toString());
				dt.setPhone(etPhone.getText().toString());
				dt.setApartment(etApartment.getText().toString());
				dt.setStreet(etStreet.getText().toString());
				dt.setCity(etCity.getText().toString());
				dt.setProvince(etProvince.getText().toString());
				dt.setCountry(etCountry.getText().toString());
				dt.setPostalCode(etPostalCode.getText().toString());
				dt.setExp(etExp.getText().toString());
				dt.setSpeciality(etSpeciality.getText().toString());
				
				Log.d("Insert: " , "Doctor" +etFirstName.getText().toString()+" "+ etLastName.getText().toString());
				db.addDoctor(dt);
				doctorId = db.getDoctorId(etFirstName.getText().toString(), etLastName.getText().toString(), doctorLoginId);
				
				db.close();
				Toast.makeText(getBaseContext(),"Doctor registration successful", Toast.LENGTH_SHORT).show();
				} else {

					Toast.makeText(getBaseContext(),"Not able to register doctor", Toast.LENGTH_SHORT).show();
				}
				
				} 
				
			
		});
	
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_doctor, menu);
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
		} else if (etExp.getText().toString().equals("")){
			etExp.setError("experience filed cant be left blank!");
		} else if (etSpeciality.getText().toString().equals("")){
			etSpeciality.setError("field");
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

