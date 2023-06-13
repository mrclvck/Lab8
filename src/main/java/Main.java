import src.database.DatabaseConnection;
import src.gui.GUI;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.createTablesIfNotExist();
        GUI.open();
    }
}