package com.wazzup.ninedrink;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;


public class Splash extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		timer = new Timer(true);
		startTime = System.currentTimeMillis();
		timer.schedule(task, 0, 1);
		

	}
	
	private Timer timer;
	private long startTime;
	private boolean _active = true;
	
	private final TimerTask task = new TimerTask(){
		@Override
		public void run(){
			if (task.scheduledExecutionTime() - startTime >= 1500 || !_active){
				Message message = new Message();
				message.what = 0;
				timerHandler.sendMessage(message);
				timer.cancel();
				this.cancel();
			}
		}
	};

	private final Handler timerHandler = new Handler(){
		public void handleMessage(Message msg){
			switch (msg.what){
				case 0:
					Splash.this.finish();
					Intent i = new Intent();
					i.setClass(Splash.this, Ninedrink.class);
					startActivity(i);
					break;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	public boolean onTouchEvent(MotionEvent event){
		if (event.getAction() == MotionEvent.ACTION_DOWN){
			_active = false;
		}
		return true;
	}
}
