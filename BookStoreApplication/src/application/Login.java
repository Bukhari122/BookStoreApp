package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class Login implements PageState {

    @Override
    public void changePage(Page page) {
        Label welcomeLabel = new Label("Welcome to the BookStore App:");
        welcomeLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18;");

        // Username input
        Label usernameLabel = new Label("Username:");
        usernameLabel.setStyle("-fx-text-fill: white;");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");

        HBox userBox = new HBox(10, usernameLabel, usernameField);

        // Password input
        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-text-fill: white;");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        HBox passBox = new HBox(10, passwordLabel, passwordField);

        // Login button
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #333; -fx-text-fill: white;");

        VBox layout = new VBox(15, welcomeLabel, userBox, passBox, loginButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: black;");

        // Login logic
        loginButton.setOnAction(event -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (username.equals("admin") && password.equals("admin")) {
                page.setPage(new OwnerDashboardScreen());
            } else {
                CustomerClass user = CustomerClass.findCustomer(username);
                if (user != null && user.getPassword().equals(password)) {
                    page.setPage(new CustomerDashboardScreen(user));
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Login Failed");
                    alert.setContentText("Invalid username or password.");
                    alert.showAndWait();
                }
            }
        });

        Scene loginScene = new Scene(layout, 400, 300);
        page.getPage().setScene(loginScene);
    }
}





