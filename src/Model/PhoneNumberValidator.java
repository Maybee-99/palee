package Model;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class PhoneNumberValidator {

    public static void Checkvalidate(JTextField txt) {
        ((AbstractDocument) txt.getDocument()).setDocumentFilter(new PhoneNumber());
    }

    public static class PhoneNumber extends DocumentFilter {

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            replace(fb, offset, offset, string, attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            String currentText=fb.getDocument().getText(0, fb.getDocument().getLength());
            StringBuilder newText=new StringBuilder(currentText);
            newText.replace(offset, offset+length, text);
            
            String formattedText=formatPhoneNumber(newText.toString());
            if(formattedText!=null)
            {
                fb.replace(0, fb.getDocument().getLength(), formattedText, attrs);
            }
        }
        private String formatPhoneNumber(String text)
        {
            text=text.replaceAll("\\D", "");
            
            if(text.length()<=3 && ("020".startsWith(text)||"030".startsWith(text)))
            {
                return text;
            }
            if(!(text.startsWith("020")||text.startsWith("030")))
            {
                return null;
            }
            if(text.length()>=4 && !"24579".contains(Character.toString(text.charAt(3))))
            {
                return null;
            }
            return text.length()>11? text.substring(0, 11):text;
        }
        
    }
}
