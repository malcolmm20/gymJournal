package ui.screens.renderers;

import model.OpenWorkout;

import javax.swing.*;
import java.awt.*;

// custom list cell renderer to display workout as the workout date
public class WorkoutRenderer extends JLabel implements ListCellRenderer<OpenWorkout> {

    // EFFECTS: displays workout as workout date, font size 20 and changes selected item colour
    @Override
    public Component getListCellRendererComponent(JList<? extends OpenWorkout> list, OpenWorkout workout, int index,
                                                     boolean isSelected, boolean cellHasFocus) {

        setOpaque(true);
        Font font = new Font(workout.getDate().toString(), Font.PLAIN, 20);
        String string = workout.getDate().toString() + " workout";
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