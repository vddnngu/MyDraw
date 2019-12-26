package GUI;


import GUI.Screens.BaseScreen;
import GUI.Screens.DrawingShapeScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ButtonsEventsHandler extends BaseScreen {
    private int buttonMode = 0;

    private boolean pressed = false;

    public ButtonsEventsHandler() {
    }

    public static void onCreateButtonClick(JFrame currentFrame) {
        SwingUtilities.invokeLater(() -> {
            BaseScreen DrawingPanel = new DrawingShapeScreen();
        });
        //currentFrame.setVisible(false);

    }

    public static void onBackButtonClick(JFrame currentFrame) {
        currentFrame.dispose();

    }

    public static void onDeleteButtonClick() {

    }

    public static void onAcceptButtonClick(JFrame currentFrame, JPanel shapeListPanel, int buttonId) {

        //shapeListPanel.add(new JButton("Shape: " + buttonId) );
    }

    public static void onCancelButtonClick() {
        
    }

    public static void onLineButtonClick() {

    }

    public static void onElipsButtonClick() {

    }

    public static void onRectButtonClick() {

    }


}

