package Model;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class PaymentTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        int pend;
        try {
            pend = Integer.parseInt(
                    table.getValueAt(row, 6).toString()
                            .replace(" ກີບ", "")
                            .replace(",", "")
                            .trim()
            );
        } catch (Exception e) {
            pend = 0;
        }

        cell.setFont(new Font("Saysettha OT", Font.PLAIN,16 ));

        if (isSelected) {
            cell.setBackground(new Color(0, 102, 255));
            cell.setForeground(Color.WHITE);
        } else {
            cell.setBackground(Color.WHITE);
            cell.setForeground(pend > 0 ? Color.RED : new Color(11, 149, 0));
        }

        return cell;
    }
}
