package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.List;

public class CustomerBuyScreen implements PageState {
    private CustomerClass customer;
    private List<Book> selectedBooks;

    public CustomerBuyScreen(CustomerClass customer, List<Book> selectedBooks) {
        this.customer = customer;
        this.selectedBooks = selectedBooks;
    }

    @Override
    public void changePage(Page page) {
        double total = selectedBooks.stream().mapToDouble(Book::getPrice).sum();
        int earnedPoints = (int) (total * 10);
        customer.setPoints(customer.getPoints() + earnedPoints);

        // ? Remove books from the store
        selectedBooks.forEach(BooksClass::deleteBook);

        VBox layout = new VBox(10,
            new Label("Total Cost: $" + total),
            new Label("Points Earned: " + earnedPoints),
            new Label("New Total Points: " + customer.getPoints()),
            new Label("Status: " + customer.getStatus())
        );

        // ? Buttons
        Button back = new Button("Back to Store");
        back.setOnAction(e -> page.setPage(new CustomerDashboardScreen(customer)));

        Button logout = new Button("Logout");
        logout.setOnAction(e -> page.setPage(new Login()));

        layout.getChildren().addAll(back, logout);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        page.getPage().setScene(new Scene(layout, 400, 300));
    }
}




