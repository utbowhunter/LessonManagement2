package com.perkdev.lesson_manager;

		import android.content.ContentValues;
		import android.content.Context;
		import android.database.Cursor;
		import android.database.SQLException;
		import android.database.sqlite.SQLiteDatabase;
		import android.database.sqlite.SQLiteOpenHelper;
		import android.util.Log;
		import android.widget.Toast;

		import java.sql.Date;
		import java.util.ArrayList;


// TO USE:
// Change the package (at top) to match your project.
// Search for "TODO", and make the appropriate changes.
public class DBAdapter {

	/////////////////////////////////////////////////////////////////////
	//	Constants & Data
	// -- Database constants
	private static final String TAG = "DBAdapter";

	public static final String DATABASE_NAME = "lesson_manager";
	public static final int DATABASE_VERSION = 1;


	// -- Student Table Definitions
	public static final String STUDENT_ID = "id";
	public static final String STUDENT_FULLNAME = "fullName";
    public static final String STUDENT_BIRTHDATE = "birthDate";
    public static final String STUDENT_FULL_ADDRESS = "streetAddress";
	public static final String STUDENT_CONTACT_PHONE = "phone";
    public static final String STUDENT_CONTACT_EMAIL = "email";
	public static final String STUDENT_PARENT_NAMES = "parentName";
    public static final String STUDENT_LESSON_RATE = "default_rate";
	public static final String STUDENT_NOTES = "default_rate";


	private static final String TABLE_STUDENT = "student";

	private static final String STUDENT_TABLE_CREATE = "create table if not exists " + TABLE_STUDENT + " (id integer primary key autoincrement, "
			+ "fullName VARCHAR(50) not null, birthDate VARCHAR(10) null, "
			+ "address VARCHAR(75) not null, city VARCHAR(50) not null, state char(2) not null, zip char(10) not null, phone VARCHAR(25) not null, email VARCHAR(50) not null"
			+ "parentName VARCHAR(50) not null, default_rate DOUBLE not null;";

	// -- Appointment Table Definition
	public static final String APPOINTMENT_ID = "id";
	public static final String APPOINTMENT_DATE = "date";
	public static final String APPOINTMENT_START = "startTime";
	public static final String APPOINTMENT_END = "endTime";
	public static final String APPOINTMENT_STATUS = "status";
	public static final String APPOINTMENT_RATE = "rate";

	private static final String TABLE_APPOINTMENT = "appointment";

	private static final String APPOINTMENT_TABLE_CREATE = "create table if not exists " + TABLE_APPOINTMENT + " (id integer primary key autoincrement, "
			+ "date VARCHAR(10) not null, startTime VARCHAR(10) not null, endTime VARCHAR(10) not null, status VARCHAR(20) not null, rate DOUBLE not null));";


	// -- Payment Table Definition
    public static final String PAYMENT_ID = "id";
    public static final String PAYMENT_AMOUNT = "amount";
    public static final String PAYMENT_TYPE = "type";
    public static final String PAYMENT_DATE = "date";
    public static final String PAYMENT_CHECKNUMBER = "checkNum";
    public static final String PAYMENT_NOTES = "notes";

    public static final String TABLE_PAYMENT = "payment";
    public static final String PAYMENT_TABLE_CREATE = "create table if not exists " + TABLE_PAYMENT + " (id integer primary key autoincrement, "
			+ "amount REAL not null, type INTEGER not null, date VARCHAR(10) not null, checkNum TEXT null, notes TEXT null);";

	// -- XRef Table for Student and Appointment relationship
    public static final String APPOINTMENT_XREF_ID = "appointmentId";
    public static final String STUDENT_XREF_ID = "studentId";

    public static final String TABLE_APPOINTMENT_STUDENT_XREF = "appointment_student_xref";
    public static final String APPOINTMENT_STUDENT_TABLE_CREATE = "create table if not exists " + TABLE_APPOINTMENT_STUDENT_XREF  + " appointment_student_xref (appointmentId integer not null, "
			+ "studentId int not null);";

	// -- XRef Table for Student and Payment relationship
    public static final String PAYMENT_XREF_ID = "paymentId";


    public static final String TABLE_PAYMENT_STUDENT_XREF = "payment_student_xref";
    public static final String PAYMENT_STUDENT_TABLE_CREATE = "create table if not exists " + TABLE_PAYMENT_STUDENT_XREF  + "  (paymentId integer not null, "
			+ "studentId int not null);";

	// Context of application who uses us.
	private final Context context;

	private DatabaseHelper myDBHelper;
	private SQLiteDatabase db;

	/////////////////////////////////////////////////////////////////////
	//	Public methods:
	/////////////////////////////////////////////////////////////////////

	public DBAdapter(Context ctx) {
		this.context = ctx;
		myDBHelper = new DatabaseHelper(context);
	}

