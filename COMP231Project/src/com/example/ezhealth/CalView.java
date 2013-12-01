package com.example.ezhealth;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
		
		final String appointmentDay = getIntent().getStringExtra("appointmentDay");
		
				
	
		calendarView.setOnDateChangeListener(new OnDateChangeListener(){
			@Override
			public void onSelectedDayChange(CalendarView view, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
			
			
			Time currentTime = new Time();
			currentTime.setToNow();
			int currentMonth = currentTime.month;
			int currentMonthDay = currentTime.monthDay;
			int currentYear = currentTime.year;
			String selectedWeekDay = null;
			
			if(selectedYear==currentYear){
				if(selectedMonth==currentMonth){
					if(selectedDayOfMonth>currentMonthDay){
						Toast.makeText(getApplicationContext(),selectedDayOfMonth +"/"+selectedMonth+"/"+ selectedYear+"\n"+
								currentMonthDay+":"+currentMonth+":"+currentYear, Toast.LENGTH_SHORT).show();
						
						final Time t = new Time();
						t.set( 0, 0 , 0, selectedDayOfMonth, selectedMonth, selectedYear);
						t.normalize(false);
						t.set(0,0,0,selectedDayOfMonth, selectedMonth, selectedYear);
						
						switch(t.weekDay){
						case 0:
							selectedWeekDay = "Sunday";
							break;
						case 1:
							selectedWeekDay = "Monday";
							break;
						case 2:
							selectedWeekDay = "Tuesday";
							break;
						case 3:
							selectedWeekDay = "Wednesay";
							break;
						case 4:
							selectedWeekDay = "Thursday";
							break;
						case 5:
							selectedWeekDay = "Friday";
							break;
						case 6:
							selectedWeekDay = "Saturday";
							break;
							
						}
						
						if(appointmentDay.trim().equals(selectedWeekDay.trim())){
							Intent i = new Intent(getBaseContext(), ReceptionistScheduleAppointment.class);
							i.putExtra("appointmentYear", selectedYear);
							i.putExtra("appointmentMonth", selectedMonth+1);
							i.putExtra("appointmentMonthDay", selectedDayOfMonth+1);
							startActivity(i);
						} else {
							Toast.makeText(getApplicationContext(),"Select Date only on " +appointmentDay+" !",Toast.LENGTH_LONG).show();
						}
						
								
					} else {
						Toast.makeText(getApplicationContext(),"Select valid day !",Toast.LENGTH_LONG).show();
					}
					
				}else if(selectedMonth > currentMonth){
					
					Toast.makeText(getApplicationContext(),selectedDayOfMonth +"/"+selectedMonth+"/"+ selectedYear+"\n"+
							currentMonthDay+":"+currentMonth+":"+currentYear, Toast.LENGTH_SHORT).show();
					
					final Time t = new Time();
					t.set( 0, 0 , 0, selectedDayOfMonth, selectedMonth, selectedYear);
					t.normalize(false);
					switch(t.weekDay){
					case 0:
						selectedWeekDay = "Sunday";
						break;
					case 1:
						selectedWeekDay = "Monday";
						break;
					case 2:
						selectedWeekDay = "Tuesday";
						break;
					case 3:
						selectedWeekDay = "Wednesay";
						break;
					case 4:
						selectedWeekDay = "Thursday";
						break;
					case 5:
						selectedWeekDay = "Friday";
						break;
					case 6:
						selectedWeekDay = "Saturday";
						break;
						
					}
					
					if(appointmentDay.trim().equals(selectedWeekDay.trim())){
						/*Intent i = new Intent(getBaseContext(), ReceptionistScheduleAppointment.class);
						i.putExtra("appointmentDate", selectedDayOfMonth+1+"/"+selectedMonth+1+":"+selectedYear);
						i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
						startActivity(i);*/
						
						 Intent returnIntent = new Intent();
						 returnIntent.putExtra("appointmentDate", selectedDayOfMonth+"/"+(selectedMonth+1)+"/"+selectedYear);
						 setResult(RESULT_OK,returnIntent);     
						 finish();
					} else {
						Toast.makeText(getApplicationContext(),"Select Date only on " +appointmentDay+" !",Toast.LENGTH_LONG).show();
					}
					
							
				} else {
					Toast.makeText(getApplicationContext(),"Select valid month !",Toast.LENGTH_LONG).show();
				}
			} else if(selectedYear>currentYear){
				
					Toast.makeText(getApplicationContext(),selectedDayOfMonth +"/"+selectedMonth+"/"+ selectedYear+"\n"+
							currentMonthDay+":"+currentMonth+":"+currentYear, Toast.LENGTH_SHORT).show();
					
					final Time t = new Time();
					t.set( 0, 0 , 0, selectedDayOfMonth, selectedMonth, selectedYear);
					t.normalize(false);
					switch(t.weekDay){
					case 0:
						selectedWeekDay = "Sunday";
						break;
					case 1:
						selectedWeekDay = "Monday";
						break;
					case 2:
						selectedWeekDay = "Tuesday";
						break;
					case 3:
						selectedWeekDay = "Wednesay";
						break;
					case 4:
						selectedWeekDay = "Thursday";
						break;
					case 5:
						selectedWeekDay = "Friday";
						break;
					case 6:
						selectedWeekDay = "Saturday";
						break;
						
					}
					
					if(appointmentDay.trim().equals(selectedWeekDay.trim())){
						Intent i = new Intent(getBaseContext(), ReceptionistScheduleAppointment.class);
						i.putExtra("appointmentYear", selectedYear);
						i.putExtra("appointmentMonth", selectedMonth+1);
						i.putExtra("appointmentMonthDay", selectedDayOfMonth+1);
						startActivity(i);
					} else {
						Toast.makeText(getApplicationContext(),"Select Date only on " +appointmentDay+" !",Toast.LENGTH_LONG).show();
					}
					
							
				} else {
					Toast.makeText(getApplicationContext(),"Select valid month !",Toast.LENGTH_LONG).show();
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
