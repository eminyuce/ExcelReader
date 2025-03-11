package com.excel.reader.util;

import java.util.Calendar;
import java.util.HashMap;

/**
 * This is a utility class that will allow you wrap a start and stop around
 * blocks of code that will allow you to capture the elapsed execution time see
 * main() for examples
 */

public class TimeMetrics {

    private final HashMap<String, Long> startTimes = new HashMap<>();
    private final HashMap<String, Long> endTimes = new HashMap<>();
    private final HashMap<String, String> userStore = new HashMap<>();
    private final HashMap<String, String> keyStore = new HashMap<>();

    private static final long SECONDS_IN_MIN = 60L;
    private static final long MS_IN_MIN = SECONDS_IN_MIN * 1000;

    private static final long SECONDS_IN_HOUR = 60 * 60L;
    private static final long MS_IN_HOUR = SECONDS_IN_HOUR * 1000;

    private static final long SECONDS_IN_DAY = 24 * 60 * 60L;
    private static final long MS_IN_DAY = SECONDS_IN_DAY * 1000;

    private static final String IN_PROGRESS = "In progress";

    private static final String MS_UNITS = " ms";

    private static final String NL = "\n";

    public TimeMetrics() {
        this.reset();
    }

    public void start(String action) {
        Long startTime = this.getCurrentTimeAndDate();
        startTimes.put(checkValue(action), startTime);
    }

    public void start(String action, String userId, String keyId) {
        Long startTime = this.getCurrentTimeAndDate();
        startTimes.put(checkValue(action), startTime);
        userStore.put(checkValue(action), checkValue(userId));
        keyStore.put(checkValue(action), checkValue(keyId));
    }

    public void stop(String action) {
        Long endTime = this.getCurrentTimeAndDate();
        endTimes.put(checkValue(action), endTime);
    }

    /*
     * This is probably only necessary in batch mode
     */
    public void remove(String action) {
        endTimes.remove(checkValue(action));
    }

    public String getErrorString(String action, String addedErrMsg) {
        return getMetricsString(action, addedErrMsg, true);
    }

    public String getErrorString(String action) {
        return getMetricsString(action, null, true);
    }

    /**
     * Build standard metrics message
     *
     * @param action - the specific action we are measuring By default, we only want
     *               to report an "in progress" action as "in progress", not how
     *               long it is currently running
     * @return formatted log message string
     */
    public String getMetricsString(String action, String additionalInfo) {
        return getMetricsString(action, additionalInfo, false);
    }

    /**
     * Build standard metrics message
     *
     * @param action - the specific action we are measuring By default, we only want
     *               to report an "in progress" action as "in progress", not how
     *               long it is currently running
     * @return formatted log message string
     */
    public String getMetricsString(String action) {
        return getMetricsString(action, null, false);
    }

    /**
     * Build standard metrics message
     *
     * @param action         - the specific action we are measuring
     * @param reportRunning, typically we only want to to report an active action as
     *                       "in progress" but in the case of a long running action,
     *                       we may want to log how long it has been running
     * @return formatted log message string
     * @paran additionalInfo additional info that you want in msg (comes before
     * elapsed time)
     */
    public String getMetricsString(String action, String additionalInfo, boolean reportRunning) {
        String trimmedAction = checkValue(action);

        StringBuilder message = new StringBuilder("User [");
        message.append(this.getUser(trimmedAction));
        message.append("] ");
        message.append(trimmedAction);
        message.append(" ");
        if (keyStore.containsKey(checkValue(trimmedAction))) {
            message.append(" target [");
            message.append(this.getKey(trimmedAction));
            message.append("] ");
        }
        message.append(" ");
        if (additionalInfo != null) {
            message.append(additionalInfo);
            message.append(" ");
        }
        if (endTimes.containsKey(checkValue(action))) {
            message.append("elapsed time: ");
            message.append(this.getRunTimeInMillis(trimmedAction));
            message.append(MS_UNITS);
        } else {
            message.append(IN_PROGRESS);
            if (reportRunning) {
                message.append(" ");
                long sTime = startTimes.get(checkValue(action));
                long currTime = this.getCurrentTimeAndDate();
                long e = currTime - sTime;
                message.append(e);
                message.append(MS_UNITS);
            }
        }

        return message.toString();
    }

