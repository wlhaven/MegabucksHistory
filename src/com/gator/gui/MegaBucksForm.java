package com.gator.gui;

import com.gator.businessLogic.Data;
import com.gator.businessLogic.ReadData;
import com.gator.businessLogic.ResultsDisplay;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Wally Haven on 6/18/2020.
 */
public class MegaBucksForm {
    private JPanel rootPanel;
    private JMenuBar menuBar1;
    private JMenu fileMenu;
    private JMenuItem importItem;
    private JMenuItem exitItem;
    private JMenu reportMenu;
    private JMenuItem winnerItem;
    private JMenuItem freqItem;
    private JTable resultsTable;
    private JLabel resultsLabel;
    private JScrollPane reportScrollPane;
    private JMenuItem timesDrawnItem;
    private JMenu drawReports;
    private JMenuItem freqByDrawItem;
    private JMenuItem timesDrawnByBall;
    private DefaultTableModel resultsDefaultTableModel;

    public MegaBucksForm() {
        rootPanel.setPreferredSize(new Dimension(800, 600));
        rootPanel.setAlignmentX(100);

        importItem.addActionListener(e -> {
            int rowCount = 0;
            JFrame frame = new JFrame();
            clearTables();
            reportScrollPane.setVisible(false);
            Data insertData = new Data();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(importItem);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                rowCount = insertData.InsertData(selectedFile);
                if (rowCount > 0) {
                    JOptionPane.showMessageDialog(frame, "Successfully Inserted " + rowCount + " rows into the database", "Success", JOptionPane.WARNING_MESSAGE);
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                }
            } else {
                clearTables();
                reportScrollPane.setVisible(false);
                JOptionPane.showMessageDialog(frame, "ERROR: Inserted " + rowCount + " rows into the database", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        });

        exitItem.addActionListener(e -> System.exit(0));

        winnerItem.addActionListener(e -> {
            clearTables();
            var data = new ReadData();
            ArrayList<Object[]> getList = data.WinningDraws();
            reportScrollPane.setVisible(true);
            resultsDefaultTableModel = SetupTables(resultsTable, 2);
            for (Object[] row : getList) {
                resultsDefaultTableModel.addRow(row);
            }
            resultsLabel.setVisible(true);
            resultsLabel.setText("Winning Tickets");
            resultsTable.setModel(resultsDefaultTableModel);
        });

        freqItem.addActionListener(e -> {
            var rate = new ReadData();
            ArrayList<Object[]> getRateList = rate.GetWinRate();
            SetupTables(resultsTable, 3);
            reportScrollPane.setVisible(true);
            for (Object[] row : getRateList) {
                resultsDefaultTableModel.addRow(row);
            }
            resultsLabel.setVisible(true);
            resultsLabel.setText("Frequency Results");
            resultsTable.setModel(resultsDefaultTableModel);
        });


        timesDrawnItem.addActionListener(e -> {
            var valueCount = new ReadData();
            ArrayList<Object[]> getCount = valueCount.CreateValuesCount();
            reportScrollPane.setVisible(true);
            SetupTables(resultsTable, 4);
            for (Object[] row : getCount) {
                resultsDefaultTableModel.addRow(row);
            }
            resultsLabel.setVisible(true);
            resultsLabel.setText("Total times drawn");
            resultsTable.setModel(resultsDefaultTableModel);
        });

        freqByDrawItem.addActionListener(e -> {
            JFrame frame = new JFrame();
            int position;
            String location = (String) JOptionPane.showInputDialog(
                    frame,
                    "Select a draw position from 1 to 6",
                    "Draw Position",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "1");
            position = Integer.parseInt(location);
            if (position < 1 || position > 6) {
                JOptionPane.showMessageDialog(frame, "ERROR: Draw position must be between 1 and 6", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                var rate = new ReadData();
                ArrayList<Object[]> getRateList = rate.GetWinRateByDraw(position);
                SetupTables(resultsTable, 3);
                reportScrollPane.setVisible(true);
                for (Object[] row : getRateList) {
                    resultsDefaultTableModel.addRow(row);
                }
                resultsLabel.setVisible(true);
                resultsLabel.setText("Frequency Results");
                resultsTable.setModel(resultsDefaultTableModel);
            }

        });

        timesDrawnByBall.addActionListener(e -> {
            JFrame frame = new JFrame();
            int position;
            String location = (String) JOptionPane.showInputDialog(
                    frame,
                    "Select a draw position from 1 to 6",
                    "Draw Position",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "1");
            position = Integer.parseInt(location);
            if (position < 1 || position > 6) {
                JOptionPane.showMessageDialog(frame, "ERROR: Draw position must be between 1 and 6", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                var valueCount = new ReadData();
                ArrayList<Object[]> getCount = valueCount.CreateValuesCountByDraw(position);
                reportScrollPane.setVisible(true);
                SetupTables(resultsTable, 4);
                for (Object[] row : getCount) {
                    resultsDefaultTableModel.addRow(row);
                }
                resultsLabel.setVisible(true);
                resultsLabel.setText("Total times drawn");
                resultsTable.setModel(resultsDefaultTableModel);
            }
        });
    }

    protected DefaultTableModel SetupTables(JTable resultsTable, int reportNumber) {
        ResultsDisplay resultsDisplay = new ResultsDisplay();
        resultsDefaultTableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        resultsTable.setModel(resultsDefaultTableModel);
        resultsTable.setCellSelectionEnabled(false);
        resultsTable.setFocusable(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        switch (reportNumber) {
            case 1:
            case 2:
                resultsTable.setBackground(new Color(255, 255, 255));
                resultsDefaultTableModel.setColumnIdentifiers(resultsDisplay.getResultsColumnNames());
                for (int i = 0; i < resultsDisplay.getResultsColumnNumber(); i++) {
                    resultsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }
                break;
            case 3:
                resultsTable.setBackground(new Color(207, 222, 241));
                resultsDefaultTableModel.setColumnIdentifiers(resultsDisplay.getWinRateColumnNames());
                for (int i = 0; i < resultsDisplay.getWinRateColumnNumber(); i++) {
                    resultsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }
                break;
            case 4:
                resultsTable.setBackground(new Color(198, 236, 191));
                resultsDefaultTableModel.setColumnIdentifiers(resultsDisplay.getTimesDrawnColumnNames());
                for (int i = 0; i < resultsDisplay.getTimesDrawnColumnNumber(); i++) {
                    resultsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }
                break;
        }
        return resultsDefaultTableModel;
    }

    private void clearTables() {
        DefaultTableModel model = new DefaultTableModel();
        resultsTable.setModel(model);
        resultsLabel.setText("");
    }

    public Component getRootPanel() {
        return rootPanel;
    }

}
