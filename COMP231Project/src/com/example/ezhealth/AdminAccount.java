package com.example.ezhealth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminAccount extends Activity {
	
	private static final String userType = "Admin";
	
	private EditText etUserName, etPassword, etConfirmPassword, etAdminKey;
	private Button btnCreateAccount;
	private String userName, password, confirmPassword, adminKey;
	
	//Database connection
	final DatabaseHandler db = new DatabaseHandler(this);
	private int adminUserId; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_account);
		
		etUserName = (EditText) findViewById(R.id.etUserName);
		etPassword = (EditText) findViewById(R.id.etPassword);
		etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
		etAdminKey = (EditText) findViewById(R.id.etAdminKey);
		btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);
		
		final DatabaseHandler db = new DatabaseHandler(this); 
		
		btnCreateAccount.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				userName = etUserName.getText().toString();
				password = etPassword.getText().toString();
				confirmPassword = etConfirmPassword.getText().toString();
				adminKey = etAdminKey.getText().toString();
				
				if(db.getAdminKey(MainActivity.md5(adminKey))){
					if(password.equals(confirmPassword)){
						db.addUser(new UserLogin(userType, userName, MainActivity.md5(password)));
						adminUserId = db.getUserId(userType, userName,MainActivity.md5(password) );
						Intent i = new Intent(getBaseContext(), AdminRegistration.class);
						i.putExtra("AdminUserId", adminUserId);
						startActivity(i);
						
					} else {
						Toast.makeText(getBaseContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
						etPassword.setText("");
						etConfirmPassword.setText("");
					}
				}else {
					Toast.makeText(getBaseContext(), "Enter correst admin key", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_account, menu);
		return true;
	}

	
}
