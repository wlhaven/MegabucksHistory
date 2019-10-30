package com.gator;

import java.util.List;

class Main {

    public static void main(String[] args) {
        // write your code here
        int count = 0;
        var readData = new ReadData();
        String filename = readData.getFileInfo();
        List<Data> resultsList= readData.getData(filename);
        var db = new Database();
        db.connect();
        for (Data results : resultsList) {
            db.insertResults(results.getDate(), results.getAmount(), results.getDraw(), results.getResult1(), results.getResult2(),
                    results.getResult3(), results.getResult4(), results.getResult5(), results.getResult6());
            count++;
        }
        System.out.println("number of rows inserted = " + count);
        db.close();
    }
}
