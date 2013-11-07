package com.example.ezhealth;

import java.sql.Date;
import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AdminRegistration extends Activity {
	
	final Calendar calendar=null;
	
	private RadioGroup rgGender;
	private Button btnAdminRegister;
	private RadioButton rbGender;
	private String gender;
	private Date dob;
	private EditText etFirstName, etLastName, etEmail, etPhone, etDateOfBirth, etApartment, etStreet, etCity, etProvince, etCountry, etPostalCode;
	
	
	
	final DatabaseHandler db = new DatabaseHandler(this);
	private int adminLoginId, adminId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_registration);
		Bundle bundle = this.getIntent().getExtras();
		adminLoginId = bundle.getInt("AdminUserId");
		
		
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
		btnAdminRegister = (Button) findViewById(R.id.btnRegisterAdmin);
		
		btnAdminRegister.setOnClickListener(new View.OnClickListener() {
			
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
				
					
				
				Admin ad = new Admin();
				ad.setAdminLoginId(adminLoginId);
				ad.setFirstName(etFirstName.getText().toString());
				ad.setLastName(etLastName.getText().toString());
				ad.setGender(gender);
				ad.setDateOfBirth(etDateOfBirth.getText().toString());
				ad.setEmail(etEmail.getText().toString());
				ad.setPhone(etPhone.getText().toString());
				ad.setApartment(etApartment.getText().toString());
				ad.setStreet(etStreet.getText().toString());
				ad.setCity(etCity.getText().toString());
				ad.setProvince(etProvince.getText().toString());
				ad.setCountry(etCountry.getText().toString());
				ad.setPostalCode(etPostalCode.getText().toString());
				
				
				Log.d("Insert: " , "Admin" +etFirstName.getText().toString()+" "+ etLastName.getText().toString());
				db.addAdmin(ad);
				adminId = db.getAdminId(etFirstName.getText().toString(), etLastName.getText().toString(), adminLoginId);
				
			
				
	
				db.close();
				Toast.makeText(getBaseContext(),"Admin registration successful", Toast.LENGTH_SHORT).show();
				} else {

					Toast.makeText(getBaseContext(),"Not able to register admin", Toast.LENGTH_SHORT).show();
				}
				
				} 
				
			
		});
	
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_registration, menu);
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


