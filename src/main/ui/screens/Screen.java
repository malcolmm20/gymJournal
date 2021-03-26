package ui.screens;

import model.GymJournal;
import ui.GymJournalGUI;

import javax.swing.*;
import java.awt.*;

public abstract class Screen extends JPanel {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 900;
    protected CardLayout cl;
    protected GymJournalGUI gui;
    protected GymJournal gj;

    public Screen(GymJournalGUI gui, GymJournal gj) {
        super();
        this.gui = gui;
        this.gj = gj;
        this.cl = (CardLayout)gui.getLayout();
        initializeGraphics();
    }

    protected void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createTools();
        setVisible(false);
    }

    protected void createTools() {
        JPanel toolArea = new JPanel();
        toolArea.setLayout(new GridLayout(0,1));
        toolArea.setSize(new Dimension(0, 0));
        add(toolArea, BorderLayout.SOUTH);

        JButton addRoutine = new JButton("Return to Menu");
        addRoutine.addActionListener(e -> cl.show(gui,"menu"));
        toolArea.add(addRoutine);

    }
}
