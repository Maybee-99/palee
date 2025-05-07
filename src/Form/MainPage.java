package Form;
import Database.connectDB;
import Report.AllStudent;
import Report.Allteacher;
import Report.ScholarShipSTD;
import Report.StudentWithRisidence;
import Report.finance;
import Report.learning;
import Report.learning1;
import Report.reportDonate;
import Report.teacherWithSub;
import Subform.CalculateSalary;
import Subform.Donate;
import Subform.Finance;
import Subform.InternalSubject;
import Subform.LevelDialog;
import Subform.Popularity;
import Subform.Prize;
import Subform.RegisterStudent;
import Subform.RegisterTeacher;
import Subform.Student;
import Subform.SubjectDialog;
import Subform.Teacher;
import Subform.User;
import Subform.checkTeaching;
import Subform.formPay;
import Subform.subjecttypeDialog;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.UIManager;

public class MainPage extends javax.swing.JFrame {

    Dashboard dashboard;

    public MainPage() {
        initComponents();
        setTitle( connectDB.selectedDB.toUpperCase());
        setExtendedState(MAXIMIZED_BOTH);
        dashboard = new Dashboard();
        dashboard.setVisible(true);
        setAppIcon();

        if (desktopPane == null) {
            desktopPane = new JDesktopPane();
            add(desktopPane);
        }

        desktopPane.add(dashboard);

        Font customFont = new Font("Saysettha OT", Font.PLAIN, 16);
        UIManager.put("InternalFrame.titleFont", customFont);
        UIManager.put("InternalFrame.activeTitleBackground", new Color(242, 242, 242));
        UIManager.put("InternalFrame.titleForeground", Color.WHITE);

    }

