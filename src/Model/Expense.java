package Model;

import Subform.Finance;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Expense extends Finance {
    public Expense()
    {
        super("ຈັດການຂໍ້ມູນລາຍຈ່າຍ");
        
    }

    @Override
    protected String getSelectData() {
        return "selectExpense()";
    }

    @Override
    protected String getInsertData() {
        return "insertExpense";
    }

    @Override
    protected String getUpdateData() {
        return "updateExpense";
    }

    @Override
    protected String getDeleteData() {
        return "deleteExpense";
    }

    @Override
    protected String[] getRowData(ResultSet rs) throws SQLException {
    return new String[]{
            rs.getString("expenseID"),
            df.format(rs.getInt("Amount")) + " ກີບ",
            rs.getString("expenseDate"),
            rs.getString("description")
        };
    }

}
