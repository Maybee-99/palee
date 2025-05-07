package Report;

import Database.connectDB;
import Model.ExportData;
import Model.comboBoxHeight;
import Model.message;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
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

public class learning1 extends javax.swing.JInternalFrame {

    DefaultTableModel model = new DefaultTableModel();
    ArrayList sub = new ArrayList();
    Connection con = connectDB.getConnect();
    String sql;
    DecimalFormat df = new DecimalFormat("#,###");

    public learning1() {
        initComponents();
        model = (DefaultTableModel) table.getModel();
        cmbSub.setRenderer(new comboBoxHeight());
        bindingSub();
    }

    private void bindingSub() {
        try {
            sql = "select * from subject";
            sub.clear();
            //cmbprovince.removeAllItems();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                sub.add(rs.getString("subID"));
                String subject = rs.getString("subName");
                cmbSub.addItem(subject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filter1() {
        String subject = cmbSub.getSelectedItem().toString();
        try {
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            sql = "SELECT DISTINCT student.stdID AS id,"
                    + "       student.stdName AS name,"
                    + "       student.Lastname AS sur,"
                    + "       sex.sex AS gender,"
                    + "       subject.subName AS sub,"
                    + "       level.level AS le,"
                    + "       award.score AS sc,"
                    + "       semester.semester as sem,"
                    + "       award.std_Rank AS r,"
                    + "       award.prize AS p,"
                    + "       student.school AS sch,"
                    + "       district.districtName AS dn,"
                    + "       province.provinceName AS pn"
                    + " FROM award"
                    + "    INNER JOIN student ON award.stdID = student.stdID"
                    + "    INNER JOIN district ON student.districtID = district.districtID"
                    + "    INNER JOIN province ON district.provinceID = province.provinceID"
                    + "    INNER JOIN sex ON student.sexID = sex.sexID"
                    + "    INNER JOIN subjectdetail ON award.subDetailID = subjectdetail.subDetailID"
                    + "    INNER JOIN subject ON subjectdetail.subID = subject.subID"
                    + "    INNER JOIN level ON subjectdetail.levelID = level.levelID"
                    + "    INNER JOIN exam ON award.examID = exam.examID"
                    + "    INNER JOIN semester ON exam.semID = semester.semID"
                    + " WHERE subject.subName =?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, subject);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String[] data = {
                    rs.getString("id"),
                    rs.getString("name") + " " + rs.getString("sur"),
                    rs.getString("gender"),
                    rs.getString("sub") + " " + rs.getString("le"),
                    rs.getString("sem"),
                    rs.getString("sc"),
                    rs.getString("r"),
                    df.format(rs.getInt("p")),
                    rs.getString("sch"),
                    rs.getString("dn"),
                    rs.getString("pn"),};
                model.addRow(data);
            }

            table.revalidate();
            table.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void print() {
        try {
            InputStream path = getClass().getResourceAsStream("/Report/result2.jrxml");
            JasperReport jreport = JasperCompileManager.compileReport(path);
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("sub", cmbSub.getSelectedItem());
            parameter.put("total", table.getRowCount() + " ຄົນ");

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cmbSub = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        btnPrint = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new Model.Table();

        setBorder(null);
        setClosable(true);
        setFrameIcon(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(20, 10, 10, 10, new java.awt.Color(242, 242, 242)));
        jPanel1.setMinimumSize(new java.awt.Dimension(141, 100));
        jPanel1.setPreferredSize(new java.awt.Dimension(1424, 90));
        jPanel1.setLayout(new net.miginfocom.swing.MigLayout());

        cmbSub.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        cmbSub.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ເລືອກຕາມວິຊາ" }));
        cmbSub.setPreferredSize(new java.awt.Dimension(300, 100));
        cmbSub.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSubItemStateChanged(evt);
            }
        });
        jPanel1.add(cmbSub);

        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 50, 0, 0, new java.awt.Color(242, 242, 242)));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        btnPrint.setBackground(new java.awt.Color(0, 102, 255));
        btnPrint.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setText("ປິ່ນ");
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrint.setPreferredSize(new java.awt.Dimension(93, 50));
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        jPanel3.add(btnPrint);

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
        jPanel3.add(btnExport);

        jPanel1.add(jPanel3);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 15, 15, 15, new java.awt.Color(242, 242, 242)));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ລະຫັດ", "ຊື່", "ເພດ", "ວິຊາ", "ຜົນການຮຽນ", "ຄະແນນ", "ຈັດທີ່", "ລາງວັນ", "ໂຮງຮຽນ", "ເມືອງ", "ແຂວງ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("Saysettha OT", 0, 14)); // NOI18N
        jScrollPane2.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMinWidth(70);
            table.getColumnModel().getColumn(0).setPreferredWidth(70);
            table.getColumnModel().getColumn(0).setMaxWidth(70);
            table.getColumnModel().getColumn(2).setMinWidth(100);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
            table.getColumnModel().getColumn(2).setMaxWidth(100);
            table.getColumnModel().getColumn(3).setMinWidth(200);
            table.getColumnModel().getColumn(3).setPreferredWidth(200);
            table.getColumnModel().getColumn(3).setMaxWidth(200);
        }

        jPanel2.add(jScrollPane2);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbSubItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSubItemStateChanged
        filter1();
    }//GEN-LAST:event_cmbSubItemStateChanged

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
    private javax.swing.JComboBox<String> cmbSub;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private Model.Table table;
    // End of variables declaration//GEN-END:variables
}
