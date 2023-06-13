
package src.commands.AllCommands;

import src.commands.ArgsChecker;
import src.commands.Command;
import src.commands.Invoker;
import src.database.DatabaseConnection;
import src.database.UserAuthentication;
import src.gui.GUI;
import src.vehicleData.Vehicle;
import src.vehicleData.VehicleCollection;

import java.util.List;

public class RemoveById implements Command {
    private void removerById(long id) {
        List<Vehicle> matchedVehicle = VehicleCollection.getVehicle().stream().filter((vehicle -> vehicle.getId() == id)).toList();
        if (matchedVehicle.isEmpty()) {
            System.out.println("Такого объекта не существует");
        } else {
            int beforeSize = VehicleCollection.getVehicle().size();
            DatabaseConnection.executeStatement("delete from vehicle where id = " + matchedVehicle.get(0).getId() + " and creator = '" + UserAuthentication.getCurrentUser() + "'");
            VehicleCollection.updateFromDB();
            if (beforeSize == VehicleCollection.getVehicle().size()) {
                System.out.println("Вы не можете удалить этот объект, так как он создан другим пользователем");
            } else {
                System.out.println("Объект успешно удалён");
            }
        }
    }
    @Override
    public void execute() {
        ArgsChecker.argsChecker(1);
        try {
            removerById(Long.parseLong(Invoker.getSplit()[1]));
        } catch (NumberFormatException ex) {
            throw new NullPointerException();
        }
    }
    public String GUI_execute(String id) {
        try {
            Long.parseLong(id);
        } catch (NumberFormatException numberFormatException) {
            return GUI.getAppLanguage().getString("invalid");
        }
        List<Vehicle> vehicleCollection = VehicleCollection.getVehicle().stream().filter(vehicle1 -> vehicle1.getId() == Long.parseLong(id)).toList();
        if (vehicleCollection.isEmpty()) return GUI.getAppLanguage().getString("doesnt_exist");
        if (!vehicleCollection.get(0).getCreator().equals(UserAuthentication.getCurrentUser())) return GUI.getAppLanguage().getString("not_ur");
        DatabaseConnection.executeStatement("delete from vehicle where id = '" + Long.parseLong(id) + "' and creator = '" + UserAuthentication.getCurrentUser() + "'");
        VehicleCollection.updateFromDB();
        return null;
    }
    @Override
    public String description() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
}