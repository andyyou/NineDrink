package com.wazzup.ninedrink;


import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
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
	   }
	   //宣告
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
	   private static final String DATABASE_NAME = "DbForSQLite.db";
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
				db.execSQL(sql);
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
	   }
	   //偵聽
	   private void setListener(){
		   cbox_s.setOnCheckedChangeListener(selected);
	   }
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
}
