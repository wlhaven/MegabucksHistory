package com.gator;

class Main {

    public static void main(String[] args) {
        var totalRows = 0;
        var readData = new ReadData();
        String filename = readData.getFileInfo();
        var resultsList = readData.getData(filename);
        var db = new Database();
        db.connect();
        totalRows = db.SendData(resultsList);
        System.out.println("Number of rows inserted = " + totalRows );
        db.close();
    }
}
