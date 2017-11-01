import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Odhiambo on 11/1/2017.

/**
 * Created by Odhiambo on 11/1/2017.
 */

public class DatabaseHandler  extends SQLiteOpenHelper {
    //All static variables
    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "StudentRecords";

    //Contacts table name
    private static final String TABLE_STUDENTS= "students";

    //Contacts Table Column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CL_ID = "class_id";
    private ArrayList<Students> studentsList;

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENTS_TABLE = "CREATE TABLE" + TABLE_STUDENTS + "("+ KEY_ID + "INTEGER PRIMARY KEY," + KEY_NAME + "TEXT," + KEY_CL_ID + "TEXT" + ")";
        db.execSQL(CREATE_STUDENTS_TABLE);
    }
    //Upgrading databases
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop older table if existed

        db.execSQL("DROP TABLE IF EXISTS" + TABLE_STUDENTS);

        //Create tables again
        onCreate(db);
    }
    // Adding student

    public void addStudent (Students students) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, students.get_name());
        values.put(KEY_CL_ID, students.get_class_id());

        // inserting row
        db.insert(TABLE_STUDENTS, null, values);
        db.close(); //closing database connection
    }
    public Students getStudents(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STUDENTS, new String[] { KEY_ID, KEY_NAME, KEY_CL_ID}
                , KEY_ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Students students = new Students (Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        //return contact
        return students;
    }
    public List<Students> getAllStudents() {
        List<Students> contactList = new ArrayList<Students>();
        String selectQuery = "SELECT  * FROM" + TABLE_STUDENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) do {
            Students students = new Students();
            students.set_id(Integer.parseInt(cursor.getString(0)));
            students.set_name(cursor.getString(1));
            students.set_class_id(cursor.getString(2));


            studentsList.add(students);
        } while (cursor.moveToNext());


        return studentsList;
    }

    //Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT * FROM" + TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    //Updating single student
    public int updateContact(Students contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.get_name());
        values.put(KEY_CL_ID, contact.get_class_id());

        return db.update(TABLE_STUDENTS, values, KEY_ID + "= ?",
                new String[] { String.valueOf(contact.get_id())});
    }

    //deleting single student
    public void deleteContact(Students contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENTS, KEY_ID + "=?",
                new String[] { String.valueOf(contact.get_id()) });
        db.close();
    }




}
 {
}
