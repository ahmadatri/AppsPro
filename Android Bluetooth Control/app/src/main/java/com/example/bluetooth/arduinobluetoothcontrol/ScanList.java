package com.example.bluetooth.arduinobluetoothcontrol;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ScanList extends AppCompatActivity implements AdapterView.OnItemClickListener{

private static final String TAG = "MainActivity";
public static final int REQUEST_ENABLE_BT=1;
        //widgets
 Button btScan,btnext;
ListView scanList;
//Bluetooth
private BluetoothAdapter myBluetooth = null;
SwipeRefreshLayout refreshLayout;
public static String EXTRA_ADDRESS = "device_address";
public ArrayList<BluetoothDevice> mBTDevices = new ArrayList<>();
public DeviceListAdapter mDeviceListAdapter;

    private BroadcastReceiver mBroadcastReceiver3 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            Log.d(TAG, "onReceive: ACTION FOUND.");

            if (action.equals(BluetoothDevice.ACTION_FOUND)){
                BluetoothDevice device = intent.getParcelableExtra (BluetoothDevice.EXTRA_DEVICE);
                mBTDevices.add(device);
                Log.d(TAG, "onReceive: " + device.getName() + ": " + device.getAddress());
                mDeviceListAdapter = new DeviceListAdapter(context, R.layout.activity_device_list_adapter, mBTDevices);
                scanList.setAdapter(mDeviceListAdapter);
            }
        }
    };

public void btnDiscover(View view){
        Log.d(TAG,"btnDiscover: Looking for unpaired devices.");

        if(myBluetooth.isDiscovering()){
        myBluetooth.cancelDiscovery();
        Log.d(TAG,"btnDiscover: Canceling discovery.");

        //check BT permissions in manifest
        checkBTPermissions();

        myBluetooth.startDiscovery();
        IntentFilter discoverDevicesIntent=new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mBroadcastReceiver3,discoverDevicesIntent);
        }
        if(!myBluetooth.isDiscovering()){

        //check BT permissions in manifest
        checkBTPermissions();

        myBluetooth.startDiscovery();
        IntentFilter discoverDevicesIntent=new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mBroadcastReceiver3,discoverDevicesIntent);
        }
        }

private void checkBTPermissions(){
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
        int permissionCheck=this.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
        permissionCheck+=this.checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
        if(permissionCheck!=0){

        this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},1001); //Any number
        }
        }else{
        Log.d(TAG,"checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
        }
        }
public void onItemClick(AdapterView<?> adapterView,View view,int i,long l){
        //first cancel discovery because its very memory intensive.
        myBluetooth.cancelDiscovery();

        Log.d(TAG,"onItemClick: You Clicked on a device.");
        String deviceName=mBTDevices.get(i).getName();
        String deviceAddress=mBTDevices.get(i).getAddress();

        Log.d(TAG,"onItemClick: deviceName = "+deviceName);
        Log.d(TAG,"onItemClick: deviceAddress = "+deviceAddress);

        //create the bond.
        //NOTE: Requires API 17+? I think this is JellyBean
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.JELLY_BEAN_MR2){
        Log.d(TAG,"Trying to pair with "+deviceName);
        mBTDevices.get(i).createBond();
        }
        }

}
