package Subform;

import javax.swing.*;
import Database.connectDB;
import Form.LoginPage;
import Model.CurrentDate;
import Model.message;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Model.comboBoxHeight;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.border.TitledBorder;

public class Teaches extends javax.swing.JFrame {

    Connection con = connectDB.getConnect();
    public static ArrayList subject = new ArrayList();
    String sql;
    DefaultTableModel model = new DefaultTableModel();

    CurrentDate dn = new CurrentDate();

    public Teaches(String teacherID, String fullName) {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        txtID.setText(teacherID);
        txtTeacher.setText(fullName);

        lbID.setText(teacherID);
        lbName.setText(fullName);
        model = (DefaultTableModel) table.getModel();
        showData();
        cmbSubject.setRenderer(new comboBoxHeight());
        table.fixTable(jScrollPane1);
        showTime();
        reportHour();

    }

    private void reportHour() {
        JPanel summaryPanel = new JPanel();

        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
        showHourSummaryInPanel(summaryPanel);

        TitledBorder border = BorderFactory.createTitledBorder("ລາຍງານຊົ່ວໂມງສອນ");
        border.setTitleFont(new Font("Phetsarath OT", Font.BOLD, 20));
        border.setTitleColor(Color.BLACK);
        summaryPanel.setBorder(border);

        jPanel14.setLayout(new BorderLayout());
        jPanel14.add(summaryPanel, BorderLayout.CENTER);
    }

    private void showTime() {
        Timer timer = new Timer(0, (ActionEvent e) -> {
            java.util.Date date = new java.util.Date();
            SimpleDateFormat sdf = new SimpleDateFormat(" ວັນ:EEEE, ວັນທີ່:dd/MM/yyyy, ເວລາ:HH:mm:ss", new Locale("lo", "LA"));
            DateFormatSymbols dfs = new DateFormatSymbols(new Locale("lo", "LA"));
            dfs.setShortWeekdays(new String[]{"", "ວັນອາທິດ", "ວັນຈັນ", "ວັນອັງຄານ", "ວັນພຸດ", "ວັນພະຫັດ", "ວັນສຸກ", "ວັນເສົາ"});
            sdf.setDateFormatSymbols(dfs);
            String formattedTime = sdf.format(date);
            lbtime.setText(formattedTime);
        });
        timer.start();
    }

