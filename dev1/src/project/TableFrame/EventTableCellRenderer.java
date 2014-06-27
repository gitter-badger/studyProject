package project.TableFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class EventTableCellRenderer extends DefaultTableCellRenderer{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column){
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        JLabel label = new JLabel();
        if (value == null)
            label.setText("");
        else{
            Event event = (Event) value;
            label = new JLabel(event.getName());
        }
        panel.add(label);

        //установка рамки
        if (hasFocus) {
            panel.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
            panel.setBackground(new Color(184, 207, 229));
        }
        else
            panel.setBorder(null);

        return panel;
    }
}