    private void setAppIcon() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/Icon/palee management system.png"));
        setIconImage(icon.getImage());
    }

    private void checkAndShowDashboard() {
        if (desktopPane.getAllFrames().length == 0) {
            dashboard = new Dashboard();
            desktopPane.add(dashboard);
            dashboard.setSize(desktopPane.getSize());
            dashboard.setLocation(0, 0);
            dashboard.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        Student = new javax.swing.JMenuItem();
        Teacher = new javax.swing.JMenuItem();
        tt = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        SubjectType = new javax.swing.JMenuItem();
        Level = new javax.swing.JMenuItem();
        Subject = new javax.swing.JMenuItem();
        SubjectDetail = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        Donate = new javax.swing.JMenuItem();
        Income = new javax.swing.JMenuItem();
        Expense = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        User = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        backUp = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        Exit = new javax.swing.JMenuItem();
        fileMenuRegis = new javax.swing.JMenu();
        RegisterStudent = new javax.swing.JMenuItem();
        RegisterStudent1 = new javax.swing.JMenuItem();
        fileMenuSave = new javax.swing.JMenu();
        Teach = new javax.swing.JMenuItem();
        pay1 = new javax.swing.JMenu();
        prize = new javax.swing.JMenuItem();
        SaveIncome = new javax.swing.JMenu();
        CalSalary = new javax.swing.JMenuItem();
        pay = new javax.swing.JMenu();
        fee = new javax.swing.JMenuItem();
        Report = new javax.swing.JMenu();
        rptStudent = new javax.swing.JMenuItem();
        rptStudent1 = new javax.swing.JMenuItem();
        rptStudent2 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        rptTeacher = new javax.swing.JMenuItem();
        rptTeacher1 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        rptFinance = new javax.swing.JMenuItem();
        rptFinance1 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        desktopPane.setBackground(new java.awt.Color(204, 204, 204));
        desktopPane.setLayout(new java.awt.GridLayout(1, 0));
        getContentPane().add(desktopPane);

        menuBar.setBackground(new java.awt.Color(242, 242, 242));
        menuBar.setBorder(null);
        menuBar.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        menuBar.setMaximumSize(new java.awt.Dimension(1540, 32767));
        menuBar.setMinimumSize(new java.awt.Dimension(1540, 50));
        menuBar.setPreferredSize(new java.awt.Dimension(2000, 50));

        fileMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/folder-open.png"))); // NOI18N
        fileMenu.setMnemonic('f');
        fileMenu.setText("ຈັດການຂໍ້ມູນ");
        fileMenu.setAutoscrolls(true);
        fileMenu.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N

        Student.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        Student.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/student.png"))); // NOI18N
        Student.setMnemonic('o');
        Student.setText("ຂໍ້ມູນນັກຮຽນ");
        Student.setIconTextGap(12);
        Student.setMargin(new java.awt.Insets(5, 10, 5, 10));
        Student.setPreferredSize(new java.awt.Dimension(200, 45));
        Student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StudentActionPerformed(evt);
            }
        });
        fileMenu.add(Student);

        Teacher.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        Teacher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/teacher.png"))); // NOI18N
        Teacher.setMnemonic('s');
        Teacher.setText("ຂໍ້ມູນອາຈານ");
        Teacher.setIconTextGap(12);
        Teacher.setMargin(new java.awt.Insets(5, 10, 5, 10));
        Teacher.setPreferredSize(new java.awt.Dimension(200, 45));
        Teacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TeacherActionPerformed(evt);
            }
        });
        fileMenu.add(Teacher);

        tt.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        tt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/time-check.png"))); // NOI18N
        tt.setMnemonic('s');
        tt.setText("ຄົ້ນຫາການສອນ");
        tt.setIconTextGap(12);
        tt.setMargin(new java.awt.Insets(5, 10, 5, 10));
        tt.setPreferredSize(new java.awt.Dimension(200, 45));
        tt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttActionPerformed(evt);
            }
        });
        fileMenu.add(tt);
        fileMenu.add(jSeparator1);

        SubjectType.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        SubjectType.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/menu.png"))); // NOI18N
        SubjectType.setMnemonic('a');
        SubjectType.setText("ໝວດວິຊາ");
        SubjectType.setIconTextGap(12);
        SubjectType.setMargin(new java.awt.Insets(5, 10, 5, 10));
        SubjectType.setPreferredSize(new java.awt.Dimension(200, 45));
        SubjectType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubjectTypeActionPerformed(evt);
            }
        });
        fileMenu.add(SubjectType);

        Level.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        Level.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/settings-sliders.png"))); // NOI18N
        Level.setMnemonic('a');
        Level.setText("ຊັ້ນຮຽນ");
        Level.setIconTextGap(12);
        Level.setMargin(new java.awt.Insets(5, 10, 5, 10));
        Level.setPreferredSize(new java.awt.Dimension(200, 45));
        Level.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LevelActionPerformed(evt);
            }
        });
        fileMenu.add(Level);

        Subject.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        Subject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/book.png"))); // NOI18N
        Subject.setMnemonic('a');
        Subject.setText("ວິຊາ");
        Subject.setIconTextGap(12);
        Subject.setMargin(new java.awt.Insets(5, 10, 5, 10));
        Subject.setPreferredSize(new java.awt.Dimension(200, 45));
        Subject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubjectActionPerformed(evt);
            }
        });
        fileMenu.add(Subject);

        SubjectDetail.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        SubjectDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/education.png"))); // NOI18N
        SubjectDetail.setMnemonic('a');
        SubjectDetail.setText("ຄ່າຮຽນ");
        SubjectDetail.setIconTextGap(12);
        SubjectDetail.setMargin(new java.awt.Insets(5, 10, 5, 10));
        SubjectDetail.setPreferredSize(new java.awt.Dimension(200, 45));
        SubjectDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubjectDetailActionPerformed(evt);
            }
        });
        fileMenu.add(SubjectDetail);
        fileMenu.add(jSeparator4);

        Donate.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        Donate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/donate.png"))); // NOI18N
        Donate.setMnemonic('o');
        Donate.setText("ບໍລິຈາກ");
        Donate.setIconTextGap(12);
        Donate.setPreferredSize(new java.awt.Dimension(200, 45));
        Donate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DonateActionPerformed(evt);
            }
        });
        fileMenu.add(Donate);

        Income.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        Income.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/wallet-income.png"))); // NOI18N
        Income.setMnemonic('o');
        Income.setText("ລາຍຮັບ");
        Income.setIconTextGap(12);
        Income.setPreferredSize(new java.awt.Dimension(200, 45));
        Income.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IncomeActionPerformed(evt);
            }
        });
        fileMenu.add(Income);

        Expense.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        Expense.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/expenses.png"))); // NOI18N
        Expense.setMnemonic('o');
        Expense.setText("ລາຍຈ່າຍ");
        Expense.setIconTextGap(12);
        Expense.setPreferredSize(new java.awt.Dimension(200, 45));
        Expense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExpenseActionPerformed(evt);
            }
        });
        fileMenu.add(Expense);
        fileMenu.add(jSeparator2);

        User.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        User.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user.png"))); // NOI18N
        User.setMnemonic('a');
        User.setText("ຜູ້ໃຊ້ລະບົບ");
        User.setIconTextGap(12);
        User.setMargin(new java.awt.Insets(5, 10, 5, 10));
        User.setPreferredSize(new java.awt.Dimension(200, 45));
        User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserActionPerformed(evt);
            }
        });
        fileMenu.add(User);
        fileMenu.add(jSeparator3);

        backUp.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        backUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/back-up.png"))); // NOI18N
        backUp.setMnemonic('a');
        backUp.setText("ຂໍ້ມູນສຳຮອງ");
        backUp.setIconTextGap(12);
        backUp.setMargin(new java.awt.Insets(5, 10, 5, 10));
        backUp.setPreferredSize(new java.awt.Dimension(200, 45));
        backUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backUpActionPerformed(evt);
            }
        });
        fileMenu.add(backUp);
        fileMenu.add(jSeparator5);

        Exit.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        Exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/exit.png"))); // NOI18N
        Exit.setMnemonic('a');
        Exit.setText("ອອກຈາກລະບົບ");
        Exit.setIconTextGap(12);
        Exit.setMargin(new java.awt.Insets(5, 10, 5, 10));
        Exit.setPreferredSize(new java.awt.Dimension(200, 45));
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        fileMenu.add(Exit);

        menuBar.add(fileMenu);

        fileMenuRegis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration-paper.png"))); // NOI18N
        fileMenuRegis.setMnemonic('f');
        fileMenuRegis.setText("ລົງທະບຽນ");
        fileMenuRegis.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N

        RegisterStudent.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        RegisterStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/student.png"))); // NOI18N
        RegisterStudent.setMnemonic('o');
        RegisterStudent.setText("ນັກຮຽນ");
        RegisterStudent.setIconTextGap(12);
        RegisterStudent.setPreferredSize(new java.awt.Dimension(150, 45));
        RegisterStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterStudentActionPerformed(evt);
            }
        });
        fileMenuRegis.add(RegisterStudent);

        RegisterStudent1.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        RegisterStudent1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/school.png"))); // NOI18N
        RegisterStudent1.setMnemonic('o');
        RegisterStudent1.setText("ອາຈານ");
        RegisterStudent1.setIconTextGap(12);
        RegisterStudent1.setPreferredSize(new java.awt.Dimension(150, 45));
        RegisterStudent1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterStudent1ActionPerformed(evt);
            }
        });
        fileMenuRegis.add(RegisterStudent1);

        menuBar.add(fileMenuRegis);

        fileMenuSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/assessment.png"))); // NOI18N
        fileMenuSave.setMnemonic('f');
        fileMenuSave.setText("ກວດສອບວິຊາ");
        fileMenuSave.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N

        Teach.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        Teach.setMnemonic('o');
        Teach.setText("ວິຊາຍອດນິຍົມ");
        Teach.setIconTextGap(0);
        Teach.setPreferredSize(new java.awt.Dimension(150, 45));
        Teach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TeachActionPerformed(evt);
            }
        });
        fileMenuSave.add(Teach);

        menuBar.add(fileMenuSave);

        pay1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/box.png"))); // NOI18N
        pay1.setMnemonic('f');
        pay1.setText("ມອບລາງວັນ");
        pay1.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N

        prize.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        prize.setMnemonic('o');
        prize.setText("ມອບລາງວັນໃຫ້ນັກຮຽນ");
        prize.setIconTextGap(0);
        prize.setPreferredSize(new java.awt.Dimension(200, 45));
        prize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prizeActionPerformed(evt);
            }
        });
        pay1.add(prize);

        menuBar.add(pay1);

        SaveIncome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/payroll-check.png"))); // NOI18N
        SaveIncome.setMnemonic('f');
        SaveIncome.setText("ຈ່າຍເງິນເດືອນ");
        SaveIncome.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N

        CalSalary.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        CalSalary.setMnemonic('o');
        CalSalary.setText("ຈ່າຍເງິນເດືອນໃຫ້ອາຈານ");
        CalSalary.setIconTextGap(0);
        CalSalary.setPreferredSize(new java.awt.Dimension(220, 45));
        CalSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalSalaryActionPerformed(evt);
            }
        });
        SaveIncome.add(CalSalary);

        menuBar.add(SaveIncome);

        pay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bill.png"))); // NOI18N
        pay.setMnemonic('f');
        pay.setText("ຈ່າຍຄ່າຮຽນ");
        pay.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N

        fee.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        fee.setMnemonic('o');
        fee.setText("ຈ່າຍຄ່າຮຽນ");
        fee.setIconTextGap(0);
        fee.setPreferredSize(new java.awt.Dimension(120, 45));
        fee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feeActionPerformed(evt);
            }
        });
        pay.add(fee);

        menuBar.add(pay);

        Report.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/newspaper.png"))); // NOI18N
        Report.setMnemonic('f');
        Report.setText("ລາຍງານ");
        Report.setFont(new java.awt.Font("Saysettha OT", 1, 18)); // NOI18N
        Report.setIconTextGap(10);
        Report.setInheritsPopupMenu(true);

        rptStudent.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        rptStudent.setMnemonic('o');
        rptStudent.setText("ນັກຮຽນທັງໝົດ");
        rptStudent.setIconTextGap(0);
        rptStudent.setPreferredSize(new java.awt.Dimension(250, 50));
        rptStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rptStudentActionPerformed(evt);
            }
        });
        Report.add(rptStudent);

        rptStudent1.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        rptStudent1.setMnemonic('o');
        rptStudent1.setText("ນັກຮຽນໄດ້ຮັບທຶນ/ ບໍ່ໄດ້ຮັບທຶນ");
        rptStudent1.setIconTextGap(0);
        rptStudent1.setPreferredSize(new java.awt.Dimension(250, 50));
        rptStudent1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rptStudent1ActionPerformed(evt);
            }
        });
        Report.add(rptStudent1);

        rptStudent2.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        rptStudent2.setMnemonic('o');
        rptStudent2.setText("ນັກຮຽນຫໍພັກນອກ/ ພໍພັກໃນ");
        rptStudent2.setIconTextGap(0);
        rptStudent2.setPreferredSize(new java.awt.Dimension(250, 50));
        rptStudent2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rptStudent2ActionPerformed(evt);
            }
        });
        Report.add(rptStudent2);
        Report.add(jSeparator6);

        rptTeacher.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        rptTeacher.setMnemonic('o');
        rptTeacher.setText("ອາຈານທັງໝົດ");
        rptTeacher.setIconTextGap(0);
        rptTeacher.setPreferredSize(new java.awt.Dimension(250, 50));
        rptTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rptTeacherActionPerformed(evt);
            }
        });
        Report.add(rptTeacher);

        rptTeacher1.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        rptTeacher1.setMnemonic('o');
        rptTeacher1.setText("ອາຈານຕາມວິຊາສອນ");
        rptTeacher1.setIconTextGap(0);
        rptTeacher1.setPreferredSize(new java.awt.Dimension(250, 50));
        rptTeacher1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rptTeacher1ActionPerformed(evt);
            }
        });
        Report.add(rptTeacher1);
        Report.add(jSeparator7);

        rptFinance.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        rptFinance.setMnemonic('o');
        rptFinance.setText("ລາຍຮັບ ແລະ ລາຍຈ່າຍ");
        rptFinance.setIconTextGap(0);
        rptFinance.setPreferredSize(new java.awt.Dimension(250, 50));
        rptFinance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rptFinanceActionPerformed(evt);
            }
        });
        Report.add(rptFinance);

        rptFinance1.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        rptFinance1.setMnemonic('o');
        rptFinance1.setText("ການບໍລິຈາກເຄື່ອງ");
        rptFinance1.setIconTextGap(0);
        rptFinance1.setPreferredSize(new java.awt.Dimension(250, 50));
        rptFinance1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rptFinance1ActionPerformed(evt);
            }
        });
        Report.add(rptFinance1);
        Report.add(jSeparator8);

        jMenuItem1.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jMenuItem1.setText("ຜົນເສັງກາງພາກ/ທ້າຍພາກ");
        jMenuItem1.setIconTextGap(0);
        jMenuItem1.setPreferredSize(new java.awt.Dimension(250, 50));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        Report.add(jMenuItem1);

        jMenuItem2.setFont(new java.awt.Font("Saysettha OT", 0, 16)); // NOI18N
        jMenuItem2.setText("ຜົນການຮຽນຕາມລາຍວິຊາ");
        jMenuItem2.setIconTextGap(0);
        jMenuItem2.setPreferredSize(new java.awt.Dimension(250, 50));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        Report.add(jMenuItem2);

        menuBar.add(Report);

        setJMenuBar(menuBar);

        setSize(new java.awt.Dimension(1564, 817));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void openFinanceForm(Class<? extends Finance> financeClass) {
        try {
            desktopPane.removeAll();
            Finance financeForm = financeClass.getDeclaredConstructor().newInstance();

            financeForm.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
                @Override
                public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                    checkAndShowDashboard();
                }
            });

            desktopPane.add(financeForm);
            financeForm.setSize(desktopPane.getSize());
            financeForm.setLocation(0, 0);
            financeForm.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void IncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IncomeActionPerformed
        desktopPane.removeAll();
        openFinanceForm(Model.Income.class);
    }//GEN-LAST:event_IncomeActionPerformed

    private void RegisterStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterStudentActionPerformed
        desktopPane.removeAll();
        RegisterStudent r = new RegisterStudent();
        r.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(r);
        r.setSize(desktopPane.getSize());
        r.setLocation(0, 0);
        r.setVisible(true);
    }//GEN-LAST:event_RegisterStudentActionPerformed

    private void ExpenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExpenseActionPerformed
        openFinanceForm(Model.Expense.class);
    }//GEN-LAST:event_ExpenseActionPerformed

    private void CalSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalSalaryActionPerformed
        desktopPane.removeAll();
        CalculateSalary sa = new CalculateSalary();
        sa.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(sa);
        sa.setSize(desktopPane.getSize());
        sa.setLocation(0, 0);
        sa.setVisible(true);
    }//GEN-LAST:event_CalSalaryActionPerformed

    private void TeachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TeachActionPerformed
        desktopPane.removeAll();
        Popularity p = new Popularity();
        p.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(p);
        p.setSize(desktopPane.getSize());
        p.setLocation(0, 0);
        p.setVisible(true);
    }//GEN-LAST:event_TeachActionPerformed

    private void rptFinanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptFinanceActionPerformed
        desktopPane.removeAll();
        finance f = new finance();
        f.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(f);
        f.setLocation(0, 0);
        f.setVisible(true);
    }//GEN-LAST:event_rptFinanceActionPerformed

    private void rptTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptTeacherActionPerformed
        desktopPane.removeAll();
        Allteacher all = new Allteacher();
        all.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(all);
        all.setLocation(0, 0);
        all.setVisible(true);
    }//GEN-LAST:event_rptTeacherActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed

    private void UserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserActionPerformed
        desktopPane.removeAll();
        User u = new User();
        u.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(u);
        u.setSize(desktopPane.getSize());
        u.setLocation(0, 0);
        u.setVisible(true);
    }//GEN-LAST:event_UserActionPerformed

    private void TeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TeacherActionPerformed
        desktopPane.removeAll();
        Teacher teacher = new Teacher();
        teacher.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(teacher);
        teacher.setSize(desktopPane.getSize());
        teacher.setLocation(0, 0);
        teacher.setVisible(true);
    }//GEN-LAST:event_TeacherActionPerformed

    private void StudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StudentActionPerformed
        desktopPane.removeAll();
        Student std = new Student();
        std.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(std);
        std.setSize(desktopPane.getSize());
        std.setLocation(0, 0);
        std.setVisible(true);
    }//GEN-LAST:event_StudentActionPerformed

    private void SubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubjectActionPerformed
        SubjectDialog s = new SubjectDialog(this, true);
        s.setVisible(true);
    }//GEN-LAST:event_SubjectActionPerformed

    private void DonateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DonateActionPerformed
        desktopPane.removeAll();
        Donate dn = new Donate();
        dn.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(dn);
        dn.setSize(desktopPane.getSize());
        dn.setLocation(0, 0);
        dn.setVisible(true);
    }//GEN-LAST:event_DonateActionPerformed

    private void feeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feeActionPerformed
        desktopPane.removeAll();
        formPay fp = new formPay();
        fp.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(fp);
        fp.setSize(desktopPane.getSize());
        fp.setLocation(0, 0);
        fp.setVisible(true);
    }//GEN-LAST:event_feeActionPerformed

    private void rptFinance1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptFinance1ActionPerformed
        desktopPane.removeAll();
        reportDonate rd = new reportDonate();
        rd.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(rd);
        rd.setSize(desktopPane.getSize());
        rd.setLocation(0, 0);
        rd.setVisible(true);
    }//GEN-LAST:event_rptFinance1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        desktopPane.removeAll();
        learning learn = new learning();
        learn.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(learn);
        learn.setLocation(0, 0);
        learn.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        desktopPane.removeAll();
        learning1 learn1 = new learning1();
        learn1.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(learn1);
        learn1.setLocation(0, 0);
        learn1.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void RegisterStudent1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterStudent1ActionPerformed
        desktopPane.removeAll();
        RegisterTeacher rt = new RegisterTeacher();
        rt.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(rt);
        rt.setLocation(0, 0);
        rt.setVisible(true);
    }//GEN-LAST:event_RegisterStudent1ActionPerformed

    private void backUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backUpActionPerformed
        desktopPane.removeAll();
        BackUp bu = new BackUp();
        bu.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(bu);
        bu.setLocation(0, 0);
        bu.setVisible(true);
    }//GEN-LAST:event_backUpActionPerformed

    private void SubjectDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubjectDetailActionPerformed
        desktopPane.removeAll();
        InternalSubject sub = new InternalSubject();
        sub.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(sub);
        sub.setLocation(0, 0);
        sub.setVisible(true);
    }//GEN-LAST:event_SubjectDetailActionPerformed

    private void LevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LevelActionPerformed
        LevelDialog l = new LevelDialog(this, true);
        l.setVisible(true);
    }//GEN-LAST:event_LevelActionPerformed

    private void SubjectTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubjectTypeActionPerformed
        subjecttypeDialog st = new subjecttypeDialog(this, true);
        st.setVisible(true);
    }//GEN-LAST:event_SubjectTypeActionPerformed

    private void rptStudent1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptStudent1ActionPerformed
        desktopPane.removeAll();
        ScholarShipSTD std = new ScholarShipSTD();
        std.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(std);
        std.setLocation(0, 0);
        std.setVisible(true);
    }//GEN-LAST:event_rptStudent1ActionPerformed

    private void rptStudent2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptStudent2ActionPerformed
        desktopPane.removeAll();
        StudentWithRisidence std = new StudentWithRisidence();
        std.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(std);
        std.setLocation(0, 0);
        std.setVisible(true);
    }//GEN-LAST:event_rptStudent2ActionPerformed

    private void rptTeacher1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptTeacher1ActionPerformed

        desktopPane.removeAll();
        teacherWithSub teacer = new teacherWithSub();
        teacer.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(teacer);
        teacer.setLocation(0, 0);
        teacer.setVisible(true);
    }//GEN-LAST:event_rptTeacher1ActionPerformed

    private void ttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttActionPerformed
        desktopPane.removeAll();
        checkTeaching teacer = new checkTeaching();
        teacer.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(teacer);
        teacer.setLocation(0, 0);
        teacer.setVisible(true);
    }//GEN-LAST:event_ttActionPerformed

    private void rptStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptStudentActionPerformed
        desktopPane.removeAll();
        AllStudent all = new AllStudent();
        all.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(all);
        all.setLocation(0, 0);
        all.setVisible(true);
    }//GEN-LAST:event_rptStudentActionPerformed

    private void prizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prizeActionPerformed
        desktopPane.removeAll();
        Prize prize = new Prize();
        prize.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                checkAndShowDashboard();
            }
        });
        desktopPane.add(prize);
        prize.setLocation(0, 0);
        prize.setVisible(true);
    }//GEN-LAST:event_prizeActionPerformed

    public static void main(String args[]) {
        FlatLightFlatIJTheme.setup();
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed");
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem CalSalary;
    private javax.swing.JMenuItem Donate;
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenuItem Expense;
    private javax.swing.JMenuItem Income;
    private javax.swing.JMenuItem Level;
    private javax.swing.JMenuItem RegisterStudent;
    private javax.swing.JMenuItem RegisterStudent1;
    private javax.swing.JMenu Report;
    private javax.swing.JMenu SaveIncome;
    private javax.swing.JMenuItem Student;
    private javax.swing.JMenuItem Subject;
    private javax.swing.JMenuItem SubjectDetail;
    private javax.swing.JMenuItem SubjectType;
    private javax.swing.JMenuItem Teach;
    private javax.swing.JMenuItem Teacher;
    private javax.swing.JMenuItem User;
    private javax.swing.JMenuItem backUp;
    public javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem fee;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu fileMenuRegis;
    private javax.swing.JMenu fileMenuSave;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu pay;
    private javax.swing.JMenu pay1;
    private javax.swing.JMenuItem prize;
    private javax.swing.JMenuItem rptFinance;
    private javax.swing.JMenuItem rptFinance1;
    private javax.swing.JMenuItem rptStudent;
    private javax.swing.JMenuItem rptStudent1;
    private javax.swing.JMenuItem rptStudent2;
    private javax.swing.JMenuItem rptTeacher;
    private javax.swing.JMenuItem rptTeacher1;
    private javax.swing.JMenuItem tt;
    // End of variables declaration//GEN-END:variables

}
