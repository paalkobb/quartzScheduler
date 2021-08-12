package com.quartzScheduler.quartzScheduler.quartz1.info;


/**
 * A class that contains information about a scheduled job
 */
public class TimerInfo {

    // how many times the job should fire
    private int totalFireCount;
    // if the job should run forever
    private boolean runForever;
    // the interval of which the job should be fired
    private long repeatInterval;
    // how long the job should wait before firing
    private long initialOffset;
    // data we would like to pass to the job
    private String callbackData;

    public int getTotalFireCount() {
        return totalFireCount;
    }

    public void setTotalFireCount(int totalFireCount) {
        this.totalFireCount = totalFireCount;
    }

    public boolean isRunForever() {
        return runForever;
    }

    public void setRunForever(boolean runForever) {
        this.runForever = runForever;
    }

    public long getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(long repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public long getInitialOffset() {
        return initialOffset;
    }

    public void setInitialOffset(long initialOffset) {
        this.initialOffset = initialOffset;
    }

    public String getCallbackData() {
        return callbackData;
    }

    public void setCallbackData(String callbackData) {
        this.callbackData = callbackData;
    }
}
