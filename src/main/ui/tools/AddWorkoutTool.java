package ui.tools;

import ui.MenuInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWorkoutTool extends Tool {

    public AddWorkoutTool(MenuInterface gui, JComponent parent) {
        super(gui, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Add Workout");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        new AddWorkoutToolClickHandler();
    }

    private void addWorkoutInterface() {
    }

    private class AddWorkoutToolClickHandler implements ActionListener {

        // EFFECTS: calls addWorkoutInterface when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            addWorkoutInterface();
        }
    }


}
