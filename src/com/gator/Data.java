package com.gator;

/**
 * Created by Wally Haven on 10/29/2019.
 */
class Data {
    private final String date;
    private final String amount;
    private final int  draw;
    private final int  result1;
    private final int  result2;
    private final int  result3;
    private final int  result4;
    private final int  result5;
    private final int  result6;

    Data(String date, String amount, int draw, int result1, int result2, int result3, int result4, int result5, int result6) {
        this.date = date;
        this.amount = amount;
        this.draw = draw;
        this.result1 = result1;
        this.result2 = result2;
        this.result3 = result3;
        this.result4 = result4;
        this.result5 = result5;
        this.result6 = result6;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

    public int getDraw() {
        return draw;
    }

    public int getResult1() {
        return result1;
    }

    public int getResult2() {
        return result2;
    }

    public int getResult3() {
        return result3;
    }

    public int getResult4() {
        return result4;
    }

    public int getResult5() {
        return result5;
    }

    public int getResult6() {
        return result6;
    }

}
