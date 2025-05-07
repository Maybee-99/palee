package Model;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBar extends JScrollBar {

    public ScrollBar() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(8, 8));
        setBackground(new Color(240, 240, 240));
        setUnitIncrement(30);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
