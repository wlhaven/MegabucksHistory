package com.gator;

import java.io.*;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Wally Haven on 10/29/2019.
 */
class ReadData {

    ReadData() {
    }

    String getFileInfo() {
        String fileName;

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the filename to read: ");
        fileName = in.nextLine();
        return fileName;
    }


    List getData(String fileName) {
        ArrayList<Data> results = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line);
                String date = st.nextToken(" ");
                String amount = st.nextToken(" ").replaceAll("[.$|,;'?]", "");
                int draw = Integer.parseInt(st.nextToken(" "));
                int result1 = Integer.parseInt(st.nextToken(" "));
                int result2 = Integer.parseInt(st.nextToken(" "));
                int result3 = Integer.parseInt(st.nextToken(" "));
                int result4 = Integer.parseInt(st.nextToken(" "));
                int result5 = Integer.parseInt(st.nextToken(" "));
                int result6 = Integer.parseInt(st.nextToken(" "));
                Data loadData = new Data(date, amount, draw, result1, result2, result3, result4, result5, result6);
                results.add(loadData);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open input file '" + fileName + "'" + "\nProgram will close.");
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        return results;
    }
}
