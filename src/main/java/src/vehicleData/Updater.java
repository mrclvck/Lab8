package src.vehicleData;

import src.database.DatabaseConnection;
import src.database.UserAuthentication;
import src.exeptions.IllegalValueOfYException;

import java.util.List;
import java.util.Scanner;

public class Updater {
    public static void updateVehicle(long id) {
        List<Vehicle> matchedVehicle = VehicleCollection.getVehicle().stream().filter(vehicle -> vehicle.getId() == id).toList();
        if (matchedVehicle.isEmpty()) {
            System.out.println("Такого объекта не существует");
        } else {
            if (matchedVehicle.get(0).getCreator().equals(UserAuthentication.getCurrentUser())) {
                Scanner scanner = new Scanner(System.in);
                String s = requestInput(scanner);
                if (!(s.matches("[1-6]"))) {
                    System.out.println("Неверный параметр");
                } else {
                    fieldsUpdater(s, scanner, matchedVehicle.get(0));
                }
            } else {
                System.out.println("Вы не можете изменить объект созданный другим пользователем");
            }
        }
    }

    private static String requestInput(Scanner scanner) {
        boolean i = true;
        String s = "";
        while (i) {
            System.out.println("""
                    Выберите параметр транспорта, который хотите изменить:
                    Название - введите  1
                    Мощность двигателя - введите 2
                    Вместимость - введите 3
                    Расход топлива - введите 4
                    Вид - введите 5
                    Координаты - введите 6""");
            s = scanner.nextLine().trim();
            i = false;
        }
        return s;
    }

    private static void fieldsUpdater(String s, Scanner scanner, Vehicle vehicle) {
        switch (s) {
            case "1" -> updateName(scanner, vehicle);
            case "2" -> updateEnginePower(scanner, vehicle);
            case "3" -> updateCapacity(scanner, vehicle);
            case "4" -> updateFuelConsumption(scanner, vehicle);
            case "5" -> updateType(scanner, vehicle);
            case "6" -> updateCoordinates(scanner, vehicle);
        }
        VehicleCollection.updateFromDB();
        System.out.println("Параметр транспорта успешно обновлён");
    }

