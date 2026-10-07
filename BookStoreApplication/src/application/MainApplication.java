package application;

import javafx.application.Application;
import javafx.stage.Stage;

// @author Angelo Huang

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage){
        Page page = new Page(primaryStage);
        page.start();
    
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}