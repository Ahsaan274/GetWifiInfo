package com.example.getwifiinfo;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    View view1,view2;
    Button getWifiInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
        
        getWifiInfo = findViewById(R.id.button);
        view1 = findViewById(R.id.view);
        view2 = findViewById(R.id.view1);

        getWifiInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.VISIBLE);

                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                // Level of a Scan Result
                List<ScanResult> wifiList = wifiManager.getScanResults();
                for (ScanResult scanResult : wifiList) {
                    int level = WifiManager.calculateSignalLevel(scanResult.level, 5);
                    System.out.println("Level is " + level + " out of 5");
                }

                // Level of current connection
                int rssi = wifiManager.getConnectionInfo().getRssi();
                int level = WifiManager.calculateSignalLevel(rssi, 5);
                double percentage = WifiManager.calculateSignalLevel(rssi, 50) * 2;

                int ip = wifiInfo.getIpAddress();
                int linkSpeed = wifiInfo.getLinkSpeed();

                int networkID = wifiInfo.getNetworkId();
                String ssid = wifiInfo.getSSID();
                boolean hssid = wifiInfo.getHiddenSSID();

                String macAddress = wifiInfo.getMacAddress();
                String IpAddress = Formatter.formatIpAddress(ip);

                String info ="Ip Address :"+IpAddress+
                        "\n"+"MAC Address :"+macAddress+
                        "\n"+"Network ID :"+networkID+
                        "\n"+"Link Speed :"+linkSpeed+
                        "\n"+"SSID :"+ssid+
                        "\n"+"Hidden SSID :"+hssid+
                        "\n"+"Wifi Strength :"+percentage+"%"+
                        "\n"+"Wifi Level :"+level+"out of 5";
                textView.setText(info);
            }
        });
    }
}