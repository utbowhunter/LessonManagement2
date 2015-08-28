package com.perkdev.lesson_manager;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class StudentActivity extends ActionBarActivity {

    DBAdapter _myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean _editFlag = false;

        if (_editFlag == false) {

            // **** Place a read-only flag here
            setContentView(R.layout.student_layout);
        }

        else
        {
            // ****  Place a Edit activity here
            setContentView(R.layout.student_layout);
        }

        ArrayAdapter<CharSequence> _adapter;

        OpenDb();


        /// Popuate the form from the database
        Intent _intent = getIntent();
        Bundle _parameters = _intent.getExtras();

        PopulateStudentInfo(_parameters.getInt("studentId") );


    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        CloseDb();
    }
    private void OpenDb()
    {
        _myDb = new DBAdapter(this);
        _myDb.open();
    }
    private void CloseDb()
    {
         _myDb.close();
    }

    private void PopulateStudentInfo(int Id) {

        // **** Get the student information by the Id passed in
        Student _student = _myDb.getStudentInfoById(Id);

        // ***** Build the fields for the data to update
        EditText _fullName = (EditText) findViewById(R.id.editTextFullName);
        EditText _birthdate = (EditText) findViewById(R.id.dateBirthday);
        EditText _fullStreetAddress = (EditText) findViewById(R.id.editTextFullAddress);
        EditText _contactPhone = (EditText) findViewById(R.id.editTextContactPhone);
        EditText _contactEmail = (EditText) findViewById(R.id.editTextContactEmail);
        EditText _parentNames = (EditText) findViewById(R.id.editTextParentNames);
        EditText _defaultRate = (EditText) findViewById(R.id.numberRate);
        EditText _notes = (EditText) findViewById(R.id.editTextNotes);

        // ***** Set the field values
        _fullName.setText(_student.fullName);
        _birthdate.setText(_student.birthDate);
        _fullStreetAddress.setText(_student.fullAddress);
        _contactPhone.setText(_student.contactPhone);
        _contactEmail.setText(_student.contactEmail);
        _parentNames.setText(_student.parentNames);

        // ***** Convert to a string from Display
        _defaultRate.setText(String.valueOf(_student.defaultRate));
        _notes.setText(_student.notes);

      }
    private void SaveStudentInfo() {


        // ***** Build the fields for the data to update
        EditText _fullName = (EditText) findViewById(R.id.editTextFullName);
        EditText _birthdate = (EditText) findViewById(R.id.dateBirthday);
        EditText _fullStreetAddress = (EditText) findViewById(R.id.editTextFullAddress);
        EditText _contactPhone = (EditText) findViewById(R.id.editTextContactPhone);
        EditText _contactEmail = (EditText) findViewById(R.id.editTextContactEmail);
        EditText _parentNames = (EditText) findViewById(R.id.editTextParentNames);
        EditText _defaultRate = (EditText) findViewById(R.id.numberRate);
        EditText _notes = (EditText) findViewById(R.id.editTextNotes);

        // ***** Create the Student Object
        Student _student = new Student();

        // ***** Set the field values
        _student.fullName = String.valueOf(_fullName.getText());
        _student.birthDate = String.valueOf(_birthdate.getText());
        _student.fullAddress = String.valueOf(_fullStreetAddress.getText());
        _student.contactPhone = String.valueOf(_contactPhone.getText());
        _student.contactEmail = String.valueOf(_contactEmail.getText());
        _student.parentNames = String.valueOf(_parentNames.getText());

        // ***** Convert to a double to store in database for calulations
        _student.defaultRate = Double.valueOf(String.valueOf(_defaultRate.getText()));

        _student.notes = String.valueOf(_notes.getText());

        // **** Save the student information
        _myDb.setStudentInfo(_student);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
