package ui.tools;

import ui.MenuInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveJournalTool extends Tool {

    public SaveJournalTool(MenuInterface gui, JComponent parent) {
        super(gui, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save Journal");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        new SaveJournalToolClickHandler();
    }

    private class SaveJournalToolClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the PlayShape tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            saveJournal();
        }
    }

    private void saveJournal() {
        gui.saveGymJournal();
    }
}
