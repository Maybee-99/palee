package Model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class SalaryTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        String status = table.getValueAt(row, 3).toString().trim();

        cell.setFont(new Font("Saysettha OT", Font.PLAIN, 16));

        if (isSelected) {
            cell.setBackground(new Color(0, 102, 255));
            cell.setForeground(Color.WHITE);
        } else {
            cell.setBackground(Color.WHITE);
            cell.setForeground(status.equals("ຈ່າຍແລ້ວ") ? new Color(11, 149, 0) : Color.RED);
        }

        return cell;
    }
}
