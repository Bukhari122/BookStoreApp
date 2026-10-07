package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.FXCollections;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class OwnerBookScreen implements PageState {

    @Override
    public void changePage(Page page) {
        TableView<Book> table = new TableView<>();
        table.setItems(FXCollections.observableArrayList(BooksClass.getBooks()));

        // Columns
        TableColumn<Book, String> nameCol = new TableColumn<>("Book Name");
        nameCol.setCellValueFactory(b -> new SimpleStringProperty(b.getValue().getName()));

        TableColumn<Book, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(b -> new SimpleDoubleProperty(b.getValue().getPrice()).asObject());

        table.getColumns().addAll(nameCol, priceCol);

        // Input fields
        TextField nameInput = new TextField();
        nameInput.setPromptText("Book name");

        TextField priceInput = new TextField();
        priceInput.setPromptText("Price");

        // Buttons
        Button addBtn = new Button("Add");
        Button deleteBtn = new Button("Delete");
        Button backBtn = new Button("Back");

        addBtn.setOnAction(e -> {
            String name = nameInput.getText();
            String priceText = priceInput.getText();

            if (!name.isEmpty() && !priceText.isEmpty()) {
                try {
                    double price = Double.parseDouble(priceText);
                    BooksClass.addBook(new Book(name, price));
                    page.setPage(new OwnerBookScreen()); // Refresh screen
                } catch (NumberFormatException ex) {
                    new Alert(Alert.AlertType.ERROR, "Enter a valid price.").showAndWait();
                }
            }
        });

        deleteBtn.setOnAction(e -> {
            Book selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                BooksClass.deleteBook(selected);
                page.setPage(new OwnerBookScreen()); // Refresh screen
            }
        });

        backBtn.setOnAction(e -> page.setPage(new OwnerDashboardScreen()));

        VBox layout = new VBox(10,
            new Label("Manage Books"),
            table,
            new HBox(10, nameInput, priceInput),
            new HBox(10, addBtn, deleteBtn, backBtn)
        );
        layout.setStyle("-fx-padding: 20;");

        page.getPage().setScene(new Scene(layout, 600, 400));
    }
}


