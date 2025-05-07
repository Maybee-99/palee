package Subform;

import java.sql.SQLException;
import java.sql.CallableStatement;
import Database.connectDB;
import Model.CurrentDate;
import Model.OnlyInputString;
import Model.GenerateID;
import Model.message;
import Model.subjectModel;
import Model.subjectTypeModel;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Model.comboBoxHeight;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import Model.ButtonEditor;
import Model.ButtonRenderer;
import static Model.Command.runCommand;
import Model.PhoneNumberValidator;
import Model.StudentSelectionListener;
import Model.formatNumber;
import static Model.generateQRCode.generateQRCode;
import com.formdev.flatlaf.ui.FlatRoundBorder;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import net.sf.jasperreports.engine.JasperExportManager;

public class RegisterStudent extends javax.swing.JInternalFrame {

    List<subjectModel> subject = new ArrayList<>();
    List<subjectTypeModel> subType = new ArrayList<>();
    public String stdID;
    public String stdName;
    public String Lastname;
    DefaultTableModel model = new DefaultTableModel();
    message m;
    Connection con = connectDB.getConnect();
    String sql;
    GenerateID id = new GenerateID();
    DecimalFormat df = new DecimalFormat("#,###");

    ButtonGroup group = new ButtonGroup();
    String level;
    String subID;
    String subName;
    String dis = "";
    int cost = 0;
    CurrentDate dn = new CurrentDate();
    private subjectModel selectedModel;

    ArrayList<String> province = new ArrayList<>();
    ArrayList<String> district = new ArrayList<>();
    ArrayList<String> sex = new ArrayList<>();
    ArrayList<String> sch = new ArrayList<>();
    ArrayList<String> stay = new ArrayList<>();

    JPanel panel;
    JScrollPane scrollPane;

    public RegisterStudent() {
        super("registration");
        initComponents();
        btnPrint.setEnabled(false);
        btnSave.setEnabled(false);
        loadAllData();

        PhoneNumberValidator.Checkvalidate(txtPhoneNumber1);
        PhoneNumberValidator.Checkvalidate(txtPhoneNumber2);
        model = (DefaultTableModel) table.getModel();
        table.fixTable(sp);
        panel1.setBorder(new FlatRoundBorder());

        table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JButton("ລຶບ"), table, model));

        table.getModel().addTableModelListener((TableModelEvent e) -> {
            if (e.getType() == TableModelEvent.INSERT || e.getType() == TableModelEvent.DELETE) {
                Total();
                Discount();
            }
        });

        formatNumber.apply(txtFee);

    }

    private void loadAllData() {
        // Generate ID and Bill
        SwingWorker<Void, Void> idWorker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                generateID();
                return null;
            }

            @Override
            protected void done() {
                generateBill();
            }
        };
        idWorker.execute();

        // Retrieve dropdown data
        SwingWorker<Void, Void> dropdownWorker = new SwingWorker<>() {
            List<String> genderList = new ArrayList<>();
            List<String> provinceList = new ArrayList<>();
            List<String> scholarshipList = new ArrayList<>();
            List<String> stayList = new ArrayList<>();

            @Override
            protected Void doInBackground() {
                try {
                    // Gender
                    ResultSet rs = con.createStatement().executeQuery("select * from sex");
                    sex.clear();
                    while (rs.next()) {
                        sex.add(rs.getString("sexID"));
                        genderList.add(rs.getString("sex"));
                    }

                    // Province
                    rs = con.createStatement().executeQuery("select * from province");
                    province.clear();
                    while (rs.next()) {
                        province.add(rs.getString("provinceID"));
                        provinceList.add(rs.getString("provinceName"));
                    }

                    // Scholarship
                    rs = con.createStatement().executeQuery("select * from scholarship");
                    sch.clear();
                    while (rs.next()) {
                        sch.add(rs.getString("scholarship_id"));
                        scholarshipList.add(rs.getString("scholarship"));
                    }

                    // Stay
                    rs = con.createStatement().executeQuery("select * from stay");
                    stay.clear();
                    while (rs.next()) {
                        stay.add(rs.getString("stay_id"));
                        stayList.add(rs.getString("stay"));
                    }
                } catch (Exception e) {
                    showErrorMessage(e.getMessage());
                }

                return null;
            }

            @Override
            protected void done() {
                SwingUtilities.invokeLater(() -> {
                    try {
                        cmbSex.removeAllItems();
                        for (String g : genderList) {
                            cmbSex.addItem(g);
                        }

                        cmbProvince.removeAllItems();
                        for (String p : provinceList) {
                            cmbProvince.addItem(p);
                        }

                        cmbScholarship.removeAllItems();
                        for (String s : scholarshipList) {
                            cmbScholarship.addItem(s);
                        }

                        cmbStay.removeAllItems();
                        for (String st : stayList) {
                            cmbStay.addItem(st);
                        }
                    } catch (Exception e) {
                        showErrorMessage(e.getMessage());
                    }
                });
            }
        };
        dropdownWorker.execute();

        // UI setup
        SwingWorker<Void, Void> uiWorker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                serviceAction();
                return null;
            }
        };
        uiWorker.execute();

        // Load subject data
        SwingWorker<Void, Void> subjectWorker = new SwingWorker<>() {
            JPanel subjectPanel;

            @Override
            protected Void doInBackground() {
                subjectPanel = LoadSubjectDetail("");
                loadSubject();
                return null;
            }

            @Override
            protected void done() {
                try {
                    TabBar(null);
                } catch (Exception e) {
                    showErrorMessage(e.getMessage());
                }
            }
        };
        subjectWorker.execute();
    }

