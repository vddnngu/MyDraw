package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ButtonsEventsHandler extends GUIStartPanel {
    private int buttonMode = 0;

    private boolean pressed = false;

    public ButtonsEventsHandler() {
    }

    public static void onCreateButtonClick(JFrame currentFrame) {
        SwingUtilities.invokeLater(() -> {
            CustomShapesDrawingPanel DrawingPanel = new CustomShapesDrawingPanel();
        });
        //currentFrame.setVisible(false);

    }

    public static void onBackButtonClick(JFrame currentFrame) {
        currentFrame.dispose();

    }

    public static void onDeleteButtonClick() {

    }

    public static void onAcceptButtonClick() {

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

