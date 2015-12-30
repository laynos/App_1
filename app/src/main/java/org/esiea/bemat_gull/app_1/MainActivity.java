package org.esiea.bemat_gull.app_1;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    TextView tv_hw;
    DatePickerDialog dpd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv_hw = (TextView) findViewById(R.id.tv_hello_world);

        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        String dateNow = f.format(date);
        int day = Integer.parseInt(dateNow.substring(0, 2));
        int month = Integer.parseInt(dateNow.substring(3, 5));
        int yeaar = Integer.parseInt(dateNow.substring(6, 10));

        tv_hw.setText(getString(R.string.date) + "\n " + dateNow );

        dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                tv_hw.setText(getString(R.string.date) +"\n "+ dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
            }
        }, yeaar, month-1, day);


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
                                .setContentTitle("INF4042")
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


    public void btnHwAct(View v) {
        Toast.makeText(getApplicationContext(),getString(R.string.msg),Toast.LENGTH_LONG).show();
        dpd.show();
    }


    public void btnSdAct(View v) {
        Intent second= new Intent(this, SecondeActivity.class);
        startActivity(second);

    }
}
