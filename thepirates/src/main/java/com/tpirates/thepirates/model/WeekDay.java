package com.tpirates.thepirates.model;

public enum WeekDay {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private String day;

    WeekDay(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public static String findDayByIndex(int index) {
        WeekDay[] weekDays = WeekDay.values();
        return weekDays[index].getDay();
    }
}
