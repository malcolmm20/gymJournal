package ui.screens;

import model.OpenWorkout;

import javax.swing.*;
import java.awt.*;

public class WorkoutRenderer extends JLabel implements ListCellRenderer<OpenWorkout> {

    @Override
    public Component getListCellRendererComponent(JList<? extends OpenWorkout> list, OpenWorkout workout, int index,
                                                     boolean isSelected, boolean cellHasFocus) {

        setText(workout.getDate().toString() + " workout");

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