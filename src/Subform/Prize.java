package Subform;

import Database.connectDB;
import Model.CurrentDate;
import Model.GenerateID;
import Model.message;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import Model.ButtonEditor;
import Model.ButtonRenderer;
import Model.StudentSelectionListener;
import Model.comboBoxHeight;
import Model.formatNumber;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import com.formdev.flatlaf.ui.FlatRoundBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.MatteBorder;

public class Prize extends javax.swing.JInternalFrame {

    Connection con = connectDB.getConnect();
    ArrayList subject = new ArrayList();
    ArrayList semester = new ArrayList();
    DecimalFormat df = new DecimalFormat("#,###");
    String sql;
    String stdID;
    String stdName;
    String Lastname;
    String school;
    String dis;
    String pro;
    DefaultTableModel model = new DefaultTableModel();
    CurrentDate dn = new CurrentDate();
    GenerateID id = new GenerateID();

    public Prize() {
        initComponents();
        formatNumber.apply(txtMoney);
        cmbExam.setRenderer(new comboBoxHeight());
        cmbSubject.setRenderer(new comboBoxHeight());
        cmbRank.setRenderer(new comboBoxHeight());
        generateBill();
        jPanel23.setBorder(BorderFactory.createCompoundBorder(new FlatRoundBorder(),new MatteBorder(15,15,15,15,new Color(212,230,249)) {
        }));

        table.fixTable(jScrollPane2);

        cmbSubject.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    txtScore.requestFocus();
                }
            }
        });

        cmbRank.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    txtMoney.requestFocus();
                }
            }
        });

        model = (DefaultTableModel) table.getModel();
        table.getColumnModel().getColumn(9).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(9).setCellEditor(new ButtonEditor(new JButton("ລຶບ"), table, model));

        table.getModel().addTableModelListener((TableModelEvent e) -> {
            if (e.getType() == TableModelEvent.INSERT || e.getType() == TableModelEvent.DELETE) {
                Total();
            }
        });
        txtStudentID.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSubjectComboBox(); // Call this method when text is inserted
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSubjectComboBox(); // Call this method when text is removed
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSubjectComboBox(); // Call this method if the text is changed in any other way
            }
        });

    }

    private void generateBill() {
        String studentID = id.generateID("select Max(examID) as ID from exam", "EX", 3);
        lbBill.setText(studentID);
    }

    public void setStudentID(String studentID, String studentName, String Lastname, String school, String dis, String pro) {
        this.stdID = studentID;
        this.stdName = studentName;
        this.Lastname = Lastname;
        this.school = school;
        this.dis = dis;
        this.pro = pro;
        lbSchool.setText(this.school);
        lbDistrict.setText(this.dis);
        lbPro.setText(this.pro);
        txtStudentID.setText(this.stdID);
        txtFullName.setText(this.stdName + " " + this.Lastname);

    }

    private void loadSemester() {
        try {
            sql = "SELECT * FROM semester";
            ResultSet rs = con.createStatement().executeQuery(sql);
            semester.clear();
            cmbExam.removeAllItems();
            while (rs.next()) {
                semester.add(rs.getString("semID"));
                cmbExam.addItem(rs.getString("semester"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel13 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cmbExam = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtStudentID = new Swing.TextField();
        btnSearch = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        txtFullName = new Swing.TextField();
        jPanel17 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lbPro = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lbDistrict = new javax.swing.JLabel();
        lbSchool1 = new javax.swing.JLabel();
        lbSchool = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        cmbSubject = new javax.swing.JComboBox<>();
        jPanel19 = new javax.swing.JPanel();
        txtScore = new Swing.TextField();
        jPanel20 = new javax.swing.JPanel();
        cmbRank = new javax.swing.JComboBox<>();
        jPanel21 = new javax.swing.JPanel();
        txtMoney = new Swing.TextField();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new Model.Table();
        jPanel11 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        lbBill = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();

        setBorder(null);
        setClosable(true);
        setTitle("ມອບລາງວັນໃຫ້ນັກຮຽນ");
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
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel13.setBorder(javax.swing.BorderFactory.createMatteBorder(20, 20, 20, 20, new java.awt.Color(212, 230, 249)));
        jPanel13.setLayout(new java.awt.BorderLayout());

        jPanel23.setBackground(new java.awt.Color(212, 230, 249));
        jPanel23.setPreferredSize(new java.awt.Dimension(1690, 400));
        jPanel23.setLayout(new java.awt.GridLayout(1, 0, 50, 0));

        jPanel4.setBackground(new java.awt.Color(212, 230, 249));
        jPanel4.setLayout(new java.awt.GridLayout(4, 1, 0, 10));

        jPanel1.setBackground(new java.awt.Color(212, 230, 249));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ຜົນສອບເສັງ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        cmbExam.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jPanel1.add(cmbExam);

        jPanel4.add(jPanel1);

        jPanel12.setBackground(new java.awt.Color(212, 230, 249));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "ລະຫັດນັກຮຽນ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel12.setFont(new java.awt.Font("Saysettha OT", 0, 12)); // NOI18N
        jPanel12.setLayout(new java.awt.GridLayout(1, 0));

        jPanel5.setBackground(new java.awt.Color(212, 230, 249));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 5, new java.awt.Color(212, 230, 249)));
        jPanel5.setLayout(new java.awt.BorderLayout(10, 0));

        txtStudentID.setCornerRadius(12.0F);
        txtStudentID.setPlaceHolder("ປ້ອນລະຫັດນັກຮຽນ");
        txtStudentID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStudentIDKeyReleased(evt);
            }
        });
        jPanel5.add(txtStudentID, java.awt.BorderLayout.CENTER);

        btnSearch.setBackground(new java.awt.Color(0, 102, 255));
        btnSearch.setFont(new java.awt.Font("Saysettha OT", 0, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("ຄົ້ນຫາ");
        btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel5.add(btnSearch, java.awt.BorderLayout.LINE_END);

        jPanel12.add(jPanel5);

        jPanel4.add(jPanel12);

        jPanel16.setBackground(new java.awt.Color(212, 230, 249));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(242, 242, 242)), "ຊື່ນັກຮຽນ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel16.setLayout(new java.awt.GridLayout(1, 0));

        txtFullName.setEditable(false);
        txtFullName.setCornerRadius(12.0F);
        txtFullName.setPlaceHolder("");
        jPanel16.add(txtFullName);

        jPanel4.add(jPanel16);

        jPanel17.setBackground(new java.awt.Color(212, 230, 249));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204))), "ມາຈາກ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel17.setLayout(new java.awt.GridLayout(1, 0));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbPro.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        lbPro.setForeground(new java.awt.Color(0, 51, 255));
        lbPro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbPro.setText("ແຂວງ");
        jPanel3.add(lbPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 160, 50));

        jLabel21.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("ເມືອງ:");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 50, 50));

        jLabel22.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("ແຂວງ:");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 50, 50));

        lbDistrict.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        lbDistrict.setForeground(new java.awt.Color(0, 51, 255));
        lbDistrict.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbDistrict.setText("ເມືອງ");
        jPanel3.add(lbDistrict, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 130, 50));

        lbSchool1.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        lbSchool1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbSchool1.setText("ໂຮງຮຽນ:");
        jPanel3.add(lbSchool1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 50));

        lbSchool.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        lbSchool.setForeground(new java.awt.Color(0, 51, 255));
        lbSchool.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbSchool.setText("ໂຮງຮຽນ");
        jPanel3.add(lbSchool, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 210, 50));

        jPanel17.add(jPanel3);

        jPanel4.add(jPanel17);

        jPanel23.add(jPanel4);

        jPanel22.setBackground(new java.awt.Color(212, 230, 249));
        jPanel22.setLayout(new java.awt.GridLayout(4, 2, 0, 10));

        jPanel18.setBackground(new java.awt.Color(212, 230, 249));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(242, 242, 242)), "ວິຊາ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel18.setLayout(new java.awt.GridLayout(1, 0));

        cmbSubject.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jPanel18.add(cmbSubject);

        jPanel22.add(jPanel18);

        jPanel19.setBackground(new java.awt.Color(212, 230, 249));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(242, 242, 242)), "ຄະແນນ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel19.setLayout(new java.awt.GridLayout(1, 0));

        txtScore.setCornerRadius(12.0F);
        txtScore.setPlaceHolder("ປ້ອນຄະແນນ");
        txtScore.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtScoreKeyTyped(evt);
            }
        });
        jPanel19.add(txtScore);

        jPanel22.add(jPanel19);

        jPanel20.setBackground(new java.awt.Color(212, 230, 249));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(242, 242, 242)), "ຈັດທີ່", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel20.setLayout(new java.awt.GridLayout(1, 0));

        cmbRank.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        cmbRank.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "ຊົມເຊີຍ" }));
        jPanel20.add(cmbRank);

        jPanel22.add(jPanel20);

        jPanel21.setBackground(new java.awt.Color(212, 230, 249));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(242, 242, 242)), "ລາງວັນ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Saysettha MX", 0, 16))); // NOI18N
        jPanel21.setLayout(new java.awt.GridLayout(1, 0));

        txtMoney.setCornerRadius(12.0F);
        txtMoney.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtMoney.setPlaceHolder("");
        txtMoney.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMoneyKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMoneyKeyTyped(evt);
            }
        });
        jPanel21.add(txtMoney);

        jPanel22.add(jPanel21);

        jPanel23.add(jPanel22);

        jPanel13.add(jPanel23, java.awt.BorderLayout.PAGE_START);

        jPanel15.setMaximumSize(new java.awt.Dimension(32767, 460));
        jPanel15.setMinimumSize(new java.awt.Dimension(36, 460));
        jPanel15.setPreferredSize(new java.awt.Dimension(100, 460));
        jPanel15.setRequestFocusEnabled(false);
        jPanel15.setLayout(new java.awt.BorderLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ລະຫັດນັກຮຽນ", "ຊື່", "ໂຮງຮຽນ", "ເມືອງ", "ແຂວງ", "ວິຊາ", "ຄະແນນ", "ຈັດທີ່", "ລາງວັນ", "ລຶບແຖວ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("Saysettha OT", 0, 14)); // NOI18N
        jScrollPane2.setViewportView(table);

        jPanel15.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel11.setBackground(new java.awt.Color(212, 230, 249));
        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(212, 230, 249)));
        jPanel11.setMaximumSize(new java.awt.Dimension(32767, 60));
        jPanel11.setMinimumSize(new java.awt.Dimension(1565, 60));
        jPanel11.setPreferredSize(new java.awt.Dimension(100, 70));
        jPanel11.setLayout(new java.awt.GridLayout(1, 0, 50, 0));

        jPanel10.setPreferredSize(new java.awt.Dimension(725, 100));
        jPanel10.setLayout(new java.awt.GridLayout(1, 0));

        jPanel7.setBackground(new java.awt.Color(0, 153, 153));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel20.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("ເລກບິນ:");
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel20.setPreferredSize(new java.awt.Dimension(60, 50));
        jPanel7.add(jLabel20);

        lbBill.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbBill.setForeground(new java.awt.Color(255, 255, 255));
        lbBill.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbBill.setText("1234");
        lbBill.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel7.add(lbBill);

        jLabel2.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("ຈຳນວນເງິນທັງໝົດ:");
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel7.add(jLabel2);

        lbTotal.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        lbTotal.setForeground(new java.awt.Color(255, 255, 255));
        lbTotal.setText("total");
        lbTotal.setPreferredSize(new java.awt.Dimension(250, 50));
        jPanel7.add(lbTotal);

        jPanel10.add(jPanel7);

        jPanel11.add(jPanel10);

        jPanel6.setPreferredSize(new java.awt.Dimension(725, 100));
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setBackground(new java.awt.Color(212, 230, 249));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 100));
        jPanel2.setLayout(new net.miginfocom.swing.MigLayout());

        btnAdd.setBackground(new java.awt.Color(0, 102, 255));
        btnAdd.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("ເພີ່ມ");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.setPreferredSize(new java.awt.Dimension(200, 50));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel2.add(btnAdd);

        btnPrint.setBackground(new java.awt.Color(0, 204, 51));
        btnPrint.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setText("ພິມບິນ");
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrint.setPreferredSize(new java.awt.Dimension(200, 50));
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        jPanel2.add(btnPrint);

        jPanel6.add(jPanel2);

        jPanel11.add(jPanel6);

        jPanel15.add(jPanel11, java.awt.BorderLayout.PAGE_START);

        jPanel13.add(jPanel15, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel13);

        setBounds(0, 0, 1550, 794);
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        if (txtStudentID.getText().equals("")) {
            loadsubjectData();
        } else {
            updateSubjectComboBox();
        }
        loadSemester();
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        addData();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        int row = table.getRowCount();
        if (row > 0) {
            saveData();
            printBill();
            generateBill();
            while (table.getRowCount() > 0) {
                model.removeRow(0);
            }
        } else {
            JOptionPane.showMessageDialog(this, new message("ກະລຸນາເພີ່ມຂໍ້ມູນໃສ່ຕາຕະລາງກ່ອນ"));
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void txtScoreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtScoreKeyTyped
        ((AbstractDocument) txtScore.getDocument()).setDocumentFilter(new DigitFilter());
    }//GEN-LAST:event_txtScoreKeyTyped

    private void txtMoneyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMoneyKeyReleased
        if (evt.getKeyChar() == '\n') {
            addData();
        }
    }//GEN-LAST:event_txtMoneyKeyReleased
    public void Search(String data) {
        try {
            sql = " SELECT *"
                    + " FROM student"
                    + " inner join district on student.districtID=district.districtID"
                    + " inner join province on district.provinceID=province.provinceID"
                    + " WHERE  student.stdID =?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, data);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                rs.getString("stdID");
                txtFullName.setText(rs.getString("stdName") + " " + rs.getString("Lastname"));
                lbSchool.setText(rs.getString("school"));
                lbDistrict.setText(rs.getString("districtName"));
                lbPro.setText(rs.getString("provinceName"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void txtStudentIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStudentIDKeyReleased
        Search(txtStudentID.getText());
    }//GEN-LAST:event_txtStudentIDKeyReleased

    private void txtMoneyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMoneyKeyTyped
        char c = evt.getKeyChar();
        String text = txtMoney.getText();

        if (!Character.isDigit(c)) {
            evt.consume();
        } else {
            if (text.isEmpty() && c == '0') {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtMoneyKeyTyped

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        SearchStuent st = new SearchStuent(null, true, new StudentSelectionListener() {
            @Override
            public void onStudentSelected(String id, String fname,String lname,String gender,String ph1,String ph2, String school, String dis, String pro) {
                // Set data to your Prize form components
                txtStudentID.setText(id);
                txtFullName.setText(fname+" "+lname);
                lbSchool.setText(school);
                lbDistrict.setText(dis);
                lbPro.setText(pro);
            }
        });
        st.setVisible(true);


    }//GEN-LAST:event_btnSearchActionPerformed
    private void updateSubjectComboBox() {
        cmbSubject.removeAllItems();
        subject.clear();
        String studentID = txtStudentID.getText().trim();

        try {
            sql = "SELECT * FROM subjectdetail "
                    + "INNER JOIN regisdetail ON regisdetail.subDetailID = subjectdetail.subDetailID "
                    + "INNER JOIN registration ON regisdetail.regisID = registration.regisID "
                    + "INNER JOIN subject ON subjectdetail.subID = subject.subID "
                    + "INNER JOIN level ON subjectdetail.levelID = level.levelID "
                    + "WHERE registration.stdID = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, studentID);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String subjectID = rs.getString("subdetailID");
                String sub = rs.getString("subName");
                String level = rs.getString("level");
                String combine = sub + " " + level;

                cmbSubject.addItem(combine);
                subject.add(subjectID);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching subjects: " + e.getMessage());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cmbExam;
    private javax.swing.JComboBox<String> cmbRank;
    private javax.swing.JComboBox<String> cmbSubject;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbBill;
    private javax.swing.JLabel lbDistrict;
    private javax.swing.JLabel lbPro;
    private javax.swing.JLabel lbSchool;
    private javax.swing.JLabel lbSchool1;
    private javax.swing.JLabel lbTotal;
    private Model.Table table;
    private Swing.TextField txtFullName;
    private Swing.TextField txtMoney;
    private Swing.TextField txtScore;
    private Swing.TextField txtStudentID;
    // End of variables declaration//GEN-END:variables

    private final HashMap<String, String> subjectMap = new HashMap<>();

    private void loadsubjectData() {
        sql = "SELECT subject.subName, level.level, subjectdetail.subDetailID "
                + "FROM subjectdetail "
                + "INNER JOIN subject ON subjectdetail.subID = subject.subID "
                + "INNER JOIN level ON subjectdetail.levelID = level.levelID";

        try (PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String subName = rs.getString("subName");
                String level = rs.getString("level");
                String subDetailID = rs.getString("subDetailID");

                String combined = subName + " " + level;

                cmbSubject.addItem(combined);
                subjectMap.put(combined, subDetailID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addData() {
        if (!txtStudentID.getText().isEmpty() && !txtScore.getText().isEmpty() && !txtMoney.getText().isEmpty()) {

            String studentID = txtStudentID.getText();
            String sub = cmbSubject.getSelectedItem().toString();

            if (isStudentIDExists(studentID, sub)) {
                JOptionPane.showMessageDialog(null, new message("ລະຫັດ" + studentID + "ຖືກເພີ່ມໃສ່ຕາຕະລາງແລ້ວ!"));
            } else {
                String[] data
                        = {
                            txtStudentID.getText(),
                            txtFullName.getText(),
                            lbSchool.getText(),
                            lbDistrict.getText(),
                            lbPro.getText(),
                            cmbSubject.getSelectedItem().toString(),
                            txtScore.getText(),
                            cmbRank.getSelectedItem().toString(),
                            txtMoney.getText()
                        };
                model.addRow(data);
                clearText();
                Total();
            }
        } else {
            JOptionPane.showMessageDialog(null, new message("ກະລຸນາປ້ອນຂໍ້ມູນໃຫ້ຄົບຖ້ວນກ່ອນ!"));
        }
    }

    private boolean isStudentIDExists(String studentID, String sub) {
        for (int i = 0; i < table.getRowCount(); i++) {
            if (table.getValueAt(i, 0).toString().equals(studentID) && table.getValueAt(i, 5).toString().equals(sub)) {
                return true;
            }
        }
        return false;
    }

    private void clearText() {
        txtStudentID.setText("");
        txtFullName.setText("");
        txtScore.setText("");
        txtMoney.setText("");
        lbSchool.setText("");
        lbDistrict.setText("");
        lbPro.setText("");
        loadsubjectData();
        cmbSubject.setSelectedIndex(0);
        cmbRank.setSelectedIndex(0);
    }

    private int Total() {
        int sum = 0;
        int row = model.getRowCount();
        for (int i = 0; i < row; i++) {
            String index = table.getValueAt(i, 8).toString().replaceAll(",", "").trim();
            int total = Integer.parseInt(index);
            sum += total;
        }
        lbTotal.setText(df.format(sum) + " ກີບ");
        return sum;
    }

    private void saveData() {
        try {
            con.setAutoCommit(false);
            String examID = lbBill.getText();
            String smt = semester.get(cmbExam.getSelectedIndex()).toString();
            String examDate = dn.DateTimeNow("yyyy-MM-dd");

            // Insert exam data
            sql = "CALL insertExam(?,?,?)";
            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, examID);
            cst.setString(2, smt);
            cst.setString(3, examDate);
            cst.executeUpdate();

            int rowCount = table.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                String studentID = table.getValueAt(i, 0).toString();
                String subName = table.getValueAt(i, 5).toString(); // Assuming column 5 contains subName

                String subDetailID = subjectMap.get(subName);
                if (subDetailID == null) {
                    JOptionPane.showMessageDialog(null, "No subDetailID found for subject: " + subName);
                    continue; // Skip if mapping is missing
                }

                String score = table.getValueAt(i, 6).toString();
                String rank = table.getValueAt(i, 7).toString();
                String prize = table.getValueAt(i, 8).toString().replaceAll(",", "").trim();

                sql = "CALL insertAward(?,?,?,?,?,?)";
                CallableStatement cstAW = con.prepareCall(sql);
                cstAW.setString(1, examID);
                cstAW.setString(2, studentID);
                cstAW.setString(3, subDetailID);
                cstAW.setString(4, score);
                cstAW.setString(5, rank);
                cstAW.setString(6, prize);
                cstAW.executeUpdate();
            }

            String total = lbTotal.getText().replaceAll(",", "").replaceAll("ກີບ", "");
            sql = "call insertExpense(?,?,?)";
            String des = "ມອບລາງວັນໃຫ້ນັກຮຽນ " + cmbExam.getSelectedItem();
            String date = java.time.LocalDate.now().toString();
            try (CallableStatement cst1 = con.prepareCall(sql)) {
                cst1.setString(1, total);
                cst1.setString(2, des);
                cst1.setString(3, date);
                cst1.executeUpdate();
            }

            con.commit();
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (Exception autoCommitEx) {
                autoCommitEx.printStackTrace();
            }
        }

    }

    private void printBill() {
        try {
            InputStream path = getClass().getResourceAsStream("/Report/prize.jrxml");
            JasperReport jreport = JasperCompileManager.compileReport(path);
            Map<String, Object> parameter = new HashMap<>();
            parameter.put("bill", lbBill.getText());
            parameter.put("total", lbTotal.getText());
            parameter.put("exam", cmbExam.getSelectedItem().toString());

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

    class DigitFilter extends DocumentFilter {

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (isValidInput(fb.getDocument().getText(0, fb.getDocument().getLength()), string)) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (isValidInput(fb.getDocument().getText(0, fb.getDocument().getLength()), text)) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
        }

        private boolean isValidInput(String currentText, String newText) {
            try {
                String fullText = currentText + newText;
                if (fullText.isEmpty() || !fullText.matches("\\d*(\\.\\d{0,2})?")) {
                    return false;
                }
                float score = Float.parseFloat(fullText);
                return score > 0 && score <= 100.0;
            } catch (NumberFormatException e) {
                return false;
            }

        }
    }

}
