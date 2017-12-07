package com.example.checkplainsurface;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class OperationActivity extends Activity {

	AppView appView;
	SensorManager sensormanager;
	SensorEventListener sensorEventListener;
	Sensor accelerometerSensor;
	private static float gX, gY, gZ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		appView = new AppView(this);
		initializeSensor();
		setContentView(appView);
	}

	public static float getgX() {
		return gX;
	}

	public static float getgY() {
		return gY;
	}

	public static float getgZ() {
		return gZ;
	}

	private void initializeSensor() {
		sensormanager = (SensorManager) getSystemService(SENSOR_SERVICE);
		sensorEventListener = new SensorEventListener() {
			
			@Override
			public void onSensorChanged(SensorEvent event) {
				if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
					gX = event.values[0];
					gY = event.values[1];
					gZ = event.values[2];
				}
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				
			}
		};
		
		accelerometerSensor = sensormanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
		startUsingSensor();
		
	}
	
	private void startUsingSensor() {
		sensormanager.registerListener(sensorEventListener, accelerometerSensor, 
				SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	private void stopUsingSensor() {
		sensormanager.unregisterListener(sensorEventListener);
	}

	@Override
	protected void onPause() {
		stopUsingSensor();
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		startUsingSensor();
		super.onResume();
	}
	
	@Override
	protected void onStop() {
		stopUsingSensor();
		super.onStop();
	}
}
