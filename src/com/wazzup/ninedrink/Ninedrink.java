package com.wazzup.ninedrink;

import java.util.Random;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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
    private ImageButton btn_1;
    private ImageButton btn_2;
    private ImageButton btn_3;
    private ImageButton btn_4;
    private ImageButton btn_reset;
    private int[] set_number; 

    private void findView(){
    	btn_1 = (ImageButton)findViewById(R.id.imgbtn_1);
    	btn_2 = (ImageButton)findViewById(R.id.imgbtn_2);
    	btn_3 = (ImageButton)findViewById(R.id.imgbtn_3);
    	btn_4 = (ImageButton)findViewById(R.id.imgbtn_4);
    	btn_reset = (ImageButton)findViewById(R.id.btn_reset); 	
    }
    private void setLisenter(){
    	btn_reset.setOnClickListener(reset);
    	btn_1.setOnClickListener(open);
    	btn_2.setOnClickListener(open);
    	btn_3.setOnClickListener(open);
    	btn_4.setOnClickListener(open);
    }
    //洗牌事件
    private Button.OnClickListener reset = new Button.OnClickListener(){
		@Override
		public void onClick(View v) {
			btn_1.setImageResource(R.drawable.selector);
			btn_2.setImageResource(R.drawable.selector);
			btn_3.setImageResource(R.drawable.selector);
			btn_4.setImageResource(R.drawable.selector);
			set_number = getRandom();	
		}
    };
    //翻牌事件
    private Button.OnClickListener open = new Button.OnClickListener(){
    	@Override
		public void onClick(View v) {
    		switch(v.getId()){
    			case R.id.imgbtn_1:
    				btn_1.setImageResource(set_number[0]);
    				break;
    			case R.id.imgbtn_2:
    				btn_2.setImageResource(set_number[1]);
    				break;
    			case R.id.imgbtn_3:
    				btn_3.setImageResource(set_number[2]);
    				break;
    			case R.id.imgbtn_4:
    				btn_4.setImageResource(set_number[3]);
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
}