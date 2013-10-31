package com.example.datasteamtest2;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	//Importing Sensors:
	SensorManager mySensorManager;
	//Use Light Sensor:
	Sensor myLightSensor;
	TextView textLightSensorData;
	//Use Accelerometer:
	boolean accelerometerPresent;
	Sensor accelerometerSensor;
	TextView x, y, z;
	//Placeholder for more Sensors:

	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	    
	       super.onCreate(savedInstanceState);
	       setContentView(R.layout.activity_main);
	       TextView textLightSensor = (TextView)findViewById(R.id.lightsensor);
	       textLightSensorData = (TextView)findViewById(R.id.lightsensordata);
	       // Accelerometer Data:
	       x = (TextView)findViewById(R.id.x);
	       y = (TextView)findViewById(R.id.y);
	       z = (TextView)findViewById(R.id.z);
	      
	       mySensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
	       myLightSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
	       // Accelerometer Data:
	       List<Sensor> sensorList = mySensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
	       if (myLightSensor == null){
	        textLightSensor.setText("No Light Sensor!");
	       }else{
	        textLightSensor.setText(myLightSensor.getName());
	        
	        mySensorManager.registerListener(lightSensorEventListener,
	          myLightSensor,
	          SensorManager.SENSOR_DELAY_NORMAL);
	       }
	   
	   if(sensorList.size() > 0){
	       accelerometerPresent = true;
	       accelerometerSensor = sensorList.get(0);
	      }
	      else{
	       accelerometerPresent = false;
	      }
	  }
	  
	   SensorEventListener lightSensorEventListener
	    = new SensorEventListener(){

	   @Override
	   public void onAccuracyChanged(Sensor arg0, int arg1) {
	    // TODO Auto-generated method stub
	    
	   }

	   @Override
	   public void onSensorChanged(SensorEvent arg0) {
	    // TODO Auto-generated method stub
	    if(arg0.sensor.getType()==Sensor.TYPE_LIGHT){
	     textLightSensorData.setText("Light Sensor Data:"
	       + String.valueOf(arg0.values[0]));
	     }
	   }};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	@Override
	protected void onResume() {
	 // TODO Auto-generated method stub
	 super.onResume();
	 if(accelerometerPresent){
	  mySensorManager.registerListener(accelerometerListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
	 }
	}

	@Override
	protected void onStop() {
	 // TODO Auto-generated method stub
	 super.onStop();
	 if(accelerometerPresent){
	  mySensorManager.unregisterListener(accelerometerListener);
	 }
	}

	private SensorEventListener accelerometerListener = new SensorEventListener(){

	 @Override
	 public void onAccuracyChanged(Sensor arg0, int arg1) {
	  // TODO Auto-generated method stub
	 
	 }

	 @Override
	 public void onSensorChanged(SensorEvent arg0) {
	  // TODO Auto-generated method stub
	  float x_value = arg0.values[0];
	  float y_value = arg0.values[1];
	  float z_value = arg0.values[2];
	  x.setText(String.valueOf(x_value));
	  y.setText(String.valueOf(y_value));
	  z.setText(String.valueOf(z_value));
	 }};
	}
