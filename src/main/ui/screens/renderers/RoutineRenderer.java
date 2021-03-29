package ui.screens.renderers;

import model.Routine;

import javax.swing.*;
import java.awt.*;

// displays routine as routine name in a routine JList
public class RoutineRenderer extends JLabel implements ListCellRenderer<Routine> {

    // EFFECTS: sets font size to 20, makes list display routine name + routine, changes selected item colour
    @Override
    public Component getListCellRendererComponent(JList<? extends Routine> list, Routine value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        setOpaque(true);
        Font font = new Font(value.getName(), Font.PLAIN, 20);
        String string = value.getName() + " routine";
        setText(string);
        setFont(font);
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }
}
