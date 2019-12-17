package GUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class EventsHandler extends Components{
    private int buttonMode = 0;

    private boolean pressed = false;

    public EventsHandler(){
    }

    public void handlerStarting(Components board){
        //board.initComponents();
        lineButton.addActionListener(event -> buttonMode = 1);

        elipsButton.addActionListener(event -> buttonMode = 2);

        rectButton.addActionListener(event -> buttonMode = 3);

        createCustomShape.addActionListener(event -> buttonMode = 0);

        graphPanel.addMouseMotionListener(new  MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
                if (pressed==true)
                {
                    
                    switch (buttonMode)
                    {

                        case 0:
                            CustomShapePanel cPanel = new CustomShapePanel();
                            cPanel.initCustomShapePanel();
                            break;

                        case 1:

                            break;

                        case 2:

                            break;
                    }
                }
                //graphPanel.repaint();
            }
        });
        graphPanel.addMouseListener(new  MouseAdapter()
        {
            public void mouseClicked(MouseEvent e) {
                switch (buttonMode)
                {

                    case 0:
                        CustomShapePanel cPanel = new CustomShapePanel();
                        cPanel.initCustomShapePanel();
                        break;

                    case 1:

                        break;

                    case 2:

                        break;

                    case 3:

                        graphPanel.requestFocus();
                        break;
                }

                pressed=true;
                //graphPanel.repaint();
            }
            public void mousePressed(MouseEvent e) {
                pressed=true;
            }
            public void mouseReleased(MouseEvent e) {
                pressed=false;
                //graphPanel.repaint();
            }
        });
        
    }
}
