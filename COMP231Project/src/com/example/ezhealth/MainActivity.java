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
	static enum TYPEOFUSER { Patient, HospitalAdmin, Receptionist, Doctor, Nurse,Pharmacist, LabStaff};
	
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
		db.addUser(new UserLogin("Patient","patient", md5("patient")));
		db.addUser(new UserLogin("LabStaff","labstaff", md5("labstaff")));
		db.addUser(new UserLogin("Nurse","nurse", md5("nurse")));
		db.addUser(new UserLogin("Receptionist","receptionist", md5("receptionist")));
		db.addUser(new UserLogin("Pharmacist","pharmacist", md5("pharmacist")));
		db.addUser(new UserLogin("Doctor","doctor", md5("doctor")));
		
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
				i = new Intent(this, HospitalAdmin.class);
				break;
			case HospitalAdmin:
				i = new Intent(this, HospitalAdmin.class);
				break;
			case Receptionist:
				i = new Intent(this, Receptionist.class);
				break;
			case Nurse:
				i = new Intent(this, HospitalAdmin.class);
				break;
			case LabStaff:
				i = new Intent(this, HospitalAdmin.class);
				break;
			case Doctor:
				i = new Intent(this, HospitalAdmin.class);
				break;
			case Pharmacist:
				i = new Intent(this, HospitalAdmin.class);
				break;
			}
			startActivity(i);
		} else {
			Toast.makeText(this, "You have entered wrong username or password", Toast.LENGTH_LONG).show();
		}
	}
	
}
