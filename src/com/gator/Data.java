package com.gator;

/**
 * Created by Wally Haven on 10/29/2019.
 */
class Data {
    private final String date;
    private final long   jackpot;
    private final int    draw;
    private final int    result1;
    private final int    result2;
    private final int    result3;
    private final int    result4;
    private final int    result5;
    private final int    result6;
    private final String winner;

    Data(String date, long jackpot, int draw, int result1, int result2, int result3, int result4, int result5, int result6, String winner) {
        this.date = date;
        this.jackpot = jackpot;
        this.draw = draw;
        this.result1 = result1;
        this.result2 = result2;
        this.result3 = result3;
        this.result4 = result4;
        this.result5 = result5;
        this.result6 = result6;
        this.winner = winner;
    }

    String getDate() {
        return date;
    }

    long getJackpot() {
        return jackpot;
    }

    int getDraw() {
        return draw;
    }

    int getResult1() {
        return result1;
    }

    int getResult2() {
        return result2;
    }

    int getResult3() {
        return result3;
    }

    int getResult4() {
        return result4;
    }

    int getResult5() {
        return result5;
    }

    int getResult6() {
        return result6;
    }

    String getWinner() { return winner; }

}
