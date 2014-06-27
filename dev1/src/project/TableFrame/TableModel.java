package project.TableFrame;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
    private final int WEEK_DAYS = 7;
    private final int START_HOUR = 7;
    private final int END_HOUR = 23;
    private String columnNames[] =
            {"Time", "Jan 1", "Jan 2", "Jan 3", "Jan 4", "Jan 5", "Jan 6", "Jan 7"};
    private Event events[][] = new Event[END_HOUR - START_HOUR +1][WEEK_DAYS];

    public TableModel(){
        for (int i = 0; i < END_HOUR - START_HOUR +1 ; i++){
            for (int j = 0; j < WEEK_DAYS; j++) {
                if (i == 0 && j == 0)
                    events[i][j] = new Event("event", "description");
                else
                    events[i][j] = null;
            }
        }
    }

    @Override
    public Class<?> getColumnClass(int c){
        if (c == 0)
            return String.class;
        else
            return Event.class;
    }

    @Override
    public int getRowCount() {
        return END_HOUR - START_HOUR +1;
    }

    @Override
    public int getColumnCount() {
        return WEEK_DAYS + 1;
    }

    @Override
    public String getColumnName(int c){
        return columnNames[c];
    }

    @Override
    public Object getValueAt(int r, int c) {
        if (c != 0)
            return events[r][c - 1];
        else {
            int time = r + 7;
            return time + ":00";
        }
    }

    //метод вызывается по завершении редактирования ячейки таблицы
    @Override
    public void setValueAt(Object event, int r, int c){
        events[r][c - 1] = (Event) event;
    }

    //задаем какие ячейки таблицы можно редактировать
    @Override
    public boolean isCellEditable(int r, int c){
        return c != 0;
    }
}
