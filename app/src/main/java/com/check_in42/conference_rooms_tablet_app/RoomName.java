package com.check_in42.conference_rooms_tablet_app;

public enum RoomName {
    IP_123_123_123_123("CLUSTER_EX"),
    IP_1("CLUSTER1_1"),
    IP_2("CLUSTER1_2"),
    IP_3("CLUSTER_X"),
    IP_4("CLUSTER3_1"),
    IP_5("CLUSTER3_2");

    private final String roomName;

    RoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }
}
