package ui;

import model.GymJournal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

// class for gym journal graphical user interface
// modelled after simple drawing player gui
public class GymJournalGUI extends JFrame {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 900;

    private GymJournal gj;

    public GymJournalGUI() {
        super("Gym Journal");
        addTitle();
        gj = new GymJournal();
        initializeGraphics();
        //initializeInteraction();
    }

    private void addTitle() {
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel();
        titlePanel.setSize(200, 80);
        titlePanel.setOpaque(false);
        titlePanel.setLocation(50,50);
        title.setIcon(new ImageIcon("./data/barbell.png"));
        titlePanel.add(title);
        add(titlePanel);
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where the GymJournal will operate
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        //createTools();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  initializes a DrawingMouseListener to be used in the JFrame
    //private void initializeInteraction() {
    //    DrawingMouseListener dml = new DrawingMouseListener();
    //    addMouseListener(dml);
   // }
}