package Report;

import Database.connectDB;
import Model.ExportData;
import Model.comboBoxHeight;
import Model.message;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ScholarShipSTD extends javax.swing.JInternalFrame {

    DefaultTableModel model = new DefaultTableModel();
    Connection con = connectDB.getConnect();
    String sql;
    ArrayList sch = new ArrayList();

    public ScholarShipSTD() {
        initComponents();
        model = (DefaultTableModel) table.getModel();
        retrieveScholarship();
        cmbScholarship.setRenderer(new comboBoxHeight());
    }

    private void retrieveScholarship() {
        try {
            sql = "select * from scholarship";
            sch.clear();
            cmbScholarship.removeAllItems();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                sch.add(rs.getString("scholarship_id"));
                cmbScholarship.addItem(rs.getString("scholarship"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ClearTable() {
        int row = table.getRowCount() - 1;
        while (row >= 0) {
            model.removeRow(row--);
        }
    }

    private void print() {
        try {
            InputStream path = getClass().getResourceAsStream("/Report/student1.jrxml");
            JasperReport jreport = JasperCompileManager.compileReport(path);
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("student", cmbScholarship.getSelectedItem());
            parameter.put("total", table.getRowCount() + " ຄົນ");

            JasperPrint jprint = JasperFillManager.fillReport(jreport, parameter, con);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            view.setExtendedState(JFrame.MAXIMIZED_BOTH);
            view.setVisible(true);
            view.setAlwaysOnTop(true);
            view.addWindowFocusListener(new WindowAdapter() {
                @Override
                public void windowLostFocus(WindowEvent e) {
                    view.setAlwaysOnTop(false);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        cmbScholarship = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnPrint = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new Model.Table();

        setBorder(null);
        setClosable(true);
        setFrameIcon(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 15, 15, 15, new java.awt.Color(242, 242, 242)));
        jPanel1.setPreferredSize(new java.awt.Dimension(830, 90));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setPreferredSize(new java.awt.Dimension(700, 80));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        cmbScholarship.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        cmbScholarship.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbScholarship.setPreferredSize(new java.awt.Dimension(300, 50));
        cmbScholarship.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbScholarshipItemStateChanged(evt);
            }
        });
        jPanel2.add(cmbScholarship);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 20, 0, 0, new java.awt.Color(242, 242, 242)));
        jPanel4.setPreferredSize(new java.awt.Dimension(400, 50));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnPrint.setBackground(new java.awt.Color(0, 102, 255));
        btnPrint.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setText("ປິ່ນ");
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrint.setPreferredSize(new java.awt.Dimension(180, 50));
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        jPanel4.add(btnPrint);

        btnExport.setBackground(new java.awt.Color(0, 153, 102));
        btnExport.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnExport.setForeground(new java.awt.Color(255, 255, 255));
        btnExport.setText("ບັນທຶກເປັນ Excel");
        btnExport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExport.setPreferredSize(new java.awt.Dimension(180, 50));
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });
        jPanel4.add(btnExport);

        jPanel3.add(jPanel4);

        jPanel2.add(jPanel3);

        jPanel1.add(jPanel2);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 15, 15, 15, new java.awt.Color(242, 242, 242)));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 0));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ລະຫັດ", "ຊື່", "ນາມສະກຸນ", "ເພດ", "ເບີຕິດຕໍ່", "ເບີຜູ້ປົກຄອງ", "ໂຮງຮຽນ", "ເມືອງ", "ແຂວງ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("Saysettha OT", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(table);

        jPanel5.add(jScrollPane1);

        getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbScholarshipItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbScholarshipItemStateChanged
        int index = cmbScholarship.getSelectedIndex();
        if (index != -1) {
            try {
                ClearTable();

                sql = "call studentWithSchol(?)";
                CallableStatement cst = con.prepareCall(sql);
                cst.setString(1, cmbScholarship.getSelectedItem().toString());

                try (ResultSet rs = cst.executeQuery()) {
                    while (rs.next()) {
                        String[] data = {
                            rs.getString("stdID"),
                            rs.getString("stdName"),
                            rs.getString("Lastname"),
                            rs.getString("sex"),
                            rs.getString("phoneNumber"),
                            rs.getString("Contact"),
                            rs.getString("school"),
                            rs.getString("districtName"),
                            rs.getString("provinceName")
                        };
                        model.addRow(data);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_cmbScholarshipItemStateChanged

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        int row = table.getRowCount();
        if (row > 0) {
            print();
        } else {
            JOptionPane.showMessageDialog(this, new message("ຂໍ້ມູນຫວ່າງເປົ່າບໍ່ພົບຂໍ້ມູນທີ່ຈະປິ່ນອອກ"));
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        int row = table.getRowCount();
        if (row > 0) {
            ExportData.exportToCSV(table);
        } else {
            JOptionPane.showMessageDialog(this, new message("ຂໍ້ມູນຫວ່າງເປົ່າ"));
        }
    }//GEN-LAST:event_btnExportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnPrint;
    private javax.swing.JComboBox<String> cmbScholarship;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    public static Model.Table table;
    // End of variables declaration//GEN-END:variables
}
