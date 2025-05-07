package Model;

import Database.connectDB;
import java.sql.Connection;
import java.sql.ResultSet;

public class GenerateID {

    Connection con = connectDB.getConnect();

    public String generateID(String sql, String value, int paddingLength) {
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            if (rs.next()) {
                String id = rs.getString("ID");
                if (id != null) {
                    int num = Integer.parseInt(id.substring(value.length()));
                    String format = "%0" + paddingLength + "d"; 
                    return value + String.format(format, num + 1);
                } else {
                    return value + String.format("%0" + paddingLength + "d", 1);
                }
            } else {
                return value + String.format("%0" + paddingLength + "d", 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
