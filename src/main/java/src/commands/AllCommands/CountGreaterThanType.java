package src.commands.AllCommands;

import src.commands.ArgsChecker;
import src.commands.Command;
import src.commands.Invoker;
import src.gui.GUI;
import src.vehicleData.VehicleCollection;
import src.vehicleData.VehicleType;

import static src.vehicleData.VehicleType.getType;

public class CountGreaterThanType implements Command {
    public String getCountOfGreaterThanType(VehicleType vehicleType) {
        return GUI.getAppLanguage().getString("count_res") + ": " + VehicleCollection.getVehicle().stream().filter(vehicle -> VehicleType.getPower(vehicle.getType()) > VehicleType.getPower(vehicleType)).count();
    }
    @Override
    public void execute() {
        ArgsChecker.argsChecker(1);
        if (VehicleCollection.getVehicle().isEmpty()) {
            System.out.println("Коллекция пуста");
        } else {
            try {
                System.out.println(getCountOfGreaterThanType(getType(Invoker.getSplit()[1])));
            } catch (NumberFormatException e) {
                throw new NullPointerException();
            }
        }
    }
    @Override
    public String description() {
        return "count_by_head head : вывести количество элементов, значение поля head которых равно заданному";
    }
}