// Util method to show error messages
    private void showErrorMessage(String msg) {
        m = new message("ເກີດຂໍ້ຜິດພາດ: " + msg);
        JOptionPane.showMessageDialog(this, m);
    }

    private void generateBill() {
        String billID = id.generateID("select Max(regisID) as ID from registration", "B", 4);
        lbBill.setText(billID);
    }

    private void generateID() {
        String studentID = id.generateID("select Max(stdID) as ID from student", "STD", 3);
        lbID.setText(studentID);
    }

    private void serviceAction() {
        m = new message("ກະລຸນາປ້ອນຕົວອັກສອນເທົ່ານັ້ນ!");
        OnlyInputString ois = new OnlyInputString(m);
        txtName.addKeyListener(ois);
        txtLastname.addKeyListener(ois);

        cmbSex.setRenderer(new comboBoxHeight());
        cmbDistrict.setRenderer(new comboBoxHeight());
        cmbProvince.setRenderer(new comboBoxHeight());
        cmbScholarship.setRenderer(new comboBoxHeight());
        cmbStay.setRenderer(new comboBoxHeight());
    }

    private JPanel LoadSubjectDetail(String subDetail) {
        panel = new JPanel();
        panel.setBackground(new Color(242, 242, 242));
        panel.setBorder(new FlatRoundBorder());
        panel.setLayout(new MigLayout("wrap 4", "[grow, fill][grow, fill]", "[]10[]"));

        try {
            sql = subDetail.isEmpty() ? "CALL selectSDAll()" : "CALL selectSD(?)";
            CallableStatement cst = con.prepareCall(sql);

            if (!subDetail.isEmpty()) {
                cst.setString(1, subDetail);
            }

            ResultSet rs = cst.executeQuery();
            while (rs.next()) {
                subjectModel subdetail = new subjectModel(
                        rs.getString("subDetailID"),
                        rs.getString("subName"),
                        rs.getString("level"),
                        rs.getInt("cost")
                );

                subdetail.cbSub.putClientProperty("model", subdetail);
                subdetail.cbSub.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        JCheckBox source = (JCheckBox) e.getSource();
                        subjectModel model = (subjectModel) source.getClientProperty("model");
                        subName = model.subName;
                        level = model.level;
                        cost = model.cost;
                        selectedModel = model;
                        btnSave.setEnabled(true);
                    }
                });

                group.add(subdetail.cbSub);
                panel.add(subdetail.cbSub);
                subject.add(subdetail);
            }
        } catch (Exception e) {
            m = new message("ເກີດຂໍ້ຜິດພາດ" + e.getMessage());
            JOptionPane.showMessageDialog(this, m);
        }
        return panel;
    }

    private void loadSubject() {
        try {
            allSubject();
            sql = "select * from subject";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                subjectTypeModel sts = new subjectTypeModel(rs.getString("subID"), rs.getString("subName"));
                subType.add(sts);
                TabBar(sts);
            }
            tabBar.setSelectedIndex(0);

        } catch (Exception e) {
            m = new message("ເກີດຂໍ້ຜິດພາດ" + e.getMessage());
            JOptionPane.showMessageDialog(this, m);
        }
    }

    private void TabBar(subjectTypeModel sts) {
        if (sts == null || sts.subName == null) {
            return;
        }

        int index = tabBar.indexOfTab(sts.subName);
        if (index != -1) {
            tabBar.setSelectedIndex(index);
            return;
        }
        tabBar.setBackground(new Color(0, 102, 255));
        tabBar.setForeground(Color.WHITE);
        panel = LoadSubjectDetail(sts.subName);
        scrollPane = new JScrollPane(panel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        tabBar.addTab(sts.subName, null, scrollPane, "ລາຍລະອຽດ" + sts.subName);
        tabBar.setSelectedIndex(tabBar.getTabCount() - 1);
    }

    private void allSubject() {
        int index = tabBar.indexOfTab("ວິຊາທັງໝົດ");
        if (index != -1) {
            tabBar.setSelectedIndex(index);
            return;
        }
        panel = LoadSubjectDetail("");
        scrollPane = new JScrollPane(panel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        tabBar.addTab("ວິຊາທັງໝົດ", null, scrollPane);
        tabBar.setSelectedIndex(0);
    }

    private void reset() {
        lbID.setText("");
        txtName.setText("");
        lbpay.setText("0");
        lbdiscout.setText("0%");
        lbpaid.setText("0");
        lbpend.setText("0");
        txtFee.setText("");
        generateID();
        generateBill();
        txtLastname.setText("");
        txtPhoneNumber1.setText("");
        txtPhoneNumber2.setText("");
        txtSchool.setText("");
        cmbSex.setSelectedIndex(0);
        cmbProvince.setSelectedIndex(0);
        cmbDistrict.setSelectedIndex(0);
        cmbScholarship.setSelectedIndex(0);
        txtName.requestFocus();
        group.clearSelection();
        while (table.getRowCount() > 0) {
            model.removeRow(0);
        }
        LoadSubjectDetail("");
        loadSubject();
        btnSave.setEnabled(false);
    }

    private void printBill() {
        try {
            String qrUrl = "https://github.com/Palee99/bill/blob/main/bills/" + lbBill.getText() + ".pdf?raw=true";

            Map<String, Object> parameter = new HashMap<>();
            parameter.put("billNo", lbBill.getText());
            parameter.put("stdID", lbID.getText());
            parameter.put("total", Double.valueOf(lbpay.getText().replaceAll(",", "").replaceAll("ກີບ", "").trim()));
            parameter.put("discount", lbdiscout.getText());
            parameter.put("pay", lbpaid.getText());
            parameter.put("pend", lbpend.getText());
            parameter.put("fee", Double.valueOf(txtFee.getText().replaceAll(",", "").trim()));

            int t = Integer.parseInt(lbpay.getText().replaceAll(",", "").replaceAll("ກີບ", "").trim());
            int p = Integer.parseInt(lbpaid.getText().replaceAll(",", "").replaceAll("ກີບ", "").trim());
            if (t == p) {
                parameter.put("status", "ຈ່າຍແລ້ວ");
            } else if (t == 0) {
                parameter.put("status", "ໄດ້ຮັບທຶນ");
            } else if (t > p && p == 0) {
                parameter.put("status", "ຍັງບໍ່ທັນຈ່າຍ");
            } else if (t > p) {
                parameter.put("status", "ຍັງຈ່າຍບໍ່ຄົບ");
            }

            BufferedImage qrImage = generateQRCode(qrUrl);
            parameter.put("qrCode", qrImage);

            InputStream path = getClass().getResourceAsStream("/Report/Registration.jrxml");
            JasperReport jreport = JasperCompileManager.compileReport(path);
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
            e.printStackTrace();
        }
    }

    private void exportPdfAndPushToGitHub(JasperPrint jprint, String billNo) {
        try {
            String repoPath = "C:/Users/pkcom/Documents/QR";
            String relativePath = "bills/" + billNo + ".pdf";
            String pdfPath = repoPath + "/" + relativePath;

            new File(repoPath + "/bills").mkdirs();
            JasperExportManager.exportReportToPdfFile(jprint, pdfPath);

            runCommand("git -C \"" + repoPath + "\" add " + relativePath);
            runCommand("git -C \"" + repoPath + "\" commit -m \"Add bill " + billNo + "\"");
            runCommand("git -C \"" + repoPath + "\" push origin main");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Git push failed: " + e.getMessage());
        }
    }

    private Map<String, String> loadScholarship() throws SQLException {
        Map<String, String> getID = new HashMap<>();
        sql = "SELECT scholarship_id, scholarship FROM scholarship";
        try (Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                String sch_ID = rs.getString("scholarship_id");
                String scho = rs.getString("scholarship");
                getID.put(scho, sch_ID);
            }
        }
        return getID;
    }

    private Map<String, String> loadSubID() throws SQLException {
        Map<String, String> getSubID = new HashMap<>();
        for (subjectModel sub : subject) {
            String key = sub.subName + " " + sub.level;
            getSubID.put(key, sub.subID);
        }
        return getSubID;
    }

    public void saveRegisterData() {
        try {
            String regisID = lbBill.getText();
            String studentID = lbID.getText();
            String date = dn.DateTimeNow("yyyy-MM-dd");

            con.setAutoCommit(false);

            try (CallableStatement cstRegis = con.prepareCall("CALL insertRegis(?,?,?)")) {
                cstRegis.setString(1, regisID);
                cstRegis.setString(2, studentID);
                cstRegis.setString(3, date);
                cstRegis.executeUpdate();
            }

            Map<String, String> subjectID = loadSubID();
            Map<String, String> getscholarshipID = loadScholarship();

            try (CallableStatement cstRD = con.prepareCall("CALL insertRD(?,?,?,?)")) {
                int rowCount = table.getRowCount();

                for (int i = 0; i < rowCount; i++) {
                    subName = table.getValueAt(i, 1).toString().trim();
                    cost = Integer.parseInt(table.getValueAt(i, 4).toString().replaceAll(",", ""));
                    level = table.getValueAt(i, 2).toString().trim();
                    String subKey = subName + " " + level;
                    subID = subjectID.get(subKey);

                    if (subID == null) {
                        throw new IllegalArgumentException("Invalid Subject Name: " + subName);
                    }
                    String scholarship = table.getValueAt(i, 3).toString();
                    String scholarshipID = getscholarshipID.get(scholarship);

                    cstRD.setString(1, regisID);
                    cstRD.setString(2, subID);
                    cstRD.setString(3, scholarshipID);
                    cstRD.setInt(4, cost);
                    cstRD.executeUpdate();
                }
            }

        } catch (SQLException e) {
            try {
                con.rollback();
                m = new message("ເກີດຂໍ້ຜິດພາດ" + e.getMessage());
                JOptionPane.showMessageDialog(this, m);
            } catch (SQLException rollbackEx) {
                m = new message("ເກີດຂໍ້ຜິດພາດ" + rollbackEx.getMessage());
                JOptionPane.showMessageDialog(this, m);
            }
            e.printStackTrace();
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                m = new message("ເກີດຂໍ້ຜິດພາດ" + e.getMessage());
                JOptionPane.showMessageDialog(this, m);
            }
        }
    }

    private int Total() {
        int totalSum = 0;
        int sum = 0;
        int stFee = 0;
        if (!txtFee.getText().equals("")) {
            try {
                stFee = Integer.parseInt(txtFee.getText().replaceAll(",", "").trim());
            } catch (NumberFormatException e) {
                stFee = 0; // fallback if input is not valid
            }
        }
        int row = model.getRowCount();
        for (int i = 0; i < row; i++) {
            String index = table.getValueAt(i, 4).toString().replaceAll(",", "").trim();
            int total = Integer.parseInt(index);
            sum += total;
            totalSum = sum + stFee;
        }
        lbpay.setText(df.format(totalSum) + " ກີບ");
        return totalSum;
    }

    public void getDiscout(String dis) {
        this.dis = dis;
        lbdiscout.setText(this.dis + "%");
    }

    private double Discount() {
        double pay = 0;
        int row = model.getRowCount();
        Set<String> validateSub = new HashSet<>(Arrays.asList("ຄະນິດສາດ", "ຟີຊິກສາດ", "ເຄມີສາດ"));
        int check = 0;
        for (int i = 0; i < row; i++) {
            String sub = table.getValueAt(i, 1).toString();
            if (validateSub.contains(sub)) {
                check++;
            }

        }
        if (check >= 3) {
            Discount d = new Discount(null, true, this);
            d.setVisible(true);
            int discount = Integer.parseInt(lbdiscout.getText().replaceAll("%", "").trim());
            int total = Integer.parseInt(lbpay.getText().replaceAll(",", "").replaceAll("ກີບ", "").trim());
            double percent = discount / 100.0;
            double discountMoney = percent * total;
            pay = total - discountMoney;
            lbpay.setText(df.format(pay) + " ກີບ");

        } else {
            lbdiscout.setText("0%");
        }
        return pay;
    }

    public void saveStudentData() {
        try {
            sql = "call insertStudent(?,?,?,?,?,?,?,?,?)";

            String s = sex.get(cmbSex.getSelectedIndex()).toString().trim();
            String d = district.get(cmbDistrict.getSelectedIndex()).toString().trim();
            String st = stay.get(cmbStay.getSelectedIndex()).toString().trim();

            CallableStatement cst = con.prepareCall(sql);
            cst.setString(1, lbID.getText().trim());
            cst.setString(2, txtName.getText().trim());
            cst.setString(3, txtLastname.getText().trim());
            cst.setString(4, s);
            cst.setString(5, txtPhoneNumber1.getText().trim());
            cst.setString(6, txtPhoneNumber2.getText().trim());
            cst.setString(7, txtSchool.getText().trim());
            cst.setString(8, d);
            cst.setString(9, st);
            cst.executeUpdate();

        } catch (Exception e) {
            m = new message("ເກີດຂໍ້ຜິດພາດ" + e.getMessage());
            JOptionPane.showMessageDialog(this, m);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pn = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lbID = new Swing.TextField();
        jPanel11 = new javax.swing.JPanel();
        btnBrowse = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtName = new Swing.TextField();
        jLabel3 = new javax.swing.JLabel();
        txtLastname = new Swing.TextField();
        jLabel4 = new javax.swing.JLabel();
        cmbSex = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtPhoneNumber1 = new Swing.TextField();
        jLabel7 = new javax.swing.JLabel();
        txtPhoneNumber2 = new Swing.TextField();
        jLabel8 = new javax.swing.JLabel();
        txtSchool = new Swing.TextField();
        jLabel10 = new javax.swing.JLabel();
        cmbProvince = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cmbDistrict = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        cmbScholarship = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        cmbStay = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        txtFee = new Swing.TextField();
        jPanel4 = new javax.swing.JPanel();
        tabBar = new javax.swing.JTabbedPane();
        panel1 = new javax.swing.JPanel();
        sp = new javax.swing.JScrollPane();
        table = new Model.Table();
        panel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        lbBill = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        lbpay = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbdiscout = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        lbpaid = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbpend = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        btnPay = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        setBorder(null);
        setClosable(true);
        setForeground(java.awt.Color.pink);
        setTitle("ຟອມລົງທະບຽນນັກຮຽນ");
        setFrameIcon(null);
        setOpaque(true);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setLayout(new java.awt.BorderLayout(20, 0));

        jPanel3.setBackground(new java.awt.Color(212, 230, 249));
        jPanel3.setPreferredSize(new java.awt.Dimension(600, 100));
        jPanel3.setLayout(new net.miginfocom.swing.MigLayout());

        jScrollPane1.setBackground(new java.awt.Color(212, 230, 249));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setOpaque(false);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(582, 850));

        pn.setBackground(new java.awt.Color(212, 230, 249));
        pn.setBorder(javax.swing.BorderFactory.createMatteBorder(20, 20, 20, 20, new java.awt.Color(212, 230, 249)));
        pn.setPreferredSize(new java.awt.Dimension(580, 800));
        pn.setLayout(new java.awt.GridLayout(12, 1, -300, 10));

        jLabel17.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("ລະຫັດ:");
        jLabel17.setPreferredSize(new java.awt.Dimension(55, 35));
        pn.add(jLabel17);

        jPanel10.setBackground(new java.awt.Color(212, 230, 249));
        jPanel10.setLayout(new net.miginfocom.swing.MigLayout());

        lbID.setEditable(false);
        lbID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 0, new java.awt.Color(0, 0, 0)));
        lbID.setForeground(new java.awt.Color(0, 102, 255));
        lbID.setCornerRadius(12.0F);
        lbID.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbID.setPlaceHolder("");
        lbID.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel10.add(lbID);

        jPanel11.setBackground(new java.awt.Color(212, 230, 249));
        jPanel10.add(jPanel11);

        btnBrowse.setBackground(new java.awt.Color(0, 0, 0));
        btnBrowse.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        btnBrowse.setForeground(new java.awt.Color(255, 255, 255));
        btnBrowse.setText("ຄົ້ນຫາ");
        btnBrowse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBrowse.setPreferredSize(new java.awt.Dimension(100, 50));
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });
        jPanel10.add(btnBrowse);

        pn.add(jPanel10);

        jLabel2.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("ຊື່:");
        pn.add(jLabel2);

        txtName.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        txtName.setBorderColor(new java.awt.Color(204, 204, 204));
        txtName.setCornerRadius(12.0F);
        txtName.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtName.setPlaceHolder("ປ້ອນຊື່");
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNameKeyReleased(evt);
            }
        });
        pn.add(txtName);

        jLabel3.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("ນາມສະກຸນ:");
        pn.add(jLabel3);

        txtLastname.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        txtLastname.setBorderColor(new java.awt.Color(204, 204, 204));
        txtLastname.setCornerRadius(12.0F);
        txtLastname.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtLastname.setPlaceHolder("ປ້ອນນາມສະກຸນ");
        pn.add(txtLastname);

        jLabel4.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("ເພດ:");
        pn.add(jLabel4);

        cmbSex.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        cmbSex.setPreferredSize(new java.awt.Dimension(72, 35));
        pn.add(cmbSex);

        jLabel6.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("ເບີໂທ:");
        pn.add(jLabel6);

        txtPhoneNumber1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        txtPhoneNumber1.setBorderColor(new java.awt.Color(204, 204, 204));
        txtPhoneNumber1.setCornerRadius(12.0F);
        txtPhoneNumber1.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtPhoneNumber1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtPhoneNumber1.setPlaceHolder("020XXXXXXXX");
        txtPhoneNumber1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPhoneNumber1FocusLost(evt);
            }
        });
        txtPhoneNumber1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPhoneNumber1KeyReleased(evt);
            }
        });
        pn.add(txtPhoneNumber1);

        jLabel7.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("ເບີຜູ້ປົກຄອງ:");
        pn.add(jLabel7);

        txtPhoneNumber2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        txtPhoneNumber2.setBorderColor(new java.awt.Color(204, 204, 204));
        txtPhoneNumber2.setCornerRadius(12.0F);
        txtPhoneNumber2.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtPhoneNumber2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtPhoneNumber2.setPlaceHolder("020XXXXXXXX");
        txtPhoneNumber2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPhoneNumber2FocusLost(evt);
            }
        });
        txtPhoneNumber2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPhoneNumber2KeyReleased(evt);
            }
        });
        pn.add(txtPhoneNumber2);

        jLabel8.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("ມາຈາກໂຮງຮຽນ:");
        pn.add(jLabel8);

        txtSchool.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        txtSchool.setBorderColor(new java.awt.Color(204, 204, 204));
        txtSchool.setCornerRadius(12.0F);
        txtSchool.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtSchool.setPlaceHolder("ປ້ອນໂຮງຮຽນ (EX: ມສ ສາທິດ)");
        pn.add(txtSchool);

        jLabel10.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("ແຂວງ:");
        pn.add(jLabel10);

        cmbProvince.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        cmbProvince.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbProvince.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbProvinceItemStateChanged(evt);
            }
        });
        pn.add(cmbProvince);

        jLabel9.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("ເມືອງ:");
        pn.add(jLabel9);

        cmbDistrict.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        cmbDistrict.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pn.add(cmbDistrict);

        jLabel19.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("ທຶນຮຽນ:");
        jLabel19.setPreferredSize(new java.awt.Dimension(55, 35));
        pn.add(jLabel19);

        cmbScholarship.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        cmbScholarship.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pn.add(cmbScholarship);

        jLabel21.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("ບ່ອນພັກ:");
        jLabel21.setPreferredSize(new java.awt.Dimension(55, 35));
        pn.add(jLabel21);

        cmbStay.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        cmbStay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbStay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStayActionPerformed(evt);
            }
        });
        pn.add(cmbStay);

        jLabel24.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("ຄ່ານ້ຳ/ຄ່າໄຟ:");
        jLabel24.setPreferredSize(new java.awt.Dimension(55, 35));
        pn.add(jLabel24);

        txtFee.setCornerRadius(12.0F);
        txtFee.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtFee.setPlaceHolder("");
        txtFee.setPreferredSize(new java.awt.Dimension(300, 50));
        txtFee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFeeKeyTyped(evt);
            }
        });
        pn.add(txtFee);

        jScrollPane1.setViewportView(pn);

        jPanel3.add(jScrollPane1);

        jPanel2.add(jPanel3, java.awt.BorderLayout.WEST);

        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 20, 20, new java.awt.Color(242, 242, 242)));
        jPanel4.setLayout(new java.awt.BorderLayout());

        tabBar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabBar.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        tabBar.setOpaque(true);
        tabBar.setPreferredSize(new java.awt.Dimension(0, 280));
        jPanel4.add(tabBar, java.awt.BorderLayout.PAGE_START);

        panel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(242, 242, 242)));
        panel1.setPreferredSize(new java.awt.Dimension(600, 450));
        panel1.setLayout(new java.awt.BorderLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ລຳດັບ", "ວິຊາຮຽນ", "ລະດັບ", "ທຶນຮຽນ", "ຄ່າຮຽນ", "ລຶບແຖວ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("Saysettha OT", 0, 14)); // NOI18N
        sp.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(5).setResizable(false);
        }

        panel1.add(sp, java.awt.BorderLayout.CENTER);

        panel2.setBackground(new java.awt.Color(212, 230, 249));
        panel2.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(212, 230, 249));
        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 20, 0, 0, new java.awt.Color(212, 230, 249)));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel20.setBackground(new java.awt.Color(0, 102, 255));
        jLabel20.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("ເລກບິນ:");
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel20.setPreferredSize(new java.awt.Dimension(60, 35));
        jPanel6.add(jLabel20);

        lbBill.setBackground(new java.awt.Color(0, 102, 255));
        lbBill.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbBill.setForeground(new java.awt.Color(204, 0, 0));
        lbBill.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbBill.setText("1234");
        lbBill.setPreferredSize(new java.awt.Dimension(200, 35));
        jPanel6.add(lbBill);

        panel2.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel8.setBackground(new java.awt.Color(212, 230, 249));
        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 20, 0, 0, new java.awt.Color(212, 230, 249)));
        jPanel8.setPreferredSize(new java.awt.Dimension(250, 100));
        jPanel8.setLayout(new java.awt.GridLayout(2, 2, -80, 0));

        jLabel22.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("ຕ້ອງຈ່າຍ:");
        jPanel8.add(jLabel22);

        lbpay.setBackground(new java.awt.Color(255, 255, 255));
        lbpay.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        lbpay.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbpay.setText("0");
        lbpay.setPreferredSize(new java.awt.Dimension(100, 19));
        jPanel8.add(lbpay);

        jLabel15.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("ສ່ວນຫຼຸດ:");
        jPanel8.add(jLabel15);

        lbdiscout.setBackground(new java.awt.Color(255, 255, 255));
        lbdiscout.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        lbdiscout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbdiscout.setText("0%");
        jPanel8.add(lbdiscout);

        panel2.add(jPanel8, java.awt.BorderLayout.LINE_START);

        jPanel9.setBackground(new java.awt.Color(212, 230, 249));
        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 20, new java.awt.Color(212, 230, 249)));
        jPanel9.setPreferredSize(new java.awt.Dimension(300, 100));
        jPanel9.setLayout(new java.awt.GridLayout(2, 2, 0, 2));

        jLabel23.setBackground(new java.awt.Color(0, 0, 0));
        jLabel23.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("ຈ່າຍແລ້ວ:");
        jPanel9.add(jLabel23);

        lbpaid.setBackground(new java.awt.Color(0, 0, 0));
        lbpaid.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        lbpaid.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbpaid.setText("0.0");
        jPanel9.add(lbpaid);

        jLabel16.setBackground(new java.awt.Color(0, 0, 0));
        jLabel16.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("ຍັງເຫຼືອ:");
        jPanel9.add(jLabel16);

        lbpend.setBackground(new java.awt.Color(0, 0, 0));
        lbpend.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        lbpend.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbpend.setText("0.0");
        jPanel9.add(lbpend);

        panel2.add(jPanel9, java.awt.BorderLayout.LINE_END);

        panel1.add(panel2, java.awt.BorderLayout.PAGE_END);

        jPanel5.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanel5.setLayout(new net.miginfocom.swing.MigLayout());

        btnSave.setBackground(new java.awt.Color(0, 102, 255));
        btnSave.setFont(new java.awt.Font("Saysettha OT", 1, 16)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("ບັນທຶກ");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.setPreferredSize(new java.awt.Dimension(250, 50));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel5.add(btnSave);

        panel1.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel4.add(panel1, java.awt.BorderLayout.CENTER);

        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 0, 0, 0, new java.awt.Color(242, 242, 242)));
        jPanel7.setPreferredSize(new java.awt.Dimension(336, 60));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnPay.setBackground(new java.awt.Color(255, 51, 51));
        btnPay.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnPay.setForeground(new java.awt.Color(255, 255, 255));
        btnPay.setText("ຊຳລະເງິນ");
        btnPay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPay.setPreferredSize(new java.awt.Dimension(200, 45));
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });
        jPanel7.add(btnPay);

        btnPrint.setBackground(new java.awt.Color(0, 153, 102));
        btnPrint.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(255, 255, 255));
        btnPrint.setText("ພິມບິນ");
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrint.setPreferredSize(new java.awt.Dimension(200, 45));
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        jPanel7.add(btnPrint);

        btnReset.setBackground(new java.awt.Color(0, 102, 255));
        btnReset.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("ຣີເຊັກ");
        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.setPreferredSize(new java.awt.Dimension(200, 45));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel7.add(btnReset);

        jPanel4.add(jPanel7, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2);

        getContentPane().add(jPanel1);

        setBounds(0, 0, 1550, 917);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyReleased
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            txtLastname.requestFocus();
        }
    }//GEN-LAST:event_txtNameKeyReleased

    private void cmbProvinceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbProvinceItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            int index = cmbProvince.getSelectedIndex();
            if (index >= 0 && index < province.size()) {
                try {
                    String selectedProvinceID = province.get(index);
                    sql = "SELECT * FROM district WHERE provinceID = '" + selectedProvinceID + "'";
                    try (ResultSet rs = con.createStatement().executeQuery(sql)) {
                        district.clear();
                        cmbDistrict.removeAllItems();
                        
                        while (rs.next()) {
                            district.add(rs.getString("districtID"));
                            cmbDistrict.addItem(rs.getString("districtName"));
                        }
                    }
                } catch (Exception e) {
                    m = new message("ເກີດຂໍ້ຜິດພາດ: " + e.getMessage());
                    JOptionPane.showMessageDialog(this, m);
                }
            }
        }
    }//GEN-LAST:event_cmbProvinceItemStateChanged

    private void txtPhoneNumber1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneNumber1KeyReleased
        if (evt.getKeyChar() == '\n') {
            txtPhoneNumber2.requestFocus();
        }
    }//GEN-LAST:event_txtPhoneNumber1KeyReleased

    private void txtPhoneNumber2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneNumber2KeyReleased
        if (evt.getKeyChar() == '\n') {
            txtSchool.requestFocus();
        }
    }//GEN-LAST:event_txtPhoneNumber2KeyReleased

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        if (!lbID.getText().isEmpty() || model.getRowCount() > 0) {
            m = new message("ທ່ານແນ່ໃຈ Reset ແທ້ບໍ?");
            int x = JOptionPane.showConfirmDialog(this, m, "Confirm", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                reset();
            }
        }
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed

        int row = table.getRowCount();
        if (row > 0) {
            printBill();
            reset();
        } else {
            m = new message("ກະລຸນາປ້ອນຂໍ້ມູນນັກຮຽນ ແລະ ລົງທະບຽນກ່ອນ!!!");
            JOptionPane.showMessageDialog(this, m);
        }
        btnPrint.setEnabled(false);
        btnSave.setEnabled(false);
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayActionPerformed
        int row = table.getRowCount();
        if (row > 0) {
            String billNO = lbBill.getText();
            String studentID = lbID.getText();
            String fee = lbpay.getText();

            payment payDialog = new payment(this, new javax.swing.JFrame(), true, billNO, studentID, fee);
            payDialog.setVisible(true);
        } else {
            m = new message("ກະລຸນາເພີ່ມຂໍ້ມູນນັກຮຽນໃສ່ຕາຕະລາງກ່ອນ!!!");
            JOptionPane.showMessageDialog(this, m);
        }


    }//GEN-LAST:event_btnPayActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        if (txtFee.getText().equals("")) {
            JOptionPane.showMessageDialog(this, new message("ກະລຸນາປ້ອນຄ່າຫໍພັກກ່ອນ!"));
            txtFee.requestFocus();
            return;

        }

        boolean stdNameEmpty = txtName.getText().isEmpty();
        boolean stdLastNameEmpty = txtLastname.getText().isEmpty();
        boolean ph1 = txtPhoneNumber1.getText().isEmpty();
        boolean ph2 = txtPhoneNumber2.getText().isEmpty();
        boolean school = txtSchool.getText().isEmpty();

        if (stdNameEmpty || stdLastNameEmpty || ph1 || ph2 || school) {
            JOptionPane.showMessageDialog(this, new message("ກະລຸນາປ້ອນຂໍ້ມູນນັກຮຽນໃຫ້ຄົບຖ້ວນກ່ອນ!"));
            return;
        }

        boolean isDuplicate = IntStream.range(0, table.getRowCount())
                .anyMatch(i -> table.getValueAt(i, 1).toString().equals(subName)
                && table.getValueAt(i, 2).toString().equals(level));
        if (isDuplicate) {
            JOptionPane.showMessageDialog(this, new message("ວິຊານີ້ຖືກລົງທະບຽນແລ້ວ!"));
            return;
        }
        try {
            String scholarship = cmbScholarship.getSelectedItem().toString();
            if (scholarship.equals("ໄດ້ຮັບທຶນ")) {
                cost = 0;
            } else if (scholarship.equals("ບໍ່ໄດ້ຮັບທຶນ")) {
                cost = selectedModel.cost;
            }
            int i = table.getRowCount() + 1;

            String[] data = {
                String.valueOf(i),
                subName,
                level,
                scholarship,
                df.format(cost)

            };
            model.addRow(data);
        } catch (Exception e) {
            m = new message("ເກີດຂໍ້ຜິດພາດ" + e.getMessage());
            JOptionPane.showMessageDialog(this, m);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtPhoneNumber1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPhoneNumber1FocusLost
        String phone = txtPhoneNumber1.getText();

        if (phone.length() > 0) {
            if (phone.length() != 10 && phone.length() != 11) {
                JOptionPane.showMessageDialog(this, new message("ກະລຸນາປ້ອນເລກໂທລະສັບໃຫ້ຖືກຕ້ອງ"));
                txtPhoneNumber1.requestFocus();
            }
        }
    }//GEN-LAST:event_txtPhoneNumber1FocusLost

    private void txtPhoneNumber2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPhoneNumber2FocusLost
        String phone = txtPhoneNumber2.getText();

        if (phone.length() > 0) {
            if (phone.length() != 10 && phone.length() != 11) {
                JOptionPane.showMessageDialog(this, new message("ກະລຸນາປ້ອນເລກໂທລະສັບໃຫ້ຖືກຕ້ອງ"));
                txtPhoneNumber2.requestFocus();
            }
        }
    }//GEN-LAST:event_txtPhoneNumber2FocusLost

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        SearchStuent st = new SearchStuent(null, true, new StudentSelectionListener() {
            @Override
            public void onStudentSelected(String id, String fname, String lname, String gender, String ph1, String ph2, String school, String dis, String pro) {
                // Set data to your Prize form components
                lbID.setText(id);
                txtName.setText(fname);
                txtLastname.setText(lname);
                cmbSex.setSelectedItem(gender);
                txtPhoneNumber1.setText(ph1);
                txtPhoneNumber2.setText(ph2);
                txtSchool.setText(school);
                cmbDistrict.setSelectedItem(dis);
                cmbProvince.setSelectedItem(pro);
            }
        });
        st.setVisible(true);
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void txtFeeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFeeKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtFeeKeyTyped

    private void cmbStayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStayActionPerformed
        txtFee.requestFocus();
    }//GEN-LAST:event_cmbStayActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnPay;
    public javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbDistrict;
    private javax.swing.JComboBox<String> cmbProvince;
    private javax.swing.JComboBox<String> cmbScholarship;
    private javax.swing.JComboBox<String> cmbSex;
    private javax.swing.JComboBox<String> cmbStay;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbBill;
    private Swing.TextField lbID;
    private javax.swing.JLabel lbdiscout;
    public static javax.swing.JLabel lbpaid;
    private javax.swing.JLabel lbpay;
    public static javax.swing.JLabel lbpend;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel pn;
    private javax.swing.JScrollPane sp;
    private javax.swing.JTabbedPane tabBar;
    private Model.Table table;
    private Swing.TextField txtFee;
    private Swing.TextField txtLastname;
    private Swing.TextField txtName;
    private Swing.TextField txtPhoneNumber1;
    private Swing.TextField txtPhoneNumber2;
    private Swing.TextField txtSchool;
    // End of variables declaration//GEN-END:variables
}
