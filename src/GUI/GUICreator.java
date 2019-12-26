package GUI;

import GUI.GraphicPanels.BaseGraphicBoard;
import GUI.Screens.BaseScreen;

import javax.swing.*;

public class GUICreator {
    public void GuiStart(){

    }

    GUICreator (){
        GuiStart();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            BaseScreen screen = new BaseScreen();
            screen.initStartScreen();

        });
    }
}