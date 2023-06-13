package src.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

public class Utils {
    public static void showInfo(String title, String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(info);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #92A1D6");
        Button ok = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        ok.setStyle("-fx-background-color: #7E7ED0; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #2D2C50; -fx-cursor: HAND;");
        alert.showAndWait();
    }
}
