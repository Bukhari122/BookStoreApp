package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.FXCollections;
import javafx.beans.property.SimpleStringProperty;
import java.util.ArrayList;
import java.util.List;

public class CustomerDashboardScreen implements PageState {

    private CustomerClass customer;
    private List<CheckBox> bookCheckboxes = new ArrayList<>();

    public CustomerDashboardScreen(CustomerClass customer) {
        this.customer = customer;
    }

    @Override
    public void changePage(Page page) {
        Label welcome = new Label("Welcome, " + customer.getUsername());
        Label status = new Label("Points: " + customer.getPoints() + " | Status: " + customer.getStatus());

        VBox bookBox = new VBox(5);
        bookBox.getChildren().add(new Label("Available Books:"));

        for (Book book : BooksClass.getBooks()) {
            CheckBox check = new CheckBox(book.getName() + " - $" + book.getPrice());
            check.setUserData(book); // Save the Book object in checkbox
            bookCheckboxes.add(check);
            bookBox.getChildren().add(check);
        }

        Button buyButton = new Button("Buy");
        Button redeemButton = new Button("Redeem Points & Buy");
        Button logoutButton = new Button("Logout");

        buyButton.setOnAction(e -> {
            List<Book> selectedBooks = getSelectedBooks();
            page.setPage(new CustomerBuyScreen(customer, selectedBooks));
        });

        redeemButton.setOnAction(e -> {
            List<Book> selectedBooks = getSelectedBooks();
            page.setPage(new CustomerBuyPointsScreen(customer, selectedBooks));
        });

        logoutButton.setOnAction(e -> page.setPage(new Login()));

        VBox layout = new VBox(15, welcome, status, bookBox, buyButton, redeemButton, logoutButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center-left;");

        Scene scene = new Scene(layout, 600, 400);
        page.getPage().setScene(scene);
    }

    private List<Book> getSelectedBooks() {
        List<Book> selected = new ArrayList<>();
        for (CheckBox cb : bookCheckboxes) {
            if (cb.isSelected()) {
                selected.add((Book) cb.getUserData());
            }
        }
        return selected;
    }
}
