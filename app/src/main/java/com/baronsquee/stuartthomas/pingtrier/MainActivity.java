package com.baronsquee.stuartthomas.pingtrier;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.io.IOException;
import java.net.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import android.os.StrictMode;


public class MainActivity extends ActionBarActivity {

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
       StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        try {
            Pingit();
        } catch (IOException e) {
            e.printStackTrace();
        }
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



    public void Pingit() throws IOException {
        // String ipAddress = "127.0.0.1";
        String ipAddress = "74.125.239.112";
        InetAddress inet = InetAddress.getByName(ipAddress);
        String str;
        String totalstr = "";
        str = "Sending Ping Request to " + ipAddress + "...";
        str = "IP address is " + (InetAddress.getByName(ipAddress));
        totalstr = totalstr + str;
        TextView StatusUpd = (TextView) findViewById(R.id.statusTextView);
        StatusUpd.setText(totalstr, TextView.BufferType.EDITABLE);
        try {
            for (int i = 0; i <= 20; i++) {
                //String ip2 = ipAddress + i;
                boolean reachable = (java.lang.Runtime.getRuntime().exec("ping " + ipAddress).waitFor() == 100000);
                //boolean b = inet.isReachable(100);
                if (reachable) {
                    str = "Reachable";
                    break;
                } else {
                    str = "Unreachable";
                }
                totalstr = totalstr + str;
                StatusUpd.setText(totalstr, TextView.BufferType.EDITABLE);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }


        ipAddress = "172.21.3.85";
        inet = InetAddress.getByName(ipAddress);
        str = "Sending Ping Request to " + ipAddress + "...";
        totalstr = totalstr + str;
        StatusUpd.setText(totalstr, TextView.BufferType.EDITABLE);
        /*boolean c = inet.isReachable(100);
        if(c){
            str="Reachable";
        }
        else{
            str ="Unreachable";
            }
        StatusUpd.setText(str, TextView.BufferType.EDITABLE); */
    }
}
