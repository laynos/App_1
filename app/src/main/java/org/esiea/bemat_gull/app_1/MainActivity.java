package org.esiea.bemat_gull.app_1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.esiea.bemat_gull.app_1.R;
import org.esiea.bemat_gull.app_1.SecondeActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Date;
import java.text.DateFormat;


public class MainActivity extends AppCompatActivity {

    TextView tv_hw;
    DatePickerDialog dpd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_hw = (TextView) findViewById(R.id.tv_hello_world);
        //Button btn_hw = (Button) findViewById(R.id.btn_hello_world);
        // String date_now = DateUtils.formatDateTime(getApplicationContext(),(new Date()).getTime(), DateFormat.FULL);
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        String dateNow = f.format(date);
        int day = Integer.parseInt(dateNow.substring(0, 2));
        int month = Integer.parseInt(dateNow.substring(3, 5));
        int yeaar = Integer.parseInt(dateNow.substring(6, 10));
        tv_hw.setText(getString(R.string.app_name) + " " + dateNow );

        dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                tv_hw.setText(getString(R.string.app_name) +" "+ dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
