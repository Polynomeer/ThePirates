package com.tpirates.thepirates.model;

import com.tpirates.thepirates.controller.StoreController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public enum Status {
    OPEN,
    CLOSE,
    HOLIDAY;

    private static final Logger logger = LoggerFactory.getLogger(Status.class);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public static String getStatusByTime(String open, String close, List<Holiday> holidays) {
        LocalTime currentTime = StoreController.currentDateTime.toLocalTime();
        LocalTime openTime = LocalTime.parse(open, timeFormatter);
        LocalTime closeTime = LocalTime.parse(close, timeFormatter);

        if (isHoliday(holidays)) {
            return Status.HOLIDAY.name();
        }
        if (currentTime.isAfter(closeTime) || currentTime.isBefore(openTime)) {
            return Status.CLOSE.name();
        }
        return Status.OPEN.name();
    }

    private static boolean isHoliday(List<Holiday> holidays) {
        String currentDay = StoreController.currentDateTime.format(dateFormatter);

        for (Holiday holiday : holidays) {
            if (currentDay.equals(holiday.getHoliday())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isClose(List<BusinessTime> businessTimes) {
        String today = StoreController.currentDateTime.getDayOfWeek().toString();
        int indexOfToday = WeekDay.findIndexByDay(today);

        for (BusinessTime businessTime : businessTimes) {
            if (businessTime.getDay() == indexOfToday) {
                return false;
            }
        }
        return true;
    }

    public static String getStatusByTime(List<BusinessTime> businessTimes, List<Holiday> holidays) {
        if (isHoliday(holidays)) {
            return Status.HOLIDAY.name();
        }

        if (isClose(businessTimes)) {
            return Status.CLOSE.name();
        }

        for (BusinessTime businessTime : businessTimes) {
            LocalTime currentTime = StoreController.currentDateTime.toLocalTime();
            LocalTime openTime = LocalTime.parse(businessTime.getOpen(), timeFormatter);
            LocalTime closeTime = LocalTime.parse(businessTime.getClose(), timeFormatter);

            if (currentTime.isAfter(closeTime) || currentTime.isBefore(openTime)) {
                return Status.CLOSE.name();
            }
        }

        return Status.OPEN.name();
    }
}
