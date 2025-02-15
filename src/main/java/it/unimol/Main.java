package it.unimol;

import it.unimol.ui.MainPanel;

public class Main {
    public static void main(String[] args) {
        MainPanel mainPanel = MainPanel.getInstance();
        mainPanel.start();
    }
}