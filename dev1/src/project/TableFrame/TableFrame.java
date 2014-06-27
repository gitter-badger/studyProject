package project.TableFrame;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class TableFrame extends JFrame {
    public TableFrame(){
        //setSize(400, 300);
        setTitle("Table frame");

        TableModel model = new TableModel();
        JTable table = new JTable(model);
        table.setRowHeight(20);
        table.getTableHeader().setReorderingAllowed(false); //обработчик столбцов
        //table.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        //установить средства воспроизведения и редактирования ячеек таблицы
        table.setDefaultRenderer(Event.class, new EventTableCellRenderer());
        table.setDefaultEditor(Event.class, new EventTableCellEditor());

        add(new JScrollPane(table));
        pack();
    }
}
