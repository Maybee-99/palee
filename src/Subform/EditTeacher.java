package Subform;

import Database.connectDB;
import Model.OnlyInputString;
import Model.message;
import java.sql.Connection;
import java.sql.*;
import javax.swing.JOptionPane;
import Model.PhoneNumberValidator;
import Model.comboBoxHeight;

public class EditTeacher extends javax.swing.JDialog {

    Connection con = connectDB.getConnect();
    String sql;
    message m;
    private static Teacher teacher;

    public EditTeacher(java.awt.Frame parent, boolean modal, Teacher teacher) {
        super(parent, modal);
        initComponents();
        this.teacher = teacher;
        cmbGender.setRenderer(new comboBoxHeight());
        PhoneNumberValidator.Checkvalidate(txtContact);
        m = new message("ກະລຸນາປ້ອນຕົວອັກສອນເທົ່ານັ້ນ!");
        OnlyInputString ois = new OnlyInputString(m);
        txtName.addKeyListener(ois);
        txtLastname.addKeyListener(ois);
        buttonGroup.add(rb1);
        buttonGroup.add(rb2);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtID = new Swing.TextField();
        jLabel1 = new javax.swing.JLabel();
        txtName = new Swing.TextField();
        jLabel3 = new javax.swing.JLabel();
        txtLastname = new Swing.TextField();
        jLabel4 = new javax.swing.JLabel();
        cmbGender = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtContact = new Swing.TextField();
        jLabel19 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        rb1 = new javax.swing.JRadioButton();
        rb2 = new javax.swing.JRadioButton();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), "ຈັດການຂໍ້ມູນອາຈານ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 1, 16), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setLayout(new java.awt.GridLayout(6, 2, -150, 10));

        jLabel2.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("ລະຫັດ:");
        jPanel3.add(jLabel2);

        txtID.setEditable(false);
        txtID.setBackground(new java.awt.Color(255, 255, 255));
        txtID.setForeground(new java.awt.Color(0, 102, 255));
        txtID.setBorderColor(new java.awt.Color(153, 153, 153));
        txtID.setCornerRadius(12.0F);
        txtID.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtID.setPlaceHolder("");
        jPanel3.add(txtID);

        jLabel1.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("ຊື່:");
        jPanel3.add(jLabel1);

        txtName.setBorderColor(new java.awt.Color(153, 153, 153));
        txtName.setCornerRadius(12.0F);
        txtName.setPlaceHolder("ປ້ອນຊື່");
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNameKeyReleased(evt);
            }
        });
        jPanel3.add(txtName);

        jLabel3.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("ນາມສະກຸນ:");
        jPanel3.add(jLabel3);

        txtLastname.setBorderColor(new java.awt.Color(153, 153, 153));
        txtLastname.setCornerRadius(12.0F);
        txtLastname.setPlaceHolder("ປ້ອນນາມສະກຸນ");
        jPanel3.add(txtLastname);

        jLabel4.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("ເພດ:");
        jPanel3.add(jLabel4);

        cmbGender.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        cmbGender.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbGenderKeyReleased(evt);
            }
        });
        jPanel3.add(cmbGender);

        jLabel5.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("ເບີໂທຕິດຕໍ່:");
        jPanel3.add(jLabel5);

        txtContact.setBorderColor(new java.awt.Color(153, 153, 153));
        txtContact.setCornerRadius(12.0F);
        txtContact.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtContact.setPlaceHolder("020XXXXXXXX");
        txtContact.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtContactFocusLost(evt);
            }
        });
        jPanel3.add(txtContact);

        jLabel19.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("ສະຖານະ:");
        jLabel19.setPreferredSize(new java.awt.Dimension(55, 35));
        jPanel3.add(jLabel19);

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        rb1.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        rb1.setForeground(new java.awt.Color(255, 255, 255));
        rb1.setText("ສອນ");
        jPanel2.add(rb1);

        rb2.setBackground(new java.awt.Color(102, 102, 102));
        rb2.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        rb2.setForeground(new java.awt.Color(255, 255, 255));
        rb2.setText("ໂຈະສອນ");
        jPanel2.add(rb2);

        jPanel3.add(jPanel2);

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 42, 470, 340));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 530, 400));

        btnSave.setBackground(new java.awt.Color(255, 102, 0));
        btnSave.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("ບັນທຶກ");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.setIconTextGap(10);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel1.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 450, 160, 40));

        getContentPane().add(jPanel1);

        setSize(new java.awt.Dimension(600, 562));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyReleased
        if (evt.getKeyChar() == '\n') {
            txtLastname.requestFocus();
        }
    }//GEN-LAST:event_txtNameKeyReleased

    private void cmbGenderKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbGenderKeyReleased
        if (evt.getKeyChar() == '\n') {
            txtContact.requestFocus();
        }
    }//GEN-LAST:event_cmbGenderKeyReleased

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        editData();
        this.dispose();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtContactFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtContactFocusLost
        int txt1 = txtContact.getText().length();
        if (!txtContact.getText().isEmpty() && txt1 < 11) {
            JOptionPane.showMessageDialog(this, new message("ກະລຸນາປ້ອນໝາຍເລກໂທລະສັບໃຫ້ຄົບ"));
            txtContact.selectAll();
            txtContact.requestFocus();
        }
    }//GEN-LAST:event_txtContactFocusLost

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(EditTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                EditTeacher dialog = new EditTeacher(new javax.swing.JFrame(), true, teacher);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.ButtonGroup buttonGroup;
    public static javax.swing.JComboBox<String> cmbGender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public static javax.swing.JRadioButton rb1;
    public static javax.swing.JRadioButton rb2;
    public static Swing.TextField txtContact;
    public static Swing.TextField txtID;
    public static Swing.TextField txtLastname;
    public static Swing.TextField txtName;
    // End of variables declaration//GEN-END:variables

    private void editData() {
        try {
            sql = "call updateTeacher(?,?,?,?,?,?)";
            String sex = teacher.gender.get(cmbGender.getSelectedIndex()).toString();
            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, txtID.getText());
            cst.setString(2, txtName.getText());
            cst.setString(3, txtLastname.getText());
            cst.setString(4, sex);
            cst.setString(5, txtContact.getText());
            if (rb1.isSelected()) {
                cst.setInt(6, 1);
            } else if (rb2.isSelected()) {
                cst.setInt(6, 0);
            }
            cst.executeUpdate();
            teacher.showData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
