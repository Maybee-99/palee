package Model;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JLabel;

public class message extends JLabel {
    public message(String text) {
        super(text);
        setFont(new Font("Saysettha OT", Font.PLAIN, 16));
        
        FontMetrics metrics = getFontMetrics(getFont());
        int textWidth = metrics.stringWidth(text);
        
        setPreferredSize(new Dimension(textWidth + 100, 40)); 
    }
}
