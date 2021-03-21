package ui.tools;

import ui.MenuInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckOneRepMaxTool extends Tool {

    public CheckOneRepMaxTool(MenuInterface gui, JComponent parent) {
        super(gui, parent);
    }

    private void oneRepMaxInterface() {
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Check One Rep Maxes");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        new CheckOneRepMaxToolClickHandler();
    }

    private class CheckOneRepMaxToolClickHandler implements ActionListener {

        // EFFECTS: calls oneRepMaxInterface when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            oneRepMaxInterface();
        }
    }


}
