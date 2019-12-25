package GUI;

import javax.swing.*;

public class GUIStart {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUIStartPanel board = new GUIStartPanel();
            board.initComponents();

        });
    }
}