package com.example.myservice;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service
{
    private int mMess = 0;
    public final float CONSTANTA_G = 9.8f;
    public MyService(){
    }

    IMyInterface.Stub mService = new IMyInterface.Stub() {
        @Override
        public void setValue(int data) {
            mMess = data;
        }

        @Override
        public int getValue() throws RemoteException {
            return mMess;
        }
    };


    @Override
    public IBinder onBind(Intent intent){
        return mService;
    }
}