package com.abstractrestapplication.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

/**
 * @author peaseloxes
 */
public final class DateUtil {

    public static final String DATE_MAY_NOT_BE_NULL = "Date may not be null";
    public static final String CALENDAR_MAY_NOT_BE_NULL = "Calendar may not be null";
    public static final String LOCAL_DATE_TIME_MAY_NOT_BE_NULL = "LocalDateTime may not be null";

    /**
     * Hidden constructor.
     */
    private DateUtil() {

    }

    /////////////
    //util.Date//
    /////////////

    /**
     * Convert a calendar to a java.util.Date.
     * <p>
     * Essentially nothing more than Calendar.getTime()
     *
     * @param calendar the instance to convert.
     * @return a Date representing the same time as the calendar.
     * @see Calendar#getTime()
     */
    public static java.util.Date toDate(final Calendar calendar) {
        if (calendar == null) {
            throw new IllegalArgumentException(CALENDAR_MAY_NOT_BE_NULL);
        }
        return calendar.getTime();
    }

    /**
     * Convert a java.util.Date to a calendar.
     *
     * @param date the instance to convert.
     * @return a Calendar representing the same time as the date.
     */
    public static Calendar toCalendar(final java.util.Date date) {
        if (date == null) {
            throw new IllegalArgumentException(DATE_MAY_NOT_BE_NULL);
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Convert a LocalDateTime to a java.util.Date.
     *
     * @param localDateTime the instance to convert.
     * @return a Date representing the same time as the LocalDateTime.
     */
    public static java.util.Date toDate(final LocalDateTime localDateTime) {
        if (localDateTime == null) {
            throw new IllegalArgumentException(LOCAL_DATE_TIME_MAY_NOT_BE_NULL);
        }
        return java.util.Date.from(
                localDateTime.atZone(ZoneId.systemDefault()).toInstant()
        );
    }

    /**
     * Convert a java.util.Date to a LocalDateTime.
     *
     * @param date the instance to convert.
     * @return a LocalDateTime representing the same time as the date.
     */
    public static LocalDateTime toLocalDateTime(final java.util.Date date) {
        if (date == null) {
            throw new IllegalArgumentException(DATE_MAY_NOT_BE_NULL);
        }
        return LocalDateTime.ofInstant(
                date.toInstant(), ZoneId.systemDefault()
        );
    }

    /**
     * Convert a java.sql.Date to a java.util.Date.
     * <p>
     * Note: sql.Date has no time, precision loss inevitable.
     *
     * @param date the instance to convert.
     * @return a Date representing the same time as the Date.
     */
    public static java.util.Date toDate(final java.sql.Date date) {
        if (date == null) {
            throw new IllegalArgumentException(DATE_MAY_NOT_BE_NULL);
        }
        return new java.util.Date(date.getTime());
    }

    ////////////
    //sql.Date//
    ////////////

    /**
     * Convert a calendar to a java.sql.Date.
     * <p>
     * Note: sql.Date has no time, precision loss inevitable.
     *
     * @param calendar the instance to convert.
     * @return a Date representing the same time as the calendar.
     */
    public static java.sql.Date toSqlDate(final Calendar calendar) {
        if (calendar == null) {
            throw new IllegalArgumentException(CALENDAR_MAY_NOT_BE_NULL);
        }
        return new java.sql.Date(calendar.getTimeInMillis());
    }

    /**
     * Convert a java.sql.Date to a calendar.
     * <p>
     * Note: sql.Date has no time, precision loss inevitable.
     *
     * @param date the instance to convert.
     * @return a Calendar representing the same time as the date.
     */
    public static Calendar toCalendar(final java.sql.Date date) {
        if (date == null) {
            throw new IllegalArgumentException(DATE_MAY_NOT_BE_NULL);
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        return calendar;
    }

    /**
     * Convert a LocalDateTime to an sql.Date.
     * <p>
     * Note: sql.Date has no time, precision loss inevitable.
     *
     * @param localDateTime the instance to convert.
     * @return a Date representing the same time as the LocalDateTime.
     */
    public static java.sql.Date toSqlDate(final LocalDateTime localDateTime) {
        if (localDateTime == null) {
            throw new IllegalArgumentException(LOCAL_DATE_TIME_MAY_NOT_BE_NULL);
        }
        return java.sql.Date.valueOf(localDateTime.toLocalDate());
    }

    /**
     * Convert an sql.Date to a LocalDateTime.
     * <p>
     * Note: sql.Date has no time, precision loss inevitable.
     *
     * @param date the instance to convert.
     * @return a LocalDateTime representing the same time as the date.
     */
    public static LocalDateTime toLocalDateTime(final java.sql.Date date) {
        if (date == null) {
            throw new IllegalArgumentException(DATE_MAY_NOT_BE_NULL);
        }
        final java.util.Date d = toDate(date);
        return toLocalDateTime(d);
    }

    /**
     * Convert an util.Date to an sql.Date.
     * <p>
     * Note: sql.Date has no time, precision loss inevitable.
     *
     * @param date the instance to convert.
     * @return a Date representing the same time as the date.
     */
    public static java.sql.Date toSqlDate(final java.util.Date date) {
        if (date == null) {
            throw new IllegalArgumentException(DATE_MAY_NOT_BE_NULL);
        }
        return new java.sql.Date(date.getTime());
    }

    //////////////////////
    //conversion helpers//
    //////////////////////

    /**
     * Convert a calendar to a LocalDateTime.
     *
     * @param calendar the instance to convert.
     * @return a LocalDateTime representing the same time as the calendar.
     * @see #toDate(Calendar)
     * @see #toLocalDateTime(java.util.Date)
     */
    public static LocalDateTime toLocalDateTime(final Calendar calendar) {
        if (calendar == null) {
            throw new IllegalArgumentException(CALENDAR_MAY_NOT_BE_NULL);
        }
        final java.util.Date date = toDate(calendar);
        return toLocalDateTime(date);
    }

    /**
     * Convert a LocalDateTime to a calendar.
     *
     * @param localDateTime the instance to convert.
     * @return a Calendar representing the same time as the LocalDateTime.
     * @see #toDate(LocalDateTime)
     * @see #toCalendar(java.util.Date)
     */
    public static Calendar toCalendar(final LocalDateTime localDateTime) {
        if (localDateTime == null) {
            throw new IllegalArgumentException(LOCAL_DATE_TIME_MAY_NOT_BE_NULL);
        }
        final java.util.Date date = toDate(localDateTime);
        return toCalendar(date);
    }

}
