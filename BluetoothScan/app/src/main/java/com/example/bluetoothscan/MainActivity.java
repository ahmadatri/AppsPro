package com.example.bluetoothscan;



import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    Button forward, backward, right, left, stop;
    ToggleButton autonomousbtn;
    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUp();

        Intent newint = getIntent();
        address = newint.getStringExtra(DeviceList.EXTRA_ADDRESS);

        new ConnectBT().execute();

        forward.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 400);
                        if (btSocket != null) {
                            try {
                                btSocket.getOutputStream().write((byte) '3');
                            } catch (IOException e) {
                                msg("Error");
                            }
                        }
                        break;

                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        if (btSocket != null) {
                            try {
                                btSocket.getOutputStream().write((byte) '0');
                            } catch (IOException e) {
                                msg("Error");
                            }
                        }
                        break;

                }
                return true;
            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {
                    if (btSocket != null) {
                        try {
                            btSocket.getOutputStream().write((byte) '3');
                        } catch (IOException e) {
                            msg("Error");
                        }
                    }
                    mHandler.postDelayed(this, 100);
                }
            };
        });
        right.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 400);
                        if (btSocket != null) {
                            try {
                                btSocket.getOutputStream().write((byte) '5');
                            } catch (IOException e) {
                                msg("Error");
                            }
                        }
                        break;

                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        if (btSocket != null) {
                            try {
                                btSocket.getOutputStream().write((byte) '0');
                            } catch (IOException e) {
                                msg("Error");
                            }
                        }
                        break;

                }
                return true;
            }
            Runnable mAction = new Runnable() {
                @Override public void run() {
                    if (btSocket != null) {
                        try {
                            btSocket.getOutputStream().write((byte) '5');
                        } catch (IOException e) {
                            msg("Error");
                        }
                    }
                    mHandler.postDelayed(this, 100);
                }
            };
        });

        left.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 400);
                        if (btSocket != null) {
                            try {
                                btSocket.getOutputStream().write((byte) '4');
                            } catch (IOException e) {
                                msg("Error");
                            }
                        }
                        break;

                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        if (btSocket != null) {
                            try {
                                btSocket.getOutputStream().write((byte) '0');
                            } catch (IOException e) {
                                msg("Error");
                            }
                        }
                        break;

                }
                return true;
            }
            Runnable mAction = new Runnable() {
                @Override public void run() {
                    if (btSocket != null) {
                        try {
                            btSocket.getOutputStream().write((byte) '4');
                        } catch (IOException e) {
                            msg("Error");
                        }
                    }
                    mHandler.postDelayed(this, 100);
                }
            };
        });

        backward.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 400);
                        if (btSocket != null) {
                            try {
                                btSocket.getOutputStream().write((byte) '6');
                            } catch (IOException e) {
                                msg("Error");
                            }
                        }
                        break;

                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        if (btSocket != null) {
                            try {
                                btSocket.getOutputStream().write((byte) '0');
                            } catch (IOException e) {
                                msg("Error");
                            }
                        }
                        break;

                }
                return true;
            }
            Runnable mAction = new Runnable() {
                @Override public void run() {
                    if (btSocket != null) {
                        try {
                            btSocket.getOutputStream().write((byte) '6');
                        } catch (IOException e) {
                            msg("Error");
                        }
                    }
                    mHandler.postDelayed(this, 100);
                }
            };
        });


        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btSocket != null) {
                    try {
                        btSocket.getOutputStream().write((byte) '0');
                    } catch (IOException e) {
                        msg("Error");
                    }
                }
            }
        });


        autonomousbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (btSocket != null) {
                        try {
                            btSocket.getOutputStream().write((byte) '3');
                        } catch (IOException e) {
                            msg("Error");
                        }
                    }
                }
                else{
                    if (btSocket != null) {
                        try {
                            btSocket.getOutputStream().write((byte) '0');
                        } catch (IOException e) {
                            msg("Error");
                        }
                    }
                }
            }
        });
    }


    private void setUp() {
        forward = (Button) findViewById(R.id.forward);
        backward = (Button) findViewById(R.id.backward);
        left = (Button) findViewById(R.id.left);
        right = (Button) findViewById(R.id.right);
        stop = (Button) findViewById(R.id.stop);
        autonomousbtn = (ToggleButton) findViewById(R.id.autonomusmode);
    }

    private void msg(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    private void Disconnect() {
        if (btSocket != null) { //If the btSocket is busy
            try {
                btSocket.close(); //close connection
            } catch (IOException e) {
                msg("Error");
            }
        }
        finish(); //return to the first layout
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void> {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(MainActivity.this, "Connecting...", "Please wait");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices) {
            try {
                if (btSocket == null || !isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            } catch (IOException e) {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (!ConnectSuccess) {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            } else {
                msg("Connected");
                isBtConnected = true;
            }
            progress.dismiss();
        }

    }
}