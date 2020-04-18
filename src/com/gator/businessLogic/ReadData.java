package com.gator.businessLogic;

import com.gator.database.Database;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Wally Haven on 10/29/2019.
 */
public class ReadData {
    final JFrame frame;
    public ReadData() {
        frame = new JFrame();
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
        var db = new Database();
        db.connect();
        ArrayList<Object[]> list = db.readTableData();
        db.close();
        return list;
    }

    public ArrayList<Object[]> GetWinRate() {
        var db = new Database();
        db.connect();
        ArrayList<Object[]> list = db.FrequencyData();
        db.close();
        return list;
    }
}
