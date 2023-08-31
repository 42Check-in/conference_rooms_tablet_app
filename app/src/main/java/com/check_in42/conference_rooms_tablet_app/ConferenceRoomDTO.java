package com.check_in42.conference_rooms_tablet_app;

import java.time.LocalDate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class ConferenceRoomDTO {
    private Long id;
    private String intraId;
    private LocalDate date;
    private Long reservationCount;
    private Long reservationInfo;
}
