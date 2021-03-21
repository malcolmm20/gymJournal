package ui.tools;

import ui.MenuInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRoutineTool extends Tool {

    public AddRoutineTool(MenuInterface gui, JComponent parent) {
        super(gui, parent);

    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Add Routine");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        AddRoutineToolClickHandler clickHandler = new AddRoutineToolClickHandler();
    }

    private void addRoutineInterface() {

    }

    private class AddRoutineToolClickHandler implements ActionListener {

        // EFFECTS: calls addRoutineInterface when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            addRoutineInterface();
        }


    }
}
