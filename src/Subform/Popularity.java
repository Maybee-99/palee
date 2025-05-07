package Subform;

import Database.connectDB;
import Model.message;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
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

public class Popularity extends javax.swing.JInternalFrame {

    Connection con = connectDB.getConnect();
    String sql;
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel model1 = new DefaultTableModel();

    public Popularity() {
        initComponents();
        model = (DefaultTableModel) table.getModel();
        model1 = (DefaultTableModel) table1.getModel();
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.setColumnIdentifiers(new Object[]{"ອັນດັບ", "ວິຊາ", "ລະດັບ", "ຈຳນວນ", "ຊາຍ", "ຍິງ", "ອື່ນໆ"});

        table.setModel(model);

        table.getColumnModel().getColumn(4).setMinWidth(0);
        table.getColumnModel().getColumn(4).setMaxWidth(0);
        table.getColumnModel().getColumn(4).setWidth(0);

        table.getColumnModel().getColumn(5).setMinWidth(0);
        table.getColumnModel().getColumn(5).setMaxWidth(0);
        table.getColumnModel().getColumn(5).setWidth(0);

        table.getColumnModel().getColumn(6).setMinWidth(0);
        table.getColumnModel().getColumn(6).setMaxWidth(0);
        table.getColumnModel().getColumn(6).setWidth(0);

        loadSubject();
        total();
        btnPrint.setEnabled(false);

        table.fixTable(jScrollPane1);
        table1.fixTable(jScrollPane2);

    }

    private void loadSubject() {
        try {
            sql = "SELECT "
                    + "    subject.subName AS sn, "
                    + "    level.level AS lv, "
                    + "    SUM(CASE WHEN sex.sex = 'ຊາຍ' THEN 1 ELSE 0 END) AS Male, "
                    + "    SUM(CASE WHEN sex.sex = 'ຍິງ' THEN 1 ELSE 0 END) AS Female, "
                    + "    SUM(CASE WHEN sex.sex = 'ສານມະເນນ' THEN 1 ELSE 0 END) AS Other, "
                    + "    COUNT(regisdetail.subDetailID) AS registrationCount "
                    + "FROM "
                    + "    subjectdetail "
                    + "LEFT JOIN subject ON subjectdetail.subID = subject.subID "
                    + "LEFT JOIN level ON subjectdetail.levelID = level.levelID "
                    + "LEFT JOIN regisdetail ON regisdetail.subDetailID = subjectdetail.subDetailID "
                    + "LEFT JOIN registration ON regisdetail.regisID = registration.regisID "
                    + "LEFT JOIN student ON registration.stdID = student.stdID "
                    + "LEFT JOIN sex ON student.sexID = sex.sexID "
                    + "GROUP BY "
                    + "    subject.subName, "
                    + "    level.level, "
                    + "    subjectdetail.subDetailID "
                    + "ORDER BY "
                    + "    registrationCount DESC, "
                    + "    subjectdetail.subDetailID ASC";

            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            int i = 1;

            model.setRowCount(0);

            // Loop through the result set and populate the table
            while (rs.next()) {
                String[] data = {
                    String.valueOf(i),
                    rs.getString("sn"),
                    rs.getString("lv"),
                    rs.getString("registrationCount"),
                    rs.getString("Male"),
                    rs.getString("Female"),
                    rs.getString("Other")
                };
                model.addRow(data);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void total() {
        int col = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            int ind = Integer.parseInt(table.getValueAt(i, 3).toString());
            col += ind;
        }
        lbTotal.setText(String.valueOf(col));
    }

    private void print() {
        try {
            InputStream path = getClass().getResourceAsStream("/Report/studentWithSub.jrxml");
            JasperReport jreport = JasperCompileManager.compileReport(path);
            Map<String, Object> parameter = new HashMap<>();

            String sub = table.getValueAt(table.getSelectedRow(), 1).toString();
            String level = table.getValueAt(table.getSelectedRow(), 2).toString();
            parameter.put("sub", sub);
            parameter.put("level", level);

            JasperPrint jprint = JasperFillManager.fillReport(jreport, parameter, con);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            view.setExtendedState(JFrame.MAXIMIZED_BOTH);
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

        jPanel2 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new Model.Table();
        jPanel14 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new Model.Table();
        jPanel12 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        lblMale = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lbFemale = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lbOther = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnPrint = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(null);
        setClosable(true);
        setFrameIcon(null);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(213, 216, 219));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(20, 10, 10, 10, new java.awt.Color(213, 216, 219)));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        jPanel13.setLayout(new java.awt.BorderLayout());

        table.setAutoCreateRowSorter(true);
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ອັບດັບ", "ວິຊາ", "ລະດັບ", "ຈຳນວນນັກຮຽນ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("Saysettha OT", 0, 14)); // NOI18N
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel13.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel14.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel26.setBackground(new java.awt.Color(0, 102, 255));
        jLabel26.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("ຈຳນວນທີ່ສະໝັກຮຽນ:");
        jLabel26.setPreferredSize(new java.awt.Dimension(200, 60));
        jPanel14.add(jLabel26);

        lbTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbTotal.setForeground(new java.awt.Color(51, 51, 51));
        lbTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotal.setText("0");
        lbTotal.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel14.add(lbTotal);

        jPanel13.add(jPanel14, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(jPanel13);

        jPanel7.setLayout(new java.awt.BorderLayout());

        table1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 0));
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ລະຫັດ", "ຊື່", "ນາມສະກຸນ", "ເພດ", "ໂຮງຮຽນ", "ເມືອງ", "ແຂວງ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.setFont(new java.awt.Font("Saysettha OT", 0, 14)); // NOI18N
        table1.setOpaque(false);
        jScrollPane2.setViewportView(table1);

