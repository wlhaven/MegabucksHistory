package com.gator;

import java.util.*;

/**
 * Created by Wally Haven on 3/15/2020.
 */
public class MainUI {
    private Scanner scanner;

    MainUI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Menu providing various queries for the use to run.
     */
    void Menu() {
        String choice = null;

        while (!Objects.equals(choice, "4")) {
            System.out.println("\t\tMEGABUCKS DATABASE QUERY PROGRAM:");
            System.out.println("\nPlease choose an option from the choices below: ");
            System.out.println("\t1. Import lottery data from Oregon Lottery into History database.");
            System.out.println("\t2. Get list of winning draws.");
            System.out.println("\t3. Get winning number frequency from History database.");
            System.out.println("\t4. Quit ");
            switch (choice = getUserString()) {
                case "1":
                    InsertData();
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;
                case "2":
                    WinningDraws();
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;
                case "3":
                    GetWinRate();
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;
                case "4":
                    System.out.println("Goodbye and have a wonderful day!");
                    return;
                default:
                    System.out.println("Unknown value. Please try again.");
            }
        }
    }

    private void InsertData() {
        var totalRows = 0;
        var readData = new ReadData();
        String filename = readData.getFileInfo();
        var resultsList = readData.getData(filename);
        if (resultsList.size() != 0) {
            var db = new Database();
            db.connect();
            totalRows = db.SendData(resultsList);
            System.out.println("Number of rows inserted = " + totalRows);
            db.close();
        }
    }

    private void GetWinRate() {
        var db = new Database();
        db.connect();
        var map = db.FrequencyData();
        for (var entry : map.entrySet()) {
            System.out.println("Draw " + entry.getKey() + ":" + entry.getValue().toString().replaceAll("(^\\[|]|$)", ""));
            System.out.println();
        }
        db.close();
    }


    private void WinningDraws() {
        var db = new Database();
        db.connect();
        var list = db.getWinners();

        String format = String.format("%s \t\t%-10s\t%-6s\t   %6s\t%6s\t%6s\t%6s\t%6s\t%6s", "Date", "Jackpot",
                "Draw", " Ball 1", "Ball 2", "Ball 3", "Ball 4", "Ball 5", "Ball 6");
        System.out.println(format);

        for (var entry : list) {
            System.out.println(entry);
            System.out.println();
        }
        db.close();
    }

    /**
     * Gets a string from the user
     *
     * @return String value to uppercase
     */
    private String getUserString() {
        String inputLine;
        scanner = new Scanner(System.in);

        System.out.print("> ");     // print prompt
        inputLine = scanner.nextLine();
        return inputLine;
    }
}
