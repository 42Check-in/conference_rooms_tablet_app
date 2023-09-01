package com.check_in42.conference_rooms_tablet_app;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class Service {
    private static final long refreshInterval = 30 * 60 * 1000; // 30분
    private MainActivity activity;
    private Repository repository = new Repository();
    private Handler handler = new Handler(Looper.getMainLooper());

    public Service(MainActivity activity) {
        this.activity = activity;
    }

    public void viewReservationList() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<ConferenceRoomDTO> conferenceRoomDTOS = null;
        try {
            conferenceRoomDTOS = mapper.readValue(repository.requestReservationInfo(), ArrayList.class);
        } catch (Exception e) {
            Log.e("MappingFailed", e.toString());
        }
        activity.reRender(conferenceRoomDTOS);
    }

    public void start30MinuteRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewReservationList();
                handler.postDelayed(this, refreshInterval);
            }
        }, refreshInterval);
    }
}
