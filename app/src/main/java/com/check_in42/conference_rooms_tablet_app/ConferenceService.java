package com.check_in42.conference_rooms_tablet_app;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;
import java.util.ArrayList;

public class ConferenceService {
    private static final long refreshInterval = 30 * 60 * 1000; // 30분
    private MainActivity activity;
    private Repository repository = new Repository();
    private Handler handler = new Handler(Looper.getMainLooper());
    private ConferenceRoomAdapter conferenceRoomAdapter;

    public ConferenceService(MainActivity activity) {
        this.activity = activity;
        conferenceRoomAdapter = new ConferenceRoomAdapter();
    }

    public ConferenceRoomAdapter getConferenceRoomAdapter() {
        return conferenceRoomAdapter;
    }

    public void viewReservationList() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        ArrayList<ConferenceRoomDTO> conferenceRoomDTOS = null;
        try {
            conferenceRoomDTOS = mapper.readValue(repository.requestReservationInfo(), new TypeReference<ArrayList<ConferenceRoomDTO>>() {});
//            conferenceRoomDTOS = new ArrayList<>();
//            conferenceRoomDTOS.add(new ConferenceRoomDTO(1L, 123L, "hyeonsul", LocalDate.now(), 4L, 1140912128L, false));
//            conferenceRoomDTOS.add(new ConferenceRoomDTO(1L, 123L, "hyeonsul", LocalDate.now(), 4L, 1140866048L, false));
//            conferenceRoomDTOS.add(new ConferenceRoomDTO(1L, 123L, "hyeonsul", LocalDate.now(), 3L, 1140851136L, false));
        } catch (Exception e) {
            Log.e("MappingFailed", e.toString());
        }
        conferenceRoomAdapter.setItems(conferenceRoomDTOS);
        conferenceRoomAdapter.notifyDataSetChanged();
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
