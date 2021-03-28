package ui.screens;

import model.GymJournal;
import model.Routine;
import ui.GymJournalGUI;
import ui.screens.renderers.RoutineRenderer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

// jpanel that displays list of past workouts
public class SelectRoutine extends Screen {
    private JLabel text;
    private JList list;

    public SelectRoutine(GymJournalGUI gui, GymJournal gj) {
        super(gui, gj);
        makeList();
        makeScrollPane();
        makeJLabel();
    }

    private void makeScrollPane() {
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
        add(listScroller, BorderLayout.WEST);
    }

    @Override
    public void createTools() {
        JPanel toolArea = new JPanel();
        toolArea.setLayout(new GridLayout(0,1));
        toolArea.setSize(new Dimension(0, 0));
        add(toolArea, BorderLayout.SOUTH);

        JButton selectRoutine = new JButton("Select Routine");
        selectRoutine.addActionListener(e -> selectRoutineMethod());
        toolArea.add(selectRoutine);
        JButton addRoutine = new JButton("Return to Menu");
        addRoutine.addActionListener(e -> cl.show(gui,"menu"));
        toolArea.add(addRoutine);
    }

    public void selectRoutineMethod() {
        ((AddRoutineWorkoutInterface)gui.getScreenMap().get("routine workout")).updateForm(
                (Routine)list.getSelectedValue());
        cl.show(gui,"routine workout");
    }

    private void makeJLabel() {
        text = new JLabel();
        text.setFont(new Font("", Font.PLAIN, 20));
        text.setHorizontalAlignment(SwingConstants.LEFT);
        text.setVerticalAlignment(SwingConstants.NORTH);
        text.setMinimumSize(new Dimension(WIDTH / 2, HEIGHT));
        add(text, BorderLayout.CENTER);
    }

    public void updateList() {
        List<Routine> routineArrayList = gj.getRoutines();
        Routine[] routineArray = new Routine[routineArrayList.size()];
        for (int i = 0; i < routineArrayList.size(); i++) {
            routineArray[i] = routineArrayList.get(i);
        }
        list.setListData(routineArray);
        list.ensureIndexIsVisible(list.getModel().getSize());
    }

    public void makeList() {
        List<Routine> routineArrayList = gj.getRoutines();
        Routine[] routineArray = new Routine[routineArrayList.size()];
        for (int i = 0; i < routineArrayList.size(); i++) {
            routineArray[i] = routineArrayList.get(i);
        }
        list = new JList(routineArray);
        list.setCellRenderer(new RoutineRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (list.getSelectedValue() != null) {
                    text.setText(paragraphText(list.getSelectedValue().toString()));
                }
            }
        });
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
    }

    private String paragraphText(String s) {
        String paragraph = "<html>".concat(s);
        paragraph.concat("</html>");
        paragraph = paragraph.replace(".", "<br/>");
        return paragraph;
    }


}
