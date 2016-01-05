package com.betterzw.app.handlethreadtest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private  CartHandlerThread handlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        handlerThread = CartHandlerThread.getInstance();

        findViewById(R.id.start_thread).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCartThread();
            }
        });

        findViewById(R.id.stop_thread).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Stop Thread");
                handlerThread.quit();
            }
        });
    }

    private void startCartThread() {
        handlerThread.enqueue(runnable1);
        handlerThread.enqueue(runnable2);
    }

    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            try {
                Log.d(TAG, "runnable 1 start");
                Thread.sleep(5000);
                Log.d(TAG, "runnable 1 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            try {
                Log.d(TAG, "runnable 2 start");
                while (true){
                    Thread.sleep(1000);
                    Log.d(TAG, "runnable 2 running");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

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
}
