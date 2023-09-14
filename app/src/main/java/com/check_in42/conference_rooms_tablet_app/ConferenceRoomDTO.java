package com.check_in42.conference_rooms_tablet_app;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ConferenceRoomDTO {
    private Long formId;
    private Long userId;
    private String intraId;
    private LocalDate date;
    private Long reservationCount;
    private Long reservationInfo;
    private LocalDateTime checkInTime;

    ConferenceRoomDTO() { }

    ConferenceRoomDTO(Long formId, Long userId, String intraId, LocalDate date, Long reservationCount, Long reservationInfo, LocalDateTime checkInTime) {
        this.formId = formId;
        this.userId = userId;
        this.intraId = intraId;
        this.date = date;
        this.reservationCount = reservationCount;
        this.reservationInfo = reservationInfo;
        this.checkInTime = checkInTime;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIntraId() {
        return intraId;
    }

    public void setIntraId(String intraId) {
        this.intraId = intraId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getReservationCount() {
        return reservationCount;
    }

    public void setReservationCount(Long reservationCount) {
        this.reservationCount = reservationCount;
    }

    public Long getReservationInfo() {
        return reservationInfo;
    }

    public void setReservationInfo(Long reservationInfo) {
        this.reservationInfo = reservationInfo;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }
}
