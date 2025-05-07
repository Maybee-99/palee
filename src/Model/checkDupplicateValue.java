
package Model;

import javax.swing.JTable;



public class checkDupplicateValue {
    public static boolean isUnique(JTable table,String value,int ind)
    {
        for(int i=0;i<table.getRowCount();i++)
        {
            String tbValue=table.getValueAt(i, ind).toString();
            if(value.equals(tbValue))
            {
                return false;
            }
        }
        return true;
    }
}
