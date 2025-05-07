package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class financial {
    public static int getTotal(Connection con, String table, String dateColumn, String year) {
        int total = 0;
        String query = "SELECT SUM(Amount) AS totalAmount FROM " + table + " WHERE YEAR(" + dateColumn + ") = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, year);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    total = rs.getInt("totalAmount");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

}
