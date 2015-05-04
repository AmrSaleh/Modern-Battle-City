package com.amr.movingsprite;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFX extends Activity implements OnTouchListener {

	GFXsurface mySurface;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mySurface=new GFXsurface(this);
		mySurface.setOnTouchListener(this);
		
		setContentView(mySurface);
		
		
	}

	

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
//		int x = (int)event.getX();
//	    int y = (int)event.getY();
		
		return true;
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mySurface.resume();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mySurface.pause();
	}

	 //////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////
	public class GFXsurface extends SurfaceView implements Runnable{

		SurfaceHolder holder;
		Thread thread = null;
		boolean loopIsOk =false;
		Bitmap right, left, up, down;
		tankSprite mytank;
		
		public GFXsurface(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			holder = getHolder();
			right=BitmapFactory.decodeResource(getResources(), R.drawable.move_right_scaled);
			down=BitmapFactory.decodeResource(getResources(), R.drawable.move_down_scaled);
			left=BitmapFactory.decodeResource(getResources(), R.drawable.move_left_scaled);
			up=BitmapFactory.decodeResource(getResources(), R.drawable.move_up_scaled);
			
			
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(!holder.getSurface().isValid());
			mytank =new tankSprite (GFXsurface.this, right,down,left,up);
			while(loopIsOk){
				if (!holder.getSurface().isValid())
					continue;
				
				Canvas canvas = holder.lockCanvas();
				draw(canvas);
				
				
				
			}
		}
		
		public void draw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.draw(canvas);
			canvas.drawColor(Color.WHITE);
			
			mytank.draw(canvas);
			
			holder.unlockCanvasAndPost(canvas);
		}
		
		public void pause() {
			loopIsOk = false;
			while (true) {
				try {
					thread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}

			thread = null;
		}

		public void resume() {
			loopIsOk = true;
			thread = new Thread(this);
			thread.start();
		}
		
		
	}
	
	
	
}
