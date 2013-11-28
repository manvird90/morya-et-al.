package com.example.ezhealth;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.Menu;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

public class CalView extends Activity {

	private CalendarView calendarView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cal_view);
		calendarView = (CalendarView) findViewById(R.id.cv);
		calendarView.setWeekNumberColor(Color.BLUE);
		
				
	
		calendarView.setOnDateChangeListener(new OnDateChangeListener(){
			@Override
			public void onSelectedDayChange(CalendarView view, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
			
			
			Time currentTime = new Time();
			currentTime.setToNow();
			int currentMonth = currentTime.month;
			int currentDay = currentTime.monthDay;
			int currentYear = currentTime.year;
			DateFormat df = new DateFormat();
			
			
			if(selectedYear>=currentYear){
				if(selectedMonth>=currentMonth){
					if(selectedDayOfMonth>currentDay){
						Toast.makeText(getApplicationContext(),selectedDayOfMonth +"/"+selectedMonth+"/"+ selectedYear+"\n"+
					currentDay+":"+currentMonth+":"+currentYear, Toast.LENGTH_SHORT).show();
						
						
								
					} else {
						Toast.makeText(getApplicationContext(),"Select valid day !",Toast.LENGTH_LONG).show();
					}
					
				}else{
					Toast.makeText(getApplicationContext(),"Select valid month !",Toast.LENGTH_LONG).show();
				}
			} else {
				Toast.makeText(getApplicationContext(),"Select valid year !",Toast.LENGTH_LONG).show();
			}
		}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cal_view, menu);
		return true;
	}

}
