
package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CurrentDate {
    public String DateTimeNow(String format) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return LocalDateTime.now().format(formatter);
}
}
