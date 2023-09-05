package com.check_in42.conference_rooms_tablet_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ConferenceService conferenceService;
    private String roomName;

    public String getRoomName() {
        return roomName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// dev
        TextView title = findViewById(R.id.roomName);
        roomName = RoomName.valueOf("IP_1").getRoomName();
        title.setText(roomName);
        Log.i("roomName", roomName);
        setRecycleView();

// 실제 서비스시 주석 풀기
//        try {
//            roomName = RoomName.valueOf(ConferenceUtil.getIp()).getRoomName();
//            title.setText(roomName);
//            setRecycleView();
//        } catch (Exception e) {
//            Log.e("UnknownHostException", e.getMessage());
//        }
    }

    void setRecycleView() {

        RecyclerView recyclerView = findViewById(R.id.reservationInfoView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        conferenceService = new ConferenceService(this);
        recyclerView.setAdapter(conferenceService.getConferenceRoomAdapter());

        conferenceService.viewReservationList();
        conferenceService.start30MinuteRefresh();
    }
}