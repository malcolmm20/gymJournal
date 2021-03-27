package ui.screens.renderers;

import javax.swing.*;
import java.awt.*;

// custom list cell renderer to make font size larger
public class ListRenderer extends DefaultListCellRenderer {

    // EFFECTS: sets font to 20
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel)super.getListCellRendererComponent(list, value,
                index, isSelected, cellHasFocus);
        Font font = new Font(value, Font.PLAIN, 20);
        setFont(font);
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return label;
    }
}
