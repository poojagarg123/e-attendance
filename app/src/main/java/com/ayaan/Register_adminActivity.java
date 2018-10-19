package com.ayaan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register_adminActivity extends Activity {
	 public EditText _AdminName;
	 public EditText _AdminPass;
	 public EditText _AdminPass1;
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.admin);
	        _AdminName=(EditText)findViewById(R.id.AdminName);
		_AdminPass=(EditText)findViewById(R.id.AdminPass);
		_AdminPass1=(EditText)findViewById(R.id.AdminPass1);

	 }
	public void admin_home(View v) {
        String fname = _AdminName.getText().toString();
        String fpass = _AdminPass.getText().toString();
        String fpass1 = _AdminPass1.getText().toString();
		if (fname.equals("POOJA") && fpass.equals("pooja") && fpass1.equals("pooja")) {
			Intent i = new Intent(this, Admin_homeActivity.class);
			finish();
			startActivity(i);
		}
		else
		{
			Toast.makeText(getApplicationContext(), "PLEASE FILL CORRECT CREDENTIALS!", Toast.LENGTH_LONG).show();
		}
	}
}