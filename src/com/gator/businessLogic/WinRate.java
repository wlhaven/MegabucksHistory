package com.gator.businessLogic;

import com.gator.database.Database;

import java.util.ArrayList;

/**
 * Created by Wally Haven on 3/18/2020.
 */
public class WinRate {

    public WinRate() {
    }

    public ArrayList<Object[]> GetWinRate() {
        var db = new Database();
        db.connect();
        ArrayList<Object[]> map = db.FrequencyData();
        db.close();
        return map;
    }

    @Override
    public String toString() {
        String ballValue = "";
        String winCount = "";
        return "\nBall number:  " + ballValue + " \tTimes Drawn:  " + winCount;
    }
}
