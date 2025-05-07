package Model;

import Subform.Finance;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Income extends Finance {
public Income()
    {
        super("ຈັດການຂໍ້ມູນລາຍຮັບ");
        
    }
    @Override
    protected String getSelectData() {
        return "selectIncome()";
    }

    @Override
    protected String getInsertData() {
        return "insertIncome";
    }

    @Override
    protected String getUpdateData() {
        return "updateIncome";
    }

    @Override
    protected String getDeleteData() {
        return "deleteIncome";
    }

    @Override
    protected String[] getRowData(ResultSet rs) throws SQLException {
        return new String[]{
            rs.getString("incomeID"),
            df.format(rs.getInt("Amount")) + " ກີບ",
            rs.getString("incomeDate"),
            rs.getString("description")
        };
    }

}