    private void showData() {
        try {
            clearTB();
            sql = "SELECT "
                    + " teach.teachDate AS dt, "
                    + " teach.teacherID, "
                    + " subject.subName, "
                    + " teach.hour, "
                    + " level.level, "
                    + " teach.teachCost,"
                    + " teach.total,"
                    + " teach.teachCondition, "
                    + " CASE "
                    + "     WHEN teach.teacherID = teach.teachCondition THEN N'ສອນປົກກະຕິ' "
                    + "     ELSE N'ສອນແທນ' "
                    + " END AS status "
                    + "FROM teach "
                    + "LEFT JOIN subjectdetail ON teach.subDetailID = subjectdetail.subDetailID "
                    + "LEFT JOIN subject ON subjectdetail.subID = subject.subID "
                    + "LEFT JOIN level ON subjectdetail.levelID = level.levelID "
                    + "WHERE teach.teacherID = ? AND MONTH(teach.teachDate) = MONTH(CURRENT_DATE())"
                    + "ORDER BY dt DESC";

            PreparedStatement psm = con.prepareStatement(sql);
            psm.setString(1, txtID.getText());
            //psm.setString(2, lbID.getText());
            ResultSet rs = psm.executeQuery();

            DecimalFormat df = new DecimalFormat("#,###");

            while (rs.next()) {
                int w = rs.getInt("teachCost");
                int t = rs.getInt("total");

                String[] data = {
                    rs.getString("dt"),
                    rs.getString("subName") + " " + rs.getString("level"),
                    rs.getString("hour"),
                    df.format(w), // Show wage here
                    df.format(t),
                    rs.getString("status")
                };
                model.addRow(data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showHourSummaryInPanel(JPanel containerPanel) {
        try {
            containerPanel.removeAll();
            containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

            sql = "SELECT subject.subName, level.level, "
                    + "CASE WHEN teach.teacherID = teach.teachCondition THEN N'ສອນປົກກະຕິ' "
                    + "ELSE N'ສອນແທນ' END AS status, "
                    + "SUM(teach.hour) AS totalHour "
                    + "FROM teach "
                    + "LEFT JOIN subjectdetail ON teach.subDetailID = subjectdetail.subDetailID "
                    + "LEFT JOIN subject ON subjectdetail.subID = subject.subID "
                    + "LEFT JOIN level ON subjectdetail.levelID = level.levelID "
                    + "WHERE teach.teacherID = ? AND MONTH(teach.teachDate) = MONTH(CURRENT_DATE()) "
                    + "GROUP BY subject.subName, level.level, status "
                    + "ORDER BY status, subject.subName, level.level";

            PreparedStatement psm = con.prepareStatement(sql);
            psm.setString(1, txtID.getText());
            ResultSet rs = psm.executeQuery();

            Map<String, List<String>> summaryMap = new LinkedHashMap<>();
            while (rs.next()) {
                String status = rs.getString("status");
                String subjects = rs.getString("subName");
                String level = rs.getString("level");
                int hour = rs.getInt("totalHour");

                String item = subjects + " " + level + " (" + hour + " ຊົ່ວໂມງ)";

                if (!summaryMap.containsKey(status)) {
                    summaryMap.put(status, new ArrayList<>());
                }
                summaryMap.get(status).add(item);
            }

            for (Map.Entry<String, List<String>> entry : summaryMap.entrySet()) {
                String statusTitle = entry.getKey();
                List<String> items = entry.getValue();

                StringBuilder line = new StringBuilder("<html><b>" + statusTitle + ":</b> ");
                line.append(String.join(", ", items));
                line.append("</html>");

                JLabel label = new JLabel(line.toString());
                label.setFont(new Font("Phetsarath OT", Font.PLAIN, 18));

                containerPanel.add(label);
            }

            containerPanel.revalidate();
            containerPanel.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearTB() {
        int row = table.getRowCount() - 1;
        while (row >= 0) {
            model.removeRow(row--);
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
            pst.setString(1, txtID.getText());
            ResultSet rs = pst.executeQuery();
            cmbSubject.removeAllItems();
            while (rs.next()) {
                String SubjectID = rs.getString("subdetailID");
                String Subject = rs.getString("subName");
                String Level = rs.getString("level");
                String combine = Subject + " " + Level;
                cmbSubject.addItem(combine);
                subject.add(SubjectID);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveData() {
        try {
            String s = subject.get(cmbSubject.getSelectedIndex()).toString();
            String date = dn.DateTimeNow("yyyy-MM-dd hh:mm:ss");

            sql = "INSERT INTO teach (teachDate, teacherID, subDetailID, hour, teachCost, teachCondition)"
                    + "VALUES (?, ?, ?, ?, ?, ?);";

            String wageQuery = "SELECT wage FROM assignment WHERE teacherID = ? LIMIT 1";
            PreparedStatement psm1 = con.prepareStatement(wageQuery);
            psm1.setString(1, txtID.getText());
            ResultSet rs1 = psm1.executeQuery();

            String w = "";
            if (rs1.next()) {
                w = rs1.getString("wage");
            }

            PreparedStatement cst = con.prepareStatement(sql);
            cst.setString(1, date);
            cst.setString(2, txtID.getText());
            cst.setString(3, s);
            cst.setString(4, txtHours.getText());
            cst.setInt(5, Integer.parseInt(w));
            cst.setString(6, lbID.getText());
            cst.executeUpdate();
            showData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtID = new Swing.TextField();
        btnReplace = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtTeacher = new Swing.TextField();
        jPanel6 = new javax.swing.JPanel();
        cmbSubject = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        txtHours = new Swing.TextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbtime = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbID = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new Model.Table();
        jPanel14 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setBackground(new java.awt.Color(0, 102, 255));
        jLabel1.setFont(new java.awt.Font("Saysettha OT", 1, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ຟອມບັນທຶກການສອນສຳລັບອາຈານ");
        jLabel1.setIconTextGap(20);
        jLabel1.setOpaque(true);
        jLabel1.setPreferredSize(new java.awt.Dimension(37, 60));
        jPanel1.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jPanel7.setBackground(new java.awt.Color(212, 230, 249));
        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(30, 30, 30, 30, new java.awt.Color(212, 230, 249)));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(212, 230, 249));
        jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true), javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(212, 230, 249))));
        jPanel3.setPreferredSize(new java.awt.Dimension(180, 200));
        jPanel3.setLayout(new java.awt.GridLayout(2, 2, 20, 10));

        jPanel4.setBackground(new java.awt.Color(212, 230, 249));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ລະຫັດອາຈານ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Phetsarath OT", 0, 16))); // NOI18N
        jPanel4.setLayout(new java.awt.BorderLayout(20, 0));

        txtID.setEditable(false);
        txtID.setBackground(new java.awt.Color(255, 255, 255));
        txtID.setCornerRadius(12.0F);
        txtID.setPlaceHolder("");
        txtID.setPreferredSize(new java.awt.Dimension(400, 27));
        jPanel4.add(txtID, java.awt.BorderLayout.CENTER);

        btnReplace.setBackground(new java.awt.Color(0, 102, 255));
        btnReplace.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnReplace.setForeground(new java.awt.Color(255, 255, 255));
        btnReplace.setText("ສອນແທນ");
        btnReplace.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReplace.setPreferredSize(new java.awt.Dimension(120, 48));
        btnReplace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReplaceActionPerformed(evt);
            }
        });
        jPanel4.add(btnReplace, java.awt.BorderLayout.EAST);

        jPanel3.add(jPanel4);

        jPanel2.setBackground(new java.awt.Color(212, 230, 249));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ຊື່ອາຈານ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Phetsarath OT", 0, 16))); // NOI18N
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        txtTeacher.setEditable(false);
        txtTeacher.setBackground(new java.awt.Color(255, 255, 255));
        txtTeacher.setCornerRadius(12.0F);
        txtTeacher.setPlaceHolder("");
        txtTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTeacherActionPerformed(evt);
            }
        });
        jPanel2.add(txtTeacher);

        jPanel3.add(jPanel2);

        jPanel6.setBackground(new java.awt.Color(212, 230, 249));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ວິຊາສອນ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Phetsarath OT", 0, 16))); // NOI18N
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        cmbSubject.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        jPanel6.add(cmbSubject);

        jPanel3.add(jPanel6);

        jPanel5.setBackground(new java.awt.Color(212, 230, 249));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ຈຳນວນຊົ່ວໂມງ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Phetsarath OT", 0, 16))); // NOI18N
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        txtHours.setEditable(false);
        txtHours.setBackground(new java.awt.Color(255, 255, 255));
        txtHours.setText("2");
        txtHours.setCornerRadius(12.0F);
        txtHours.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtHours.setPlaceHolder("");
        jPanel5.add(txtHours);

        jPanel3.add(jPanel5);

        jPanel7.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel9.setPreferredSize(new java.awt.Dimension(100, 55));
        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        jPanel10.setBackground(new java.awt.Color(212, 230, 249));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT);
        flowLayout1.setAlignOnBaseline(true);
        jPanel10.setLayout(flowLayout1);

