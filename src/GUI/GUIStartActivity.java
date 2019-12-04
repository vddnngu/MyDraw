package GUI;

import javax.swing.*;

public class GUIStartActivity {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUIComponentsInitializer());
    }
}