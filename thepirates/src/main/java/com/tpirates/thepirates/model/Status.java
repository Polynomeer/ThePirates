package com.tpirates.thepirates.model;

import com.tpirates.thepirates.controller.StoreController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public enum Status {
    OPEN,
    CLOSE,
    HOLIDAY;

    private static final Logger logger = LoggerFactory.getLogger(Status.class);
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public static String getStatusByTime(String open, String close, List<Holiday> holidays) {
        try {
            Date currentTime = Date.from(StoreController.currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
            Date openTime = new Date(simpleDateFormat.parse(open).getTime());
            Date closeTime = new Date(simpleDateFormat.parse(close).getTime());

            if (isHoliday(holidays)) {
                return Status.HOLIDAY.name();
            }
            if (currentTime.after(closeTime) || currentTime.before(openTime)) {
                return Status.CLOSE.name();
            }
        } catch (ParseException e) {
            logger.info("Cannot parse open or close time.\n" + e.getMessage());
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
            try {
                // TODO: fix to LocalDateTime
                Date currentTime = Date.from(StoreController.currentDateTime.atZone(ZoneId.systemDefault()).toInstant());
                Date openTime = new Date(simpleDateFormat.parse(businessTime.getOpen()).getTime());
                Date closeTime = new Date(simpleDateFormat.parse(businessTime.getClose()).getTime());

                if (currentTime.after(closeTime) || currentTime.before(openTime)) {
                    System.out.println(openTime + " " + closeTime + " " + currentTime);
                    return Status.CLOSE.name();
                }
            } catch (ParseException e) {
                logger.info("Cannot parse open or close time.\n" + e.getMessage());
            }
        }

        return Status.OPEN.name();
    }
}
