package GUI;

import javax.swing.*;
import java.awt.*;

public class GUIComponentsInitializer implements IGUIBaseComponents {
    public GUIComponentsInitializer() {
        createGUIComponents();
    }

    public void createGUIComponents() {
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setJMenuBar(menuBar);
        mFrame.setVisible(true);

        // Set size
        mFrame.setSize(650, 650);

        // Set layouts
        externalPanel.setLayout(new BorderLayout());
        templateBar.setLayout(new BorderLayout());
        shapeButtonPanel.setLayout(new BorderLayout());

        // Set backgrounds
        externalPanel.setBackground(Color.green);
        graphPanel.setBackground(Color.WHITE);
        templateListPanel.setBackground(Color.GRAY);
        selectCustomShape.setBackground(Color.CYAN);
        templateListLabel.setBackground(Color.WHITE);
        menuBar.setBackground(Color.lightGray);
        fileMenu.setBackground(Color.WHITE);
        toolBar.setBackground(Color.lightGray);
        templateBar.setBackground(Color.lightGray);
        editCustomShape.setBackground(Color.CYAN);
        createCustomShape.setBackground(Color.CYAN);
        deleteCustomShape.setBackground(Color.pink);

        // Set font
        fileMenu.setFont(bigFontTR);
        templateListLabel.setFont(middleFontTR);
        selectCustomShape.setFont(smallFontTR);
        editCustomShape.setFont(smallFontTR);
        createCustomShape.setFont(smallFontTR);
        deleteCustomShape.setFont(smallFontTR);

        // Remove transparency
        templateListLabel.setOpaque(true);
        graphPanel.setOpaque(true);
        fileMenu.setOpaque(true);

        // Add to the right place
        mFrame.add(externalPanel);
        menuBar.add(fileMenu);
        fileMenu.add(saveBoard);
        fileMenu.add(loadDrawnBoard);

        externalPanel.add(graphPanel, BorderLayout.CENTER);
        externalPanel.add(toolBar, BorderLayout.WEST);
        externalPanel.add(templateBar, BorderLayout.EAST);

        templateBar.add(shapeButtonPanel, BorderLayout.SOUTH);
        templateBar.add(templateListPanel, BorderLayout.CENTER);
        templateBar.add(templateListLabel, BorderLayout.NORTH);

        shapeButtonPanel.add(createCustomShape, BorderLayout.NORTH);
        shapeButtonPanel.add(selectCustomShape, BorderLayout.WEST);
        shapeButtonPanel.add(editCustomShape, BorderLayout.EAST);
        shapeButtonPanel.add(deleteCustomShape, BorderLayout.SOUTH);

        toolBar.add(lineButton);
        toolBar.add(elipsButton);
        toolBar.add(rectButton);
    }
}
