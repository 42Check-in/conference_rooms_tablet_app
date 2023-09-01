package com.check_in42.conference_rooms_tablet_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ConferenceRoomAdapter conferenceRoomAdapter;
//    private Service service = new Service(this);

    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.reservationInfoView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        conferenceRoomAdapter = new ConferenceRoomAdapter();
        recyclerView.setAdapter(conferenceRoomAdapter);

        conferenceRoomAdapter.getItems().clear();
        conferenceRoomAdapter.addItem(new ConferenceRoomDTO(1L, 123L, "hyeonsul", LocalDate.now(), 4L, 1140912128L, false));
        conferenceRoomAdapter.addItem(new ConferenceRoomDTO(1L, 123L, "hyeonsul", LocalDate.now(), 4L, 1140866048L, false));
        conferenceRoomAdapter.addItem(new ConferenceRoomDTO(1L, 123L, "hyeonsul", LocalDate.now(), 3L, 1140851136L, false));
        conferenceRoomAdapter.notifyDataSetChanged();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                conferenceRoomAdapter.getItems().clear();
                conferenceRoomAdapter.addItem(new ConferenceRoomDTO(1L, 123L, "hello", LocalDate.now(), 4L, 1140912128L, false));
                conferenceRoomAdapter.addItem(new ConferenceRoomDTO(1L, 123L, "hello", LocalDate.now(), 4L, 1140866048L, false));
                conferenceRoomAdapter.addItem(new ConferenceRoomDTO(1L, 123L, "hello", LocalDate.now(), 3L, 1140851136L, false));
                conferenceRoomAdapter.notifyDataSetChanged();

                handler.postDelayed(this, 4000);

            }
        }, 4000);

//        service.viewReservationList();
//        service.start30MinuteRefresh();
    }

    public void reRender(ArrayList<ConferenceRoomDTO> conferenceRoomDTOS) {
        conferenceRoomAdapter.getItems().clear();
        conferenceRoomAdapter.getItems().addAll(conferenceRoomDTOS);
        conferenceRoomAdapter.notifyDataSetChanged();
    }
}