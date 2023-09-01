package com.check_in42.conference_rooms_tablet_app;

public class ConferenceRoomDTO {
    private String intraId;
    private Long reservationInfo;
    private boolean stat;

    public ConferenceRoomDTO(String intraId, Long reservationInfo, boolean stat) {
        this.intraId = intraId;
        this.reservationInfo = reservationInfo;
        this.stat = stat;
    }

    public String getIntraId() {
        return intraId;
    }

    public void setIntraId(String intraId) {
        this.intraId = intraId;
    }

    public Long getReservationInfo() {
        return reservationInfo;
    }

    public void setReservationInfo(Long reservationInfo) {
        this.reservationInfo = reservationInfo;
    }

    public boolean getStat() {
        return stat;
    }

    public void setStat(boolean stat) {
        this.stat = stat;
    }
}
