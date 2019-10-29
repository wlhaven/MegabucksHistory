package com.gator;

/**
 * Created by Wally Haven on 10/29/2019.
 */
class Data {
    private String date;
    private long amount;
    private int  result1;
    private int  result2;
    private int  result3;

    private int  result4;
    private int  result5;
    private int  result6;

    Data() {}

    Data(String date, long amount, int result1, int result2, int result3, int result4, int result5, int result6) {
        this.date = date;
        this.amount = amount;
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

    public long getAmount() {
        return amount;
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

    public void setDate(String date) {
        this.date = date;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setResult1(int result1) {
        this.result1 = result1;
    }

    public void setResult2(int result2) {
        this.result2 = result2;
    }

    public void setResult3(int result3) {
        this.result3 = result3;
    }

    public void setResult4(int result4) {
        this.result4 = result4;
    }

    public void setResult5(int result5) {
        this.result5 = result5;
    }

    public void setResult6(int result6) {
        this.result6 = result6;
    }
}
