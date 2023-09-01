package com.check_in42.conference_rooms_tablet_app;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ConferenceRoomAdapter extends RecyclerView.Adapter<ConferenceRoomAdapter.ViewHolder> {

    private List<ConferenceRoomDTO> items = new ArrayList<>();

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

        return new ViewHolder(itemView);
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

        public ViewHolder(View itemView) {
            super(itemView);

            intraIdView = itemView.findViewById(R.id.intraId);
            timeView = itemView.findViewById(R.id.time);
            btnInOut = itemView.findViewById(R.id.btnInOut);
        }

        public void setItem(ConferenceRoomDTO item) {
            intraIdView.setText(item.getIntraId());
            timeView.setText(ConferenceUtil.getTimeRange(item.getReservationInfo()));
            /* 오늘이면 check-in */
            btnInOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /* 버튼 로직 */
                    Toast.makeText(view.getContext(), "버튼이 클릭됨: " + item.getIntraId(), Toast.LENGTH_SHORT).show();
                }
            });
            btnInOut.setText("Check-in");
            /* 버튼 색상 로직도 추가해야 함 */
            btnInOut.setBackgroundColor(Color.parseColor("#6A70FF"));
        }
    }
}
