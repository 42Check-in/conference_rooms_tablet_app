package com.check_in42.conference_rooms_tablet_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ConferenceRoomAdapter conferenceRoomAdapter;
    private Service service = new Service(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.reservationInfoView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        conferenceRoomAdapter = new ConferenceRoomAdapter();
        recyclerView.setAdapter(conferenceRoomAdapter);

        service.viewReservationList();
        service.start30MinuteRefresh();
    }

    public void reRender(ArrayList<ConferenceRoomDTO> conferenceRoomDTOS) {
        conferenceRoomAdapter.getItems().clear();
        conferenceRoomAdapter.setItems(conferenceRoomDTOS);
        conferenceRoomAdapter.notifyDataSetChanged();
    }
}