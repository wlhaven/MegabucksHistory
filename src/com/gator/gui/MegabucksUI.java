package com.gator.gui;

import com.gator.businessLogic.ReadData;
import com.gator.businessLogic.ResultsDisplay;
import com.gator.database.Database;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Wally Haven on 3/30/2020.
 */
public class MegabucksUI extends Component {
    private JPanel rootPanel;
    private JPanel tablePanel;
    private JPanel titlePanel;
    private JLabel formTitle;
    private JPanel reportPanel;
    private JLabel reportLabel;
    private JComboBox<String> reportComboBox;
    private JPanel displayPanel;
    private JScrollPane reportScrollPane;
    private JTable resultsTable;
    private JLabel resultsLabel;
    int reportID;
    private DefaultTableModel summaryDefaultTableModel;

    public MegabucksUI() {
        rootPanel.setPreferredSize(new Dimension(640, 480));
        rootPanel.setAlignmentX(100);
        SetupComboBox();
        reportComboBox.addActionListener(e -> {
            JComboBox<Object> reportSelect = (JComboBox<Object>) e.getSource();
            reportID = reportSelect.getSelectedIndex();
            int rowCount = 0;
            JFrame frame = new JFrame();
            switch (reportID) {
                case 1:
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    int result = fileChooser.showOpenDialog(this);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        rowCount = InsertData(selectedFile);
                        if (rowCount > 0) {
                            JOptionPane.showMessageDialog(frame, "Successfully Inserted " + rowCount + " rows into the database", "Success", JOptionPane.WARNING_MESSAGE);
                            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "ERROR: Inserted " + rowCount + " rows into the database", "Alert", JOptionPane.WARNING_MESSAGE);
                        DefaultTableModel model = new DefaultTableModel();
                        resultsTable.setModel(model);
                        resultsLabel.setText("");
                    }
                    break;
                case 2:
                    var data = new ReadData();
                    ArrayList<Object[]> getList = data.WinningDraws();
                    SetupTables(resultsTable, reportID);
                    for (Object[] row : getList) {
                        summaryDefaultTableModel.addRow(row);
                    }
                    resultsLabel.setText("Winning Ticket Report");
                    resultsTable.setModel(summaryDefaultTableModel);
                    break;
                case 3:
                    var rate = new ReadData();
                    ArrayList<Object[]> getRateList = rate.GetWinRate();
                    SetupTables(resultsTable, reportID);
                    for (Object[] row : getRateList) {
                        summaryDefaultTableModel.addRow(row);
                    }
                    resultsLabel.setText("Frequency Results");
                    resultsTable.setModel(summaryDefaultTableModel);
                    break;
                case 4:
                    System.out.println("Goodbye and have a wonderful day!");
                    JOptionPane.showMessageDialog(frame, "Goodbye and have a wonderful day!", "Alert", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                default:
                    JOptionPane.showMessageDialog(frame, "Unknown value. Please try again.", "Alert", JOptionPane.WARNING_MESSAGE);
                    System.out.println("Unknown value. Please try again.");
                    DefaultTableModel model = new DefaultTableModel();
                    resultsTable.setModel(model);
                    resultsLabel.setText("");
            }
        });

        reportComboBox.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                reportComboBox.requestFocusInWindow();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }
        });
    }

    public int InsertData(File filename) {
        var totalRows = 0;
        var readData = new ReadData();
        var resultsList = readData.getData(filename);
        if (resultsList.size() != 0) {
            var db = new Database();
            db.connect();
            totalRows = db.SendData(resultsList);
            System.out.println("Number of rows inserted = " + totalRows);
            db.close();
        }
        return totalRows;
    }

    private void SetupComboBox() {
        ResultsDisplay resultsDisplay = new ResultsDisplay();

        DefaultTableModel myDefaultTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        myDefaultTableModel.setColumnIdentifiers(resultsDisplay.getReportColumnNames());
        String[] reportRows = resultsDisplay.getReportChoices();

        reportComboBox.setBackground(new Color(255, 255, 255));
        Object child = reportComboBox.getAccessibleContext().getAccessibleChild(0);
        ((BasicComboPopup) child).getList().setSelectionBackground(new Color(151, 255, 47));
        // Need to prevent selection when using arrow keys
        reportComboBox.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);

        reportComboBox.addItem("<html><i>-- Select Report --</i>");
        for (String rows : reportRows) {
            reportComboBox.addItem(rows);
        }
    }

    private void SetupTables(JTable resultsTable, int reportNumber) {
        ResultsDisplay resultsDisplay = new ResultsDisplay();
        summaryDefaultTableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        resultsTable.setModel(summaryDefaultTableModel);
        resultsTable.setCellSelectionEnabled(false);
        resultsTable.setFocusable(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        switch (reportNumber) {
            case 1:
            case 2:
                summaryDefaultTableModel.setColumnIdentifiers(resultsDisplay.getResultsColumnNames());
                for (int i = 0; i < resultsDisplay.getResultsColumnNumber(); i++) {
                    resultsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }
                break;
            case 3:
                summaryDefaultTableModel.setColumnIdentifiers(resultsDisplay.getWinRateColumnNames());
                for (int i = 0; i < resultsDisplay.getWinRateColumnNumber(); i++) {
                    resultsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }
                break;
        }
    }

    /**
     * Returns rootPanel
     *
     * @return rootPanel
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }

}
