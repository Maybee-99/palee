package Model;

import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class formatNumber {

    public static void apply(JTextField txt) {
        txt.getDocument().addDocumentListener(new DocumentListener() {
            private boolean update=false;
            @Override
            public void insertUpdate(DocumentEvent e) {
                formatted();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                formatted();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                formatted();
            }
            private void formatted(){
                if(update)return;
                update=true;
                
                SwingUtilities.invokeLater(()->{
                
                    String str=txt.getText().replaceAll(",", "");
                    if(!str.isEmpty())
                    {
                        try {
                            long number=Long.parseLong(str);
                            NumberFormat formatter=NumberFormat.getInstance(Locale.US);
                            txt.setText(formatter.format(number));
                        } catch (NumberFormatException ignored) {
                        }
                    }
                    update=false;
                });
            }
            public static long getNumber(JTextField txt){
                String text=txt.getText().replaceAll(",", "");
                return text.isEmpty()? 0: Long.parseLong(text);
            }

        });
    }

}
