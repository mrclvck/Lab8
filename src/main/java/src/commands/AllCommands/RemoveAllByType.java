package src.commands.AllCommands;

import src.commands.ArgsChecker;
import src.commands.Command;
import src.commands.Invoker;
import src.database.DatabaseConnection;
import src.database.UserAuthentication;
import src.gui.GUI;
import src.vehicleData.Vehicle;
import src.vehicleData.VehicleCollection;
import src.vehicleData.VehicleType;

import java.util.List;

public class RemoveAllByType implements Command {
    private void removerByType(Vehicle thisVehicle) {
        List<Vehicle> list = VehicleCollection.getVehicle().stream().filter(vehicle -> vehicle.getType().equals(VehicleType.valueOf(Invoker.getSplit()[1].trim()))).toList();
        if (list.isEmpty()) {
            System.out.println("Объектов такого типа не существует");
        } else {
            int beforeSize = VehicleCollection.getVehicle().size();
            list.forEach(vehicle -> DatabaseConnection.executeStatement("delete from vehicle where type = " + vehicle.getType() + " and creator = '" + UserAuthentication.getCurrentUser() + "'"));
            VehicleCollection.updateFromDB();
            System.out.println("Количество удалённых объектов: " + (beforeSize - VehicleCollection.getVehicle().size()) + "\n P.S. (Вы можете удалять только те объекты, создателем которых являетесь)");
        }
    }
    @Override
    public void execute() {
        ArgsChecker.argsChecker(1);
        try {
            List<Vehicle> matchedVehicle = VehicleCollection.getVehicle().stream().filter(vehicle -> vehicle.getType().equals(VehicleType.valueOf(Invoker.getSplit()[1].trim()))).toList();
            if (matchedVehicle.isEmpty()) System.out.println("Заданного объекта не существует");
            else removerByType(matchedVehicle.get(0));
        } catch (NumberFormatException ex) {
            throw new NullPointerException();
        }
    }
    public String GUI_execute(VehicleType vehicleType) {
        int beforeSize = VehicleCollection.getVehicle().size();
        DatabaseConnection.executeStatement("delete from vehicle where type = '" + vehicleType + "' and creator = '" + UserAuthentication.getCurrentUser() + "'");
        VehicleCollection.updateFromDB();
        return GUI.getAppLanguage().getString("deleted") + ": " + (beforeSize - VehicleCollection.getVehicle().size()) + "\n (" + GUI.getAppLanguage().getString("delete_ps") + ")";
    }
    @Override
    public String description() {
        return "remove_all_by_type type : удалить из коллекции все элементы, значение поля type которого эквивалентно заданному";
    }
}