        jPanel7.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel12.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel12.setLayout(new net.miginfocom.swing.MigLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(500, 80));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("ເພດຊາຍ:");
        jLabel23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel23.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel1.add(jLabel23);

        lblMale.setBackground(new java.awt.Color(255, 255, 255));
        lblMale.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblMale.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMale.setText("0");
        lblMale.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblMale.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel1.add(lblMale);

        jLabel24.setBackground(new java.awt.Color(0, 102, 255));
        jLabel24.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("ເພດຍິງ:");
        jLabel24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel24.setPreferredSize(new java.awt.Dimension(65, 60));
        jPanel1.add(jLabel24);

        lbFemale.setBackground(new java.awt.Color(0, 102, 255));
        lbFemale.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbFemale.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbFemale.setText("0");
        lbFemale.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbFemale.setPreferredSize(new java.awt.Dimension(60, 50));
        jPanel1.add(lbFemale);

        jLabel25.setBackground(new java.awt.Color(0, 102, 255));
        jLabel25.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("ອື່ນໆ:");
        jLabel25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel25.setPreferredSize(new java.awt.Dimension(50, 60));
        jPanel1.add(jLabel25);

        lbOther.setBackground(new java.awt.Color(0, 102, 255));
        lbOther.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbOther.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbOther.setText("0");
        lbOther.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbOther.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel1.add(lbOther);

        jPanel12.add(jPanel1);

        jPanel4.setPreferredSize(new java.awt.Dimension(410, 60));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        btnPrint.setBackground(new java.awt.Color(0, 153, 51));
        btnPrint.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setText("ພິມ");
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrint.setPreferredSize(new java.awt.Dimension(200, 50));
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        jPanel4.add(btnPrint);

        jPanel12.add(jPanel4);

        jPanel7.add(jPanel12, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(jPanel7);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(0, 102, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setFont(new java.awt.Font("Saysettha OT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ວິຊາຍອດນິຍົມຮຽນຫຼາຍ");
        jPanel3.add(jLabel1);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);

        setBounds(0, 0, 1550, 710);
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String maleCount = model.getValueAt(selectedRow, 4).toString();
                String femaleCount = model.getValueAt(selectedRow, 5).toString();
                String otherCount = model.getValueAt(selectedRow, 6).toString();

                lblMale.setText(maleCount);
                lbFemale.setText(femaleCount);
                lbOther.setText(otherCount);

                String sub = model.getValueAt(selectedRow, 1).toString();
                String level = model.getValueAt(selectedRow, 2).toString();
                model1.setRowCount(0);
                sql = "call selectAAA(?,?)";
                try (CallableStatement cst = con.prepareCall(sql)) {
                    cst.setString(1, sub);
                    cst.setString(2, level);
                    ResultSet rs = cst.executeQuery();
                    while (rs.next()) {
                        String[] data1 = {
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("lname"),
                            rs.getString("gender"),
                            rs.getString("sch"),
                            rs.getString("dis"),
                            rs.getString("pro"),};
                        model1.addRow(data1);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        btnPrint.setEnabled(true);
    }//GEN-LAST:event_tableMouseClicked

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
//        retrieveSubType();
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        int row = table1.getRowCount();
        if (row > 0) {
            print();
        } else {
            JOptionPane.showMessageDialog(this, new message("ຂໍ້ມູນຫວ່າງເປົ່າບໍ່ພົບຂໍ້ມູນທີ່ຈະປິ່ນອອກ"));
        }
        btnPrint.setEnabled(false);
    }//GEN-LAST:event_btnPrintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbFemale;
    private javax.swing.JLabel lbOther;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JLabel lblMale;
    private Model.Table table;
    public static Model.Table table1;
    // End of variables declaration//GEN-END:variables

}
