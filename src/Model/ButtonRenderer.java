
package Model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.table.TableCellRenderer;
import javax.swing.*;


public class ButtonRenderer extends JButton implements TableCellRenderer{
    
    public ButtonRenderer()
    {
        setOpaque(true);
        setFont(new Font("Saysettha OT",0,16));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(Color.red);
        setForeground(Color.white);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText("ລຶບ");
        return this;
    }
    
}
