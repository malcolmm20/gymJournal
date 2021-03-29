package ui.screens.renderers;

import javax.swing.*;
import java.awt.*;

// custom list cell renderer to make font size larger
public class ListRenderer extends JLabel implements ListCellRenderer<String> {

    // EFFECTS: sets font to 20, colours cell when selected
    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        setOpaque(true);
        Font font = new Font(value, Font.PLAIN, 20);
        setFont(font);
        setText(value);
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
