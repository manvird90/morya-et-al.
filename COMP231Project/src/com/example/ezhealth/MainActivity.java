package com.example.ezhealth;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	
	//GUI variables//
	private String userType, userName, userPassword, hexPassword;
	static enum TYPEOFUSER { Patient, HospitalAdmin, Receptionist, Doctor,LabStaff};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final TextView tvUserName = (TextView) findViewById(R.id.eTxtUserName);
		final TextView tvPassword = (TextView) findViewById(R.id.eTxtPassword);
		final Spinner spinnerUser = (Spinner) findViewById(R.id.spinnerUser);
		

		//create database ezhealth instance
		final DatabaseHandler db = new DatabaseHandler(this);
		//adding default user account for hospitaladmin
		Log.d("Insert: " , "Default Users");
		db.addAdminKey(md5("HospitalAdminEzHealth"));
		db.addUser(new UserLogin("HospitalAdmin","admin", md5("admin")));
		db.addUser(new UserLogin("Receptionist","receptionist", md5("receptionist")));
		
		//add departments 
		db.addDepartment("Anaesthetics");
		db.addDepartment("Cardiology");
		db.addDepartment("ENT");
		db.addDepartment("Critical Care");
		db.addDepartment("General Surgery");
		db.addDepartment("Gastroenterology");
		db.addDepartment("Gynaecology");
		db.addDepartment("Haematology");
		db.addDepartment("Neurology");
		db.addDepartment("Oncology");
		db.addDepartment("Opthalmology");
		db.addDepartment("Orthopaedics");
		db.addDepartment("Radiology");
		
		//Login button click event
		Button login = (Button)findViewById(R.id.btnLogin);
		login.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				userName = tvUserName.getText().toString();
				userPassword = tvPassword.getText().toString();
				TextView tvUser = (TextView) spinnerUser.getSelectedView();
			    userType = tvUser.getText().toString();
			    hexPassword = md5(userPassword);
			    navigateToUserProfile(db, userType, userName, hexPassword);
			    //tvUserName.setText() = "";
			   // tvUserPassword.setText()="";
			    tvUserName.setText("");
			    tvPassword.setText("");
			}
			
		});
		
		}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = null;
	    switch (item.getItemId()) {
	        case R.id.adminRegistration:
	        	intent = new Intent(this,AdminAccount.class);
	    
	     }
	    startActivity(intent);
        return true;
        
	}
	
	//md5 encryption method
	public static String md5(String input) {
        String md5 = null;
        if(null == input) return null;
        try {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(input.getBytes(), 0, input.length());
        md5 = new BigInteger(1, digest.digest()).toString(16);
         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
        }
        return md5;
	}
	
	public void navigateToUserProfile(DatabaseHandler db, String ut, String un, String up){
		//checking userlogin -- if it is successful then login
		if(db.getUserLogin(userType,userName,md5(userPassword)))
		{
			Intent i = null;
			switch (TYPEOFUSER.valueOf(userType)){
			case Patient:
				i = new Intent(this, PatientHomepage.class);
				int k = db.getPatientLoginId(userType,userName,md5(userPassword));
				if(k!=0){
					i.putExtra("patientLoginId", k);
				} else {
					Toast.makeText(this, "Not such patient found", Toast.LENGTH_LONG).show();
				}
				break;
			case HospitalAdmin:
				i = new Intent(this, HospitalAdmin.class);
				break;
			case Receptionist:
				i = new Intent(this, Receptionist.class);
				break;
			
			case LabStaff:
				i = new Intent(this, HospitalAdmin.class);
				break;
			case Doctor:
				i = new Intent(this,DoctorLoginBtn.class);
				int d = db.getDoctorLoginId(userType,userName,md5(userPassword));
				if(d!=0){
					i.putExtra("doctorLoginId", d);
				} else {
					Toast.makeText(this, "Not such doctor found", Toast.LENGTH_LONG).show();
				}
				break;
			}
			startActivity(i);
		} else {
			Toast.makeText(this, "You have entered wrong username or password", Toast.LENGTH_LONG).show();
		}
	}
	
}
