package com.example.ezhealth;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class AddDoctor extends Activity {
	
	final Calendar calendar=null;
	
	private RadioGroup rgGender;
	private Button btnAddDoctor;
	private RadioButton rbGender;
	private String gender;
	private Date dob;
	private EditText etFirstName, etLastName, etEmail, etPhone, etDateOfBirth, etApartment, etStreet, etCity, etProvince, etCountry, etPostalCode, etExp, etSpeciality;
	private Spinner department;
	private ArrayList<String> deptList;
	private int displayDoctorId;
	
	
	final DatabaseHandler db = new DatabaseHandler(this);
	private int doctorLoginId, doctorId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_doctor);
		Bundle bundle = getIntent().getExtras();
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
		department = (Spinner) findViewById(R.id.spinnerDeptartment);
		
		deptList = db.getDepartmentList();
		department.setAdapter(new MyAdapter(deptList));
		
		
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
					
				int deptId = department.getSelectedItemPosition();
				
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
				dt.setDepartmentId(deptId+1);//because department id start with 1 and spinner with 0
				
				Log.d("Insert: " , "Doctor" +etFirstName.getText().toString()+" "+ etLastName.getText().toString());
				displayDoctorId = db.addDoctor(dt);
				doctorId = db.getDoctorId(etFirstName.getText().toString(), etLastName.getText().toString(), doctorLoginId);
				
				db.close();
				Toast.makeText(getBaseContext(),"Doctor id is " + displayDoctorId, Toast.LENGTH_SHORT).show();
				finish();
				Intent intent = new Intent(getBaseContext(), HospitalAdmin.class);
				startActivity(intent);
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
	int buffer1=0;
	boolean checkEmailCorrect(String etEmail) {
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
	boolean checkPhoneCorrect(String etPhone) {
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
	boolean checkDOBCorrect(String etDateOfBirth) {
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
	boolean checkFnameCorrect(String etFirstName) {
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
	boolean checkLnameCorrect(String etLastName) {
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
			etLastName.setError("Last Name is required!");
		} else if (etDateOfBirth.getText().toString().equals("")|| checkDOBCorrect(etDOB)!=true){
				etDateOfBirth.setError("Enter Valid Date Of Birth!");
		} else if (etEmail.getText().toString().equals("")|| checkEmailCorrect(etEmail1)!=true/* buffer1!=1*/){
			etEmail.setError("Enter valid email address!");
		} else if (etPhone.getText().toString().equals("")|| checkPhoneCorrect(etPhone1)!=true/* buffer1!=1*/){
			etPhone.setError("Enter valid Phone number!");
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
		
	
    private class MyAdapter extends BaseAdapter implements SpinnerAdapter {

        /**
         * The internal data (the ArrayList with the Objects).
         */
        private final ArrayList<String> dept;

        public MyAdapter(ArrayList<String> dept) {
            this.dept = dept;
        }

        /**
         * Returns the Size of the ArrayList
         */
        @Override
        public int getCount() {
            return dept.size();
        }

        /**
         * Returns one Element of the ArrayList
         * at the specified position.
         */
        @Override
        public Object getItem(int position) {
            return dept.get(position);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }
        /**
         * Returns the View that is shown when a element was
         * selected.
         */
        @Override
        public View getView(int position, View recycle, ViewGroup parent) {
            TextView text;
            if (recycle != null){
                // Re-use the recycled view here!
                text = (TextView) recycle;
            } else {
                // No recycled view, inflate the "original" from the platform:
                text = (TextView) getLayoutInflater().inflate(
                        android.R.layout.simple_dropdown_item_1line, parent, false
                );
            }
            text.setTextColor(Color.BLACK);
            text.setText(dept.get(position));
            return text;
        }


    }
	
}

