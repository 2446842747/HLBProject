package bean;

import org.litepal.crud.LitePalSupport;

public class Label extends LitePalSupport {
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
