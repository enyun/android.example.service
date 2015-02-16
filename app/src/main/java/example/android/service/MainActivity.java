package example.android.service;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, ServiceConnection {


    private Button btnStartService,btnStopService,btnBindService,btnUnbindService,btnGetCurrentNumber;
    private Intent serviceIntent;
    private EchoService echoService=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceIntent = new Intent(this, EchoService.class);

        btnStartService = (Button) findViewById(R.id.btnStartService);
        btnStopService = (Button) findViewById(R.id.btnStopService);
        btnBindService = (Button) findViewById(R.id.btnBindService);
        btnUnbindService = (Button) findViewById(R.id.btnUnbindService);
        btnGetCurrentNumber = (Button) findViewById(R.id.btnGetCurrentNum);

        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
        btnUnbindService.setOnClickListener(this);
        btnGetCurrentNumber.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStartService:
                startService(serviceIntent);
                break;
            case R.id.btnStopService:
                stopService(serviceIntent);
                break;
            case R.id.btnBindService:
                bindService(serviceIntent, this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbindService:
                unbindService(this);
                echoService=null;
                break;
            case R.id.btnGetCurrentNum:
                if (echoService!=null) {
                    Toast.makeText(this,"当前服务中的数字是："+echoService.getCurrentNum(), Toast.LENGTH_SHORT).show();
                    System.out.println("当前服务中的数字是："+echoService.getCurrentNum());
                }
                Toast.makeText(this,"当前服务中的数字meiyoua", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        System.out.println("onServiceConnected");


        echoService = ((EchoService.EchoServiceBinder)binder).getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
    }

}