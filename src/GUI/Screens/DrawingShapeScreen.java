package GUI.Screens;

import GUI.ButtonsEventsHandler;
import GUI.GraphicPanels.BaseGraphicBoard;
import TopoGeomAPI.HotFix.Shape;
import TopoGeomAPI.HotFix.Wire;

import  java.awt.*;
import  java.awt.geom.*;
import  java.awt.event.*;
import  java.io.*;
import  javax.swing.*;
import  javax.swing.event.*;
import  java.awt.image.*;
import  javax.imageio.*;
import  javax.swing.filechooser.FileFilter;

public class DrawingShapeScreen extends BaseScreen{
    public DrawingShapeScreen (){
        initShapeScreen();
    }

    private void initShapeScreen() {
        /**
         * Frame initialization
         */
        JFrame mFrame = new JFrame();
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrame.setJMenuBar(menuBar);

        mFrame.setSize(850, 650);
        mFrame.add(externalPanel);

        /**
         * Panel initialization
         */
        MyPanel graphPanel = new MyPanel();
        //BaseGraphicBoard graphPanel = new  BaseGraphicBoard (imag);
        graphPanel.setBackground(Color.WHITE);
        graphPanel.setOpaque(true);

        externalPanel.setBackground(Color.green);
        externalPanel.setLayout(new BorderLayout());
        externalPanel.add(graphPanel, BorderLayout.CENTER);
        externalPanel.add(toolBar, BorderLayout.WEST);

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
        //buttomBar.add(cancelActionButton);

        /**
         * Button initialization
         */
        cancelActionButton.setBackground(Color.CYAN);
        cancelActionButton.setFont(smallFontTR);
        cancelActionButton.addActionListener(e -> {
            ButtonsEventsHandler.onCancelButtonClick();
        });

        JButton shapeButton = new JButton("Shape");
        shapeButton.setFont(smallFontTR);
        shapeButton.setBackground(Color.CYAN);

        acceptDrawingButton.setBackground(Color.CYAN);
        acceptDrawingButton.setFont(smallFontTR);
        acceptDrawingButton.addActionListener(e -> {
            buttonId++;
            //ButtonsEventsHandler.onAcceptButtonClick(mFrame, shapeListPanel, buttonId);
            //acceptDrawingButton.setText(String.format("Shape #%d", buttonId));
            //shapeButton.setText(String.format("Shape #%d", buttonId));
            //shapeListPanel.add(shapeButton);
        });

        backButton.setBackground(Color.CYAN);
        backButton.setFont(smallFontTR);
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
}