    public String getUser(String action) {
        StringBuilder user = new StringBuilder();

        if (userStore.containsKey(checkValue(action))) {
            user.append(userStore.get(checkValue(action)));
        } else {
            user.append("N/A");
        }

        return user.toString();
    }

    public String getKey(String action) {
        StringBuilder key = new StringBuilder();

        if (keyStore.containsKey(checkValue(action))) {
            key.append(keyStore.get(checkValue(action)));
        } else {
            key.append("N/A");
        }

        return key.toString();
    }

    public String getStartTime(String action) {
        StringBuilder startTime = new StringBuilder();

        if (startTimes.containsKey(checkValue(action))) {
            startTime.append(startTimes.get(checkValue(action)));
        } else {
            startTime.append("Not Started");
        }

        return startTime.toString();
    }

    public String getEndTime(String action) {
        StringBuilder endTime = new StringBuilder();

        if (endTimes.containsKey(checkValue(action))) {
            endTime.append(endTimes.get(checkValue(action)));
        } else {
            endTime.append(IN_PROGRESS);
        }

        return endTime.toString();
    }

    // *********************************************************************

    /**
     * getRunTimeInMillis
     *
     * @param action
     * @return string
     */
    // *********************************************************************
    public String getRunTimeInMillis(String action, boolean showUnits) {
        StringBuilder runTime = new StringBuilder();

        if ((startTimes.containsKey(checkValue(action)) && (endTimes.containsKey(checkValue(action))))) {
            long sTime = startTimes.get(checkValue(action));
            long eTime = endTimes.get(checkValue(action));

            runTime.append((eTime - sTime));
            if (showUnits) {
                runTime.append(MS_UNITS);
            }
        } else {
            runTime.append(IN_PROGRESS);
        }

        return runTime.toString();
    }

    // *********************************************************************

    /**
     * getRunTime (expressed in millis)
     *
     * @param action
     * @return string
     */
    // *********************************************************************
    public String getRunTimeInMillis(String action) {
        return getRunTimeInMillis(action, false);
    }

    // *********************************************************************

    /**
     * default action to getElapsedTime with boolean components
     *
     * @param action
     * @return string
     */
    // *********************************************************************
    public String getElapsedTime(String action) {
        return getElapsedTime(action, false);
    }

    // *********************************************************************

    /**
     * Similar to getElapsedTime, but suppresses insignificant components
     *
     * @param action
     * @return string
     */
    // *********************************************************************
    public String getPrettyElapsedTime(String action) {
        return getElapsedTime(action, true);
    }

    // *********************************************************************

    /**
     * Similar to getFormattedRunTime, but suppresses insignificant components
     *
     * @param action
     * @return string
     */
    // *********************************************************************
    public String getElapsedTime(String action, boolean suppressInsignifcantUnits) {
        StringBuilder runTime = new StringBuilder();

        if ((startTimes.containsKey(checkValue(action)) && (endTimes.containsKey(checkValue(action))))) {
            long sTime = startTimes.get(checkValue(action));
            long eTime = endTimes.get(checkValue(action));
            long elapseTime = eTime - sTime;
            long days = 0L;
            long hrs = 0L;
            long mins = 0L;
            long secs = 0L;
            long ms;
            boolean displayUnit = !suppressInsignifcantUnits;

            if (elapseTime >= MS_IN_DAY) {
                displayUnit = true;
                days = elapseTime / MS_IN_DAY;
                elapseTime = elapseTime % MS_IN_DAY;
            }
            if (displayUnit) {
                runTime.append(days + " days ");
            }
            if (elapseTime >= MS_IN_HOUR) {
                displayUnit = true;
                hrs = elapseTime / MS_IN_HOUR;
                elapseTime = elapseTime % MS_IN_HOUR;
            }
            if (displayUnit) {
                runTime.append(hrs + " hrs ");
            }
            if (elapseTime >= MS_IN_MIN) {
                displayUnit = true;
                mins = elapseTime / MS_IN_MIN;
                elapseTime = elapseTime % MS_IN_MIN;
            }
            if (displayUnit) {
                runTime.append(mins + " mins ");
            }

            if (elapseTime >= 1000) {
                displayUnit = true;
                secs = elapseTime / 1000;
                elapseTime = elapseTime % 1000;
            }
            if (displayUnit) {
                runTime.append(secs + " secs ");
            }
            ms = elapseTime;

            runTime.append(ms + MS_UNITS);
        } else {
            runTime.append(IN_PROGRESS);
        }

        return runTime.toString();
    }

