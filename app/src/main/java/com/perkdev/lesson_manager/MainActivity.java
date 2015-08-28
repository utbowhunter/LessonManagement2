package com.perkdev.lesson_manager;


import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    DBAdapter _myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<CharSequence> _adapter;

        OpenDb();
        /// Popuate the List View from the database

        PopulateListViewFromDB();

        //setContentView(R.layout.activity_main);

        Button _buttonNewStudent = (Button) findViewById(R.id.buttonNewStudent);
        Spinner _spinner = (Spinner) findViewById(R.id.spinnerPaymentType);

        _adapter = ArrayAdapter.createFromResource(this, R.array.payment_types,android.R.layout.simple_spinner_item);
        _adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        _spinner.setAdapter(_adapter);
        _spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + "selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        _buttonNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent = new Intent(MainActivity.this, Student.class);

                startActivity(_intent);


            }
        });




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
    private void PopulateListViewFromDB() {

        Cursor _cursor = _myDb.getStudentInfoListView();


        // Allow Acitivy to manage the lifetime of the cursor DEPRECATED
        startManagingCursor(_cursor);

        // Setup mapping from cursor to view fields
        String[] fromFieldNames = new String[]{
                DBAdapter.STUDENT_FULLNAME
        };

        int[] toViewIds = new int[]{R.id.editTextFullName};

        // Create adapter to map columns of the database to UI
        SimpleCursorAdapter myCursorAdapater = new SimpleCursorAdapter(
                this,                   // Context
                R.layout.item_layout,   //Row layout
                _cursor,                // Set of database records to map
                fromFieldNames,         // DB Column Names4
                toViewIds              // View Ids to put info into
                );

        // Set adapter for the List View
        ListView _listView;
        _listView = (ListView) findViewById(R.id.listViewStudent);
        _listView.setAdapter(myCursorAdapater);

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
