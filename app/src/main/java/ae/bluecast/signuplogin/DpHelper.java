package ae.bluecast.signuplogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ASUS on 9/18/2017.
 */

public class DpHelper extends SQLiteOpenHelper {

    public static final String TAG = DpHelper.class.getSimpleName();
    public static final String DB_NAME = "my_app";
    public  static final int DB_VERSION = 1;
    public static final String USERS_TABLE="users";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_EMAIL="email";
    public static final String COLUMN_PASS="password";

    public  static final String CREATE_TABLE_USERS = "CREATE TABLE " + USERS_TABLE +
            "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
             + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PASS + " TEXT);";

    public DpHelper(Context context)
    {
        super(context, DB_NAME ,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("query",CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_USERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXIST" + USERS_TABLE);
        onCreate(db);
    }
    public void addUser(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL , email);
        values.put(COLUMN_PASS , password);
        long id = db.insert(USERS_TABLE ,null,values);
        Log.d(TAG, "USER INSERTED " + id);
    }
    public  boolean getUser (String email ,String password)
    {
        String selectQuery  = " select * from "  + USERS_TABLE + " where " +COLUMN_EMAIL + "=" + "'" +email+ "'" + " and " +COLUMN_PASS +
                "=" + "'" +password+ "'" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0 ){
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }
}
