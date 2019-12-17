package GUI;

import javax.swing.*;
import java.awt.*;

public class CustomShapePanel {
    void initCustomShapePanel() {
        JFrame shapeFrame = new JFrame("MyDraw ver 1.0");

        GUI.MyPanel graphShapePanel = new GUI.MyPanel();

        JPanel externalPanel = new JPanel();
        JPanel templateListPanel = new JPanel();
        JPanel shapeButtonPanel = new JPanel();
        

        JToolBar toolBar = new JToolBar("Toolbar", JToolBar.VERTICAL);
        JToolBar templateBar = new JToolBar("template", JToolBar.VERTICAL);

        JButton lineButton = new JButton(new ImageIcon("img/line.png"));
        JButton elipsButton = new JButton(new ImageIcon("img/elips.png"));
        JButton rectButton = new JButton(new ImageIcon("img/rect.png"));
       

        Font bigFontTR = new Font("TimesRoman", Font.BOLD, 16);
        Font smallFontTR = new Font("Times", Font.BOLD, 12);
        Font middleFontTR = new Font("Times", Font.BOLD, 13);


        
        shapeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        shapeFrame.setVisible(true);
        shapeFrame.setSize(650, 550);

            // Set layouts
            externalPanel.setLayout(new BorderLayout());
            templateBar.setLayout(new BorderLayout());
            shapeButtonPanel.setLayout(new BorderLayout());

            // Set backgrounds
            externalPanel.setBackground(Color.green);
            graphShapePanel.setBackground(Color.WHITE);
            templateListPanel.setBackground(Color.GRAY);

            toolBar.setBackground(Color.lightGray);
            templateBar.setBackground(Color.lightGray);

            graphShapePanel.setOpaque(true);

            // Add to the right place
            shapeFrame.add(externalPanel);

            externalPanel.add(graphShapePanel, BorderLayout.CENTER);
            externalPanel.add(toolBar, BorderLayout.WEST);
            externalPanel.add(templateBar, BorderLayout.EAST);

            templateBar.add(shapeButtonPanel, BorderLayout.SOUTH);
            templateBar.add(templateListPanel, BorderLayout.CENTER);

            toolBar.add(lineButton);
            toolBar.add(elipsButton);
            toolBar.add(rectButton);

    }
}
