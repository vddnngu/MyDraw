package GUI;

import  java.awt.*;
import  java.awt.geom.*;
import  java.awt.event.*;
import  java.io.*;
import  javax.swing.*;
import  javax.swing.event.*;
import  java.awt.image.*;
import  javax.imageio.*;
import  javax.swing.filechooser.FileFilter;

public class CustomShapesDrawingPanel {
    // Режим рисования
    int  rezhim=0;
    int  xPad;
    int  xf;
    int  yf;
    int  yPad;
    int  thickness;
    boolean pressed=false;
    // текущий цвет
    Color maincolor;
    
    BufferedImage imag;
    // если мы загружаем картинку
    boolean loading=false;
    String fileName;

    CustomShapesDrawingPanel (){
        initCustomShapePanel();
        
    }

    private void initCustomShapePanel() {
        JFrame mFrame = new JFrame("Create custom shape");
         maincolor=Color.black;
        MyPanel graphPanel = new MyPanel();

        JPanel externalPanel = new JPanel();
        JPanel templateListPanel = new JPanel();

        JMenuBar menuBar = new JMenuBar();

        JToolBar toolBar = new JToolBar("Toolbar", JToolBar.VERTICAL);
        JToolBar buttomBar = new JToolBar("Buttombar", JToolBar.HORIZONTAL);

        JButton lineButton = new JButton(new ImageIcon("img/line.png"));
        JButton elipsButton = new JButton(new ImageIcon("img/elips.png"));
        JButton rectButton = new JButton(new ImageIcon("img/rect.png"));
        JButton acceptDrawingButton = new JButton("Accept");
        JButton cancelActionButton = new JButton("Cancel");
        JButton backButton = new JButton("Back");

        Font smallFontTR = new Font("Times", Font.BOLD, 12);


        /**
         * Frame initialization
         */
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setJMenuBar(menuBar);

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
        externalPanel.add(toolBar, BorderLayout.WEST);

        templateListPanel.setBackground(Color.GRAY);

        /**
         * MenuBar initialization
         */
        menuBar.setBackground(Color.lightGray);
        menuBar.setLayout(new BorderLayout());

        menuBar.add(acceptDrawingButton, BorderLayout.EAST);
        menuBar.add(buttomBar, BorderLayout.WEST);


        /**
         * ToolBar initialization
         */
        toolBar.setBackground(Color.lightGray);
        toolBar.add(lineButton);
        toolBar.add(elipsButton);
        toolBar.add(rectButton);

        buttomBar.add(backButton);
        buttomBar.add(cancelActionButton);

        /**
         * Button initialization
         */
        cancelActionButton.setBackground(Color.CYAN);
        cancelActionButton.setFont(smallFontTR);
        cancelActionButton.setIcon(new ImageIcon("img/undo-icon.png"));
        cancelActionButton.addActionListener(e -> {
            ButtonsEventsHandler.onCancelButtonClick();
        });

        acceptDrawingButton.setBackground(Color.CYAN);
        acceptDrawingButton.setFont(smallFontTR);
        acceptDrawingButton.setIcon(new ImageIcon("img/accept-icon.png"));
        acceptDrawingButton.addActionListener(e -> {
            ButtonsEventsHandler.onAcceptButtonClick();
        });

        backButton.setBackground(Color.CYAN);
        backButton.setFont(smallFontTR);
        backButton.setIcon(new ImageIcon("img/back.png"));
        backButton.addActionListener(e -> {
            ButtonsEventsHandler.onBackButtonClick(mFrame);
        });

        lineButton.setBackground(Color.CYAN);
        elipsButton.setBackground(Color.CYAN);
        rectButton.setBackground(Color.CYAN);

        lineButton.setBackground(Color.CYAN);
        lineButton.addActionListener(e -> {
            rezhim = 4;
            //ButtonsEventsHandler.onLineButtonClick();
        });

        elipsButton.setBackground(Color.CYAN);
        elipsButton.addActionListener(e -> {
            rezhim = 5;
            //ButtonsEventsHandler.onElipsButtonClick();
        });

        rectButton.setBackground(Color.CYAN);
        rectButton.addActionListener(e -> {
            rezhim = 6;
            //ButtonsEventsHandler.onRectButtonClick();
        });

        /**
         * Всё что ниже - говно код, надо пофиксить! И вообще он не правильно рисует. Временный костыль.
         */

        graphPanel.addMouseMotionListener(new  MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
                if (pressed==true)
                {
                    Graphics g = imag.getGraphics();
                    Graphics2D g2 = (Graphics2D)g;
                    // установка цвета
                    g2.setColor(maincolor);

                    xPad=e.getX();
                    yPad=e.getY();
                }
                graphPanel.repaint();
            }
        });
        graphPanel.addMouseListener(new  MouseAdapter()
        {
            public void mouseClicked(MouseEvent e) {

                Graphics g = imag.getGraphics();
                Graphics2D g2 = (Graphics2D)g;
                // установка цвета
                g2.setColor(maincolor);

                xPad=e.getX();
                yPad=e.getY();

                pressed=true;
                graphPanel.repaint();
            }
            public void mousePressed(MouseEvent e) {
                xPad=e.getX();
                yPad=e.getY();
                xf=e.getX();
                yf=e.getY();
                pressed=true;
            }
            public void mouseReleased(MouseEvent e) {

                Graphics g = imag.getGraphics();
                Graphics2D g2 = (Graphics2D)g;
                // установка цвета
                g2.setColor(maincolor);
                // Общие рассчеты для овала и прямоугольника
                int  x1=xf, x2=xPad, y1=yf, y2=yPad;
                if(xf>xPad)
                {
                    x2=xf; x1=xPad;
                }
                if(yf>yPad)
                {
                    y2=yf; y1=yPad;
                }
                switch(rezhim)
                {
                    // линия
                    case 4:
                        g.drawLine(xf, yf, e.getX(), e.getY());
                        break;
                    // круг
                    case 5:
                        g.drawOval(x1, y1, (x2-x1), (y2-y1));
                        break;
                    // прямоугольник
                    case 6:
                        g.drawRect(x1, y1, (x2-x1), (y2-y1));
                        break;
                }
                xf=0; yf=0;
                pressed=false;
                graphPanel.repaint();
            }
        });

        mFrame.addComponentListener(new  ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                // если делаем загрузку, то изменение размеров формы
                // отрабатываем в коде загрузки
                if(loading==false)
                {
                    graphPanel.setSize(mFrame.getWidth()-40, mFrame.getHeight()-80);
                    BufferedImage tempImage = new  BufferedImage(graphPanel.getWidth(), graphPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics2D d2 = (Graphics2D) tempImage.createGraphics();
                    d2.setColor(Color.white);
                    d2.fillRect(0, 0, graphPanel.getWidth(), graphPanel.getHeight());
                    tempImage.setData(imag.getRaster());
                    imag=tempImage;
                    graphPanel.repaint();
                }
                loading=false;
            }
        });
        mFrame.setVisible(true);
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