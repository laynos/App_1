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



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




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
