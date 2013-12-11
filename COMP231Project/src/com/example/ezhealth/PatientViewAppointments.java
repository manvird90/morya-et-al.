package com.example.ezhealth;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PatientViewAppointments extends Activity implements OnItemClickListener {
	ListView lvAppointment;
	DatabaseHandler db;
	ArrayList<Appointment> listAppointment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_view_appointments);
		int patientId = getIntent().getExtras().getInt("patientId");
		lvAppointment = (ListView) findViewById(R.id.lvAllAppointment);
		db = new DatabaseHandler(getApplicationContext());
		listAppointment = db.getAllAppointments(patientId);
		lvAppointment.setAdapter(new DoctorsListAdapter());
		lvAppointment.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_view_appointments, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long arg3) {
		// TODO Auto-generated method stub
		Appointment appointment = listAppointment.get(position);
		Intent intent = new Intent(getBaseContext(), AppointmentDetail.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("appointment", appointment);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
	class DoctorsListAdapter extends BaseAdapter {

		LayoutInflater mInflater = null;

		public DoctorsListAdapter() {
			mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listAppointment.size();
		}

		@Override
		public Object getItem(int index) {
			// TODO Auto-generated method stub
			return listAppointment.get(index);
		}

		@Override
		public long getItemId(int id) {
			// TODO Auto-generated method stub
			return id;
		}

		@Override
		public View getView(int index, View view, ViewGroup root) {
			// TODO Auto-generated method stub
			if (view == null) {
				view = mInflater.inflate(R.layout.all_doctors_display, root, false);
			}
			Appointment appointment = listAppointment.get(index);
			TextView firstName = (TextView) view.findViewById(R.id.tvFirstName);
			TextView lastName = (TextView) view.findViewById(R.id.tvLastName);
			TextView doctorId = (TextView) view.findViewById(R.id.tvDoctorId);

			doctorId.setText(""+appointment.getAppointmentId()+"\t");
			firstName.setText(appointment.getDate()+"\t");
			lastName.setText(appointment.getTime());
			

			return view;
		}

	}
}
