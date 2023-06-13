package src.commands.AllCommands;

import src.commands.Command;
import src.commands.Invoker;
import src.exeptions.InvalidCommandException;
import src.vehicleData.Updater;
import src.vehicleData.VehicleCollection;

import java.util.Scanner;

public class Update implements Command {

    @Override
    public void execute() {
        try {
            if (Invoker.getSplit().length != 2) {
                throw new InvalidCommandException();
            }
            try {
                Long.parseLong(Invoker.getSplit()[1]);
            } catch (NumberFormatException ex) {
                throw new InvalidCommandException();
            }
            long id = Long.parseLong(Invoker.getSplit()[1]);
            if (!VehicleCollection.getVehicle().isEmpty()) {
                Updater.updateVehicle(id);
            } else {
                System.out.println("Такого элемента не существует");
            }
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }

    protected static void updaterFromFile(Scanner scanner) {
        // Updater.updaterFromFile(scanner);
    }
    @Override
    public String description() {
        return "update id : обновить значение элемента коллекции, id которого равен заданному";
    }
}

