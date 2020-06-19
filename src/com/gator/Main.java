package com.gator;

import com.gator.gui.MegaBucksForm;

import javax.swing.*;

import static javax.swing.SwingUtilities.invokeLater;

public class Main extends JFrame {
    private static JFrame uFrame = null;

    public static void createAndShowGUI() {
        uFrame = new JFrame();
        uFrame.setDefaultCloseOperation(uFrame.EXIT_ON_CLOSE);
        showReports();
    }

    public static void showReports() {
        uFrame.setTitle("Megabucks Reporting");
        uFrame.getContentPane().removeAll();
        uFrame.getContentPane().add(new MegaBucksForm().getRootPanel());
        uFrame.setResizable(true);
        uFrame.pack();
        uFrame.setLocationRelativeTo(null);
        uFrame.setVisible(true);
    }

    public static void main(String[] args) {
        invokeLater(Main::createAndShowGUI);
    }
}
