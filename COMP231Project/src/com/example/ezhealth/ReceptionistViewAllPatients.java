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

public class ReceptionistViewAllPatients extends Activity implements OnItemClickListener{
	
	ListView lvPatients;
	DatabaseHandler db;
	ArrayList<Patient> listPatients;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receptionist_view_all_patients);
		lvPatients = (ListView) findViewById(R.id.lvAllPatients);
		db = new DatabaseHandler(getApplicationContext());
		listPatients = db.getAllPatients();
		lvPatients.setAdapter(new PatientsListAdapter());
		lvPatients.setOnItemClickListener(this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long arg3) {
		// TODO Auto-generated method stub
		Patient patient = listPatients.get(position);
		Intent intent = new Intent(ReceptionistViewAllPatients.this, ReceptionistPatientProfileView.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("patient", patient);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.receptionist_view_all_patients, menu);
		return true;
	}
	
	class PatientsListAdapter extends BaseAdapter {

		LayoutInflater mInflater = null;

		public PatientsListAdapter() {
			mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listPatients.size();
		}

		@Override
		public Object getItem(int index) {
			// TODO Auto-generated method stub
			return listPatients.get(index);
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
				view = mInflater.inflate(R.layout.all_patients_display, root, false);
			}
			Patient patient = listPatients.get(index);
			TextView firstName = (TextView) view.findViewById(R.id.tvFirstName);
			TextView lastName = (TextView) view.findViewById(R.id.tvLastName);
			TextView patientId = (TextView) view.findViewById(R.id.tvPatientId);

			patientId.setText(""+patient.getPatientId()+"\t");
			firstName.setText(patient.getFirstName()+"\t");
			lastName.setText(patient.getLastName());
			

			return view;
		}

	}


}
