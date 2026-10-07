package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class OwnerDashboardScreen implements PageState {

    @Override
    public void changePage(Page page) {
        Label welcome = new Label("Welcome, Admin!");

        Button customerBtn = new Button("Manage Customers");
        Button bookBtn = new Button("Manage Books");
        Button logoutBtn = new Button("Logout");

        customerBtn.setOnAction(e -> page.setPage(new OwnerCustomerScreen()));

        // ? Fixed: Navigate to the OwnerBookScreen
        bookBtn.setOnAction(e -> page.setPage(new OwnerBookScreen()));

        logoutBtn.setOnAction(e -> page.setPage(new Login()));

        VBox layout = new VBox(15, welcome, customerBtn, bookBtn, logoutBtn);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 300);
        page.getPage().setScene(scene);
    }
}


