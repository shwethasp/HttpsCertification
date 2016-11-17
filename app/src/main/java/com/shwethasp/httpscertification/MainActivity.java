package com.shwethasp.httpscertification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.shwethasp.httpscertification.MyHttpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class MainActivity extends AppCompatActivity {

    @Deprecated
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * Thread to connect to the server
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Instantiate the custom HttpClient
                    DefaultHttpClient client = new MyHttpClient(getApplicationContext());
                    HttpGet get = new HttpGet("https://www.google.com");
                    // Execute the GET call and obtain the response
                    HttpResponse getResponse = client.execute(get);
                    getResponse.getStatusLine().getStatusCode();
                    Log.e("StatusCode", "" + getResponse.getStatusLine().getStatusCode());
                    if (getResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity responseEntity = getResponse.getEntity();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (Exception e) {
                    Log.e("Exception", "" + e.getMessage().toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }
}
