package com.example.ezhealth;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AddLab extends Activity {
	
	final Calendar calendar=null;
	
	private RadioGroup rgGender;
	private Button btnAddLab;
	private RadioButton rbGender;
	private String gender;
	private Date dob;
	private EditText etFirstName, etLastName, etEmail, etPhone, etDateOfBirth, etApartment, etStreet, etCity, etProvince, etCountry, etPostalCode;
	private int displayLabId;
	
	
	final DatabaseHandler db = new DatabaseHandler(this);
	private int labLoginId, labId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_lab);
		Bundle bundle = getIntent().getExtras();
		labLoginId = bundle.getInt("LabUserId");
		
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
		
		//RadioGroup Event
		rgGender = (RadioGroup) findViewById(R.id.rgGender);
		btnAddLab = (Button) findViewById(R.id.btnAddLab);
		
		btnAddLab.setOnClickListener(new View.OnClickListener() {
			
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
					
				
				Lab lb = new Lab();
				lb.setLabLoginId(labLoginId);
				lb.setFirstName(etFirstName.getText().toString());
				lb.setLastName(etLastName.getText().toString());
				lb.setGender(gender);
				lb.setDateOfBirth(etDateOfBirth.getText().toString());
				lb.setEmail(etEmail.getText().toString());
				lb.setPhone(etPhone.getText().toString());
				lb.setApartment(etApartment.getText().toString());
				lb.setStreet(etStreet.getText().toString());
				lb.setCity(etCity.getText().toString());
				lb.setProvince(etProvince.getText().toString());
				lb.setCountry(etCountry.getText().toString());
				lb.setPostalCode(etPostalCode.getText().toString());
				
				Log.d("Insert: " , "Lab" +etFirstName.getText().toString()+" "+ etLastName.getText().toString());
				displayLabId = db.addLab(lb);
				labId = db.getLabId(etFirstName.getText().toString(), etLastName.getText().toString(), labLoginId);
				
				db.close();
				Toast.makeText(getBaseContext(),"Lab id is " + displayLabId, Toast.LENGTH_SHORT).show();
				finish();
				Intent intent = new Intent(getBaseContext(), HospitalAdmin.class);
				startActivity(intent);
				} else {

					Toast.makeText(getBaseContext(),"Not able to register labstaff", Toast.LENGTH_SHORT).show();
				}
				
				} 
				
			
		});
	
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_lab, menu);
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

