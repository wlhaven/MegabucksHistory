package com.gator;

class Main {

    public static void main(String[] args) {
        int count = 0;
        var readData = new ReadData();
        String filename = readData.getFileInfo();
        var resultsList = readData.getData(filename);
        var db = new Database();
        db.connect();
        for (Data results : resultsList) {
            db.insertResults(results.getDate(), results.getJackpot(), results.getDraw(), results.getResult1(),
                    results.getResult2(), results.getResult3(), results.getResult4(), results.getResult5(),
                    results.getResult6(), results.getWinner());
            count++;
        }
        System.out.println("number of rows inserted = " + count);
        db.close();
    }
}
