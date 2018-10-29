package com.ayaan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestActivity extends Activity {
	public EditText fclassid;
	public static String fcclassid;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage);
		fclassid=(EditText)findViewById(R.id.class_id);


	}
	public void log_out(View v)
	{
		
		Intent i = new Intent(this,PoojaActivity.class);
		finish();
		startActivity(i);
	}
	public void manage_stud(View v)
	{
		fcclassid=fclassid.getText().toString();
		if(fcclassid.equals(""))
		{
			Toast.makeText(getApplicationContext(), "Pls Fill in the classid!", Toast.LENGTH_LONG).show();
			return;
		}
		Intent i = new Intent(this,ManageActivity.class);
		finish();
		startActivity(i);
	}
	public void mark_att(View v)
	{
		fcclassid=fclassid.getText().toString();
		if(fcclassid.equals(""))
		{
			Toast.makeText(getApplicationContext(), "Pls Fill in the classid!", Toast.LENGTH_LONG).show();
			return;
		}
		Intent i = new Intent(this,AttendanceActivity.class);
		startActivity(i);
	}
	public void view_att(View v)
	{
		fcclassid=fclassid.getText().toString();
		if(fcclassid.equals(""))
		{
			Toast.makeText(getApplicationContext(), "Pls Fill in the classid!", Toast.LENGTH_LONG).show();
			return;
		}
		Intent i = new Intent(this,ViewActivity.class);
		startActivity(i);
	}
    public void uploadcsv(View v)
    {
		fcclassid=fclassid.getText().toString();
		if(fcclassid.equals(""))
		{
			Toast.makeText(getApplicationContext(), "Pls Fill in the classid!", Toast.LENGTH_LONG).show();
			return;
		}
        Intent i = new Intent(this,UploadStudentList.class);
        startActivity(i);
    }

	public void ExtractRecords(View v)
	{
		fcclassid=fclassid.getText().toString();
		if(fcclassid.equals(""))
		{
			Toast.makeText(getApplicationContext(), "Pls Fill in the classid!", Toast.LENGTH_LONG).show();
			return;
		}
		Intent i = new Intent(this,ViewAttendance.class);
		startActivity(i);
	}

}