	// Open the database connection.
	public DBAdapter open() {
		db = myDBHelper.getWritableDatabase();
		return this;
	}

	// Close the database connection.
	public void close() {
		myDBHelper.close();
	}

	// Add a new set of values to the database.
	public long insertStudent(String name, int studentNum, String favColour) {
		/*
		 * CHANGE 3:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		/*initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_STUDENTNUM, studentNum);
		initialValues.put(KEY_FAVCOLOUR, favColour);*/

		// Insert it into the database.
		//return db.insert(DATABASE_TABLE, null, initialValues);
		return 0;
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteRow(long rowId) {
//		String where = KEY_ROWID + "=" + rowId;
//		return db.delete(DATABASE_TABLE, where, null) != 0;

		return true;
	}

	public void deleteAll() {
//		Cursor c = getAllRows();
//		long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
//		if (c.moveToFirst()) {
//			do {
//				deleteRow(c.getLong((int) rowId));
//			} while (c.moveToNext());
//		}
//		c.close();
	}

	// Return all data in the database.
	public Cursor getStudentInfoListView()
	{

		ArrayList<StudentListViewInfo> _studentInfoList = new ArrayList<StudentListViewInfo>();

		_studentInfoList = GetStudentInfoForListView();

		if (_studentInfoList.size() > 0)
		{


		}

        return null;
	}

	private ArrayList<StudentListViewInfo> GetStudentInfoForListView() {

		ArrayList<StudentListViewInfo> _studentInfoList = new ArrayList<StudentListViewInfo>();

		// Setup mapping from cursor to view fields
		String[] fromFieldNames = new String[]{
				DBAdapter.STUDENT_ID,
				DBAdapter.STUDENT_FULLNAME,
		};

		// ***** query the database for all students
		Cursor _studentCursor = db.query(TABLE_STUDENT, fromFieldNames, null, null, null, null, null, null);

		if (_studentCursor != null)
		{
			_studentCursor.moveToFirst();

			// ***** Loop through and add info from query to object list
			do {

				StudentListViewInfo _studentInfo = new StudentListViewInfo();

				_studentInfo.studentId = Integer.parseInt(_studentCursor.getString(0));  // Id
				_studentInfo.fullName = _studentCursor.getString(1);   // Fullname

				_studentInfoList.add(_studentInfo);
			}
			while (_studentCursor.moveToNext());


		}
		else
		{
			return null;
		}

		return _studentInfoList;

	}


	public Student getStudentInfoById(int id) {

		// Setup mapping from cursor to view fields
		String[] fromFieldNames = new String[]{
				DBAdapter.STUDENT_ID,
				DBAdapter.STUDENT_FULLNAME,
				DBAdapter.STUDENT_BIRTHDATE,
				DBAdapter.STUDENT_FULL_ADDRESS,
				DBAdapter.STUDENT_CONTACT_PHONE,
				DBAdapter.STUDENT_CONTACT_EMAIL,
				DBAdapter.STUDENT_PARENT_NAMES,
				DBAdapter.STUDENT_LESSON_RATE,
				DBAdapter.STUDENT_NOTES
		};

		Cursor _cursor = db.query(TABLE_STUDENT, fromFieldNames, STUDENT_ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);

		if (_cursor != null)
		{
			_cursor.moveToFirst();
		}
		else
		{
			return null;
		}

		Student _student = new Student();

		_student.id = Integer.parseInt(_cursor.getString(0));  // Id
		_student.fullName = _cursor.getString(1);   // Fullname
		_student.birthDate = _cursor.getString(2);   // Birthdate
		_student.fullAddress = _cursor.getString(3);   // Full Address
		_student.contactPhone = _cursor.getString(4);   // Contact Phone
		_student.contactEmail = _cursor.getString(5);   // Contact Email
		_student.parentNames = _cursor.getString(6);   // Parents Name
		_student.defaultRate = Double.parseDouble(_cursor.getString(7));    // Lesson Rate
		_student.notes = _cursor.getString(8);     // Notes


		return _student;
	}

	public void setStudentInfo(Student student) {


	}


	/////////////////////////////////////////////////////////////////////
	//	Private Helper Classes:
	/////////////////////////////////////////////////////////////////////

	/**
	 * Private class which handles database creation and upgrading.
	 * Used to handle low-level database access.
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(android.database.sqlite.SQLiteDatabase db) {
			try
			{
				db.execSQL(STUDENT_TABLE_CREATE);
				db.execSQL(APPOINTMENT_TABLE_CREATE);
				db.execSQL(APPOINTMENT_STUDENT_TABLE_CREATE);
				db.execSQL(PAYMENT_TABLE_CREATE);
				db.execSQL(PAYMENT_STUDENT_TABLE_CREATE);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}


		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			/*Log.w(TAG, "Upgrading application's database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data!");

			// Destroy old database:
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

			// Recreate new database:
			onCreate(_db);*/
		}
	}
}

	
	
	
	
	
	