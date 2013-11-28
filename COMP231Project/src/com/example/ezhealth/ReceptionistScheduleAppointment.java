package com.example.ezhealth;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ReceptionistScheduleAppointment extends Activity implements OnItemSelectedListener{

	private Spinner spinnerDoctor, spinnerDepartment;
	private RadioGroup appointmentTime;
	private RadioButton firstSlot, secondSlot, thirdSlot;
	private Button selectDate, btnGetPatient;
	private ArrayList<String> deptList, doctorNameList;
	private ArrayList<Integer> doctorIdList;
	private TextView tv, tvPatientName;
	private EditText etPatientId;
	private int departmentId, doctorId;
	
	final DatabaseHandler db = new DatabaseHandler(this);
	Patient patient = new Patient();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receptionist_schedule_appointment);
		tv = (TextView) findViewById(R.id.tv);
		spinnerDepartment = (Spinner) findViewById(R.id.spinnerDeptartmentAppointment);
		spinnerDoctor = (Spinner) findViewById(R.id.spinnerDoctorName);
		selectDate = (Button) findViewById(R.id.btnSelectDate);
		btnGetPatient = (Button) findViewById(R.id.btnGetPatient);
		etPatientId = (EditText) findViewById(R.id.etPatientId);
		tvPatientName = (TextView) findViewById(R.id.tvPatientName);
		
		btnGetPatient.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(etPatientId.getText().toString()!=""){
					try{
						patient = db.getPatient(Integer.parseInt(etPatientId.getText().toString()));
						tvPatientName.setText("Patient name is "+patient.getFirstName()+"\t"+patient.getLastName());
						
					} catch (NumberFormatException e){
						Toast.makeText(getBaseContext(), "Enter valis patient id!!", Toast.LENGTH_SHORT).show();
					}
					
				} else {
					Toast.makeText(getBaseContext(), "Patient Id is required!!", Toast.LENGTH_SHORT).show();
				}
				
			}
		});		
		Calendar calendar = Calendar.getInstance();
		int month = calendar.MONTH;
		int day = calendar.DAY_OF_MONTH;
		int year = calendar.YEAR;
		
		
		//calendarView.setMinDate("year/mm/dd");
		tv.setText(calendar.getTimeInMillis()+"");
		
		deptList = db.getDepartmentList();
		spinnerDepartment.setAdapter(new MyAdapter(deptList));
		spinnerDepartment.setOnItemSelectedListener(this);
		
		selectDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getBaseContext(), CalView.class);
				startActivity(i);
				
			}
		});
		

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.receptionist_schedule_appointment,
				menu);
		return true;
	}
	
	//Methods to select item listener
	 @Override
	 public void onItemSelected(AdapterView<?> parent, View v, int position,long id) {

		if(parent.getId() == R.id.spinnerDeptartmentAppointment){
			  tv.setText(deptList.get(position));
			  departmentId = position+1;
			  doctorNameList = db.getDoctorNameList(departmentId);
			  doctorIdList = db.getDoctorIdList(departmentId);
			  spinnerDoctor.setAdapter(new MyAdapter(doctorNameList));
		}
		
		if(parent.getId() == R.id.spinnerDoctorName){
			  
		}
		
	 }
	 
	 @Override
	 public void onNothingSelected(AdapterView<?> arg0) {
	  tv.setText("");
	 }
	 
	
    private class MyAdapter extends BaseAdapter implements SpinnerAdapter {

      
        private final ArrayList<String> dept;

        public MyAdapter(ArrayList<String> dept) {
            this.dept = dept;
        }

        
        @Override
        public int getCount() {
            return dept.size();
        }

       
        @Override
        public Object getItem(int position) {
            return dept.get(position);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }
       
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
