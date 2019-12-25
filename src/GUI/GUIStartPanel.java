package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIStartPanel {
    JFrame mFrame = new JFrame("Source panel");

    JPanel graphPanel = new JPanel();

    JPanel externalPanel = new JPanel();
    JPanel templateListPanel = new JPanel();
    JPanel shapeButtonPanel = new JPanel();

    JMenuBar menuBar = new JMenuBar();

    JToolBar toolBar = new JToolBar("Toolbar", JToolBar.VERTICAL);
    JToolBar templateBar = new JToolBar("template", JToolBar.VERTICAL);
    JToolBar buttomBar = new JToolBar("Buttombar", JToolBar.HORIZONTAL);

    JButton createCustomShapeButton = new JButton("Create shape");
    JButton deleteCustomShapeButton = new JButton("Delete shape");
    JButton acceptDrawingButton = new JButton("Accept");
    JButton cancelActionButton = new JButton("Cancel");

    JLabel shapeListLabel = new JLabel("List of custom shape");

    Font bigFontTR = new Font("TimesRoman", Font.BOLD, 16);
    Font smallFontTR = new Font("Times", Font.BOLD, 12);
    Font middleFontTR = new Font("Times", Font.BOLD, 13);
    public void initComponents() {
        /*JFrame mFrame = new JFrame("Source panel");

        GUI.MyPanel graphPanel = new GUI.MyPanel();

        JPanel externalPanel = new JPanel();
        JPanel templateListPanel = new JPanel();
        JPanel shapeButtonPanel = new JPanel();

        JMenuBar menuBar = new JMenuBar();

        JToolBar toolBar = new JToolBar("Toolbar", JToolBar.VERTICAL);
        JToolBar templateBar = new JToolBar("template", JToolBar.VERTICAL);

        JButton lineButton = new JButton(new ImageIcon("img/line.png"));
        JButton elipsButton = new JButton(new ImageIcon("img/elips.png"));
        JButton rectButton = new JButton(new ImageIcon("img/rect.png"));
        JButton createCustomShapeButton = new JButton("Create shape");
        JButton deleteCustomShapeButton = new JButton("Delete shape");
        JButton acceptDrawingButton = new JButton("Accept");
        JButton cancelActionButton = new JButton("Cancel");

        JLabel shapeListLabel = new JLabel("List of custom shape");

        Font bigFontTR = new Font("TimesRoman", Font.BOLD, 16);
        Font smallFontTR = new Font("Times", Font.BOLD, 12);
        Font middleFontTR = new Font("Times", Font.BOLD, 13);*/

        /**
         * Frame initialization
         */
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setJMenuBar(menuBar);
        mFrame.setVisible(true);
        mFrame.setSize(850, 650);
        mFrame.add(externalPanel);

        /**
         * Panel initialization
         */
        graphPanel.setBackground(Color.WHITE);
        graphPanel.setOpaque(true);

        externalPanel.setBackground(Color.green);
        externalPanel.setLayout(new BorderLayout());
        externalPanel.add(graphPanel, BorderLayout.CENTER);
        //externalPanel.add(toolBar, BorderLayout.WEST);
        externalPanel.add(templateBar, BorderLayout.EAST);

        templateListPanel.setBackground(Color.GRAY);

        shapeButtonPanel.setLayout(new BorderLayout());
        shapeButtonPanel.add(createCustomShapeButton, BorderLayout.NORTH);
        shapeButtonPanel.add(deleteCustomShapeButton, BorderLayout.SOUTH);

        /**
         * MenuBar initialization
         */
        menuBar.setBackground(Color.lightGray);

        menuBar.setLayout(new BorderLayout());
        menuBar.add(buttomBar, BorderLayout.WEST);
        menuBar.add(acceptDrawingButton, BorderLayout.EAST);
        //menuBar.add(cancelActionButton, BorderLayout.WEST);

        //buttomBar.add(acceptDrawingButton);
        buttomBar.add(cancelActionButton);

        /**
         * ToolBar initialization
         */
        toolBar.setBackground(Color.lightGray);

        templateBar.setBackground(Color.lightGray);
        templateBar.setLayout(new BorderLayout());
        templateBar.add(shapeButtonPanel, BorderLayout.SOUTH);
        templateBar.add(templateListPanel, BorderLayout.CENTER);
        templateBar.add(shapeListLabel, BorderLayout.NORTH);

        /**
         * Button initialization
         */
        createCustomShapeButton.setBackground(Color.CYAN);
        createCustomShapeButton.setFont(smallFontTR);
        createCustomShapeButton.addActionListener(e -> {
           ButtonsEventsHandler.onCreateButtonClick(mFrame);
        });

        deleteCustomShapeButton.setBackground(Color.pink);
        deleteCustomShapeButton.setFont(smallFontTR);
        deleteCustomShapeButton.addActionListener(e -> {
            ButtonsEventsHandler.onDeleteButtonClick();
        });

        cancelActionButton.setBackground(Color.CYAN);
        cancelActionButton.setFont(smallFontTR);
        cancelActionButton.setIcon(new ImageIcon("src/GUI/image/undo-icon.png"));
        cancelActionButton.addActionListener(e -> {
            ButtonsEventsHandler.onCancelButtonClick();
        });

        acceptDrawingButton.setBackground(Color.CYAN);
        acceptDrawingButton.setFont(smallFontTR);
        acceptDrawingButton.setIcon(new ImageIcon("src/GUI/image/accept-icon.png"));
        acceptDrawingButton.addActionListener(e -> {
            ButtonsEventsHandler.onAcceptButtonClick();
        });

        /**
         * Label initialization
         */
        shapeListLabel.setBackground(Color.WHITE);
        shapeListLabel.setFont(bigFontTR);
        shapeListLabel.setBackground(Color.CYAN);
        shapeListLabel.setOpaque(true);

    }
}
