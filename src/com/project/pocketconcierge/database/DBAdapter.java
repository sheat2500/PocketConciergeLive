package com.project.pocketconcierge.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {

	// 数据库静态信息
	static final String DATABASE_NAME = "myDB";
	static final String DATABASE_TABLE = "accounttable";
	static final int DATABASE_VERSION = 1;
	static final String _ID = "_id";
	static final String USERNAME = "username";
	static final String PASSWORD = "password";
	static final String DATEBIRTH = "datebirth";
	static final String DATABASE_CREATE = "CREATE TABLE accounttable(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "username	TEXT NOT NULL, password	TEXT NOT NULL, datebirth TEXT NOT NULL);";

	DatabaseHelper DBHelper;
	final Context context;
	SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	public static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try {
				db.execSQL(DATABASE_CREATE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS contacts");
			onCreate(db);
		}

	}

	// open the database
	public DBAdapter open() {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		DBHelper.close();
	}

	public Cursor getAllFavourite() {
		// query can order data set
		return db.query(false, DATABASE_TABLE, null, null, null, null, null,
				null, null, null);
	}

	// "'" whether need ' on the both sides of parameter?
	public Cursor getAccount(String username, String password) {
		return db.query(true, DATABASE_TABLE, null, USERNAME + "=" + "'"
				+ username + "' AND " + PASSWORD + "=" + "'" + password + "'",
				null, null, null, null, null, null);
	}

	public boolean deleteFavourite(long rowId) {
		return db.delete(DATABASE_TABLE, _ID + "=" + rowId, null) > 0;
	}

	public boolean insertAccount(String username, String password,
			String datebirth) {
		ContentValues insertvalue = new ContentValues();
		insertvalue.put(USERNAME, username);
		insertvalue.put(PASSWORD, password);
		insertvalue.put(DATEBIRTH, datebirth);
		return db.insert(DATABASE_TABLE, null, insertvalue) > 0;
	}
}
