package com.example.ezhealth;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
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
	private RadioGroup rgAppointmentTime, rgDoctorDays;
	private RadioButton firstSlot, secondSlot, thirdSlot;
	private Button selectDate, btnGetPatient, makeAppointment, done;
	private ArrayList<String> deptList, doctorNameList;
	private ArrayList<Integer> doctorIdList;
	private TextView tv, tvPatientName;
	private EditText etPatientId;
	private int departmentId, doctorId;
	private String appointmentTime = null; 
	private String appointmentDate =null;
	private String appointmentDay = null;
	
	final DatabaseHandler db = new DatabaseHandler(this);
	Patient patient = null;
	Doctor doctor = null;
	
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
		rgDoctorDays = (RadioGroup) findViewById(R.id.rgDoctorDays);
		rgAppointmentTime = (RadioGroup) findViewById(R.id.rgAppointmentTime);
		makeAppointment = (Button) findViewById(R.id.btnMakeAppointment);
		done = (Button) findViewById(R.id.btnDone);
		

		
		
		btnGetPatient.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(etPatientId.getText().toString()!=""){
					try{
						patient = db.getPatient(Integer.parseInt(etPatientId.getText().toString()));
						tvPatientName.setText("Patient name is "+patient.getFirstName()+"\t"+patient.getLastName());
						
					} catch (NumberFormatException e){
						Toast.makeText(getBaseContext(), "Enter valid patient id!!", Toast.LENGTH_SHORT).show();
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
		  spinnerDoctor.setOnItemSelectedListener(this);
		
		selectDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(patient!=null){
					Intent i = new Intent(getBaseContext(), CalView.class);
					try{
						appointmentDay = ((RadioButton)findViewById(rgDoctorDays.getCheckedRadioButtonId() )).getText().toString();
				    	i.putExtra("appointmentDay", appointmentDay);
						startActivityForResult(i, 1);
						if(appointmentDate!=null){
							db.getDoctorScheduleDayList(doctorId);
						} else {
							Toast.makeText(getBaseContext(), "Date Selection is required!", Toast.LENGTH_SHORT).show();
						}
					} catch (Exception e) {
						Toast.makeText(getBaseContext(), "You have not selected doctor OR \n"+
								"Doctor appointment is not available OR\n"+
								"You have not selected day from above radiobutton", Toast.LENGTH_SHORT).show();
					}
				   
					
				} else {
					Toast.makeText(getBaseContext(), "Patient information is not retrieved!!", Toast.LENGTH_SHORT).show();
				}
			}
			
		});
		
		makeAppointment.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
				db.addAppointment(patient.getPatientId(), doctor.getDoctorId(), departmentId, appointmentDate, appointmentTime);
				} catch (Exception e){
					Toast.makeText(getBaseContext(), "Error in inserting appointment", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		done.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getBaseContext(), Receptionist.class);
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
			rgDoctorDays.clearCheck();
			rgDoctorDays.removeAllViews();
			  tv.setText(deptList.get(position));
			  departmentId = position+1;
			  doctorNameList = db.getDoctorNameList(departmentId);
			  doctorIdList = db.getDoctorIdList(departmentId);
			  spinnerDoctor.setAdapter(new MyAdapter(doctorNameList));
		} else if (parent.getId() == R.id.spinnerDoctorName){
			rgDoctorDays.clearCheck();
			rgDoctorDays.removeAllViews();
			
			ArrayList<String> day = new ArrayList<String>();
			day = db.getDoctorScheduleDayList(doctorIdList.get(position));
			
			for (int i =0; i < day.size(); i++){
				RadioButton rb = new RadioButton(this);
				rb.setText(day.get(i));
				rb.setId(i);
				rgDoctorDays.addView(rb);
			}
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
    
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

    	  if (requestCode == 1) {

    	     if(resultCode == RESULT_OK){      
    	         appointmentDate = intent.getStringExtra("appointmentDate");
    	         //ArrayList<String> timeList = null;
    	         try{
    	        	 String did = Integer.toString(doctor.getDoctorId());
    	             new RetrieveTime().execute(appointmentDay, did);
    	         } catch (Exception e){
    	        	 Toast.makeText(getBaseContext(), "Error in retriving time list", Toast.LENGTH_SHORT).show();
    	         }
    	         
    	     }
    	     
    	  }
    	}
    
    
    private class RetrieveTime extends AsyncTask<String, Void, ArrayList<String>> {

    	
        @Override
        protected ArrayList<String> doInBackground(String... params) {
        	
        	ArrayList<String> timeList = null;
        	 timeList = db.getDoctorDayTimeList(Integer.parseInt(params[1]), params[0]);
            return timeList;
        }        
        protected void onPostExecute(ArrayList<String> list) {
        	 for (int i =0; i < list.size(); i++){
	 				RadioButton rb = new RadioButton(getBaseContext());
	 				rb.setText(list.get(i));
	 				rb.setId(i);
	 				rgAppointmentTime.addView(rb);
	 			}
			
		}
    }
 
}
