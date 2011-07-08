package com.wazzup.ninedrink;


import java.util.Calendar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class Mainmenu extends Activity {
	/** Called when the activity is first created. */
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.splash);
	      findView();
	      setListener();
	      mOpenHelper = new DatebaseHelper(this);
	      createTable();
	   }
	   //宣告
	   private Button btn_cancel;
	   private Button btn_done;
	   private CheckBox[] cbox_poker = new CheckBox[14] ;
	   private boolean[] poker_list ={false,false,false,false,false,false,false,false,false,false,false,false,false,false};
	   private CheckBox cbox_s ;
	   private int[] cboxId = {
			   R.id.checkBox0,R.id.checkBox1,R.id.checkBox2,
			   R.id.checkBox3,R.id.checkBox4,R.id.checkBox5,
			   R.id.checkBox6,R.id.checkBox7,R.id.checkBox8,
			   R.id.checkBox9,R.id.checkBox10,R.id.checkBox11,
			   R.id.checkBox12,R.id.checkBox13
	   };
	   private static final String DATABASE_NAME = "NdDb.db";
	   private static final int DATABASE_VERSION = 1;
	   private static final String TABLE_NAME = "set_poker";
	   private static final String TITLE = "poker_number";
	   private static final String BODY = "is_into";
	   //設定資料庫
	   DatebaseHelper mOpenHelper;

	   private static class DatebaseHelper extends SQLiteOpenHelper {
			DatebaseHelper(Context context) {
				super(context, DATABASE_NAME, null, DATABASE_VERSION);
			}

			@Override
			public void onCreate(SQLiteDatabase db) {
				String sql = "CREATE TABLE " + TABLE_NAME + " (" + TITLE +
					" text not null, " + BODY + " boolean not null " + ");";
				try{
					db.execSQL(sql);
				}catch(SQLException e ){
					Log.i("Test:createDB = ", e.toString());
				}
			}

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
		}
	   //設定各物件
	   private void findView(){
		   cbox_s = (CheckBox)findViewById(R.id.checkBoxAll);
		   for(int i=0;i<14;i++){
			   cbox_poker[i] = (CheckBox)findViewById(cboxId[i]);
		   }
		   btn_cancel = (Button)findViewById(R.id.btn_setcancel);
		   btn_done = (Button)findViewById(R.id.btn_setcomplete);
	   }
	   //偵聽
	   private void setListener(){
		   cbox_s.setOnCheckedChangeListener(selectAll);
		   btn_cancel.setOnClickListener(setcancel);
		   for(int i=0;i<14;i++){
			   cbox_poker[i].setOnCheckedChangeListener(selected);
		   }
	   }
	   private Button.OnClickListener setcancel = new Button.OnClickListener() {
	    	public void onClick(View v) {
	    		finish();
			}
	    };
	   private CheckBox.OnCheckedChangeListener selectAll= new CheckBox.OnCheckedChangeListener(){
			   @Override
			   public void onCheckedChanged(CompoundButton btnView, boolean isChecked) {
				   if(isChecked){
					   for(int i=0;i<14;i++){
						   cbox_poker[i].setChecked(true);
					   }
				   }else{
					   for(int i=0;i<14;i++){
						   cbox_poker[i].setChecked(false);
					   }
				   }
			   }
		};
	   private CheckBox.OnCheckedChangeListener selected= new CheckBox.OnCheckedChangeListener()
	   {
		   @Override
		   public void onCheckedChanged(CompoundButton btnView, boolean isChecked) {
			   // TODO Auto-generated method stub
			   if(isChecked){
				   for(int i=0;i<14;i++){
					   if(cboxId[i] == btnView.getId()){
						   poker_list[i] = true;
						   
						   break;
					   }
				   }
			   }else{
				   for(int i=0;i<14;i++){
					   if(cboxId[i] == btnView.getId()){
						   poker_list[i] = false;
						   break;
					   }
				   }
			   }
		
		   }
	   };
	   
	   //建立資料表
	   
	   public void createTable() {
	    	SQLiteDatabase db = mOpenHelper.getWritableDatabase();
	    	String SQL = "CREATE TABLE " + TABLE_NAME + " (" + TITLE +
	    		" int not null, " + BODY + " boolean not null " + ");";
	    	try {
	    		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	    		db.execSQL(SQL);
	    		setTitle("資料表成功重建");
	    	} catch (SQLException e) {
	    		setTitle("資料表重建失敗");
	    	}
	    	//加入系統預設資料
	    	for(int i=0;i<14;i++){
	    		ContentValues values = new ContentValues(); 
		    	values.put(String.valueOf(cboxId[i]), poker_list[i]); 
		    	mOpenHelper.getReadableDatabase().insert(TABLE_NAME, 
		    			"", values); 
		    	mOpenHelper.getReadableDatabase().close();     		
	    	}
	    }
	   //選取資料
	   	
	   //刪除一筆資料
	    public void deleteItem() {
	    	Log.i("Test:deleteRecord", "");
	    	try {
	            SQLiteDatabase db = mOpenHelper.getWritableDatabase();
	            db.delete(TABLE_NAME, " title = 'People1'", null);
	            setTitle("刪除 title 為 People1 的一筆資料");
	        } catch (SQLException e) {
	        	setTitle("刪除資料失敗");
	        }
	    }

}
