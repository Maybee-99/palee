package Model;

import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class subjectModel {

    public String subID;
    public String subName;
    public String level;
    public int cost;
    public JCheckBox cbSub;
    public JLabel lbName;
    public JLabel lbCost;

    public subjectModel(
            String subID,
            String subName,
            String level,
            int cost
    ) {
        this.subID = subID;
        this.subName = subName;
        this.level = level;
        this.cost = cost;

        cbSub = new JCheckBox(subName +" "+level);
        cbSub.setFont(new Font("Saysettha OT", 0, 16));
        cbSub.setCursor(new Cursor(Cursor.HAND_CURSOR));


    }


}
