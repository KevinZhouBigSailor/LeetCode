package Google;

import java.util.HashMap;

public class LoggerRateLimiter_359 {
    private HashMap<String, Integer> msgDict;

    /**
     * Initialize your data structure here.
     */
    public LoggerRateLimiter_359() {
        msgDict = new HashMap<String, Integer>();
    }

    /**
     * Returns true if the message should be printed in the given timestamp, otherwise returns false.
     */
    public boolean shouldPrintMessage(int timestamp, String message) {
        synchronized (this) {
            if (!this.msgDict.containsKey(message)) {
                this.msgDict.put(message, timestamp);
                return true;
            }

            Integer oldTimestamp = this.msgDict.get(message);
            if (timestamp - oldTimestamp >= 10) {
                this.msgDict.put(message, timestamp);
                return true;
            }
        }
        return false;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
