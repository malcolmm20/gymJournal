package ui.tools;

import ui.MenuInterface;

import javax.swing.*;
import java.awt.event.MouseEvent;

public abstract class Tool {

    protected JButton button;
    protected MenuInterface gui;

    public Tool(MenuInterface gui, JComponent parent) {
        this.gui = gui;
        createButton(parent);
        addToParent(parent);
        addListener();
    }

    // MODIFIES: this
    // EFFECTS:  customizes the button used for this tool
    protected JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        return button;
    }

    // EFFECTS: creates button to activate tool
    protected abstract void createButton(JComponent parent);

    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }

    // EFFECTS: default behaviour does nothing
    public void mouseClickedInDrawingArea(MouseEvent e) {}


}
