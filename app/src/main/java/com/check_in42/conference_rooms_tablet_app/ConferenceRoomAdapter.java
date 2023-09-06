package com.check_in42.conference_rooms_tablet_app;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConferenceRoomAdapter extends RecyclerView.Adapter<ConferenceRoomAdapter.ViewHolder> {

    private List<ConferenceRoomDTO> items;
    private ConferenceService conferenceService;

    ConferenceRoomAdapter(ConferenceService conferenceService) {
        items = new ArrayList<>();
        this.conferenceService = conferenceService;
    }

    public void addItem(ConferenceRoomDTO item) {
        items.add(item);
    }

    public List<ConferenceRoomDTO> getItems() {
        return items;
    }

    public void setItems(List<ConferenceRoomDTO> items) {
        this.items = items;
    }

    public ConferenceRoomDTO getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, ConferenceRoomDTO item) {
        items.set(position, item);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recode_item, parent, false);

        return new ViewHolder(itemView, conferenceService);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ConferenceRoomDTO item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView intraIdView;
        TextView timeView;
        Button btnInOut;
        ConferenceService conferenceService;

        public ViewHolder(View itemView, ConferenceService conferenceService) {
            super(itemView);

            intraIdView = itemView.findViewById(R.id.intraId);
            timeView = itemView.findViewById(R.id.time);
            btnInOut = itemView.findViewById(R.id.btnInOut);
            this.conferenceService = conferenceService;
        }

        public void setItem(ConferenceRoomDTO item) {
            final int nowTimeIdx = ConferenceUtil.getTimeIdx();
            intraIdView.setText(item.getIntraId());
            timeView.setText(ConferenceUtil.getTimeRange(item.getReservationInfo()));

            btnInOut.setOnClickListener(new View.OnClickListener() {
                Object lock = new Object();
                @Override
                public void onClick(View view) {
                    try {
                        synchronized (lock) {
                            if (nowTimeIdx >= 0 && (item.getReservationInfo() & (1L << nowTimeIdx)) > 0) {
                                if (item.isCheckInState())
                                    conferenceService.checkOutBtn(item);
                                else
                                    conferenceService.checkInBtn(item);
                            } else {
                                conferenceService.cancelBtn(item);
                            }
                        }
                        synchronized (lock) {
                            conferenceService.viewReservationList();
                        }
                    } catch (JSONException e) {
                        Log.i("Button Error.", Objects.requireNonNull(e.getMessage()));
                    }
                }
            });
            if ((item.getReservationInfo() & (1L << nowTimeIdx)) > 0) {
                if (!item.isCheckInState()) {
                    btnInOut.setText("Check-in");
                    btnInOut.setBackgroundColor(Color.parseColor("#6A70FF"));
                } else {
                    btnInOut.setText("Check-out");
                    btnInOut.setBackgroundColor(Color.parseColor("#228B22"));
                }
                intraIdView.setTextColor(Color.parseColor("#2E2E2E"));
                timeView.setTextColor(Color.parseColor("#2E2E2E"));
            } else {
                btnInOut.setText("Cancel");
                btnInOut.setBackgroundColor(Color.parseColor("#DC143C"));
                intraIdView.setTextColor(Color.parseColor("#D8D8D8"));
                timeView.setTextColor(Color.parseColor("#D8D8D8"));
            }
            // check-in: 현재 회의실 사용 시간 이지만 checkInState가 false일 경우
            // check-out: 현재 회의실 사용 중인 경우
            // cancel: 회의실 사용 시간이 아닌 경우
        }
    }
}
