package com.ayaan;

import android.app.Activity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.content.ContentValues;
import android.content.Context;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UploadStudentList extends ListActivity {
	public EditText classid;
	public EditText sub_code;
	public String fcname;
	public String fcpass;
	public String fccode;
    public String fclassid;
	public Button btnimport;
	public ListView lv;
	public ListAdapter adapter;
	public ArrayList<HashMap<String, String>> myList;
	public static final int requestcode = 1;
	DBAdapter db;
	Cursor c;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploadcsv);
        classid = (EditText)findViewById(R.id.class_id);
        sub_code=(EditText)findViewById(R.id.sub_code);
        //ListView lv = getListView();
        //ListAdapter adapter = new SimpleAdapter(UploadStudentList.this, myList,
         //       R.layout.uploadcsv, new String[]{"Name", "Student_Id"}, new int[]{
         //       R.id.Name, R.id.stud_id});
         //   setListAdapter(adapter);


        //fcode=(EditText)findViewById(R.id.Code);
        db = new DBAdapter(this);
	}
	public void upload_csv(View v)
    {

        Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
        fileintent.addCategory(Intent.CATEGORY_OPENABLE);
        fileintent.setType("*/*");
        try {
            fileintent = Intent.createChooser(fileintent, "Choose a file");
            startActivityForResult(fileintent, requestcode);
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Some Problem with the app!", Toast.LENGTH_LONG).show();
        }

   }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)
            return;
        switch (requestCode) {
            case requestcode:
                Uri filepath = data.getData();
                try {
                    if (resultCode == RESULT_OK) {
                        try {

                           // FileReader file = new FileReader(filepath);
                            InputStream in = getContentResolver().openInputStream(filepath);

                            BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
                            ContentValues contentValues = new ContentValues();
                            String line = "";
                            fccode=sub_code.getText().toString();
                            fclassid= classid.getText().toString();


                            while ((line = buffer.readLine()) != null) {

                                String[] str = line.split(",", 3);  // defining 3 columns with null or blank field //values acceptance
                                //Id, Company,Name,Price
                                //String company = str[0].toString();
                                String Name = str[0].toString().toUpperCase();
                                String Student_id = str[1].toString();
                                String Phone=str[2].toString().toUpperCase();



                                contentValues.put("Name", Name);
                                contentValues.put("Student_id", Student_id);
                                try{

                                    db.open();
                                    db.insertStudentRecord(Name, Student_id, fccode, 0, 0, Phone, fclassid);
                                    db.close();

                                }catch(Exception e)
                                {
                                    Toast.makeText(getApplicationContext(), "Unable to Insert Pls Try again!!",Toast.LENGTH_LONG).show();
                                }


                            }
                            Toast.makeText(getApplicationContext(), "updated", Toast.LENGTH_LONG).show();

                        } catch (IOException e) {
                            Toast.makeText(getApplicationContext(), "cant handle Name!", Toast.LENGTH_LONG).show();
                            // db.endTransaction();
                        }
                    }
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), "cant handle Name!", Toast.LENGTH_LONG).show();
                    // db.endTransaction();
                }
        }
        //myList= controller.getAllProducts();
/*
        if (myList.size() != 0) {
            ListView lv = getListView();
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, myList,
                    R.layout.uploadcsv, new String[]{"Company", "Name", "Price"}, new int[]{
                    R.id.txtproductcompany, R.id.txtproductname, R.id.txtproductprice});
            setListAdapter(adapter);
            //lbl.setText("Data Imported");
        }*/
    }

}
