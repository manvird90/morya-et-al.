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

public class ReceptionistScheduleAppointment extends Activity{

        private Spinner spinnerDoctor, spinnerDepartment, spinnerTime;
        private RadioGroup rgAppointmentTime, rgDoctorDays;
        private RadioButton firstSlot, secondSlot, thirdSlot;
        private Button selectDate, btnGetPatient, makeAppointment, done, getTime;
        private ArrayList<String> deptList, doctorNameList;
        private ArrayList<Integer> doctorIdList;
        private TextView tvPatientName, tvDate;
        private EditText etPatientId;
        private int departmentId, doctorId;
        private String appointmentTime = null; 
        private String appointmentDate =null;
        private String appointmentDay = null;
        
        final DatabaseHandler db = new DatabaseHandler(this);
        Patient patient = null;
        
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_receptionist_schedule_appointment);
                
                spinnerDepartment = (Spinner) findViewById(R.id.spinnerDeptartmentAppointment);
                spinnerDoctor = (Spinner) findViewById(R.id.spinnerDoctorName);
                spinnerTime = (Spinner) findViewById(R.id.spinnerTime);
                selectDate = (Button) findViewById(R.id.btnSelectDate);
                btnGetPatient = (Button) findViewById(R.id.btnGetPatient);
                etPatientId = (EditText) findViewById(R.id.etPatientId);
                tvPatientName = (TextView) findViewById(R.id.tvPatientName);
                tvDate = (TextView) findViewById(R.id.tvDate);
                rgDoctorDays = (RadioGroup) findViewById(R.id.rgDoctorDays);
                makeAppointment = (Button) findViewById(R.id.btnMakeAppointment);
                done = (Button) findViewById(R.id.btnDone);
                
                

                
                
                btnGetPatient.setOnClickListener(new View.OnClickListener() {
                        
                        @Override
                        public void onClick(View v) {
                                if(etPatientId.getText().toString()!=""){
                                        try{
                                                patient = db.getPatient(Integer.parseInt(etPatientId.getText().toString()));
                                                if (patient!=null){
                                                tvPatientName.setText(patient.getFirstName()+"  "+patient.getLastName());
                                                } else {
                                                	Toast.makeText(getBaseContext(), "Patient not found!!", Toast.LENGTH_SHORT).show();
                                                }
                                                
                                        } catch (NumberFormatException e){
                                                Toast.makeText(getBaseContext(), "Enter valid patient id!!", Toast.LENGTH_SHORT).show();
                                        }
                                        
                                } else {
                                        Toast.makeText(getBaseContext(), "Patient Id is required!!", Toast.LENGTH_SHORT).show();
                                }
                                
                        }
                });                
                
                deptList = db.getDepartmentList();
                
                spinnerDepartment.setAdapter(new MyAdapter(deptList));
                
                spinnerDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        			public void onItemSelected(AdapterView<?> parent, View v, int position,long id) {

        				rgDoctorDays.clearCheck();
                        rgDoctorDays.removeAllViews();
                          departmentId = position+1;
                          doctorNameList = db.getDoctorNameList(departmentId);
                          doctorIdList = db.getDoctorIdList(departmentId);
                          spinnerDoctor.setAdapter(new MyAdapter(doctorNameList));
       			}
       			 @Override
       			 public void onNothingSelected(AdapterView<?> arg0) {
       				
       			 }
       		});
                
                spinnerDoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        			public void onItemSelected(AdapterView<?> parent, View v, int position,long id) {

        				rgDoctorDays.clearCheck();
                        rgDoctorDays.removeAllViews();
                        
                        ArrayList<String> day = new ArrayList<String>();
                        doctorId = doctorIdList.get(position);
                        day = db.getDoctorScheduleDayList(doctorId);
                        
                        for (int i =0; i < day.size(); i++){
                                RadioButton rb = new RadioButton(v.getContext());
                                rb.setText(day.get(i));
                                rb.setId(i);
                                rgDoctorDays.addView(rb);
                        }
                        
       			}
       			 @Override
       			 public void onNothingSelected(AdapterView<?> arg0) {
       			 }
       		});
                
                selectDate.setOnClickListener(new View.OnClickListener() {
                        
                        @Override
                        public void onClick(View v) {
                                if(patient!=null){
                                        Intent i = new Intent(getBaseContext(), CalView.class);
                                        try{
                                                appointmentDay = ((RadioButton)findViewById(rgDoctorDays.getCheckedRadioButtonId() )).getText().toString();
                                                i.putExtra("appointmentDay", appointmentDay);
                                                startActivityForResult(i, 1);
                                                
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
                        	
                        	TextView tvUser = (TextView) spinnerTime.getSelectedView();
            			    appointmentTime = tvUser.getText().toString();
                        
                                try{
                                	if(appointmentTime != null){
                                db.addAppointment(patient.getPatientId(), doctorId, departmentId, appointmentDate, appointmentTime);
                                
                                
                                Toast.makeText(getBaseContext(), "Appointment ID: "+db.getAppointmentId()+
                                		"\nDoctor ID: " +doctorId+
                                		"\nPatient ID: "+patient.getPatientId()+
                                		"\nDate: "+appointmentDate+
                                		"\nTime: "+ appointmentTime, Toast.LENGTH_SHORT).show();
                                } else {
                                	Toast.makeText(getBaseContext(), "No time available", Toast.LENGTH_SHORT).show();
                                }
                                } catch (Exception e){
                                Toast.makeText(getBaseContext(), "Error while booking an appointment", Toast.LENGTH_SHORT).show();
                                }
                                
                                
                        }
                });
                
                done.setOnClickListener(new View.OnClickListener() {
                        
                        @Override
                        public void onClick(View v) {
                                Intent i = new Intent(getBaseContext(), Receptionist.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
                     tvDate.setText(appointmentDate);
                     ArrayList<String> time = new ArrayList<String>();
                     time = db.getDoctorDayTimeList(doctorId, appointmentDay);
                     spinnerTime.setAdapter(new MyAdapter(time));
                     
               }
            }
    
    
   /* private class RetrieveTime extends AsyncTask<String, Void, ArrayList<String>> {

            
        @Override
        protected ArrayList<String> doInBackground(String... params) {
                
                ArrayList<String> timeList = null;
                 timeList = db.getDoctorDayTimeList(Integer.parseInt(params[1]), params[0]);
            return timeList;
        }        
        protected void onPostExecute(ArrayList<String> list) {
                 for (int i =0; i < list.size(); i++){
                	 rgAppointmentTime.clearCheck();
                	 rgAppointmentTime.removeAllViews();
                                         RadioButton rb = new RadioButton(getBaseContext());
                                         rb.setText(list.get(i));
                                         rb.setId(i);
                                         rgAppointmentTime.addView(rb);
                                 }
                        
                }
    }*/
        }
}