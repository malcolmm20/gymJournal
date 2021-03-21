package ui.tools;

import ui.MenuInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadJournalTool extends Tool {

    public LoadJournalTool(MenuInterface gui, JComponent parent) {
        super(gui, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Load Journal");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        new LoadJournalToolClickHandler();
    }

    private class LoadJournalToolClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the PlayShape tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            loadJournal();
        }
    }

    private void loadJournal() {
        gui.loadGymJournal();
    }
}
