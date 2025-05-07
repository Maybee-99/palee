package Subform;

import Database.connectDB;
import static Model.Command.runCommand;
import Model.CurrentDate;
import Model.formatNumber;
import static Model.generateQRCode.generateQRCode;
import Model.message;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class payment extends javax.swing.JDialog {

    private RegisterStudent register;
    private formPay pay;
    String billNo;
    String stdID;
    String fee;
    message m;
    DecimalFormat df = new DecimalFormat("#,###");
    CurrentDate dn = new CurrentDate();
    Connection con = connectDB.getConnect();
    String sql;

    public payment(RegisterStudent register, java.awt.Frame parent, boolean modal, String billNo, String stdID, String fee) {
        super(parent, modal);
        this.register = register;
        initPayment(billNo, stdID, fee);
    }

    public payment(formPay payForm, java.awt.Frame parent, boolean modal, String billNo, String stdID, String fee) {
        super(parent, modal);
        this.pay = payForm;
        initPayment(billNo, stdID, fee);
    }

    private void initPayment(String billNo, String stdID, String fee) {
        this.billNo = billNo;
        this.stdID = stdID;
        this.fee = fee;
        initComponents();
        lbBill.setText(billNo);
        lbStdID.setText(stdID);
        lbFee.setText(fee);
        formatNumber.apply(txtMoney);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbBill = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbStdID = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbFee = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMoney = new Swing.TextField();
        jLabel11 = new javax.swing.JLabel();
        txtPend = new Swing.TextField();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(721, 60));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jLabel16.setBackground(new java.awt.Color(0, 102, 255));
        jLabel16.setFont(new java.awt.Font("Saysettha OT", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("ຟອມຈ່າຍຄ່າຮຽນ");
        jLabel16.setOpaque(true);
        jPanel1.add(jLabel16);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(20, 40, 20, 40, new java.awt.Color(204, 204, 204)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("ເລກບິນ:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 21, 100, 54));

        lbBill.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbBill.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbBill.setText("Bill");
        lbBill.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 200, 200), 1, true));
        lbBill.setOpaque(true);
        jPanel3.add(lbBill, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 386, 54));

        jLabel5.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("ລະຫັດນັກຮຽນ:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 100, 54));

        lbStdID.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbStdID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbStdID.setText("Bill");
        lbStdID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 200, 200), 1, true));
        lbStdID.setOpaque(true);
        jPanel3.add(lbStdID, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 386, 54));

        jLabel9.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("ຈຳນວນເງິນ:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 100, 54));

        lbFee.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        lbFee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFee.setText("Bill");
        lbFee.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(200, 200, 200), 1, true));
        lbFee.setOpaque(true);
        jPanel3.add(lbFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 386, 54));

        jLabel7.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("ປ້ອນເງິນ:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 100, 50));

        txtMoney.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        txtMoney.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMoney.setCornerRadius(12.0F);
        txtMoney.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtMoney.setPlaceHolder("");
        txtMoney.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMoneyFocusGained(evt);
            }
        });
        txtMoney.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMoneyKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMoneyKeyTyped(evt);
            }
        });
        jPanel3.add(txtMoney, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 386, 54));

        jLabel11.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("ຍັງເຫຼືອ:");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 100, 54));

        txtPend.setEditable(false);
        txtPend.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        txtPend.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPend.setCornerRadius(12.0F);
        txtPend.setFont(new java.awt.Font("Saysettha OT", 1, 24)); // NOI18N
        txtPend.setPlaceHolder("");
        jPanel3.add(txtPend, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 386, 54));

        btnSave.setBackground(new java.awt.Color(0, 153, 102));
        btnSave.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("ບັນທຶກ");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.setPreferredSize(new java.awt.Dimension(380, 50));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel3.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 386, -1));

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(591, 514));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if ("".equals(txtMoney.getText()) || txtMoney.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, new message("ກະລຸນາປ້ອນຈຳນວນເງິນກ່ອນ!"));
        } else {
            try {
                String sqlCheck = "SELECT * FROM payment WHERE regisID = ?";
                try (PreparedStatement pst = con.prepareStatement(sqlCheck)) {
                    pst.setString(1, lbBill.getText().trim());
                    try (ResultSet rs = pst.executeQuery()) {
                        if (rs.next()) {
                            updatePay();
                            saveToIncome();
                            printBill();

                            this.dispose();
                        } else {
                            saveMethod();
                        }

                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, new message("ລະບົບເກີດຂໍ້ຜິດພາດ: " + e));
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtMoneyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMoneyKeyReleased
        if (evt.getKeyChar() == '\n') {
            if (txtMoney.getText() == null || txtMoney.getText().trim().isEmpty()) {
                txtMoney.setText("0");
                txtPend.setText(lbFee.getText());
                txtPend.requestFocus();
            } else {
                cal();
            }
        }
    }//GEN-LAST:event_txtMoneyKeyReleased

    private void txtMoneyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMoneyKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMoneyKeyTyped

    private void txtMoneyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMoneyFocusGained
        txtPend.setText("");
        if ("".equals(txtMoney.getText()) || txtMoney.getText().isEmpty()) {
            txtPend.setText("");
        }
    }//GEN-LAST:event_txtMoneyFocusGained

    private void savePay() {
        try {
            sql = "call insertToPay(?,?,?,?,?)";

            String total = lbFee.getText().replaceAll(",", "").replaceAll("ກີບ", "").trim();
            String pays = txtMoney.getText().replaceAll(",", "").trim();

            int pend = Integer.parseInt(total) - Integer.parseInt(pays);
            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, lbBill.getText());
            cst.setString(2, total);
            cst.setString(3, (txtMoney.getText() == null ? "0" : pays));
            cst.setInt(4, pend);
            cst.setString(5, dn.DateTimeNow("yyyy-MM-dd"));
            cst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());

        }
    }

    private void updatePay() {
        try {
            sql = "CALL updatPay(?,?,?,?)";

            String paysText = txtMoney.getText().replaceAll(",", "").trim();
            int pays = paysText.isEmpty() ? 0 : Integer.parseInt(paysText);

            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, lbBill.getText());
            cst.setInt(2, pays);
            cst.setInt(3, 0);
            cst.setString(4, dn.DateTimeNow("yyyy-MM-dd"));

            cst.executeUpdate();
            pay.showData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                payment dialog = new payment((RegisterStudent) null, new javax.swing.JFrame(), true, "", "", "");

                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private void saveToIncome() {
        String moneyText = txtMoney.getText().replaceAll(",", "").trim();

        if (moneyText == null || moneyText.trim().isEmpty()) {
            return;
        }
        int pays = Integer.parseInt(moneyText.trim());
        if (pays > 0) {
            try {
                sql = "call insertIncome(?,?,?)";
                String money = txtMoney.getText().replaceAll(",", "").trim().trim();
                String des = "ຄ່າຮຽນ ເລກບິນ" + lbBill.getText();
                CallableStatement cst = con.prepareCall(sql);
                cst.setString(1, money);
                cst.setString(2, des);
                cst.setString(3, dn.DateTimeNow("yyyy-MM-dd"));
                cst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbBill;
    private javax.swing.JLabel lbFee;
    private javax.swing.JLabel lbStdID;
    private Swing.TextField txtMoney;
    private Swing.TextField txtPend;
    // End of variables declaration//GEN-END:variables

    private void cal() {
        int total = Integer.parseInt(lbFee.getText().replaceAll(",", "").replaceAll("ກີບ", "").trim());
        int pays = Integer.parseInt(txtMoney.getText().replaceAll(",", "").trim());
        if (pays > total) {
            m = new message("ກະລຸນາປ້ອນວົງເງິີນ<= " + lbFee.getText());
            JOptionPane.showMessageDialog(this, m);
        } else {
            int pend = total - pays;
            txtPend.setText(df.format(pend) + " ກີບ");
        }

    }

    private void saveMethod() {
        if (!txtPend.getText().isEmpty()) {
            if (register != null) {
                try {
                    int pays = 0;
                    try {
                        pays = Integer.parseInt(txtMoney.getText().replaceAll(",", "").trim().isEmpty() ? "0" : txtMoney.getText().replaceAll(",", "").trim());
                    } catch (NumberFormatException e) {
                        m = new message("ຂໍ້ມູນຈໍານວນເງິນບໍ່ຖືກຕ້ອງ!");
                        JOptionPane.showMessageDialog(this, m);
                        return;
                    }
                    register.lbpaid.setText(df.format(pays) + " ກີບ");
                    register.lbpend.setText(txtPend.getText());
                    String sqlCheck = "select * from student where stdID=?";
                    PreparedStatement pst = con.prepareStatement(sqlCheck);
                    pst.setString(1, lbStdID.getText().trim());
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        register.saveRegisterData();
                    } else {
                        register.saveStudentData();
                        register.saveRegisterData();
                    }
                    register.btnPrint.setEnabled(true);
                    savePay();
                    this.dispose();

                } catch (SQLException ex) {
                    Logger.getLogger(payment.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
//            else if (pay != null) {
//                updatePay();
//                this.dispose();
//
//            }
            saveToIncome();

        } else {
            m = new message("ກະລຸນາປ້ອນຈຳນວນເງິນໃຫ້ຄົບຖ້ວນກ່ອນ");
            JOptionPane.showMessageDialog(this, m);
        }
    }

    private void exportPdfAndPushToGitHub(JasperPrint jprint, String billNo) {
    try {
        String repoPath = "C:/Users/pkcom/Documents/Fee"; // <- your Git repository root
        String relativePath = "fee/" + billNo + ".pdf";
        String pdfPath = repoPath + "/" + relativePath;

        new File(repoPath + "/fee").mkdirs(); // create 'fee' folder if not exists
        JasperExportManager.exportReportToPdfFile(jprint, pdfPath); // export the PDF

        // Git commands to add, commit, and push
        runCommand("git -C \"" + repoPath + "\" add " + relativePath);
        runCommand("git -C \"" + repoPath + "\" commit -m \"Add bill " + billNo + "\"");
        runCommand("git -C \"" + repoPath + "\" push origin main");

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Git push failed: " + e.getMessage());
    }
}


    private void printBill() {
        try {
            String qrUrl = "https://github.com/Palee99/fee/blob/main/fee/" + lbBill.getText() + ".pdf?raw=true";
            InputStream path = getClass().getResourceAsStream("/Report/fee.jrxml");
            JasperReport jreport = JasperCompileManager.compileReport(path);
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("billID", lbBill.getText());
            BufferedImage qrImage = generateQRCode(qrUrl);
            parameter.put("qrCode", qrImage);

            JasperPrint jprint = JasperFillManager.fillReport(jreport, parameter, con);
            exportPdfAndPushToGitHub(jprint, lbBill.getText());
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
            m = new message("ເກີດຂໍ້ຜິດພາດ" + e.getMessage());
            JOptionPane.showMessageDialog(this, m);
        }
    }

}
