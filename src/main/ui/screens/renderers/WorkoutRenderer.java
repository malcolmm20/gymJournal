package ui.screens.renderers;

import model.OpenWorkout;

import javax.swing.*;
import java.awt.*;

// custom list cell renderer to display workout as the workout date
public class WorkoutRenderer extends JLabel implements ListCellRenderer<OpenWorkout> {

    // EFFECTS: displays workout as workout date
    @Override
    public Component getListCellRendererComponent(JList<? extends OpenWorkout> list, OpenWorkout workout, int index,
                                                     boolean isSelected, boolean cellHasFocus) {

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