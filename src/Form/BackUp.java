package Form;

import Database.connectDB;
import Model.message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class BackUp extends javax.swing.JInternalFrame {

    public BackUp() {
        initComponents();
        if ("local".equalsIgnoreCase(connectDB.selectedDB)) {
            btn1.setEnabled(false);
            appendLog("⚠️ ທ່ານກຳລັງໃຊ້ຖານຂໍ້ມູນໃນເຄື່ອງ - ບໍ່ສາມາດສຳຮອງໄດ້");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        logArea = new javax.swing.JTextArea();

        setBorder(null);
        setClosable(true);
        setFrameIcon(null);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(20, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(1550, 90));
        jPanel1.setLayout(new net.miginfocom.swing.MigLayout());

        btn1.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        btn1.setText("ໂຫຼດຂໍ້ມູນຈາກ Server ມາໄວ້ໃນເຄື່ອງຄອມພິວເຕີ");
        btn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn1.setPreferredSize(new java.awt.Dimension(400, 45));
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        jPanel1.add(btn1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        logArea.setBackground(new java.awt.Color(0, 0, 0));
        logArea.setColumns(20);
        logArea.setFont(new java.awt.Font("Saysettha MX", 0, 18)); // NOI18N
        logArea.setForeground(new java.awt.Color(255, 255, 255));
        logArea.setRows(5);
        jScrollPane1.setViewportView(logArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        setBounds(0, 0, 1550, 797);
    }// </editor-fold>//GEN-END:initComponents

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed

        String[] options = {"No", "Yes"};
        int x = JOptionPane.showOptionDialog(
                this,
                new message("ທ່ານແນ່ໃຈທີ່ຈະສຳຮອງຂໍ້ມູນຈາກ Server ມາຍັງເຄື່ອງຄອມພິວເຕີ ແທ້ຫຼືບໍ?"),
                "Confirm",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]
        );

        if (x == 1) {
            logArea.setText("ກຳລັງສຳຮອງຂໍ້ມູນຈາກ Server ➜ ເຄື່ອງ ...\n");
            new Thread(() -> performDataSync("server", "local")).start();
        }

    }//GEN-LAST:event_btn1ActionPerformed

    private void performDataSync(String fromDB, String toDB) {
        try {
            Connection fromConn = connectDB.getConnect(fromDB);
            Connection toConn = connectDB.getConnect(toDB);

            String[] tables = {
                "teacher", "student", "subjecttype", "level", "subject",
                "subjectdetail", "assignment", "registration", "regisdetail",
                "expense", "income", "payment", "exam", "award", "unit",
                "thing_income", "salary", "saldetail", "teach", "user"
            };

            for (String table : tables) {
                try {
                    syncTable(table, fromConn, toConn);
                    appendLog("✓ ຕາຕະລາງ: " + table);
                } catch (SQLException ex) {
                    appendLog("⚠️ ເກີດຂໍ້ຜິດພາດ " + table + ": " + ex.getMessage());
                }
            }

            appendLog("\n✅ ການສຳຮອງຂໍ້ມູນສຳເລັດແລ້ວ!");
        } catch (Exception ex) {
            appendLog("❌ ເກີດຂໍ້ຜິດພາດໃນຂະນະກຳລັງສຳຮອງ: " + ex.getMessage());
        }
    }

    private void syncTable(String tableName, Connection fromConn, Connection toConn) throws SQLException {
        Statement disableFK = toConn.createStatement();
        disableFK.execute("SET FOREIGN_KEY_CHECKS=0");

        Statement truncate = toConn.createStatement();
        truncate.executeUpdate("TRUNCATE TABLE " + tableName);

        Statement sourceStmt = fromConn.createStatement();
        ResultSet rs = sourceStmt.executeQuery("SELECT * FROM " + tableName);
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();

        StringBuilder sb = new StringBuilder("INSERT INTO " + tableName + " VALUES(");
        for (int i = 0; i < columnCount; i++) {
            sb.append("?,");
        }
        sb.setLength(sb.length() - 1);
        sb.append(")");

        PreparedStatement insert = toConn.prepareStatement(sb.toString());

        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                insert.setObject(i, rs.getObject(i));
            }
            insert.executeUpdate();
        }

        disableFK.execute("SET FOREIGN_KEY_CHECKS=1");
    }

    private void appendLog(String message) {
        SwingUtilities.invokeLater(() -> logArea.append(message + "\n"));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea logArea;
    // End of variables declaration//GEN-END:variables
}
