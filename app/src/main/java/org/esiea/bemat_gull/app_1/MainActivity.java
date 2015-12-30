package org.esiea.bemat_gull.app_1;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Observable;
import java.util.Observer;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observer;


public class MainActivity extends AppCompatActivity implements OnClickListener, Observer {

    private Button actionButton;
    private Button rulesButton;
    private TextView chronometerValue;
    private Chrono chronometerMotor;
    private Thread chronometer;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionButton = (Button) findViewById(R.id.btn_start);
        rulesButton = (Button) findViewById(R.id.btn_rules);
       /* setupButton = (Button) findViewById(R.id.configuration_chrono);*/
        chronometerValue = (TextView) findViewById(R.id.ready);
        actionButton.setOnClickListener(this);
     /*   resetButton.setOnClickListener(this);
        setupButton.setOnClickListener(this);*/



    }

    @Override
    public void onResume(){
        super.onResume();
        chronometerMotor = new Chrono();
        chronometerMotor.addObserver(this);
        chronometer = new Thread(chronometerMotor);
    }

    @Override
    public void onClick(final View view) {
        new Thread(){

            {
                setDaemon(true);
            }

            public void run(){
                if (view == actionButton){
                    toggleChronometerState();
                }

            }
        }.start();
    }

    @Override
    public void update(Observable observable, final Object data) {
        runOnUiThread(new Thread() {
            public void run() {
                long totalSeconds = (Long) data;
                String timeString = getTimeString(totalSeconds);
                chronometerValue.setText(timeString);
                if(totalSeconds==0)
                    chronometerValue.setText("GAME OVER !!");

            }
        });
    }
    private String getTimeString(long totalSeconds) {
        int seconds = (int) totalSeconds % 60;
      /*  int minits = (int) (totalSeconds / 60) % 60;
        int hours = (int) totalSeconds / 3600;*/
        return String.format(/*%02d:%02d:*/"%02d",/* hours, minits,*/ seconds);
    }


    private void resetChronometer() {
        throw new UnsupportedOperationException();
    }

    private void toggleChronometerState() {
        if ( ! chronometer.isAlive() )
            chronometer.start();
        else
            chronometer.stop();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:
                // Comportement du bouton "A Propos"
                Intent second= new Intent(this, SecondeActivity.class);
                startActivity(second);
                return true;
            case R.id.menu_help:
                // Comportement du bouton "Aide"
                return true;
            case R.id.menu_refresh:
                // Comportement du bouton "Rafraichir"
                return true;
            case R.id.menu_search:
                // Comportement du bouton "Recherche"
                return true;
            case R.id.action_settings:
                // Comportement du bouton "Paramètres"
                return true;
            case R.id.action_notification:
                // Comportement du bouton "Notification"
                NotificationCompat.Builder wat =
                        new NotificationCompat.Builder(this)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("CLICK THAT")
                                .setContentText(getString(R.string.notification_example));
                NotificationManager manager = ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE));
                manager.notify(1,wat.build());
                return true;
            case R.id.action_toast:
                // Comportement du bouton "Toast"
                Toast.makeText(getApplicationContext(),getString(R.string.toast_example),Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }





    public void btnRules(View v) {
        /*Intent third= new Intent(this, ThirdActivity.class);
        startActivity(third);*/
        //soit on fait une activity/intent ou sinon on fait un popup ou qqchose du genre (je sais pas encore cmt faire)
    }
    public void btnPlay(View v) {

    }
    public void btnStart(View v) {

    }
}
