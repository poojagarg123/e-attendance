package com.ayaan;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.util.Log;
import android.widget.TextView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;

import javax.security.auth.Subject;

public class ViewAttendance  extends ListActivity implements OnClickListener  {
	public String Sccode = PoojaActivity.fccode;
	public EditText fromDateEtxt;
	public EditText toDateEtxt;
	public EditText classcode;
	public EditText Subjectcode;
	public ListView listV;
	public String fcfromdate;
	public String fctodate;
	public String fcsubcode;
	public String fcclasscode;
	private DatePickerDialog fromDatePickerDialog;
	private DatePickerDialog toDatePickerDialog;

	private SimpleDateFormat dateFormatter;

	DBAdapter db;
	Cursor c,c1;
	public ListAdapter adapter;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extract_records);
		dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

		findViewsById();

		setDateTimeField();
         db=new DBAdapter(this);

	}

	private void setDateTimeField() {
		fromDateEtxt.setOnClickListener(this);
		toDateEtxt.setOnClickListener(this);

		Calendar newCalendar = Calendar.getInstance();
		fromDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				Calendar newDate = Calendar.getInstance();
				newDate.set(year, monthOfYear, dayOfMonth);
				dateFormatter =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
			}

		},newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

		toDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				Calendar newDate = Calendar.getInstance();
				newDate.set(year, monthOfYear, dayOfMonth);
				dateFormatter =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				toDateEtxt.setText(dateFormatter.format(newDate.getTime()));
			}

		},newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
	}


	@Override
	public void onClick(View view) {
		if(view == fromDateEtxt) {
			fromDatePickerDialog.show();
		} else if(view == toDateEtxt) {
			toDatePickerDialog.show();
		}
	}
	 public void DisplayRecord(Cursor c)
     {
         
		  
		  TextView name=(TextView)findViewById(R.id.fac);
		  TextView clg=(TextView)findViewById(R.id.scode);
		  name.setText(c.getString(1));
		  clg.setText(c.getString(3));
		
		 
		  
     }

	private void findViewsById() {

		classcode = (EditText) findViewById(R.id.ClassCode);
		Subjectcode = (EditText) findViewById(R.id.Username);
		listV = getListView() ;


		fromDateEtxt = (EditText) findViewById(R.id.StartDate);
		fromDateEtxt.setInputType(InputType.TYPE_NULL);

		fromDateEtxt.requestFocus();

		toDateEtxt = (EditText) findViewById(R.id.Enddate);
		toDateEtxt.setInputType(InputType.TYPE_NULL);
	}


	 public void Search(View v)
     {

		 fcfromdate=fromDateEtxt.getText().toString();
		 fctodate=toDateEtxt.getText().toString();
		 fcsubcode=Subjectcode.getText().toString();
		 fcclasscode=classcode.getText().toString();

		 try
		 {





			 db.open();

			 c=db.getAdvanceSearch(fcsubcode, fcclasscode, fcfromdate, fctodate);
			 ArrayList<HashMap<String, String>> userList = new ArrayList<HashMap<String, String>>();
			 while (c.moveToNext()){
				 HashMap<String,String> user = new HashMap<String, String>();
				 user.put("Sname",c.getString(c.getColumnIndex("Sname")));
				 user.put("_id",c.getString(c.getColumnIndex("_id")));
				 user.put("absent_count",c.getString(c.getColumnIndex("absent_count")));
				 user.put("present_count",c.getString(c.getColumnIndex("present_count")));
				 userList.add(user);
			 }

			 int[] boundTo = new int[] {
					 R.id.Usn1,
					 R.id.student_id1,
					 R.id.Attended1,
					 R.id.Missed1

			 };

			 if(c.getCount()==0)
			 {
				 Toast.makeText(getApplicationContext(), "No Records Exist for this Data", Toast.LENGTH_LONG).show();
				 adapter = new SimpleAdapter(
						 this,
						 userList,
						 R.layout.display_records,
						 new String[] {"Sname", "_id","absent_count","present_count"},
						 boundTo
				 );
				 listV.setAdapter(adapter);
				 db.close();
				 return ;
			 }
			 else
			 {
				 adapter = new SimpleAdapter(
						 this,
						 userList,
						 R.layout.display_records,
						 new String[] {"Sname", "_id","absent_count","present_count"},
						 boundTo
						 );
				 listV.setAdapter(adapter);
				// setListAdapter(adapter);
				 //db.close();
			 }
			 db.close();
		 }catch(Exception e)
		 {
			 Toast.makeText(getApplicationContext(), "Exception Occured", Toast.LENGTH_LONG).show();
			 return;
		 }

		 
		
		 
		  
     }


}
