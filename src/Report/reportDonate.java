package Report;

import Database.connectDB;
import Model.ExportData;
import Model.message;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class reportDonate extends javax.swing.JInternalFrame {

    Connection con = connectDB.getConnect();
    DecimalFormat df = new DecimalFormat("#,###");
    DefaultTableModel model = new DefaultTableModel();
    String sql;

    public reportDonate() {
        initComponents();
        model = (DefaultTableModel) table.getModel();
        showData();
        double total = total();
        lbMoney.setText(df.format(total) + " ₭");
    }

    private double total() {
        double getTotal = 0.0;
        for (int i = 0; i < table.getRowCount(); i++) {
            String money = table.getValueAt(i, 4).toString()
                    .replaceAll(",", "")
                    .replaceAll("ກີບ", "")
                    .trim();
            double amount = Double.parseDouble(money);
            getTotal += amount;
        }
        return getTotal;
    }

    private void ClearTable() {
        int row = table.getRowCount() - 1;
        while (row >= 0) {
            model.removeRow(row--);
        }
    }

    private void showData() {
        try {
            ClearTable();
            sql = "call selectDonate()";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                int money = rs.getInt("estimatedValue");
                String[] data = {
                    rs.getString("itemID"),
                    rs.getString("itemName"),
                    rs.getString("quantity"),
                    rs.getString("unit"),
                    df.format(money) + " ກີບ",
                    rs.getString("receivedDate"),
                    rs.getString("donator"),};
                model.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbMoney = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnPrint = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new Model.Table();

        setBorder(null);
        setClosable(true);
        setFrameIcon(null);

        jPanel2.setPreferredSize(new java.awt.Dimension(1540, 80));
        jPanel2.setLayout(new net.miginfocom.swing.MigLayout());

        jPanel3.setBackground(new java.awt.Color(0, 204, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(242, 242, 242)), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        jPanel3.setPreferredSize(new java.awt.Dimension(410, 50));
        jPanel3.setLayout(new net.miginfocom.swing.MigLayout());

        jLabel1.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ລາຍຮັບທັງໝົດ:");
        jLabel1.setPreferredSize(new java.awt.Dimension(37, 60));
        jPanel3.add(jLabel1);

        jLabel3.setPreferredSize(new java.awt.Dimension(60, 50));
        jPanel3.add(jLabel3);

        lbMoney.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbMoney.setForeground(new java.awt.Color(255, 255, 255));
        lbMoney.setText("kip");
        lbMoney.setPreferredSize(new java.awt.Dimension(350, 60));
        jPanel3.add(lbMoney);

        jPanel2.add(jPanel3);

        jPanel5.setPreferredSize(new java.awt.Dimension(50, 45));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel5);

        jPanel4.setPreferredSize(new java.awt.Dimension(400, 45));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        btnPrint.setBackground(new java.awt.Color(0, 102, 255));
        btnPrint.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setText("ປິ່ນ");
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });
        jPanel4.add(btnExport);

        jPanel2.add(jPanel4);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(242, 242, 242)));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane1.setFont(new java.awt.Font("Saysettha OT", 0, 14)); // NOI18N

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ລະຫັດ", "ລາຍການ", "ຈຳນວນ", "ຫົວໜ່ວຍ", "ຄິດໄລ່ເປັນເງິນ", "ວັນທີ່ມອບ", "ມອບໂດຍ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("Saysettha OT", 0, 15)); // NOI18N
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbMoney;
    private Model.Table table;
    // End of variables declaration//GEN-END:variables

    private void print() {
        try {
            InputStream path = getClass().getResourceAsStream("/Report/donate.jrxml");
            JasperReport jreport = JasperCompileManager.compileReport(path);
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("total", lbMoney.getText());

            JasperPrint jprint = JasperFillManager.fillReport(jreport, parameter, con);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            view.setExtendedState(JFrame.MAXIMIZED_BOTH);
           view.setAlwaysOnTop(true);
            view.setVisible(true);
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
}
