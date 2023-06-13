package src.commands.AllCommands;

import src.commands.ArgsChecker;
import src.commands.Command;
import src.database.DatabaseConnection;
import src.database.UserAuthentication;
import src.vehicleData.VehicleCollection;

public class Clear implements Command {
    @Override
    public void execute() {
        ArgsChecker.argsChecker(0);
        DatabaseConnection.executeStatement("delete from vehicle where creator = '" + UserAuthentication.getCurrentUser() + "'");
        VehicleCollection.updateFromDB();
        System.out.println("Созданная Вами часть коллекции очищена");
    }
    public void GUI_execute() {
        DatabaseConnection.executeStatement("delete from vehicle where creator = '" + UserAuthentication.getCurrentUser() + "'");
        VehicleCollection.updateFromDB();
    }
    @Override
    public String description() {
        return "clear : очистить коллекцию";
    }
}