package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.List;

public class CustomerBuyPointsScreen implements PageState {
    private CustomerClass customer;
    private List<Book> selectedBooks;

    public CustomerBuyPointsScreen(CustomerClass customer, List<Book> selectedBooks) {
        this.customer = customer;
        this.selectedBooks = selectedBooks;
    }

    @Override
    public void changePage(Page page) {
        double total = selectedBooks.stream().mapToDouble(Book::getPrice).sum();
        double discount = customer.getPoints() / 100.0;
        double finalCost = Math.max(0, total - discount);

        int usedPoints = (int) (discount * 100);
        int earnedPoints = (int) (finalCost * 10);

        customer.setPoints(customer.getPoints() - usedPoints + earnedPoints);

        // ? Remove books from the store
        selectedBooks.forEach(BooksClass::deleteBook);

        VBox layout = new VBox(10,
            new Label("Original Cost: $" + total),
            new Label("Discount Used: $" + discount),
            new Label("Final Cost: $" + finalCost),
            new Label("Remaining Points: " + customer.getPoints()),
            new Label("Status: " + customer.getStatus())
        );

        // ? Buttons
        Button back = new Button("Back to Store");
        back.setOnAction(e -> page.setPage(new CustomerDashboardScreen(customer)));

        Button logout = new Button("Logout");
        logout.setOnAction(e -> page.setPage(new Login()));

        layout.getChildren().addAll(back, logout);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        page.getPage().setScene(new Scene(layout, 400, 350));
    }
}




