package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EventsHandler extends Components{
    private int drawMode = 0;

    private boolean pressed = false;

    public EventsHandler(){
    }

    public void handlerStarting(Components board){
        board.initComponents();
        lineButton.addActionListener(event -> drawMode = 1);

        elipsButton.addActionListener(event -> drawMode = 2);

        rectButton.addActionListener(event -> drawMode = 3);

        graphPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                pressed = true;
            }

            public void mouseReleased(MouseEvent e) {
                pressed = false;
            }
        });
    }
}
