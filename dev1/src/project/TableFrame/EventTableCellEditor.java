package project.TableFrame;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.util.EventObject;

public class EventTableCellEditor extends AbstractCellEditor implements TableCellEditor {
    private EventFrame eventFrame;
    private boolean isEvent, delEvent;

    public EventTableCellEditor(){ //1
        //обработчики для кнопок Ok и Cancel
        eventFrame = new EventFrame(
                EventHandler.create(ActionListener.class, this, "stopCellEditing"),
                EventHandler.create(ActionListener.class, this, "cancelCellEditing"));
    }

    @Override
    public Component getTableCellEditorComponent(JTable table,
            Object value, boolean isSelected, int row, int column) { //2
        System.out.println("getTableCellEditorComponent");
        //заполнение полей (если событие в ячейке установлено)
        eventFrame.setValues(value);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(184, 207, 229));
        panel.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
        JLabel label = new JLabel();
        if (value == null) {
            isEvent = false;
            label.setText("");
        }
        else{
            isEvent = true;
            Event event = (Event) value;
            label = new JLabel(event.getName());
        }
        panel.add(label);
        return panel;
    }

    //инициализация процесса редактирования
    public boolean shouldSelectCell(EventObject event){ //3
        System.out.println("shouldSelectCell");
        //отображение диалогового окна
        if (isEvent) //обнаружено событие
            if (0 == JOptionPane.showOptionDialog(null, "Change or delete current event?",
                    "Option", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, new String[]{"Edit", "Delete"}, "Edit"))
                eventFrame.setVisible(true);
            else {
                delEvent = true; //удалить событие
                stopCellEditing();
            }
        else
            eventFrame.setVisible(true);

        //уведомить вызывающую часть программы о том,
        //что эту ячейку разрешается выбрать
        return true;
    }

    //вызывается при отказе от редактирования
    public void cancelCellEditing(){
        System.out.println("cancelCellEditing");
        //редактирование отменено - скрыть диалоговое окно
        eventFrame.setVisible(false);
        super.cancelCellEditing();
    }

    //вызывается при окончании редактирования
    @Override
    public boolean stopCellEditing(){
        System.out.println("stopCellEditing");
        //редактирование отменено - скрыть диалоговое окно
        eventFrame.setVisible(false);
        super.stopCellEditing();

        //уведомить вызывающую часть программы о том,
        //данные разрешается использовать
        return true;
    }

    //метод возвращает отредактированное значение по завершении редактирования
    @Override
    public Object getCellEditorValue() {
        Event event = null;

        if (!delEvent) //если пользователь не выбрал удалить событие
        event = eventFrame.getEvent();

        return event;
    }
}

//Модальное диалоговое окно
//отоображает данные события и ожидает щелчка "Ok"
class EventFrame extends JFrame{
    private JTextField eventName;
    private JTextField eventDescr;

    public EventFrame(ActionListener okL, ActionListener cancelL){
        setTitle("Event frame");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel nameLabel = new JLabel("Name:");
        panel.add(nameLabel);
        eventName = new JTextField(9);
        panel.add(eventName);
        JLabel descrLabel = new JLabel("Description:");
        panel.add(descrLabel);
        eventDescr = new JTextField(9);
        panel.add(eventDescr);
        add(panel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(okL);

        buttonPanel.add(okButton);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(cancelL);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    //заполнение полей (если событие в ячейке установлено)
    public void setValues(Object obj){
        if (obj != null){
            Event event = (Event) obj;
            eventName.setText(event.getName());
            eventDescr.setText(event.getDescription());
        }
        else{
            eventName.setText("");
            eventDescr.setText("");
        }
    }

    //Получение введенной информации
    public Event getEvent(){
        if (!eventName.getText().equals("") && !eventDescr.getText().equals(""))
            return new Event(eventName.getText(), eventDescr.getText());
        else
            return null;
    }
}

