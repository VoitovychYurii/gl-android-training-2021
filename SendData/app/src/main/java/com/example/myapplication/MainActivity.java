package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myservice.IMyInterface;

public class MainActivity extends AppCompatActivity{

    final static String TAG = "MyApplication";
    private IMyInterface mService;
    private ServiceConnection mConeection = new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service){
            if (service != null){
                mService = IMyInterface.Stub.asInterface(service);
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName Name){
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText messagefield = (EditText)findViewById(R.id.messagefield);
        final Button buttonsend = (Button)findViewById(R.id.buttonsend);
        final Button buttonclear = (Button)findViewById(R.id.buttonclear);

        buttonsend.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view){
                String sdata = messagefield.getText().toString();
                try
                    {
                        int data = Integer.parseInt(sdata);
                        mService.setValue(data);
                        messagefield.setText("Messages sent");
                    }
                catch (NumberFormatException | NullPointerException | RemoteException e){
                        e.printStackTrace();
                        messagefield.setText("INV");
                        Log.e(TAG, "call add filed");
                    }
            }

        });
        buttonclear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                messagefield.setText("");
            }
        });

        Intent inten = new Intent();
        inten.setClassName("com.example.myservice", "com.example.myservice.MyService");
        try
        {
            bindService(inten, mConeection, BIND_AUTO_CREATE);
        }
        catch (SecurityException e)
        {
            Log.e(TAG,"bind to service failed by security");
        }
    }
}