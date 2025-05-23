
package Model;

import javax.swing.Icon;

public class Model_Card {


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Model_Card( String title, String values, String description) {
        this.title = title;
        this.values = values;
        this.description = description;
    }

    public Model_Card() {
    }

    private String title;
    private String values;
    private String description;
}

