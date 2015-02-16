package example.android.service;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class EchoService extends Service {



    @Override
    public IBinder onBind(Intent arg0) {
        System.out.println("onBind");
        return echoServiceBinder;
    }



    private final EchoServiceBinder echoServiceBinder = new EchoServiceBinder();

    public class EchoServiceBinder extends Binder{

        public EchoService getService(){
            return EchoService.this;
        }

    }


    public int getCurrentNum(){
        return i;
    }



    @Override
    public void onCreate() {
        System.out.println("onCreate");

        startTimer();

        super.onCreate();
    }

    @Override
    public void onDestroy() {
        System.out.println("onDestory");

        stopTimer();

        super.onDestroy();

    }

    private int i = 0;

    public void startTimer(){
        if (timer==null) {
            timer = new Timer();
            task = new TimerTask() {

                @Override
                public void run() {
                    i++;

                    System.out.println(i);
                }
            };

            timer.schedule(task, 1000, 1000);
        }
    }

    public void stopTimer(){
        if (timer!=null) {
            task.cancel();
            timer.cancel();

            task = null;
            timer = null;
        }
    }


    private Timer timer=null;
    private TimerTask task=null;
}
