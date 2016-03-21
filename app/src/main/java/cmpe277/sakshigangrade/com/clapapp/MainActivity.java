package cmpe277.sakshigangrade.com.clapapp;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.hardware.SensorManager;
import android.widget.TextView;
import android.content.Context;
import java.io.IOException;


public class MainActivity extends Activity implements SensorEventListener {

    SensorManager sensorMgr;
    Sensor sensor;
    TextView textView;
    MediaPlayer player;
    boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView2);
        sensorMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorMgr.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        player = MediaPlayer.create(getApplicationContext(), R.raw.music_file);

        try {
            player.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorMgr.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorMgr.unregisterListener(this);
        player.stop();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == sensor.TYPE_PROXIMITY) {

            /*
            if(event.values[0]!=0) {

                player.pause();
                textView.setText("Pause");


            } else {

                player.start();
                textView.setText("Now Playing");

            } */
            /*
            if(flag == false) {
                try {
                    player.prepare();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player.start();
                textView.setText("Now Playing");
                flag = true;
            } else {
                player.pause();
                textView.setText("Pause");
                flag = false;
            } */

            if(event.values[0] == 0) {
                if(flag == false) {
                    player = MediaPlayer.create(getApplicationContext(), R.raw.music_file);

                    try {
                        player.prepare();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    player.start();
                    textView.setText("Now Playing..!");
                    flag = true;
                }
            }else {

                if(flag == true) {
                    player.pause();
                    textView.setText("Pause");
                    flag = false;
                }
            }
            /* //Working code
            if(event.values[0] == 0) {
                if(flag == true){
                    player.pause();
                    textView.setText("Pause");
                    flag = false;
                } else {
                    player = MediaPlayer.create(getApplicationContext(), R.raw.music_file);

                    try {
                        player.prepare();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    player.start();
                    textView.setText("Now Playing..!");
                    flag = true;
                }

            } */
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