        jLabel2.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("ສະຖານທີ່:");
        jLabel2.setPreferredSize(new java.awt.Dimension(80, 50));
        jPanel10.add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("ສູນປາລີ ");
        jLabel3.setPreferredSize(new java.awt.Dimension(80, 50));
        jPanel10.add(jLabel3);

        lbtime.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        lbtime.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbtime.setText("time");
        lbtime.setPreferredSize(new java.awt.Dimension(450, 50));
        jPanel10.add(lbtime);

        jPanel9.add(jPanel10);

        jPanel11.setBackground(new java.awt.Color(212, 230, 249));
        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(212, 230, 249)));
        jPanel11.setLayout(new java.awt.BorderLayout(20, 0));

        jPanel13.setBackground(new java.awt.Color(212, 230, 249));
        jPanel13.setPreferredSize(new java.awt.Dimension(400, 100));
        jPanel13.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        btnSave.setBackground(new java.awt.Color(255, 102, 0));
        btnSave.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("ບັນທຶກ");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.setPreferredSize(new java.awt.Dimension(150, 48));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel13.add(btnSave);

        btnReset.setBackground(new java.awt.Color(255, 0, 255));
        btnReset.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("ເຄຼຍ");
        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.setPreferredSize(new java.awt.Dimension(150, 48));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel13.add(btnReset);

        jPanel11.add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel12.setBackground(new java.awt.Color(0, 153, 102));
        jPanel12.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel4.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("ລະຫັດອາຈານ:");
        jLabel4.setPreferredSize(new java.awt.Dimension(120, 40));
        jPanel12.add(jLabel4);

        lbID.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        lbID.setForeground(new java.awt.Color(255, 255, 255));
        lbID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbID.setText(".....................");
        lbID.setPreferredSize(new java.awt.Dimension(120, 40));
        jPanel12.add(lbID);

        jLabel5.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("ຊື່ອາຈານ:");
        jLabel5.setPreferredSize(new java.awt.Dimension(120, 40));
        jPanel12.add(jLabel5);

        lbName.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbName.setText(".......................");
        lbName.setPreferredSize(new java.awt.Dimension(120, 40));
        jPanel12.add(lbName);

        jPanel11.add(jPanel12, java.awt.BorderLayout.EAST);

        jPanel9.add(jPanel11);

        jPanel8.add(jPanel9, java.awt.BorderLayout.PAGE_START);

        table.setAutoCreateRowSorter(true);
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ວັນທີ", "ວິຊາ", "ຈຳນວນຊົ່ວໂມງ", "ຄ່າສອນ", "ເປັນເງິນ", "ໝາຍເຫດ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("Saysettha OT", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(table);

        jPanel8.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel14.setPreferredSize(new java.awt.Dimension(10, 100));
        jPanel8.add(jPanel14, java.awt.BorderLayout.PAGE_END);

        jPanel7.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel7, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1);

        setSize(new java.awt.Dimension(1554, 795));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            String subDetailID = subject.get(cmbSubject.getSelectedIndex()).toString();
            String today = dn.DateTimeNow("yyyy-MM-dd");
            int maxPerDay = 1;
            String sqlMax
                    = "SELECT st.maxPerDay "
                    + "FROM subjectdetail sd "
                    + " JOIN subject s  ON sd.subID    = s.subID "
                    + " JOIN subjecttype st ON s.subTypeID = st.subTypeID "
                    + "WHERE sd.subDetailID = ?";
            PreparedStatement pstMax = con.prepareStatement(sqlMax);
            pstMax.setString(1, subDetailID);
            ResultSet rsMax = pstMax.executeQuery();
            if (rsMax.next()) {
                maxPerDay = rsMax.getInt("maxPerDay");
            }

            String sqlCnt
                    = "SELECT COUNT(*) AS cnt "
                    + "FROM teach "
                    + "WHERE subDetailID = ? AND DATE(teachDate) = ?";
            PreparedStatement pstCnt = con.prepareStatement(sqlCnt);
            pstCnt.setString(1, subDetailID);
            pstCnt.setString(2, today);
            ResultSet rsCnt = pstCnt.executeQuery();
            int count = rsCnt.next() ? rsCnt.getInt("cnt") : 0;

            if (count >= maxPerDay) {
                JOptionPane.showMessageDialog(this,
                        new message("ວິຊານີ້ບັນທຶກໄດ້ສູງສຸດ "
                                + maxPerDay + " ຄັ້ງ/ມື້"));
            } else {
                saveData();
                reportHour();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        loadsubjectData();
    }//GEN-LAST:event_formWindowOpened

    private void txtTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTeacherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTeacherActionPerformed

    private void btnReplaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReplaceActionPerformed
        Replace rpl = new Replace(this, true);
        rpl.setVisible(true);
    }//GEN-LAST:event_btnReplaceActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        lbID.setText(txtID.getText());
        lbName.setText(txtTeacher.getText());
        loadsubjectData();

    }//GEN-LAST:event_btnResetActionPerformed

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
            java.util.logging.Logger.getLogger(Teaches.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Teaches.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Teaches.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Teaches.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Teaches(LoginPage.teacherID, LoginPage.Fullname).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReplace;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    public static javax.swing.JComboBox<String> cmbSubject;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lbID;
    public static javax.swing.JLabel lbName;
    private javax.swing.JLabel lbtime;
    private Model.Table table;
    private Swing.TextField txtHours;
    private Swing.TextField txtID;
    private Swing.TextField txtTeacher;
    // End of variables declaration//GEN-END:variables
}
