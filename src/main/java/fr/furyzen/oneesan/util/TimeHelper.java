package fr.furyzen.oneesan.util;

//i actually have no idea where this pasted class is from.
public class TimeHelper {
    private long startTime;

    public TimeHelper(long time) {
        this.startTime = time;
    }

    public TimeHelper() {
        this.reset();
    }

    public void reset() {
        this.startTime = System.currentTimeMillis();
    }

    public long getPassed() {
        return System.currentTimeMillis() - this.startTime;
    }
    public boolean hasPassed(long toPass) {
        return this.getPassed() >= toPass;
    }

    public boolean hasPassed(long toPass, boolean reset) {
        boolean passed = this.getPassed() >= toPass;
        if (passed && reset) reset();
        return passed;
    }
}