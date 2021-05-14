package com.tpirates.thepirates.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public enum Status {
    OPEN,
    CLOSE,
    HOLIDAY;

    private static final Logger logger = LoggerFactory.getLogger(Status.class);

    public static String getStatusByTime(String open, String close) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            Date openTime = new Date(simpleDateFormat.parse(open).getTime());
            Date closeTime = new Date(simpleDateFormat.parse(close).getTime());

            if (openTime.after(closeTime)) {
                return Status.CLOSE.name();
            }
        } catch (ParseException e) {
            logger.info("Cannot parse open or close time.\n" + e.getMessage());
        }
        return Status.OPEN.name();
    }
}
