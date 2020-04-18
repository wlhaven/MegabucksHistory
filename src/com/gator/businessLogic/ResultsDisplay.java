package com.gator.businessLogic;

public class ResultsDisplay {
    final int winRateColumnNumber = 3;
    final int resultsColumnNumber = 9;

    public ResultsDisplay() {
    }

    public String[] getReportColumnNames() {
        return new String[]{"Available Actions"};
    }

    public String[] getResultsColumnNames() {
        return new String[]{"Date", "Jackpot", "Draw", "Ball 1", "Ball 2", "Ball 3", "Ball 4", "Ball 5", "Ball 6"};
    }

    public String[] getWinRateColumnNames() {
        return new String[]{"Ball Position", "Ball Value", "Rate"};
    }

    public String[] getReportChoices() {
        return new String[]{
                "Load History Data into the server",
                "Get list of winning draws.",
                "Get winning number frequency from History database.",
                "Exit the program"};
    }

    public int getWinRateColumnNumber() {
        return winRateColumnNumber;
    }

    public int getResultsColumnNumber() {
        return resultsColumnNumber;
    }
}
