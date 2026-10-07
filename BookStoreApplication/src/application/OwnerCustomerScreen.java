package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.FXCollections;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class OwnerCustomerScreen implements PageState {

    @Override
    public void changePage(Page page) {
        // Table and Columns
        TableView<CustomerClass> table = new TableView<>();

        TableColumn<CustomerClass, String> userColumn = new TableColumn<>("Username");
        userColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getUsername()));

        TableColumn<CustomerClass, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPassword()));

        TableColumn<CustomerClass, Integer> pointColumn = new TableColumn<>("Points");
        pointColumn.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getPoints()).asObject());

        table.getColumns().addAll(userColumn, passwordColumn, pointColumn);
        table.setItems(FXCollections.observableArrayList(CustomerClass.getCustomers()));

        // Inputs
        TextField usernameInput = new TextField();
        usernameInput.setPromptText("Username");

        TextField passwordInput = new TextField();
        passwordInput.setPromptText("Password");

        TextField pointsInput = new TextField();
        pointsInput.setPromptText("Points (edit)");

        // Buttons
        Button add = new Button("Add");
        Button delete = new Button("Delete");
        Button edit = new Button("Edit");
        Button back = new Button("Back");

        // Events
        add.setOnAction(e -> {
            String user = usernameInput.getText();
            String pass = passwordInput.getText();
            if (!user.isEmpty() && !pass.isEmpty()) {
                CustomerClass.addCustomer(new CustomerClass(user, pass));
                page.setPage(new OwnerCustomerScreen());
            }
        });

        delete.setOnAction(e -> {
            CustomerClass selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                CustomerClass.deleteCustomer(selected);
                page.setPage(new OwnerCustomerScreen());
            }
        });

        edit.setOnAction(e -> {
            CustomerClass selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                if (!passwordInput.getText().isEmpty()) {
                    selected.setPassword(passwordInput.getText());
                }
                if (!pointsInput.getText().isEmpty()) {
                    try {
                        int pts = Integer.parseInt(pointsInput.getText());
                        selected.setPoints(pts);
                    } catch (NumberFormatException ignored) {}
                }
                page.setPage(new OwnerCustomerScreen());
            }
        });

       back.setOnAction(e -> page.setPage(new OwnerDashboardScreen()));

        // Layout
        HBox inputRow = new HBox(10, usernameInput, passwordInput, pointsInput);
        HBox buttonRow = new HBox(10, add, edit, delete, back);
        VBox layout = new VBox(10, new Label("Manage Customers"), table, inputRow, buttonRow);

        page.getPage().setScene(new Scene(layout, 700, 400));
    }
}

