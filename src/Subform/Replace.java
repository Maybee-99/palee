package Subform;

import Database.connectDB;
import Model.comboBoxHeight;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Replace extends javax.swing.JDialog {

    String sql;
    Connection con = connectDB.getConnect();
    ArrayList tch = new ArrayList();

    public Replace(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cmbTeacher.setRenderer(new comboBoxHeight());
    }

    private void retrieveSubType() {
        try {
            sql = "select * from teacher";
            tch.clear();
            cmbTeacher.removeAllItems();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                tch.add(rs.getString("teacherID"));
                cmbTeacher.addItem(rs.getString("teacherName") + " " + rs.getString("Lastname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadsubjectData() {
        try {
            sql = "select * from assignment "
                    + " inner join subjectdetail on assignment.subDetailID=subjectdetail.subDetailID"
                    + " inner join subject on subjectdetail.subID=subject.subID"
                    + " inner join level on subjectdetail.levelID=level.levelID"
                    + " where teacherID=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, Teaches.lbID.getText());
            ResultSet rs = pst.executeQuery();

            Teaches.cmbSubject.removeAllItems();
            Teaches.subject.clear();

            while (rs.next()) {
                String SubjectID = rs.getString("subdetailID");
                String Subject = rs.getString("subName");
                String Level = rs.getString("level");
                String combine = Subject + " " + Level;

                Teaches.cmbSubject.addItem(combine);
                Teaches.subject.add(SubjectID);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbTeacher = new javax.swing.JComboBox<>();
        btnSend = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 51, 51));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        cmbTeacher.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        cmbTeacher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSend.setBackground(new java.awt.Color(51, 153, 255));
        btnSend.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnSend.setForeground(new java.awt.Color(255, 255, 255));
        btnSend.setText("ຕົກລົງ");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("ເລືອກອາຈານ");
        jLabel4.setPreferredSize(new java.awt.Dimension(120, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbTeacher, 0, 355, Short.MAX_VALUE))
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(58, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(293, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(cmbTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(36, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(212, Short.MAX_VALUE)))
        );

        setSize(new java.awt.Dimension(485, 304));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        retrieveSubType();
    }//GEN-LAST:event_formWindowOpened

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        int id = cmbTeacher.getSelectedIndex();
        if (id >= 0) {
            String teacherID = (String) tch.get(id);
            String teacherName = cmbTeacher.getSelectedItem().toString();
            Teaches.lbID.setText(teacherID);
            Teaches.lbName.setText(teacherName);
            loadsubjectData();
        }

        this.dispose();
    }//GEN-LAST:event_btnSendActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Replace dialog = new Replace(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnSend;
    private javax.swing.JComboBox<String> cmbTeacher;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
