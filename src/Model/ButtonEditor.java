package Model;

import javax.swing.DefaultCellEditor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

public class ButtonEditor extends DefaultCellEditor {

    public JButton button;
    public boolean click;
    public int row;
    public JTable table;
    public DefaultTableModel model;

    public ButtonEditor(JButton button, JTable table, DefaultTableModel model) {
        super(new JTextField());
        this.button = button;
        this.table = table;
        this.model = model;
        this.button.setOpaque(true);
        this.button.setFont(new Font("Saysettha OT", 0, 16));
        this.button.addActionListener((ActionEvent e) -> {
            click = true;
            fireEditingStopped();
            model.fireTableDataChanged();
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        this.row = row;
        button.setText("ລຶບ");
        button.setBackground(new Color(0, 102, 255));
        button.setForeground(Color.white);
        click = false;
        return button;

    }

    @Override
    public Object getCellEditorValue() {
        if (click) {
            int confirm = JOptionPane.showConfirmDialog(button, new message("ທ່ານຕ້ອງການລຶບແທ້ບໍ?"), "ແຈ້ງເຕືອນ", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int modelRow = table.convertRowIndexToModel(table.getSelectedRow());
                if (modelRow >= 0 && modelRow < model.getRowCount()) {
                    SwingUtilities.invokeLater(() -> model.removeRow(modelRow));

                }
            }
            table.revalidate();
            table.repaint();
        }

        click = false;
        return "ລຶບ";
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
        table.clearSelection();
    }

}
