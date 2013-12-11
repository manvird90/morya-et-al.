package com.example.ezhealth;

import java.sql.Date;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
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
	private int displayPatientId;
	
	
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
				displayPatientId = db.addPatient(pt);
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
				Toast.makeText(getBaseContext(),"Patient id is "+ displayPatientId, Toast.LENGTH_SHORT).show();
				finish();
				Intent intent = new Intent(getBaseContext(), Receptionist.class);
				startActivity(intent);
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
	int buffer1=0;
	public boolean checkEmailCorrect(String etEmail) {
        String pttn = "^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+([a-zA-Z])+";
        Pattern p = Pattern.compile(pttn);
        Matcher m = p.matcher(etEmail);

        if (m.matches()) {
        	//buffer1=1;
        	
               return true;
               
        }
        //buffer1=2;
        return false;
        
 }
	int buffer2=0;
	public boolean checkPhoneCorrect(String etPhone) {
        String pttn = "^[+]?[0-9]{10,13}$";
        Pattern p = Pattern.compile(pttn);
        Matcher m = p.matcher(etPhone);

        if (m.matches()) {
        	//buffer1=1;
        	
               return true;
               
        }
        //buffer1=2;
        return false;
        
 }
	//^(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(\/|-|\.)(?:0?[1,3-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$
	int buffer3=0;
	public boolean checkDOBCorrect(String etDateOfBirth) {
        String pttn = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
        Pattern p = Pattern.compile(pttn);
        Matcher m = p.matcher(etDateOfBirth);

        if (m.matches()) {
        	//buffer1=1;
        	
               return true;
               
        }
        //buffer1=2;
        return false;
        
 }
	int buffer4=0;
	public boolean checkFnameCorrect(String etFirstName) {
        String pttn = "[A-Za-z]+";
        Pattern p = Pattern.compile(pttn);
        Matcher m = p.matcher(etFirstName);

        if (m.matches()) {
        	//buffer1=1;
        	
               return true;
               
        }
        //buffer1=2;
        return false;
        
 }
	int buffer5=0;
	public boolean checkLnameCorrect(String etLastName) {
        String pttn = "[A-Za-z]+";
        Pattern p = Pattern.compile(pttn);
        Matcher m = p.matcher(etLastName);

        if (m.matches()) {
        	//buffer1=1;
        	
               return true;
               
        }
        //buffer1=2;
        return false;
        
 }
	String etEmail1=null;
	String etPhone1=null;
	String etDOB=null;
	String etFname=null;
	String etLname=null;
	public boolean validateField(){
		
		 etEmail1=etEmail.getText().toString();
		 etPhone1=etPhone.getText().toString();
		 etDOB=etDateOfBirth.getText().toString();
		 etFname=etFirstName.getText().toString();
		 etLname=etLastName.getText().toString();
		 
		if(etFirstName.getText().toString().equals("")||checkFnameCorrect(etFname)!=true){
			etFirstName.setError("First Name recieve only Characters");
		} else if (etLastName.getText().toString().equals("")||checkLnameCorrect(etLname)!=true){
			etLastName.setError("Last Name recieve only Characters");
		}  else if (etDateOfBirth.getText().toString().equals("")|| checkDOBCorrect(etDOB)!=true){
			etDateOfBirth.setError("Enter Valid Date Of Birth!");
		} else if (etEmail.getText().toString().equals("")|| checkEmailCorrect(etEmail1)!=true/* buffer1!=1*/){
			etEmail.setError("Enter valid email address!");
		} else if (etPhone.getText().toString().equals("")|| checkPhoneCorrect(etPhone1)!=true/* buffer1!=1*/){
			etPhone.setError("Enter valid Phone number!");
		}  else if (etApartment.getText().toString().equals("")){
			etApartment.setError("Apartment filed cant be left blank!");
		}else if (etStreet.getText().toString().equals("")){
				etStreet.setError("Apartment filed cant be left blank!");
		}else if (etCity.getText().toString().equals("")){
			etCity.setError("City filed cant be left blank!");
		}else if (etProvince.getText().toString().equals("")){
			etProvince.setError("Province filed cant be left blank!");
		} else if (etCountry.getText().toString().equals("")){
			etCountry.setError("Country filed cant be left blank!");
		} else if (etPostalCode.getText().toString().equals("")){
			etPostalCode.setError("Postal Code filed cant be left blank!");
		} else if (etHealthPolicyNumber.getText().toString().equals("")){
			etHealthPolicyNumber.setError("Policy Number is required!");
		} else if (etInsuranceCompany.getText().toString().equals("")){
			etInsuranceCompany.setError("Insurance company name is required!");
		}
		
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

