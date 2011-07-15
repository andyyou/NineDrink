package com.wazzup.ninedrink;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NDDBOpenHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "NdDb.db";
	private static final int DATABASE_VERSION = 1;
	private SQLiteDatabase db;

	public NDDBOpenHelper(Context ctx) {
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
		db = this.getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		int[] initValue = {1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0};
		String DATABASE_CREATE_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + " (" + Constants.NUMBER + " int not null, " + Constants.SELECTED + " boolean not null " + ");";
		try{
			db.execSQL(DATABASE_CREATE_TABLE);
			//加入系統預設資料(預設牌組為 Jorker, 7, 8, 9)
			for(int i = 0; i < 14; i++){
				String DATABASE_INIT_DATA = "Insert Into " + Constants.TABLE_NAME + " (" + Constants.NUMBER + ", " + Constants.SELECTED + ") values(" + i + ", " + initValue[i] + ");";
				try{
					db.execSQL(DATABASE_INIT_DATA);
				}catch (SQLException e){ }
			}
		}catch(SQLException e){ }
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
		onCreate(db);
	}

	//取得所有資料
	public Cursor getAll(){
		return db.query(Constants.TABLE_NAME,	//資料表名稱
			null, //欄位名稱
			null, //WHERE
			null, //WHERE 的參數
			null, //GROUP BY
			null, //HAVING
			null  //ORDOR BY
		);
	}

	//更新資料，回傳成功修改筆數
	public int update(int number ,boolean selected){
		ContentValues args = new ContentValues();
		args.put(Constants.SELECTED, selected);
		return db.update(Constants.TABLE_NAME,	//資料表名稱
			args,											//VALUE
			Constants.NUMBER + "=" + String.valueOf(number),//WHERE
			null											//WHERE的參數
		);
	}
}
