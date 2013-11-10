package com.example.ezhealth;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HospitalAdmin extends Activity {

	String[] registerStaff = new String[] {"Register a Doctor", "Register a Receptionist", "Register a Nurse", "Register a Pharmacist"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hospital_admin);
		
		ListView lstView = (ListView) findViewById(R.id.lvAdminStaff);
		
        lstView.setChoiceMode(ListView.CHOICE_MODE_NONE);
        lstView.setTextFilterEnabled(true);

        lstView.setAdapter(new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, registerStaff));
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
   			 		
        		Intent intent = null;
					 switch(position){
					 case 0:
						 intent = new Intent(view.getContext(), DoctorAccount.class);
						 break;
					 case 1:
						 intent = new Intent(view.getContext(), ReceptionistAccount.class);
						 break;
					 case 2:
						 intent = new Intent(view.getContext(), Addnurse.class);
						 break;
					 case 3:
						 intent = new Intent(view.getContext(), AddPharmacist.class);
						 break;
					 }
					 startActivity(intent);
			 }
        	
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hospital_admin, menu);
		return true;
	}
	
}
