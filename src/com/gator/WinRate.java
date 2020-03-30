package com.gator;

/**
 * Created by Wally Haven on 3/18/2020.
 */
public class WinRate {
    private final String ballValue;
    private final String winCount;

    public WinRate(String ballValue, String winCount) {
        this.ballValue = ballValue;
        this.winCount = winCount;
    }

    @Override
    public String toString() {
        return "\nBall number:  " + ballValue + " \tTimes Drawn:  " + winCount;
    }
}
