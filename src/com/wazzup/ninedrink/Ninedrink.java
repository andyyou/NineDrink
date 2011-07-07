package com.wazzup.ninedrink;

import java.util.Random;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Ninedrink extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        set_number = getRandom();
        findView();
        setLisenter();      
    }
    private ImageView btn_1;
    private ImageView btn_2;
    private ImageView btn_3;
    private ImageView btn_4;
    private ImageView btn_reset;
    private ImageView btn_close;
    private int[] set_number; 
    //用來判斷是否已翻開
    private int btn_pressed;
    private boolean[] is_btn_pressed = {false,false,false,false};
    //private boolean btn_2_pressed = false;
    //private boolean btn_3_pressed = false;
    //private boolean btn_4_pressed = false;
    //老千模式變數
    private int cheat_mod =0; // 0:NoStatus,1:AlwaysLose,2:AlwaysWin
    private void findView(){
    	btn_1 = (ImageView)findViewById(R.id.imgbtn_1);
    	btn_2 = (ImageView)findViewById(R.id.imgbtn_2);
    	btn_3 = (ImageView)findViewById(R.id.imgbtn_3);
    	btn_4 = (ImageView)findViewById(R.id.imgbtn_4);
    	btn_reset = (ImageView)findViewById(R.id.btn_reset); 	
    	btn_close = (ImageView)findViewById(R.id.imgbtn_close);
    }
    private void setLisenter(){
    	btn_reset.setOnClickListener(reset);
    	btn_close.setOnClickListener(close);
    	btn_1.setOnClickListener(open);
    	btn_2.setOnClickListener(open);
    	btn_3.setOnClickListener(open);
    	btn_4.setOnClickListener(open);
    }
    //洗牌事件
    private View.OnClickListener reset = new View.OnClickListener(){
		@Override
		public void onClick(View v) {
			btn_1.setImageResource(R.drawable.selector);
			btn_2.setImageResource(R.drawable.selector);
			btn_3.setImageResource(R.drawable.selector);
			btn_4.setImageResource(R.drawable.selector);
			for(int i=0;i<4;i++){
				is_btn_pressed[i]=false;
			}
			/*
			btn_1_pressed = false;
			btn_2_pressed = false;
			btn_3_pressed = false;
			btn_4_pressed = false;
			*/
			set_number = getRandom();	
		}
    };
    //翻牌事件
    private View.OnClickListener open = new View.OnClickListener(){
    	@Override
		public void onClick(View v) {
    		switch(v.getId()){
    			case R.id.imgbtn_1: 
    				switch_cards(0);
    				btn_pressed = 1;
    				break;
    			case R.id.imgbtn_2:  
    				switch_cards(1);
    				btn_pressed = 2;
    				break;
    			case R.id.imgbtn_3: 
    				switch_cards(2);
    				btn_pressed = 3;
    				break;
    			case R.id.imgbtn_4:
    				switch_cards(3);
    				btn_pressed = 4;
    				break;
    		}
    		
    		Animation animation = AnimationUtils.loadAnimation(Ninedrink.this, R.anim.back_scale);
    		animation.setAnimationListener(new Animation.AnimationListener() {
    			@Override
    			public void onAnimationStart(Animation animation) { }
    			
    			@Override
    			public void onAnimationRepeat(Animation animation) { }
    			
    			@Override
    			public void onAnimationEnd(Animation animation) {
    				switch(btn_pressed)
    				{
    				    case 1:    	
    				    	btn_1.setImageResource(set_number[0]);
    				    	btn_1.startAnimation(AnimationUtils.loadAnimation(Ninedrink.this, R.anim.front_scale));
    					    break;
    				    case 2:
    				    	btn_2.setImageResource(set_number[1]);
    				    	btn_2.startAnimation(AnimationUtils.loadAnimation(Ninedrink.this, R.anim.front_scale));
    					    break;
    				    case 3:
    				    	btn_3.setImageResource(set_number[2]);
    				    	btn_3.startAnimation(AnimationUtils.loadAnimation(Ninedrink.this, R.anim.front_scale));
    					    break;
    				    case 4:
    				    	btn_4.setImageResource(set_number[3]);
    				    	btn_4.startAnimation(AnimationUtils.loadAnimation(Ninedrink.this, R.anim.front_scale));
    					    break;
    				}
    			}
    		});
    		switch(btn_pressed)
			{
			    case 1:
			    	if(!is_btn_pressed[0]) btn_1.startAnimation(animation);
			    	is_btn_pressed[0]= true;
				    break;
			    case 2:
			    	if(!is_btn_pressed[1]) btn_2.startAnimation(animation);
			    	is_btn_pressed[1] = true;
				    break;
			    case 3:
			    	if(!is_btn_pressed[2]) btn_3.startAnimation(animation);
			    	is_btn_pressed[2] = true;
				    break;
			    case 4:
			    	if(!is_btn_pressed[3]) btn_4.startAnimation(animation);
			    	is_btn_pressed[3] = true;
				    break;
			}
		}
    };
	//亂數排序
    private int[] getRandom(){
    		//初始化
    		int x[] = {R.drawable.icon_7,R.drawable.icon_8,R.drawable.icon_9,R.drawable.icon_p};
    		int y, tmp;
            Random r = new Random(System.currentTimeMillis()); 
            //洗牌
            for(int i=0;i<4;i++){
            	y = r.nextInt(3);
            	tmp = x[i];
            	x[i] = x[y];
            	x[y] = tmp;
            }

            return x;
    }
    //選單
    protected static final int MENU_ABOUT = Menu.FIRST;
    protected static final int MENU_Quit = Menu.FIRST+1;
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	menu.add(0,MENU_ABOUT,0,"關於");
    	menu.add(0,MENU_Quit,0,"結束");
    	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	super.onOptionsItemSelected(item);
    	switch(item.getItemId()){
    	case MENU_ABOUT:
    		openOptionsDialog();
    		break;
    	case MENU_Quit:
    		finish();
    		break;
    	}
    	return true;
    }
    private void openOptionsDialog(){
		new AlertDialog.Builder(Ninedrink.this).setTitle(R.string.about_title)
			.setMessage(R.string.about_msg)
			.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int i) {	
				}
			})
			.setNegativeButton(R.string.lb_homepage, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int i) {
					Uri uri = Uri.parse(getString(R.string.url_homepage));
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);						
				}
			})
			.show();
	}
    //結束程式
    private Button.OnClickListener close = new Button.OnClickListener(){
		@Override
		public void onClick(View v) {
			finish();	
		}  	
    };
    //老千模式:監聽音量建
   @Override
   public boolean onKeyDown(int keyCode,KeyEvent event){   
	   if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){	   
		   cheat_mod = 1; //Always lose
	   }else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP){
		   cheat_mod = 2; //Always win and Plus Drink XD
	   }else{
		   cheat_mod = 0;//Cancel
	   }
	   return true;
   }
   public void switch_cards(int n){
	   int switch_tmp;
	   if(cheat_mod == 1){
		   if(set_number[n] != R.drawable.icon_9 && is_btn_pressed[n] == false  ){
				 for(int i=0;i<4;i++){
					 if(is_btn_pressed[i] == false && set_number[i] == R.drawable.icon_9 && i!=n){
						 switch_tmp = set_number[n];
						 set_number[n] = set_number[i] ;
						 set_number[i] = switch_tmp;
						 break;
					 }
				 }
		   }			   	   
	   }else if(cheat_mod == 2){
		   if(set_number[n] != R.drawable.icon_7 && is_btn_pressed[n] == false  ){
				 for(int i=0;i<4;i++){
					 if(is_btn_pressed[i] == false && set_number[i] == R.drawable.icon_7 && i!=n){
						 switch_tmp = set_number[n];
						 set_number[n] = set_number[i] ;
						 set_number[i] = switch_tmp;
						 break;
					 }
				 }
		   }		
	   }else{}
   }
   /*//測試訊息
   private void openTestDialog(){
		Toast popup =  Toast.makeText(Ninedrink.this, R.string.about_title, Toast.LENGTH_SHORT);
	    popup.show();
	}
	*/
}