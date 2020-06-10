package com.gator.businessLogic;

import com.gator.database.Database;

import javax.swing.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Wally Haven on 10/29/2019.
 */
public class ReadData {
    final JFrame frame;
    final Database db;

    public ReadData() {
        frame = new JFrame();
        db = new Database();
        db.connect();
    }

    public ArrayList<Data> getData(File fileName) {
        ArrayList<Data> results = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    StringTokenizer st = new StringTokenizer(line);
                    String date = st.nextToken(" ");
                    long jackpot = Long.parseLong(st.nextToken(" ").replaceAll("[.$|,;'?]", ""));
                    int draw = Integer.parseInt(st.nextToken(" "));
                    int result1 = Integer.parseInt(st.nextToken(" "));
                    int result2 = Integer.parseInt(st.nextToken(" "));
                    int result3 = Integer.parseInt(st.nextToken(" "));
                    int result4 = Integer.parseInt(st.nextToken(" "));
                    int result5 = Integer.parseInt(st.nextToken(" "));
                    int result6 = Integer.parseInt(st.nextToken(" "));
                    String winner = st.nextToken(" ").toUpperCase();
                    Data loadData = new Data(date, jackpot, draw, result1, result2, result3, result4, result5,
                            result6, winner);
                    results.add(loadData);
                } catch (NoSuchElementException | NumberFormatException ex) {
                    ex.printStackTrace();
                    System.out.println("Exception parsing data: " + line);
                    JOptionPane.showMessageDialog(frame, "ERROR: NoSuchElementException - value is:  " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(frame, "ERROR: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Unable to open input file '" + fileName + "'" + "\nReturning to main menu.");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        return results;
    }

    public ArrayList<Object[]> WinningDraws() {
        ArrayList<Object[]> list = db.readTableData();
        db.close();
        return list;
    }

    public ArrayList<Object[]> GetWinRate() {
        ArrayList<Object[]> list = db.FrequencyData();
        db.close();
        return list;
    }

    public int GetTotalRows() {
        int count = db.getTableCount();
        db.close();
        return count;
    }

    public Map<Integer, ArrayList<Object[]>> GetValuesCount() {
        int ballCount;
        int ballValue;
        String ballColumn;
        HashMap<Integer, ArrayList<Object[]>> tmp = new HashMap<>() {
        };
        ArrayList<Object[]> list = new ArrayList<>();

        for (ballCount = 1; ballCount < 7; ballCount++) {
            ballColumn = "Ball" + ballCount;
            for (ballValue = 1; ballValue <= 48; ballValue++) {
                list.addAll(db.getRowValues(ballColumn, ballValue));
            }
            var tmpList = new ArrayList<>(list);
            tmp.put(ballCount, tmpList);
            list.clear();
        }
        db.close();
        return tmp;
    }

    public ArrayList<Object[]> CreateValuesCount() {
        ArrayList<Object[]> testData = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("0.00");
        int getTotalRows = GetTotalRows();
        var rows = new ReadData();
        var map = rows.GetValuesCount();
        for (var entry : map.entrySet()) {
            for (int ballNumber = 0; ballNumber < 48; ballNumber++) {
                Object[] tableRow = {entry.getKey(), (ballNumber + 1),
                        Integer.parseInt(Arrays.toString(entry.getValue().get(ballNumber)).replaceAll("(^\\[|]|$)", "")),
                        df.format(Float.parseFloat(Arrays.toString(entry.getValue().get(ballNumber)).replaceAll("(^\\[|]|$)", ""))
                                / getTotalRows * 100)};
                testData.add(tableRow);
            }
            System.out.println();
        }
        return testData;
    }
}
