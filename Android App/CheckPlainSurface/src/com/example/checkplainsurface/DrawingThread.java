package com.example.checkplainsurface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class DrawingThread extends Thread {
	
	private Canvas canvas;
	private Context context;
	private AppView appView;
	boolean threadFlag = false;
	int displayX, displayY;
	Bitmap backgroundBitmap;
	
	public DrawingThread(AppView appView, Context context) {
		super();
		this.appView = appView;
		this.context = context;
		
		initializeAll();
		
	}
	
	private void initializeAll() {
		WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display defaultDisplay = manager.getDefaultDisplay();
		Point displayPoint = new Point();
		defaultDisplay.getSize(displayPoint);
		displayX = displayPoint.x;
		displayY = displayPoint.y;
		
		backgroundBitmap = BitmapFactory.decodeResource(context.getResources(), 
				R.drawable.background);
		backgroundBitmap = Bitmap.createScaledBitmap(backgroundBitmap, displayX, displayY, true);
	}

	@Override
	public void run() {
		threadFlag = true;
		
		while(threadFlag) {
			
			canvas = appView.surfaceHolder.lockCanvas();
			
			try {
				synchronized (appView.surfaceHolder) {
					updateDisplay();
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				if(canvas != null) {
					appView.surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	private void updateDisplay() {
		canvas.drawBitmap(backgroundBitmap, 0, 0, null);
		drawSensorData();
	}

	public void stopThread() {
		threadFlag = false;
	}
	
	public void drawSensorData() {
		float size = displayY/3;
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(displayX/10);
		canvas.drawText("X-Axis : "+OperationActivity.getgX(), 10, size/2, paint);
		canvas.drawText("Y-Axis : "+OperationActivity.getgY(), 10, size + (size/2), paint);
		canvas.drawText("Z-Axis : "+OperationActivity.getgZ(), 10, (size/2) + (size*2), paint);
	}
	
}
