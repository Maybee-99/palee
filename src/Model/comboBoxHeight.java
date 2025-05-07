package Model;

import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;

public class comboBoxHeight extends BasicComboBoxRenderer {
    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.height = 34; 
        return size;
    }
}

