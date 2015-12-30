package org.esiea.bemat_gull.app_1;

import java.util.Observable;

import android.os.Handler;
import android.widget.TextView;

public class Chrono extends Observable implements Runnable {

    private long totalSeconds=31;
    private int delayMilliSeconds = 1000;
    private Handler myHandler = new Handler();
    public void setChanged(){
        super.setChanged();
    }

    public void run(){
        myHandler.postDelayed(chronometerSnoozer, delayMilliSeconds);
    }

    private MyChronometerMotorSnoozer chronometerSnoozer = new MyChronometerMotorSnoozer();




    private class MyChronometerMotorSnoozer implements Runnable {

        @Override
        public void run() {

           if(totalSeconds>0)
               totalSeconds--;
            setChanged();
            notifyObservers(totalSeconds);
            myHandler.removeCallbacks(chronometerSnoozer);
            myHandler.postDelayed(chronometerSnoozer, delayMilliSeconds);
        }



    }


}