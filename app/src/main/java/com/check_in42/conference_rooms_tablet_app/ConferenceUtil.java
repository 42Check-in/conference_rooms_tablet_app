package com.check_in42.conference_rooms_tablet_app;

import android.util.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ConferenceUtil {

    private static String getTimeString(int[] time) {
        String result = "";

        if (time[0] < 10)
            result = "0";
        result = result + time[0] + ":";
        if (time[1] == 0)
            result = result + "0";
        result = result + time[1];
        return result;
    }

//    public static String getTimeRange(Long reservationInfo) {
//        final int timeBitSize = 24;
//
//        int timeHour = 8;
//        int timeMin = 0;
//        int[] startTime = new int[2];
//        int[] endTime = new int[2];
//        boolean flag = true;
//
//        for (int i = 0; i < timeBitSize; i++) {
//            timeMin = i * 30 % 60;
//            if (i != 0 && timeMin == 0)
//                timeHour++;
//            if (flag && (reservationInfo & 1L) == 1) {
//                startTime[0] = timeHour;
//                startTime[1] = timeMin;
//                reservationInfo = reservationInfo >> 1;
//                flag = false;
//            }
//            if (i == 23 || (!flag && (reservationInfo & 1L) == 0)) {
//                endTime[0] = timeHour;
//                endTime[1] = timeMin + 29;
//                break ;
//            }
//            reservationInfo = reservationInfo >> 1;
//        }
//        return getTimeString(startTime) + " ~ " + getTimeString(endTime);
//    }

    public static String getTimeRange(Long reservationInfo) {
        final int timeBitSize = 24;

        int[] timeIdx = new int[2];
        int range = -1;
        boolean flag = true;

        for (int i = 0; i < timeBitSize; i++) {
            if ((reservationInfo & 1L) == 1) {
                if (flag) {
                    timeIdx[0] = i + 1;
                    flag = false;
                }
                range++;
            }
            reservationInfo = reservationInfo >> 1;
        }
        timeIdx[1] = timeIdx[0] + range;
        LocalDateTime startTime = LocalDateTime.now().withHour(8 * );
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
}
