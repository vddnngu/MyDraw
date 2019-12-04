package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIEventsHandler extends GUIComponentsInitializer {
    private int drawMode = 0;

    private boolean pressed = false;

    public GUIEventsHandler(){
    }

    public void handlerStarting(){
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
