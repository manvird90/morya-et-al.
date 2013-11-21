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

public class ReceptionistViewAllDoctor extends Activity implements OnItemClickListener{
	
	ListView lvDoctors;
	DatabaseHandler db;
	ArrayList<Doctor> listDoctors;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receptionist_view_all_doctor);
		lvDoctors = (ListView) findViewById(R.id.lvAllDoctors);
		db = new DatabaseHandler(getApplicationContext());
		listDoctors = db.getAllDoctors();
		lvDoctors.setAdapter(new DoctorsListAdapter());
		lvDoctors.setOnItemClickListener(this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long arg3) {
		// TODO Auto-generated method stub
		Doctor doctor = listDoctors.get(position);
		Intent intent = new Intent(ReceptionistViewAllDoctor.this, ReceptionistDoctorProfileView.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("doctor", doctor);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.receptionist_view_all_doctor, menu);
		return true;
	}
	
	class DoctorsListAdapter extends BaseAdapter {

		LayoutInflater mInflater = null;

		public DoctorsListAdapter() {
			mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listDoctors.size();
		}

		@Override
		public Object getItem(int index) {
			// TODO Auto-generated method stub
			return listDoctors.get(index);
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
			Doctor doctor = listDoctors.get(index);
			TextView firstName = (TextView) view.findViewById(R.id.tvFirstName);
			TextView lastName = (TextView) view.findViewById(R.id.tvLastName);
			TextView doctorId = (TextView) view.findViewById(R.id.tvDoctorId);

			doctorId.setText(""+doctor.getDoctorId()+"\t");
			firstName.setText(doctor.getFirstName()+"\t");
			lastName.setText(doctor.getLastName());
			

			return view;
		}

	}


}
