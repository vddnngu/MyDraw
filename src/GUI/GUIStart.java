package GUI;

import javax.swing.*;

public class GUIStart {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Components board = new Components();
            board.initComponents();
        });
    }
}