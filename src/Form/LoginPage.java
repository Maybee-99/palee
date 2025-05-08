package Form;

import Database.connectDB;
import Model.BackgroundPanel;
import Model.comboBoxHeight;
import Model.message;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import Swing.PasswordField;
import Swing.TextField;
import Swing.RoundPanel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import Model.eye;
import Subform.Teaches;

public class LoginPage extends JFrame {

    private RoundPanel panel;
    private JPanel mainPanel;
    private JLabel lbLog, title;
    private eye icon;
    private JButton btnLogin;
    JToggleButton btnConnectDB;
    private TextField txtUsername;
    private PasswordField txtPassword;
    private final Connection con = connectDB.getConnect();
    private message message;
    public static String Username, Auth, teacherID, Fullname;
    private String password;
    private JPanel dbChooserPanel;
    private boolean isDBChooserVisible = false;

    public LoginPage() {
        initComponents();
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setupUI();
        setAppIcon();
        icon.setVisible(false);
        txtPassword.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                visiblepassword();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                visiblepassword();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                visiblepassword();
            }
        });

    }

    private void setAppIcon() {
         ImageIcon icons = new ImageIcon(getClass().getResource("/Icon/palee management system.png"));
         setIconImage(icons.getImage());
    }

    private void visiblepassword() {
        if (txtPassword.getPassword().length == 0) {
            icon.setVisible(false);
        } else {
            icon.setVisible(true);
        }
    }

    private void setupUI() {
        mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new GridBagLayout());

        panel = createPanel();
        mainPanel.add(panel);

        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/Icon/bg.png"));
        BackgroundPanel bgPanel = new BackgroundPanel(backgroundIcon.getImage());
        bgPanel.setLayout(new BorderLayout());
        bgPanel.add(mainPanel, BorderLayout.CENTER);

        setContentPane(bgPanel);
    }

    private RoundPanel createPanel() {
        panel = new RoundPanel();

        panel.setPreferredSize(new Dimension(400, 410));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);

        lbLog = createLabel("ຍິນດີເຂົ້າສູ່ລະບົບ", 20, new Color(0, 0, 0), 0, 20, 400, 50, new Font("Saysettha OT", Font.PLAIN, 20));
        panel.add(lbLog);

        title = createLabel("Palee Elite Training Center", 20, new Color(0, 0, 0), 0, 70, 400, 50, new Font("Gill Sans Nova", Font.BOLD, 24));
        panel.add(title);

        txtUsername = createTextField("ຊື່ຜູ້ໃຊ້", 30, 140, 340, 45);
        txtUsername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtPassword.requestFocus();
                }
            }
        });
        panel.add(txtUsername);

        txtPassword = createPasswordField("ລະຫັດຜ່ານ", 30, 200, 340, 45);
        panel.add(txtPassword);

        icon = new eye();
        icon.setBounds(300, 5, 40, 40);
        icon.setOpaque(false);
        txtPassword.add(icon);
        icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                togglePasswordVisibility();
            }
        });

        btnLogin = createLoginButton(30, 260, 340, 48);
        btnLogin.addActionListener(e -> attemptLogin());
        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    attemptLogin();
                }
            }
        });
        panel.add(btnLogin);
        JSeparator sp = new JSeparator();
        sp.setBounds(30, 325, 340, 30);
        sp.setForeground(Color.lightGray);
        panel.add(sp);

        btnConnectDB = new JToggleButton("<HTML><U>ເຊື່ອມຕໍ່ຖານຂໍ້ມູນ</U></HTML>");
        btnConnectDB.setBounds(30, 340, 340, 30);
        btnConnectDB.setFont(new Font("Saysettha OT", Font.PLAIN, 16));
        btnConnectDB.setForeground(Color.RED);
        btnConnectDB.setBackground(Color.WHITE);
        btnConnectDB.setBorderPainted(false);
        btnConnectDB.setContentAreaFilled(false);
        btnConnectDB.setFocusPainted(false);
        btnConnectDB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(btnConnectDB);

        // DB Chooser panel (initially hidden)
        dbChooserPanel = new JPanel();
        dbChooserPanel.setLayout(new GridLayout(3, 1, 10, 5));
        dbChooserPanel.setBounds(30, 360, 340, 140); // Below btnConnectDB
        dbChooserPanel.setBackground(new Color(255, 255, 255));

        JLabel dbLabel = createLabel("ເລືອກຖານຂໍ້ມູນ:", 16, Color.BLACK, 10, 370, 400, 40, new Font("Saysettha OT", Font.PLAIN, 14));
        dbLabel.setHorizontalAlignment(SwingConstants.LEFT);
        dbLabel.setVerticalAlignment(SwingConstants.BOTTOM);

        JComboBox<String> dbCombo = new JComboBox<>(new String[]{"ຖານຂໍ້ມູນໃນເຄື່ອງ", "ຖານຂໍ້ມູນໃນ Server"});
        dbCombo.setFont(new Font("Saysettha OT", Font.PLAIN, 16));
        dbCombo.setRenderer(new comboBoxHeight());

        JButton btnSelect = new JButton("ຕົກລົງ");
        btnSelect.setFont(new Font("Saysettha OT", Font.PLAIN, 16));
        btnSelect.setBackground(Color.GREEN);
        btnSelect.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnSelect.addActionListener(ev -> {
            String selected = (String) dbCombo.getSelectedItem();
            connectDB.setDB(selected.equals("ຖານຂໍ້ມູນໃນເຄື່ອງ") ? "local" : "server");
            connectDB.resetConnection();
            JOptionPane.showMessageDialog(this, new message("ເຊື່່ອມຕໍ່ຖານຂໍ້ມູນ " + connectDB.selectedDB.toUpperCase() + " ສຳເລັດແລ້ວ"));
            toggleDBChooser();
        });

        dbChooserPanel.add(dbLabel);
        dbChooserPanel.add(dbCombo);
        dbChooserPanel.add(btnSelect);
        dbChooserPanel.setVisible(false);
        panel.add(dbChooserPanel);

        btnConnectDB.addActionListener(e -> toggleDBChooser());

        panel.add(btnConnectDB);

        return panel;
    }

    private void toggleDBChooser() {
        isDBChooserVisible = !isDBChooserVisible;
        dbChooserPanel.setVisible(isDBChooserVisible);

        int newHeight = isDBChooserVisible ? 540 : 420; // Adjust as needed
        panel.setPreferredSize(new Dimension(400, newHeight));
        btnConnectDB.setText(isDBChooserVisible
                ? "<HTML><U>❌</U></HTML>"
                : "<HTML><U>ເຊື່ອມຕໍ່ຖານຂໍ້ມູນ</U></HTML>");
        panel.revalidate();
        panel.repaint();
    }

    private JLabel createLabel(String text, int fontSize, Color color, int x, int y, int width, int height, Font font) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(color);
        label.setFont(font);
        label.setBounds(x, y, width, height);
        return label;
    }

    private TextField createTextField(String placeholder, int x, int y, int width, int height) {
        TextField textField = new TextField();
        textField.setPlaceHolder(placeholder);
        textField.setBounds(x, y, width, height);
        textField.setFont(new Font("Saysettha OT", Font.PLAIN, 16));
        textField.setCornerRadius(14);
        return textField;
    }

    private PasswordField createPasswordField(String placeholder, int x, int y, int width, int height) {
        PasswordField passwordField = new PasswordField();
        passwordField.setPlaceHolder(placeholder);
        passwordField.setBounds(x, y, width, height);
        passwordField.setFont(new Font("Saysettha OT", Font.PLAIN, 16));
        passwordField.setCornerRadius(14);
        return passwordField;
    }

    private JButton createLoginButton(int x, int y, int width, int height) {
        JButton button = new JButton("ເຂົ້າສູ່ລະບົບ");
        button.setBounds(x, y, width, height);
        button.setBackground(new Color(38, 136, 255));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Saysettha OT", Font.BOLD, 18));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void togglePasswordVisibility() {
        if (icon.isShowPassword()) {
            txtPassword.setEchoChar((char) 0);
        } else {
            txtPassword.setEchoChar('●');
        }
    }

    private void attemptLogin() {
        JProgressBar progressBar = createProgressBar();
        panel.add(progressBar);
        progressBar.setVisible(true);
        panel.repaint();

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                try {
                    if (isInputValid()) {
                        if (validateCredentials()) {
                            SwingUtilities.invokeLater(() -> openMainPage());
                        } else {
                            showMessage("ຊື່ ຫຼື ລະຫັດຜ່ານບໍ່ຖືກຕ້ອງ!");
                            progressBar.setVisible(false);
                        }
                    } else {
                        showMessage("ກະລຸນາປ້ອນຊື່ ແລະ ລະຫັດຜ່ານກ່ອນ!");
                        txtUsername.requestFocus();
                        progressBar.setVisible(false);
                    }
                } catch (SQLException ex) {
                    showError(ex.getMessage());
                    progressBar.setVisible(false);
                }
                return null;
            }

            @Override
            protected void done() {
                progressBar.setVisible(false);
            }
        }.execute();
    }

    private boolean isInputValid() {
        return !txtUsername.getText().isEmpty() && txtPassword.getPassword().length > 0;
    }

    private boolean validateCredentials() throws SQLException {
        Username = txtUsername.getText().trim();
        password = new String(txtPassword.getPassword()).trim();

        String sql = "SELECT * FROM user WHERE Username=?";
        try (Connection cons = connectDB.getConnect(); 
                 PreparedStatement psm = cons.prepareStatement(sql)) {

            psm.setString(1, Username);

            try (ResultSet rs = psm.executeQuery()) {
                if (rs.next()) {
                    String dbUser = rs.getString("Username");
                    String dbPassword = rs.getString("password");
                    Auth = rs.getString("auth");
                    return dbUser.equals(Username) && dbPassword.equals(password);
                }
                return false;
            }
        }
    }

    private void openMainPage() {
        if ("ຜູ້ດູແລລະບົບ".equals(Auth)) {
            MainPage m = new MainPage();
            m.setVisible(true);
        } else if ("ອາຈານ".equals(Auth)) {
            openTeacherPage();
        }
        dispose();
    }

    private void openTeacherPage() {
        String sql = "SELECT * FROM teacher"
                + " inner join assignment on assignment.teacherID=teacher.teacherID"
                + " WHERE teacherName=?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, Username);
            try (ResultSet rsID = pst.executeQuery()) {
                if (rsID.next()) {
                    teacherID = rsID.getString("teacherID");
                    String name = rsID.getString("teacherName");
                    String lastname = rsID.getString("Lastname");
                    Fullname = name + " " + lastname;
                    Teaches t = new Teaches(teacherID, Fullname);
                    t.setVisible(true);
                }
            }
        } catch (SQLException ex) {
            showError(ex.getMessage());
        }
    }

    private void showMessage(String messageText) {
        message = new message(messageText);
        JOptionPane.showMessageDialog(this, message);
    }

    private void showError(String errorMessage) {
        Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, errorMessage);
    }

    private JProgressBar createProgressBar() {
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setBounds(30, 320, 340, 5);
        return progressBar;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        setSize(new java.awt.Dimension(864, 657));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatLightFlatIJTheme.setup();
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());

        } catch (Exception ex) {
            System.err.println("Failed");
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LoginPage().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