    private static void updateName(Scanner scanner, Vehicle vehicle) {
        boolean i = true;
        while (i) {
            System.out.println("Введите название");
            String name = scanner.nextLine().trim();
            if (!(name.trim().isEmpty() | name.contains("'"))) {
                DatabaseConnection.executeStatement("update vehicle set name = '" + name + "' where id = " + vehicle.getId());
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
    }

    private static void updateEnginePower(Scanner scanner, Vehicle vehicle) {
        boolean i = true;
        while (i) {
            System.out.println("Введите новое значение мощности двигателя (должен быть больше 0)");
            try {
                double enginePower = Double.parseDouble(scanner.nextLine().trim());
                if (enginePower <= 0) throw new NumberFormatException();
                DatabaseConnection.executeStatement("update vehicle set enginepower = '" + enginePower + "' where id = " + vehicle.getId());
                i = false;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Неверный тип данных");
            }
        }
    }

    private static void updateType(Scanner scanner, Vehicle vehicle) {
        boolean i = true;
        while (i) {
            System.out.println("Введите новый вид техники (Цифру или полное название) 1 - PLANE, 2 - HELICOPTER, 3 - SUBMARINE");
            String type = scanner.nextLine().trim();
            if (type.matches("[1-3]") || type.equals("PLANE") || type.equals("HELICOPTER") || type.equals("SUBMARINE")) {
                switch (type) {
                    case "1" -> type = "PLANE";
                    case "2" -> type = "HELICOPTER";
                    case "3" -> type = "SUBMARINE";
                }
                DatabaseConnection.executeStatement("update vehicle set type = '" + type + "' where id = " + vehicle.getId());
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
    }

    private static void updateCapacity(Scanner scanner, Vehicle vehicle) {
        boolean i = true;
        while (i) {
            System.out.println("Введите новое значение вместимости (должен быть больше 0)");
            try {
                long capacity = Long.parseLong(scanner.nextLine().trim());
                if (capacity <= 0) throw new NumberFormatException();
                DatabaseConnection.executeStatement("update vehicle set capacity = '" + capacity + "' where id = " + vehicle.getId());
                i = false;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Неверный тип данных");
            }
        }
    }

    private static void updateFuelConsumption(Scanner scanner, Vehicle vehicle) {
        boolean i = true;
        while (i) {
            System.out.println("ведите новое значение расхода топлива (должен быть больше 0)");
            try {
                long fuelConsumption = Long.parseLong(scanner.nextLine().trim());
                if (fuelConsumption <= 0) throw new NumberFormatException();
                DatabaseConnection.executeStatement("update vehicle set fuelconsumption = '" + fuelConsumption + "' where id = " + vehicle.getId());
                i = false;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Неверный тип данных");
            }
        }
    }

    private static void updateCoordinates(Scanner scanner, Vehicle vehicle) {
        DatabaseConnection.executeStatement("update vehicle set x = '" + getNewXCoordinate(scanner) + "' where id = " + vehicle.getId());
        DatabaseConnection.executeStatement("update vehicle set y = '" + getNewYCoordinate(scanner) + "' where id = " + vehicle.getId());
    }

    private static double getNewXCoordinate(Scanner scanner) {
        double x = 0;
        boolean i = true;
        while (i) {
            System.out.println("Введите новую координату Х");
            String xString = scanner.nextLine();
            if (!xString.trim().isEmpty()) {
                x = Double.parseDouble(xString);
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
        return x;
    }

    private static long getNewYCoordinate(Scanner scanner) {
        long y = 0;
        boolean i = true;
        while (i) {
            System.out.println("Введите новую координату Y");
            String yString = scanner.nextLine();
            try {
                if (!yString.trim().isEmpty()) {
                    y = Long.parseLong(yString);
                    if (y > 746) {
                        throw new IllegalValueOfYException();
                    } else {
                        i = false;
                    }
                } else {
                    System.out.println("Неверный тип данных");
                }
            } catch (IllegalValueOfYException illegalValueOfYException) {
                System.out.println(illegalValueOfYException.getMessage());
            }
        }
        return y;
    }

/*

    public static void updaterFromFile(Scanner scanner) {
        String[] parameters = parametersReader(scanner);
        try {
            if (Invoker.getSplit().length != 2) throw new InputMismatchException();
            List<Vehicle> matchedVehicle = VehicleCollection.getVehicle().stream().filter(vehicle -> vehicle.getId() == Long.parseLong(Invoker.getSplit()[1])).toList();
            if (matchedVehicle.isEmpty()) {
                throw new InputMismatchException();
            } else {
                try {
                    ResultSet resultSet = DatabaseConnection.executePreparedStatement("select * from vehicle where id = " + matchedVehicle.get(0).getId() + " and creator = '" + UserAuthentication.getCurrentUser() + "'");
                    resultSet.next();
                    resultSet.getLong(1);
                    fieldsUpdaterFromFile(parameters[0], parameters[1], matchedVehicle.get(0), scanner);
                } catch (SQLException sqlException) {
                    throw new InputMismatchException();
                }
            }
        } catch (InputMismatchException | NumberFormatException ignored) {
        }
    }

    private static String[] parametersReader(Scanner scanner) {
        String[] parameters = new String[2];
        for (int i = 0; i < parameters.length; ++i) {
            try {
                parameters[i] = scanner.nextLine();
                if (parameters[i].trim().isEmpty()) parameters[i] = null;
            } catch (NoSuchElementException noSuchElementException) {
                parameters[i] = null;
            }
        }
        return parameters;
    }

    private static void fieldsUpdaterFromFile(String parameter, String newValue, Vehicle vehicle, Scanner scanner) {
        if (parameter.matches(("[1-7]"))) {
            switch (parameter) {
                case "1" -> updateNameFromFile(newValue, vehicle);
                case "2" -> updateEnginePowerFromFile(newValue, vehicle);
                case "3" -> updateTypeFromFile(newValue, vehicle);
                case "4" -> updateCapacityFromFile(newValue, vehicle);
                case "5" -> updateFuelConsumptionFromFile(newValue, vehicle);
                case "6" -> updateCoordinatesFromFile(newValue, vehicle);
                case "7" -> updateCoordinatesFromFile(newValue, scanner, vehicle);
            }
            VehicleCollection.updateFromDB();
            System.out.println("Параметр успешно обновлён");
        } else {
            throw new InputMismatchException();
        }
    }

    private static void updateNameFromFile(String name, Vehicle vehicle) {
        if (!(name.trim().isEmpty() | name.contains("'"))) {
            DatabaseConnection.executeStatement("update vehicle set name = '" + name + "' where id = " + vehicle.getId());
        } else {
            throw new InputMismatchException();
        }
    }

    private static void updateEnginePowerFromFile(String ageString, Vehicle vehicle) {
        try {
            Double enginePower = Double.parseDouble(ageString);
            if (enginePower <= 0) throw new InputMismatchException();
            DatabaseConnection.executeStatement("update vehicle set age = '" + enginePower + "' where id = " + vehicle.getId());
        } catch (NumberFormatException numberFormatException) {
            throw new InputMismatchException();
        }
    }

    private static void updateTypeFromFile(String type, Vehicle vehicle) {
        if (type.matches("[1-3]") || type.equals("PLANE") || type.equals("HELICOPTER") || type.equals("SUBMARINE")) {
            switch (type) {
                case "1" -> type = "PLANE";
                case "2" -> type = "HELICOPTER";
                case "3" -> type = "SUBMARINE";
            }
            DatabaseConnection.executeStatement("update vehicle set type = '" + type + "' where id = " + vehicle.getId());
        } else {
            throw new InputMismatchException();
        }
    }

*/

}

