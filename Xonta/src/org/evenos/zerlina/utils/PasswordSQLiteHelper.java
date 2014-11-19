package org.evenos.zerlina.utils;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

import org.evenos.zerlina.model.MPassword;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

/**
 * Helper class which handles read/write from/to the Password.db
 * 
 * @author Jan Thielemann
 * 
 */
public class PasswordSQLiteHelper extends SQLiteOpenHelper {

	private String password;

	private static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "Password.db";

	public PasswordSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// Default password for the password db. This password is not critical because the key to the db containing the data is stored
		// encrypetd with aes in this db
		this.password = "you are a bad boy! Desu ne!!!";
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_BPARTNER_TABLE = "CREATE TABLE " + MPassword.TABLE_NAME + "( " + MPassword.Columnname_ID + " INTEGER PRIMARY KEY, "
				+ MPassword.Columnname_Key + " TEXT, " + MPassword.Columnname_Tries + " INTEGER, " + MPassword.Columnname_Salt + " TEXT, "
				+ MPassword.Columnname_InitVector + " TEXT" + ")";
		db.execSQL(CREATE_BPARTNER_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + MPassword.TABLE_NAME);
		this.onCreate(db);
	}

	/**
	 * Save the Password in the Password.db
	 * 
	 * @param password
	 */
	public void setPassword(MPassword password) {
		SQLiteDatabase db = this.getWritableDatabase(this.password);
		ContentValues values = new ContentValues();
		values.put(MPassword.Columnname_ID, password.getPassword_id());
		values.put(MPassword.Columnname_Key, password.getKey());
		values.put(MPassword.Columnname_Tries, password.getTries());
		values.put(MPassword.Columnname_Salt, password.getSalt());
		values.put(MPassword.Columnname_InitVector, password.getInitVector());
		db.insertWithOnConflict(MPassword.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
		db.close();
	}

	/**
	 * Load the Password from the Password.db
	 * 
	 * @return
	 */
	public MPassword getPassword() {
		MPassword password = null;

		String query = "SELECT * FROM " + MPassword.TABLE_NAME;
		SQLiteDatabase db = this.getWritableDatabase(this.password);
		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			password = new MPassword(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)),
					cursor.getString(3), cursor.getString(4));
		}
		cursor.close();
		db.close();

		return password;
	}

	/**
	 * Check if the Password.db has an entry in it
	 * 
	 * @return
	 */
	public boolean hasPassword() {
		String query = "SELECT COUNT(*) FROM " + MPassword.TABLE_NAME;
		SQLiteDatabase db = this.getWritableDatabase(this.password);
		Cursor cursor = db.rawQuery(query, null);
		try {
			if (cursor.moveToFirst()) {
				return cursor.getInt(0) > 0;
			}
		} catch (Exception e) {

		} finally {
			cursor.close();
			db.close();
		}
		return false;
	}

	/**
	 * Add a failed try to the Password.db
	 * 
	 * @return
	 */
	public int addTry() {

		int tries = 0;

		String query = "SELECT " + MPassword.Columnname_Tries + " FROM " + MPassword.TABLE_NAME;
		SQLiteDatabase db = this.getWritableDatabase(this.password);
		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			tries = Integer.parseInt(cursor.getString(0));
			tries++;
			db.execSQL("UPDATE " + MPassword.TABLE_NAME + " SET " + MPassword.Columnname_Tries + "=" + tries);
		}

		Log.d("addTry()", "Added a failure try. Count is now: " + tries);

		cursor.close();
		db.close();

		return tries;
	}

	/**
	 * Reset the failed tries on the Password.db
	 */
	public void clearTries() {
		SQLiteDatabase db = this.getWritableDatabase(this.password);
		db.execSQL("UPDATE " + MPassword.TABLE_NAME + " SET " + MPassword.Columnname_Tries + "=0");
		db.close();
	}

}
