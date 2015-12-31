package org.esiea.bemat_gull.app_1;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SecondeActivity extends AppCompatActivity {

    RecyclerView rv;
    TextView tv_hw;
    DatePickerDialog dpd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconde);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GetBiersServices.startActionGet_All_Biers(this);

        IntentFilter intentFilter = new IntentFilter(BIERS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BierUpdate(),intentFilter);

        rv = (RecyclerView)findViewById(R.id.rv_biere);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(new BiersAdapter(getBiersFromFile()));


        tv_hw = (TextView) findViewById(R.id.tv_hello_world);

        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        String dateNow = f.format(date);
        int day = Integer.parseInt(dateNow.substring(0, 2));
        int month = Integer.parseInt(dateNow.substring(3, 5));
        int yeaar = Integer.parseInt(dateNow.substring(6, 10));

        tv_hw.setText(getString(R.string.date) + ":\t " + dateNow);

        dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                tv_hw.setText(getString(R.string.date) +":\t "+ dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
            }
        }, yeaar, month-1, day);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_seconde, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {

            case R.id.change_date:
                Toast.makeText(getApplicationContext(), getString(R.string.msg), Toast.LENGTH_LONG).show();
                dpd.show();
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
                Toast.makeText(getApplicationContext(), getString(R.string.toast_example), Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void notification() {
        NotificationCompat.Builder wat =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notification")
                        .setContentText(getString(R.string.endDL));
        NotificationManager manager = ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE));
        manager.notify(1,wat.build());
    }


    /*public void btnHwAct(View v) {
        Toast.makeText(getApplicationContext(), getString(R.string.msg), Toast.LENGTH_LONG).show();
        dpd.show();
    }*/


    public static final String BIERS_UPDATE = "gull_bemat.esiea.org.action.BIERS_UPDATE";

    public class BierUpdate extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            BiersAdapter bierAdapt = (BiersAdapter) rv.getAdapter();
            bierAdapt.setNewBiere(getBiersFromFile());
            Log.d("SecondeActivity", "" + getIntent().getAction());
            notification();
        }
    }

    public JSONArray getBiersFromFile() {
        try {
            InputStream is = new FileInputStream(getCacheDir()+"/"+"bieres.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new JSONArray(new String(buffer, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONArray();
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    private class BiersAdapter extends RecyclerView.Adapter<BiersAdapter.BierHolder> {

        private JSONArray bieres;

        BiersAdapter(JSONArray biers) {
            this.bieres=biers;
        }

        @Override
        public BierHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new BierHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_bier_element, viewGroup, false));
        }

        public void setNewBiere(JSONArray biers) {
            this.bieres=biers;
            this.notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(BierHolder bierHolder, int i) {
            JSONObject object;
            try {
                object = (JSONObject) bieres.get(i);
                String nom = object.getString("name");
                bierHolder.name.setText(nom);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        @Override
        public int getItemCount() {
            return bieres.length();
        }


        public class BierHolder extends RecyclerView.ViewHolder {

            public TextView name;

            public BierHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.rv_bier_element);
            }
        }

    }
}
