package com.check_in42.conference_rooms_tablet_app;

import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Repository {

    private final static String api42CheckIn = "https://api.42check-in.kr/tablet/";

    public String requestReservationInfo() {
        final String reservations = "reservations";
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(api42CheckIn + reservations);

            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            if (conn != null) {
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                int resCode = conn.getResponseCode();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                while (true) {
                    line = br.readLine();
                    if (line == null)
                        break;
                    sb.append(line + "\n");
                }
                br.close();
                conn.disconnect();
            }
        } catch (Exception e) {
            Log.e("requestFailed", e.toString());
        }
        return sb.toString();
    }
}
