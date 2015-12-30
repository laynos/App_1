package org.esiea.bemat_gull.app_1;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class GetBiersServices extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_GET_ALL_BIERS = "org.esiea.bemat_gull.app_1.action.GET_ALL_BIERS";


    // TODO: Rename parameters

    // TODO: Customize helper method
    public static void startActionGet_All_Biers(Context context) {
        Intent intent = new Intent(context, GetBiersServices.class);
        intent.setAction(ACTION_GET_ALL_BIERS);
        context.startService(intent);
        Toast.makeText(context, R.string.dl, Toast.LENGTH_LONG).show();
    }


    public GetBiersServices() {
        super("GetBiersServices");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_ALL_BIERS.equals(action)) {
                handleActionGet_All_Biers();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */

    private void copyInputStreamToFile(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0) {
                out.write(buf,0,len);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleActionGet_All_Biers() {
        // TODO: Handle action Foo
        Log.d("GetBiersServices", "Thread service name:" + Thread.currentThread().getName());
        URL url;
        try {
            url = new URL("http://binouze.fabrigli.fr/bieres.json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if(HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                copyInputStreamToFile(conn.getInputStream(), new File(getCacheDir(), "bieres.json"));
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(SecondeActivity.BIERS_UPDATE));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    /*
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
    */
}
