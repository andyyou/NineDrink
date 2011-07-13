package com.wazzup.ninedrink;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NDDBOpenHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "NdDb.db";
	private static final int DATABASE_VERSION = 1;
	private SQLiteDatabase db;

	public NDDBOpenHelper(Context ctx) {
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
		db = this.getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		int[] initValue = {1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0};
		String DATABASE_CREATE_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + " (" + Constants.NUMBER + " int not null, " + Constants.SELECTED + " boolean not null " + ");";
		try{
			db.execSQL(DATABASE_CREATE_TABLE);
			//加入系統預設資料
			for(int i = 0; i < 14; i++){
				String DATABASE_INIT_DATA = "Insert Into " + Constants.TABLE_NAME + " (" + Constants.NUMBER + ", " + Constants.SELECTED + ") values(" + i + ", " + initValue[i] + ");";
				try{
					db.execSQL(DATABASE_INIT_DATA);
				}catch (SQLException e){ }
			}
			//initData();
		}catch(SQLException e){ }
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
		onCreate(db);
	}
}
