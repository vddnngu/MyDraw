package GUI.Screens;

import GUI.ButtonsEventsHandler;
import GUI.GraphicPanels.BaseGraphicBoard;
import GUI.GraphicPanels.CustomShapePanel;
import GUI.GraphicPanels.ShapeListPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class BaseScreen {
    // Режим рисования
    int  rezhim=0;
    int  xPad;
    int  xf;
    int  yf;
    int  yPad;
    int  thickness;
    boolean pressed=false;
    int buttonId = 0;
    // текущий цвет
    Color maincolor = Color.BLACK;

    BufferedImage imag;
    // если мы загружаем картинку
    boolean loading=false;
    String fileName;

    JFrame startFrame = new JFrame("Base screen");
    JFrame secondFrame = new JFrame("Create custom shape screen");

    JPanel baseGraphicPanel = new BaseGraphicBoard(imag);

    //СustomShapeGraphPanel customShapePanel = new СustomShapeGraphPanel();

    JPanel shapeListPanel = new JPanel();
    
    JPanel externalPanel = new JPanel();
    JPanel shapeButtonPanel = new JPanel();

    JMenuBar menuBar = new JMenuBar();

    JToolBar toolBar = new JToolBar("Toolbar", JToolBar.VERTICAL);
    JToolBar templateBar = new JToolBar("template", JToolBar.VERTICAL);
    JToolBar buttomBar = new JToolBar("Buttombar", JToolBar.HORIZONTAL);

    JButton createCustomShapeButton = new JButton("Create shape");
    JButton deleteCustomShapeButton = new JButton("Delete shape");
    JButton acceptDrawingButton = new JButton("Accept" ,
            new ImageIcon("src/GUI/image/accept-icon.png"));
    JButton cancelActionButton = new JButton("Cancel",
            new ImageIcon("src/GUI/image/undo-icon.png"));
    JButton refreshBoardButton = new JButton("Refresh",
            new ImageIcon("src/GUI/image/refresh.png"));
    JButton backButton = new JButton("Back",
            new ImageIcon("src/GUI/image/back.png"));


    JButton lineButton = new JButton(new ImageIcon("src/GUI/image/line.png"));
    JButton elipsButton = new JButton(new ImageIcon("src/GUI/image/elips.png"));
    JButton rectButton = new JButton(new ImageIcon("src/GUI/image/rect.png"));

    JLabel shapeListLabel = new JLabel("List of custom shape");

    Font bigFontTR = new Font("TimesRoman", Font.BOLD, 16);
    Font smallFontTR = new Font("Times", Font.BOLD, 12);
    Font middleFontTR = new Font("Times", Font.BOLD, 13);

    public void initStartScreen() {
        /**
         * Frame initialization
         */
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setJMenuBar(menuBar);
        startFrame.setVisible(true);
        startFrame.setSize(850, 650);
        startFrame.add(externalPanel);

        /**
         * Panel initialization
         */
        baseGraphicPanel.setBackground(Color.WHITE);
        baseGraphicPanel.setOpaque(true);

        externalPanel.setBackground(Color.green);
        externalPanel.setLayout(new BorderLayout());
        externalPanel.add(baseGraphicPanel, BorderLayout.CENTER);
        //externalPanel.add(toolBar, BorderLayout.WEST);
        externalPanel.add(templateBar, BorderLayout.EAST);

        shapeListPanel.setBackground(Color.GRAY);
        shapeListPanel.setLayout(new FlowLayout());

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
        buttomBar.add(refreshBoardButton);

        /**
         * ToolBar initialization
         */
        toolBar.setBackground(Color.lightGray);

        templateBar.setBackground(Color.lightGray);
        templateBar.setLayout(new BorderLayout());
        templateBar.add(shapeButtonPanel, BorderLayout.SOUTH);
        templateBar.add(shapeListPanel, BorderLayout.CENTER);
        templateBar.add(shapeListLabel, BorderLayout.NORTH);

        /**
         * Button initialization
         */
        createCustomShapeButton.setBackground(Color.CYAN);
        createCustomShapeButton.setFont(smallFontTR);
        createCustomShapeButton.addActionListener(e -> {
           ButtonsEventsHandler.onCreateButtonClick(startFrame);
        });

        deleteCustomShapeButton.setBackground(Color.pink);
        deleteCustomShapeButton.setFont(smallFontTR);
        deleteCustomShapeButton.addActionListener(e -> {
            ButtonsEventsHandler.onDeleteButtonClick();
        });

        cancelActionButton.setBackground(Color.CYAN);
        cancelActionButton.setFont(smallFontTR);
        cancelActionButton.addActionListener(e -> {
        });


        refreshBoardButton.setBackground(Color.CYAN);
        refreshBoardButton.setFont(smallFontTR);
        refreshBoardButton.addActionListener(e -> {
            ButtonsEventsHandler.onCancelButtonClick();
        });

        acceptDrawingButton.setBackground(Color.CYAN);
        acceptDrawingButton.setFont(smallFontTR);
        acceptDrawingButton.addActionListener(e -> {
            //ButtonsEventsHandler.onAcceptButtonClick();
        });

        /**
         * Label initialization
         */
        shapeListLabel.setBackground(Color.WHITE);
        shapeListLabel.setFont(bigFontTR);
        shapeListLabel.setBackground(Color.CYAN);
        shapeListLabel.setOpaque(true);
    }

    class MyPanel extends JPanel
    {
        public MyPanel()
        { }
        public void paintComponent (Graphics g)
        {
            if(imag==null)
            {
                imag = new  BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D d2 = (Graphics2D) imag.createGraphics();
                d2.setColor(Color.white);
                d2.fillRect(0, 0, this.getWidth(), this.getHeight());
            }
            super.paintComponent(g);
            g.drawImage(imag, 0, 0,this);
        }
    }

}