    public void reset() {
        startTimes.clear();
        endTimes.clear();
        userStore.clear();
        keyStore.clear();
    }

    private long getCurrentTimeAndDate() {
        Calendar currentCalendarDate = Calendar.getInstance();
        return currentCalendarDate.getTime().getTime();
    }

    private String checkValue(String value) {
        if (value != null) {
            return value.trim();
        } else {
            return "__DEFAULT__";
        }
    }

    // *****************************************************************

    /**
     * This is the internal tester for the TimeMetrics class. To test this class
     * just compile and run. No args are needed. ex: java TimeMetrics
     */
    // *****************************************************************
    public static void main(String[] args) throws Exception {
        // split this out so it minimizes what is not covered in junit
        System.out.println(TimeMetrics.bigTest());
    }

    // *****************************************************************

    /**
     * This is the internal tester for the TimeMetrics class. To test this class
     * just compile and run. No args are needed. ex: java TimeMetrics
     */
    // *****************************************************************
    public static String bigTest() throws Exception {
        StringBuilder sb = new StringBuilder();
        String key1 = "329468";
        String key2 = "117612";
        String user1 = "303270";
        String user2 = "48266";
        String action1 = "Action1";
        String action2 = "Action2";
        String action3 = "Action3";
        String action4 = "Action4";

        TimeMetrics timer = new TimeMetrics();

        timer.start(action1, user1, key1);

        Thread.sleep(10);

        timer.start(action2, user2, key2);

        Thread.sleep(15);

        timer.start(action3, user2, key2);

        timer.start(action4);

        timer.stop(action1);

        Thread.sleep(18);

        timer.stop(action2);

        timer.stop(action4);

        sb.append("*****************************************************\n");
        sb.append("Action 1 info :\n");
        sb.append("MetricsString : " + timer.getMetricsString(action1) + NL);
        sb.append("         User : " + timer.getUser(action1) + NL);
        sb.append("          Key : " + timer.getKey(action1) + NL);
        sb.append("   Start Time : " + timer.getStartTime(action1) + NL);
        sb.append("     End Time : " + timer.getEndTime(action1) + NL);
        sb.append("     Run Time : " + timer.getRunTimeInMillis(action1) + NL);
        sb.append("*****************************************************\n");
        sb.append("Action 2 info :\n");
        sb.append("MetricsString : " + timer.getMetricsString(action2) + NL);
        sb.append("         User : " + timer.getUser(action2) + NL);
        sb.append("          Key : " + timer.getKey(action2) + NL);
        sb.append("   Start Time : " + timer.getStartTime(action2) + NL);
        sb.append("     End Time : " + timer.getEndTime(action2) + NL);
        sb.append("     Run Time : " + timer.getRunTimeInMillis(action2) + NL);
        sb.append("*****************************************************\n");
        sb.append("Action 3 info :\n");
        sb.append("MetricsString : " + timer.getMetricsString(action3) + NL);
        sb.append("ErrorString   : " + timer.getErrorString(action3) + NL);
        sb.append("         User : " + timer.getUser(action3) + NL);
        sb.append("          Key : " + timer.getKey(action3) + NL);
        sb.append("   Start Time : " + timer.getStartTime(action3) + NL);
        sb.append("     End Time : " + timer.getEndTime(action3) + NL);
        sb.append("     Run Time : " + timer.getRunTimeInMillis(action3) + NL);
        sb.append("*****************************************************\n");
        sb.append("Action 4 info :\n");
        sb.append("MetricsString : " + timer.getMetricsString(action4) + NL);
        sb.append("         User : " + timer.getUser(action4) + NL);
        sb.append("          Key : " + timer.getKey(action4) + NL);
        sb.append("   Start Time : " + timer.getStartTime(action4) + NL);
        sb.append("     End Time : " + timer.getEndTime(action4) + NL);
        sb.append("     Run Time : " + timer.getRunTimeInMillis(action4) + NL);
        sb.append("  Pretty Time : " + timer.getPrettyElapsedTime(action4) + NL);
        sb.append("*****************************************************\n");
        return sb.toString();
    }
}
