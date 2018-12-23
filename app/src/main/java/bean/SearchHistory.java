package bean;

import org.litepal.crud.LitePalSupport;

public class SearchHistory extends LitePalSupport {
    private String history;

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}
