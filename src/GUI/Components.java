package GUI;

import javax.swing.*;
import java.awt.*;

public class Components {
    JFrame mFrame = new JFrame("MyDraw ver 1.0");

    GUI.MyPanel graphPanel = new GUI.MyPanel();

    JPanel externalPanel = new JPanel();
    JPanel templateListPanel = new JPanel();
    JPanel shapeButtonPanel = new JPanel();

    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("Menu");
    JMenuItem saveBoard = new JMenuItem("Save board");
    JMenuItem loadDrawnBoard = new JMenuItem("Load board");

    JToolBar toolBar = new JToolBar("Toolbar", JToolBar.VERTICAL);
    JToolBar templateBar = new JToolBar("template", JToolBar.VERTICAL);

    JButton lineButton = new JButton(new ImageIcon("img/line.png"));
    JButton elipsButton = new JButton(new ImageIcon("img/elips.png"));
    JButton rectButton = new JButton(new ImageIcon("img/rect.png"));
    JButton loadBoard = new JButton("Load the board");
    JButton selectCustomShape = new JButton("Select shape");
    JButton editCustomShape = new JButton("Edit shape");
    JButton createCustomShape = new JButton("Create shape");
    JButton deleteCustomShape = new JButton("Delete shape");

    JLabel templateListLabel = new JLabel("List of custom shape");

    Font bigFontTR = new Font("TimesRoman", Font.BOLD, 16);
    Font smallFontTR = new Font("Times", Font.BOLD, 12);
    Font middleFontTR = new Font("Times", Font.BOLD, 13);

    public void initComponents() {
        mFrame = new JFrame("MyDraw ver 1.0");
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setJMenuBar(menuBar);
        mFrame.setVisible(true);
        mFrame.setSize(850, 650);

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
