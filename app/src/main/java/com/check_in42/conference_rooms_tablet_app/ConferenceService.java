package com.check_in42.conference_rooms_tablet_app;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConferenceService {
    final static String uri = "https://api.42check-in.kr/tablet/";
    private MainActivity activity;
    private ConferenceRoomAdapter conferenceRoomAdapter;
    private RequestQueue requestQueue;
    private ObjectMapper mapper;

    public ConferenceService(MainActivity activity) {
        this.activity = activity;
        conferenceRoomAdapter = new ConferenceRoomAdapter(this);
        requestQueue = Volley.newRequestQueue(activity);
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    public ConferenceRoomAdapter getConferenceRoomAdapter() {
        return conferenceRoomAdapter;
    }

    public void viewReservationList() {
        final String url = uri + "reservations/";

        Log.i("rendering", "rendering...");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url + activity.getRoomName(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<ConferenceRoomDTO> conferenceRoomDTOS = null;
                        try {
                            conferenceRoomDTOS = mapper.readValue(response.getString("conferenceRoomDTOList"), new TypeReference<ArrayList<ConferenceRoomDTO>>() {});
                        } catch (Exception e) {
                            Log.e("MappingFailed", e.toString());
                        }
                        conferenceRoomAdapter.setItems(conferenceRoomDTOS);
                        conferenceRoomAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error_code: " + error.networkResponse.statusCode, error.getMessage());
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    public void start30MinuteRefresh() {
        Log.i("refresh!!", "refresh");
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Log.i("refresh!!", "refresh");
                viewReservationList();
            }
//        }, ConferenceUtil.startDelay(), 30 * 60, TimeUnit.SECONDS);
        }, 1, 1, TimeUnit.SECONDS);
    }

    public void checkInBtn(Long formId) throws JSONException {
        final String url = uri + "check-in";

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("formId", formId);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("checkInSuccess", Integer.toString(200));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse != null) {
                            Log.e("error_code: " + error.networkResponse.statusCode, error.getMessage());
                        } else {
                            Log.e("Error", error.getMessage());
                        }
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    public void checkOutBtn(Long formId) {
        final String url = uri + "check-out";

        Log.i("checkOutBtn", url);
    }

    public void cancelBtn(Long formId) {
        final String url = uri + "cancel";

        Log.i("cancelBtn", url);
    }
}
