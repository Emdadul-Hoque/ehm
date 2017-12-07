package com.example.checkplainsurface;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class AppView extends SurfaceView implements Callback {

	Context context;
	SurfaceHolder surfaceHolder;
	DrawingThread drawingThread;
	
	public AppView(Context context) {
		super(context);
		this.context = context;
		
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
		
		drawingThread = new DrawingThread(this, context);
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			drawingThread.start();
		} catch (Exception e) {
			restartThread();
		}
	}

	private void restartThread() {
		drawingThread.stopThread();
		drawingThread = null;
		drawingThread = new DrawingThread(this, context);
		drawingThread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		drawingThread.stopThread();
	}
}
