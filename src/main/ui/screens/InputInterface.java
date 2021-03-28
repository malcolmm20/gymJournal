package ui.screens;

import model.GymJournal;
import ui.GymJournalGUI;

import javax.swing.*;
import java.awt.*;

// abstract class for screens with user input
public abstract class InputInterface extends JPanel {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 900;
    protected CardLayout cl;
    protected GymJournalGUI gui;
    protected GymJournal gj;
    protected JPanel southPanel;
    protected JPanel northPanel;

    public InputInterface(GymJournalGUI gui, GymJournal gj) {
        super();
        this.gui = gui;
        this.gj = gj;
        this.cl = (CardLayout)gui.getLayout();
        northPanel = new JPanel(new BorderLayout());
        southPanel = new JPanel();
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: sets up graphics for JPanel
    protected void initializeGraphics() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        add(northPanel);
        add(southPanel);
        createTools();
    }

    // MODIFIES: this
    // EFFECTS: adds return to menu button at the bottom of the screen
    protected void createTools() {
        JButton menu = new JButton("Return to Menu");
        menu.addActionListener(e -> cl.show(gui,"menu"));
        southPanel.add(menu, BorderLayout.SOUTH);
    }
}
