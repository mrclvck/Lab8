package src.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.localization.Language;

import java.io.IOException;
import java.util.ResourceBundle;

public class GUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        openAuthWindow(stage);
    }
    public static void open() {
        launch();
    }
    public static void openAuthWindow(Stage stage) throws IOException {
        stage.setTitle("Vehicles collection manager");
        Scene scene = new Scene(new FXMLLoader(GUI.class.getResource("/fxml/auth.fxml")).load(), 1080, 768);
        scene.getStylesheets().add("/css/style.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.show();
    }
    private static ResourceBundle appLanguage = Language.ru;

    public static ResourceBundle getAppLanguage() {
        return appLanguage;
    }

    public static void setAppLanguage(ResourceBundle appLanguage) {
        GUI.appLanguage = appLanguage;
    }

    public static void openMainWindow() {
        try {
            Stage stage = new Stage();
            stage.setTitle("Vehicles collection manager");
            Scene scene = new Scene(new FXMLLoader(GUI.class.getResource("/fxml/main-page.fxml")).load(), 1080, 768);
            scene.getStylesheets().add("/css/style.css");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setMaximized(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void openMapWindow() {
        try {
            Stage stage = new Stage();
            stage.setTitle("Vehicles collection manager");
            Scene scene = new Scene(new FXMLLoader(GUI.class.getResource("/fxml/map.fxml")).load(), 1080, 768);
            scene.getStylesheets().add("/css/style.css");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setMaximized(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}