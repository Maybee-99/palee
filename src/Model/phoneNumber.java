package Model;

import javax.swing.*;
import java.sql.*;

public class phoneNumber {

    private Connection con;

    public phoneNumber(Connection connection) {
        this.con = connection;
    }

    public void validatePhoneNumber(JTextField txt, String sqlQuery, String mes) {
        int textLength = txt.getText().length();

        if (!txt.getText().isEmpty() && textLength < 11) {
            JOptionPane.showMessageDialog(null, new message("ກະລຸນາປ້ອນໝາຍເລກໂທລະສັບໃຫ້ຄົບ"));
            txt.selectAll();
            txt.requestFocus();
            return;
        }

        try {
            if (!txt.getText().isEmpty()) {
                try (PreparedStatement pst = con.prepareStatement(sqlQuery)) {
                    pst.setString(1, txt.getText());
                    try (ResultSet rs = pst.executeQuery()) {
                        if (rs.next()) {
                            JOptionPane.showMessageDialog(null, new message(mes));
                            txt.selectAll();
                            txt.requestFocus();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
