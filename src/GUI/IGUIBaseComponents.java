package GUI;

import javax.swing.*;
import java.awt.*;

public interface IGUIBaseComponents
{
    JFrame mFrame = new JFrame("MyDraw ver 1.0");
    GUI.MyPanel graphPanel = new GUI.MyPanel();

    JPanel externalPanel = new JPanel();
    JPanel templateListPanel = new JPanel();
    JPanel shapeButtonPanel = new JPanel();

    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new  JMenu("Menu");

    JMenuItem saveBoard = new JMenuItem("Save board");
    JMenuItem loadDrawnBoard = new JMenuItem("Load board");

    // Тулбар для кнопок выбора отрисовки
    JToolBar toolBar = new JToolBar("Toolbar", JToolBar.VERTICAL);

    // Тулбар для выбора шаблона
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
}
