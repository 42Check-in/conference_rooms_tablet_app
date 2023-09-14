package com.check_in42.conference_rooms_tablet_app;

import android.util.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ConferenceUtil {

    private static LocalDateTime getTime(int timeIdx) {
        return LocalDateTime.now().withHour(timeIdx / 2 + 8).withMinute(timeIdx % 2 == 1 ? 30 : 0);
    }

    private static String getTimeString(LocalDateTime time) {
        String result = "";

        if (time.getHour() < 10)
            result = "0";
        result = result + time.getHour() + ":";
        if (time.getMinute() == 0)
            result = result + "0";
        result = result + time.getMinute();
        return result;
    }

    public static String getTimeRange(Long reservationInfo) {
        final int timeBitSize = 24;
        int[] timeIdx = new int[2];
        int range = -1;
        boolean flag = true;

        for (int i = 0; i < timeBitSize; i++) {
            if ((reservationInfo & 1L) == 1) {
                range++;
                if (flag) {
                    timeIdx[0] = i;
                    flag = false;
                }
            }
            reservationInfo = reservationInfo >> 1;
        }
        timeIdx[1] = timeIdx[0] + range;
        LocalDateTime startTime = getTime(timeIdx[0]);
        LocalDateTime endTime = getTime(timeIdx[1]).plusMinutes(29);
        return getTimeString(startTime) + " ~ " + getTimeString(endTime);
    }

    public static long startDelay() {
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime nextRunTime = now.withSecond(0).withNano(0);
        if (now.getMinute() >= 30) {
            nextRunTime = nextRunTime.plusHours(1);
        }

        return ChronoUnit.MILLIS.between(now, nextRunTime) / 1000;
    }

    public static String getIp() throws UnknownHostException {
        InetAddress localhost = InetAddress.getLocalHost();
        return "IP_" + localhost.getHostAddress().replace('.', '_');
    }

    public static int getTimeIdx(LocalDateTime dateTime) {
        if (dateTime.getHour() < 8)
            return -1;
        return ((dateTime.getHour() - 8) * 2) + (dateTime.getMinute() >= 30 ? 1 : 0);
    }

    public static int getTimeIdx() {
        return getTimeIdx(LocalDateTime.now());
    }
}
