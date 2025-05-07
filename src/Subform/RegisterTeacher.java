package Subform;

import Database.connectDB;
import Model.ButtonEditor;
import Model.ButtonRenderer;
import Model.GenerateID;
import Model.OnlyInputString;
import Model.PhoneNumberValidator;
import Model.comboBoxHeight;
import Model.formatNumber;
import Model.message;
import Model.phoneNumber;
import Swing.RoundPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class RegisterTeacher extends javax.swing.JInternalFrame {

    GenerateID id = new GenerateID();
    Connection con = connectDB.getConnect();
    ArrayList gender = new ArrayList();
    String sql;
    DefaultTableModel model = new DefaultTableModel();
    message m;
    Map<String, String> subIDMap = new HashMap<>();
    phoneNumber num = new phoneNumber(con);
    private Map<JCheckBox, String> checkBoxMaps;
    ButtonGroup group = new ButtonGroup();

    public RegisterTeacher() {
        initComponents();
        model = (DefaultTableModel) table.getModel();
        loadAllData();
        PhoneNumberValidator.Checkvalidate(txtContact);
        table.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JButton("ລຶບ"), table, model));
        formatNumber.apply(txtWage);
    }

    private void loadAllData() {
        // Step 1: Generate ID, then fetch gender
        SwingWorker<Void, Void> idAndGenderWorker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                generateID();
                return null;
            }

            @Override
            protected void done() {
                // Retrieve gender on the EDT
                SwingUtilities.invokeLater(() -> retrieveGender());
            }
        };
        idAndGenderWorker.execute();

        // Step 2: Set up service actions (key listeners, renderers)
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                SwingUtilities.invokeLater(() -> serviceAction()); // UI changes must be on EDT
                return null;
            }
        }.execute();

        // Step 3: Retrieve subjects and display them
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                retrieveSub(subIDMap); // contains UI updates, handled safely inside
                return null;
            }
        }.execute();
    }

    private void generateID() {
        String teacherID = id.generateID("select Max(teacherID)  as ID from teacher", "T", 4);
        lbID.setText(teacherID);
    }

    private void serviceAction() {

        m = new message("ກະລຸນາປ້ອນຕົວອັກສອນເທົ່ານັ້ນ!");
        OnlyInputString ois = new OnlyInputString(m);
        txtName.addKeyListener(ois);
        txtLastname.addKeyListener(ois);

        cmbGender.setRenderer(new comboBoxHeight());
    }

    private void retrieveGender() {
        try {
            sql = "select * from sex";
            gender.clear();
            cmbGender.removeAllItems();
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                gender.add(rs.getString("sexID"));
                cmbGender.addItem(rs.getString("sex"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void retrieveSub(Map<String, String> subIDMap) {
        try {
            sql = "SELECT subject.subName, level.level, subjectdetail.subDetailID "
                    + "FROM subjectdetail "
                    + "INNER JOIN subject ON subjectdetail.subID = subject.subID "
                    + "INNER JOIN level ON subjectdetail.levelID = level.levelID";

            JPanel panelContainer = new JPanel();
            panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));
            panelContainer.setBackground(new Color(212, 230, 249));
            panelContainer.setPreferredSize(new Dimension(500, 400));

            ResultSet rs = con.createStatement().executeQuery(sql);
            Map<String, JPanel> subjectPanels = new HashMap<>();
            Map<JCheckBox, String> checkBoxMap = new HashMap<>();

            while (rs.next()) {
                String subjectName = rs.getString("subName");
                String levelName = rs.getString("level");
                String subDetailID = rs.getString("subDetailID");

                JPanel subjectPanel = subjectPanels.get(subjectName);

                if (subjectPanel == null) {
                    subjectPanel = new JPanel();
                    subjectPanel.setLayout(new GridLayout(1, 8, 10, 0));
                    TitledBorder titledBorder = BorderFactory.createTitledBorder(subjectName);
                    titledBorder.setTitleFont(new Font("Phetsarath OT", Font.BOLD, 16));
                    subjectPanel.setBorder(titledBorder);
                    subjectPanels.put(subjectName, subjectPanel);
                    panelContainer.add(subjectPanel);
                }
                subjectPanel.setBackground(new Color(212, 230, 249));

                JCheckBox checkBox = new JCheckBox(levelName);
                group.add(checkBox);
                checkBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
                checkBox.setHorizontalAlignment(SwingConstants.CENTER);
                checkBox.setPreferredSize(new Dimension(160, 40));
                checkBox.setFont(new Font("Saysettha OT", Font.PLAIN, 16));
                checkBox.setBackground(new Color(255, 255, 255));

                RoundPanel rp = new RoundPanel();
                rp.setPreferredSize(new Dimension(180, 60));
                rp.setBackground(Color.white);
                rp.add(checkBox);
                subjectPanel.add(rp);
                checkBox.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        rp.setBackground(new Color(15, 198, 21));
                        checkBox.setBackground(new Color(15, 198, 21));
                        rp.setForeground(Color.WHITE);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        rp.setBackground(new Color(255, 255, 255));
                        checkBox.setBackground(new Color(255, 255, 255));
                        rp.setForeground(Color.BLACK);
                    }

                });

                checkBoxMap.put(checkBox, subDetailID); // Store checkbox and its ID
                subIDMap.put(subjectName + " " + levelName, subDetailID);
            }

            JScrollPane scrollPane = new JScrollPane(panelContainer);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());

            panel.removeAll();
            panel.add(scrollPane);
            panel.revalidate();
            panel.repaint();

            this.checkBoxMaps = checkBoxMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addData() {
        try {
            StringBuilder selectedSubjects = new StringBuilder();
            for (Map.Entry<JCheckBox, String> entry : checkBoxMaps.entrySet()) {
                if (entry.getKey().isSelected()) {
                    String subDetailID = entry.getValue();
                    String levelName = entry.getKey().getText();

                    String subjectName = "";
                    for (Map.Entry<String, String> subEntry : subIDMap.entrySet()) {
                        if (subEntry.getValue().equals(subDetailID)) {
                            subjectName = subEntry.getKey().split(" ")[0];
                            break;
                        }
                    }
                    selectedSubjects.append(subjectName).append(" ").append(levelName).append(", ");
                }
            }

            if (selectedSubjects.length() > 0) {
                selectedSubjects.setLength(selectedSubjects.length() - 2);
            }

            String selectedSubjectsStr = selectedSubjects.toString();

            boolean duplicate = false;
            for (int i = 0; i < model.getRowCount(); i++) {
                if (model.getValueAt(i, 6).equals(selectedSubjectsStr)) {
                    duplicate = true;
                    break;
                }
            }

            if (duplicate) {
                JOptionPane.showMessageDialog(this, new message("ວິຊາທີ່ລົງທະບຽນຊ້ຳກັນແລ້ວ"));
                return;
            }

            String[] data = {
                lbID.getText(),
                txtName.getText(),
                txtLastname.getText(),
                cmbGender.getSelectedItem().toString(),
                txtContact.getText(),
                txtWage.getText(),
                selectedSubjectsStr
            };

            model.addRow(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveData() {
        try {
            sql = "call insertToTeacher(?,?,?,?,?)";
            String sex = gender.get(cmbGender.getSelectedIndex()).toString();
            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, lbID.getText());
            cst.setString(2, txtName.getText());
            cst.setString(3, txtLastname.getText());
            cst.setString(4, sex);
            cst.setString(5, txtContact.getText());
            cst.executeUpdate();

            int row = table.getRowCount();
            for (int i = 0; i < row; i++) {
                String wage = table.getValueAt(i, 5).toString().replaceAll(",", "");
                String teacherID = table.getValueAt(i, 0).toString();
                String subjectName = table.getValueAt(i, 6).toString();
                String subDetailID = subIDMap.get(subjectName);
                if (subDetailID != null) {
                    sql = "call Assigment(?,?,?)";
                    CallableStatement assignCst = con.prepareCall(sql);
                    assignCst.setString(1, teacherID);
                    assignCst.setString(2, subDetailID);
                    assignCst.setString(3, wage);
                    assignCst.executeUpdate();
                } else {
                    JOptionPane.showMessageDialog(this, new message("ບໍ່ພົບວິຊາ"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertToUser() {
        try {

            sql = "call insertUser(?,?,?)";
            String Auth = "ອາຈານ";
            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, txtName.getText().trim());
            cst.setString(2, txtContact.getText().trim().substring(3));
            cst.setString(3, Auth);
            cst.executeUpdate();

        } catch (Exception e) {
            m = new message("ເກີດຂໍ້ຜິດພາດ" + e.getMessage());
            JOptionPane.showMessageDialog(this, m);
        }
    }

    private void clearData() {
        txtName.setText("");
        txtLastname.setText("");
        txtContact.setText("");
        txtWage.setText("");
        while (table.getRowCount() > 0) {
            model.removeRow(0);
        }
        generateID();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lbID = new Swing.TextField();
        jPanel6 = new javax.swing.JPanel();
        cmbGender = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        txtName = new Swing.TextField();
        jPanel9 = new javax.swing.JPanel();
        txtContact = new Swing.TextField();
        jPanel10 = new javax.swing.JPanel();
        txtLastname = new Swing.TextField();
        jPanel11 = new javax.swing.JPanel();
        txtWage = new Swing.TextField();
        jPanel5 = new javax.swing.JPanel();
        panel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new Model.Table();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setBorder(null);
        setClosable(true);
        setFrameIcon(null);

        jPanel1.setBackground(new java.awt.Color(212, 230, 249));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(20, 20, 20, 20, new java.awt.Color(212, 230, 249)));
        jPanel1.setPreferredSize(new java.awt.Dimension(1538, 400));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        jPanel3.setBackground(new java.awt.Color(212, 230, 249));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ຂໍ້ມູນອາຈານ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Phetsarath OT", 1, 16))); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(1458, 380));
        jPanel3.setLayout(new java.awt.GridLayout(1, 1, 20, 0));

        jPanel4.setBackground(new java.awt.Color(212, 230, 249));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(20, 20, 20, 20, new java.awt.Color(212, 230, 249)));
        jPanel4.setLayout(new java.awt.GridLayout(3, 1, 10, 10));

        jPanel7.setBackground(new java.awt.Color(212, 230, 249));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ລະຫັດ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        lbID.setEditable(false);
        lbID.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        lbID.setForeground(new java.awt.Color(0, 102, 255));
        lbID.setCornerRadius(12.0F);
        lbID.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbID.setPlaceHolder("");
        jPanel7.add(lbID);

        jPanel4.add(jPanel7);

        jPanel6.setBackground(new java.awt.Color(212, 230, 249));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ເພດ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        cmbGender.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        cmbGender.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbGenderKeyReleased(evt);
            }
        });
        jPanel6.add(cmbGender);

        jPanel4.add(jPanel6);

        jPanel8.setBackground(new java.awt.Color(212, 230, 249));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ຊື່", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        txtName.setBorderColor(new java.awt.Color(153, 153, 153));
        txtName.setCornerRadius(12.0F);
        txtName.setPlaceHolder("ປ້ອນຊື່");
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNameKeyReleased(evt);
            }
        });
        jPanel8.add(txtName);

        jPanel4.add(jPanel8);

        jPanel9.setBackground(new java.awt.Color(212, 230, 249));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ເບີຕິດຕໍ່", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        txtContact.setBorderColor(new java.awt.Color(153, 153, 153));
        txtContact.setCornerRadius(12.0F);
        txtContact.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtContact.setPlaceHolder("020XXXXXXXX");
        txtContact.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtContactFocusLost(evt);
            }
        });
        txtContact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtContactKeyReleased(evt);
            }
        });
        jPanel9.add(txtContact);

        jPanel4.add(jPanel9);

        jPanel10.setBackground(new java.awt.Color(212, 230, 249));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ນາມສະກຸນ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel10.setLayout(new java.awt.GridLayout(1, 0));

        txtLastname.setBorderColor(new java.awt.Color(153, 153, 153));
        txtLastname.setCornerRadius(12.0F);
        txtLastname.setPlaceHolder("ປ້ອນນາມສະກຸນ");
        jPanel10.add(txtLastname);

        jPanel4.add(jPanel10);

        jPanel11.setBackground(new java.awt.Color(212, 230, 249));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ຄ່າສອນ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel11.setLayout(new java.awt.GridLayout(1, 0));

        txtWage.setBorderColor(new java.awt.Color(153, 153, 153));
        txtWage.setCornerRadius(12.0F);
        txtWage.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtWage.setPlaceHolder("");
        txtWage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtWageKeyTyped(evt);
            }
        });
        jPanel11.add(txtWage);

        jPanel4.add(jPanel11);

        jPanel3.add(jPanel4);

        jPanel1.add(jPanel3);

        jPanel5.setLayout(new java.awt.BorderLayout(0, 10));

        panel.setBackground(new java.awt.Color(212, 230, 249));
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ເລືອກວິຊາທີ່ຈະສອນ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 1, 16))); // NOI18N
        panel.setLayout(new java.awt.GridLayout(1, 0));
        jPanel5.add(panel, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel5);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 20, 20, 20, new java.awt.Color(242, 242, 242)));
        jPanel2.setLayout(new java.awt.BorderLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ລະຫັດ", "ຊື່", "ນາມສະກຸນ", "ເພດ", "ເບີໂທຕິດຕໍ່", "ຄ່າສອນ", "ວິຊາສອນ", "ລຶບແຖວ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jScrollPane1.setViewportView(table);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel13.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 755, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel13.add(jPanel14);

        jPanel12.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel12.setLayout(new net.miginfocom.swing.MigLayout());

        btnAdd.setBackground(new java.awt.Color(0, 102, 255));
        btnAdd.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("ບັນທຶກ");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.setPreferredSize(new java.awt.Dimension(380, 60));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel12.add(btnAdd);

        btnSave.setBackground(new java.awt.Color(255, 102, 0));
        btnSave.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("ຢືນຢັນການລົງທະບຽນ");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.setPreferredSize(new java.awt.Dimension(380, 60));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel12.add(btnSave);

        jPanel13.add(jPanel12);

        jPanel2.add(jPanel13, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        setBounds(0, 0, 1550, 797);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbGenderKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbGenderKeyReleased
        if (evt.getKeyChar() == '\n') {
            txtContact.requestFocus();
        }
    }//GEN-LAST:event_cmbGenderKeyReleased

    private void txtNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyReleased
        if (evt.getKeyChar() == '\n') {
            txtLastname.requestFocus();
        }
    }//GEN-LAST:event_txtNameKeyReleased

    private void txtContactFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtContactFocusLost
        sql = "SELECT * FROM teacher WHERE Contact = ?";
        num.validatePhoneNumber(txtContact, sql, "ໝາຍເລກໂທລະສັບນີ້ມີແລ້ວ!");
    }//GEN-LAST:event_txtContactFocusLost

    private void txtContactKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactKeyReleased
        if (evt.getKeyChar() == '\n') {
            txtWage.requestFocus();
        }
    }//GEN-LAST:event_txtContactKeyReleased

    private void txtWageKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtWageKeyTyped
        char c = evt.getKeyChar();
        String text = txtWage.getText();

        if (!Character.isDigit(c)) {
            evt.consume();
        } else {
            if (text.isEmpty() && c == '0') {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtWageKeyTyped

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (txtName.getText().equals("") || txtContact.getText().equals("") || txtLastname.getText().equals("") || txtWage.getText().equals("")) {
            JOptionPane.showMessageDialog(this, new message("ກະລຸນາປ້ອນຂໍ້ມູນໃຫ້ຄົບຖ້ວນກ່ອນ"));
        } else {
            addData();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (table.getRowCount() > 0) {
            saveData();
            insertToUser();
            clearData();
            JOptionPane.showMessageDialog(this, new message("ລົງທະບຽນສຳເລັດແລ້ວ"));
        } else {
            JOptionPane.showMessageDialog(this, new message("ກະລຸນາເພີ່ມຂໍ້ມູນກ່ອນບັນທຶກ!"));
        }
    }//GEN-LAST:event_btnSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbGender;
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
    private Swing.TextField lbID;
    private javax.swing.JPanel panel;
    private Model.Table table;
    private Swing.TextField txtContact;
    private Swing.TextField txtLastname;
    private Swing.TextField txtName;
    private Swing.TextField txtWage;
    // End of variables declaration//GEN-END:variables
}
