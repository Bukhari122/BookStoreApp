package application;

import javafx.stage.Stage;

// @author Angelo Huang

public class Page {
    private Stage page;
    private PageState pageState;
    
    public Page(Stage page){
        this.page = page;
        this.pageState = new Login();
    }
    
    public void setPage(PageState newPage) {
        this.pageState = newPage;
        pageState.changePage(this);
    }
    
    public Stage getPage() {
        return page;
    }
    
    public void start() {
        pageState.changePage(this);
        page.show();
    }
    
}