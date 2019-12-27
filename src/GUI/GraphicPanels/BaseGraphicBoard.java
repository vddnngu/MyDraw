package GUI.GraphicPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class BaseGraphicBoard extends JPanel implements GUI.IBoard  {
    private BufferedImage imag;

    public BaseGraphicBoard () { }

    public BaseGraphicBoard(BufferedImage imag)
    {
        this.imag = imag;
    }

    public void paintComponent (Graphics g)
    {
        if(imag==null)
        {
            imag = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D d2 = (Graphics2D) imag.createGraphics();
            d2.setColor(Color.white);
            d2.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        super.paintComponent(g);
        g.drawImage(imag, 0, 0,this);
    }

    public void refreshBoard(){

    }
    public void confirmChangeBoard(){

    }
}
