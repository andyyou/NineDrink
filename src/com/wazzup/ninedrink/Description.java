package com.wazzup.ninedrink;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;


public class Description extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.description);
		findView();
		setLisenter();
		// Create the adView
	    AdView adView = new AdView(this, AdSize.BANNER, "a14e205a4aecaef");
	    // Lookup your LinearLayout assuming it’s been given
	    // the attribute android:id="@+id/mainLayout"
	    TableLayout layout = (TableLayout)findViewById(R.id.description_layout);
	    // Add the adView to it
	    layout.addView(adView);
	    // Initiate a generic request to load it with an ad
	    adView.loadAd(new AdRequest());
		
	}
	
	//宣告變數
	private Button btn_back;
	private Button btn_statment;
	//設定事件
	private void findView(){
		 btn_back = (Button)findViewById(R.id.btn_back);
		 btn_statment = (Button)findViewById(R.id.btn_statment);
	}
	//偵聽
	private void setLisenter(){
		btn_back.setOnClickListener(back);
		btn_statment.setOnClickListener(openDescription);
	}
	//事件
	private Button.OnClickListener back = new Button.OnClickListener(){
		public void onClick(View v){
			finish();
		}
	};
	private Button.OnClickListener openDescription = new Button.OnClickListener(){
		public void onClick(View v){
			openOptionsDialog();
		}
	};
	private void openOptionsDialog(){
		new AlertDialog.Builder(Description.this).setTitle(R.string.about_title)
			.setMessage(R.string.about_description)
			.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int i){
				}
			})
			.setNegativeButton(R.string.lb_homepage, new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int i){
					Uri uri = Uri.parse(getString(R.string.url_homepage));
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);
				}
			})
			.show();
	}
}
