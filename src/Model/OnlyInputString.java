
package Model;

import Model.message;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;


public class OnlyInputString implements KeyListener{
    private final message message;
    public OnlyInputString(message message)
    {
        this.message=message;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char text = e.getKeyChar();
         String allowedSymbols = " ່ ໋ ໊ ້ "; 
        if (!Character.isAlphabetic(text) && text != KeyEvent.VK_BACK_SPACE && text != KeyEvent.VK_SPACE && text != KeyEvent.VK_ENTER && allowedSymbols.indexOf(text)==-1 ) {
            e.consume(); 
            JOptionPane.showMessageDialog(null, message);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
  
    }

    @Override
    public void keyReleased(KeyEvent e) {
   
    }
    
    
    
}
