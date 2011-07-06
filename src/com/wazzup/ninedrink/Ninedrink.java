package com.wazzup.ninedrink;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_reset;
    private int[] set_number; 

    private void findView(){
    	btn_1 = (Button)findViewById(R.id.btn_1);
    	btn_2 = (Button)findViewById(R.id.btn_2);
    	btn_3 = (Button)findViewById(R.id.btn_3);
    	btn_4 = (Button)findViewById(R.id.btn_4);
    	btn_reset = (Button)findViewById(R.id.btn_reset); 	
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
			btn_1.setText("");
			btn_2.setText("");
			btn_3.setText("");
			btn_4.setText("");
			set_number = getRandom();	
		}
    };
    //翻牌事件
    private Button.OnClickListener open = new Button.OnClickListener(){
    	@Override
		public void onClick(View v) {
    		switch(v.getId()){
    			case R.id.btn_1:
    				btn_1.setText(Integer.toString(set_number[0]));
    				break;
    			case R.id.btn_2:
    				btn_2.setText(Integer.toString(set_number[1]));
    				break;
    			case R.id.btn_3:
    				btn_3.setText(Integer.toString(set_number[2]));
    				break;
    			case R.id.btn_4:
    				btn_4.setText(Integer.toString(set_number[3]));
    				break;
    		}
		}
    };
	//亂數排序
    private int[] getRandom(){
    		//初始化
    		int x[] = {1,2,3,4};
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