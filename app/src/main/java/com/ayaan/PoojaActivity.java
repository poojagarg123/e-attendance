package com.ayaan;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class PoojaActivity extends Activity {
	public EditText fcode;
	public EditText fpass;
	public EditText fusername;
	public static String fccode;
	public String fcpass;
	public static String fcusername;
	DBAdapter db;
	Cursor c;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        fcode=(EditText)findViewById(R.id.subCode);
		fusername=(EditText)findViewById(R.id.facname);
        fpass=(EditText)findViewById(R.id.Password);
                
    }
     
    
      public void register(View v)
      {
    	  Intent i = new Intent(this,Register_adminActivity.class);
    	  startActivity(i);
      }
      public void home(View v)
      {
    	  DBAdapter db = new DBAdapter(this);
    	    fccode=fcode.getText().toString();
  			fcpass=fpass.getText().toString();
		  	fcusername=fusername.getText().toString();
  			if(fccode.equals("")||fcpass.equals("")||fcusername.equals(""))
  			{
  				if(fccode.equals(""))
  				{
  				Toast.makeText(getApplicationContext(), "Pls Fill in the Username!", Toast.LENGTH_LONG).show();
  				}
  				else  if(fcpass.equals(""))
  				{	
  					Toast.makeText(getApplicationContext(), "Pls Fill in the Password!", Toast.LENGTH_LONG).show();
  				}
  				else if(fcusername.equals(""))
				{
					Toast.makeText(getApplicationContext(), "Pls Fill in the Username!", Toast.LENGTH_LONG).show();
				}
  			}
  			else
  			{
  				try{
  			
  					db.open();
  					c=db.getRecordByFacNameSubCode(fcusername,fccode);
  					if(c.getCount()==0)
  					{
  						Toast.makeText(getApplicationContext(), "No Teacher is taking this course", Toast.LENGTH_LONG).show();
  						return ;	
  					}
  					if(!c.getString(3).equals(fcpass))
  					{
  						Toast.makeText(getApplicationContext(), "Incorrect Password!", Toast.LENGTH_LONG).show();
  						return ;
  					}
  					db.close();
  				}
  				catch(Exception e)
  				{
  					Toast.makeText(getApplicationContext(), "Could Not Retrieve Data", Toast.LENGTH_LONG).show();
  					return ;
  				}
  				Intent i=new Intent(this,TestActivity.class);
  	            startActivity(i);
  	            //Toast.makeText(getApplicationContext(), "Welcome "+fcname+" !", Toast.LENGTH_LONG).show();
  			}
      }

  }
