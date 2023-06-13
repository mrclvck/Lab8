package src.vehicleData;

import src.commands.Invoker;
import src.database.DatabaseConnection;
import src.exeptions.IllegalValueOfYException;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Adder {
    public static Vehicle vehicleAdder() {
            Scanner sc = new Scanner(System.in);
            String name = "k";
            double x = 0;
            long y = 0;
            Double enginePower = null;
            long capacity = Long.parseLong("0");
            long fuelConsumption = Long.parseLong("0");
            VehicleType type = VehicleType.PLANE;
            int i = 1;
            while (i != 0) {
                try {
                    while (i == 1) {
                        System.out.println("Введите название");
                        name = sc.nextLine();
                        if (name.trim().isEmpty()) {
                            throw new InputMismatchException();
                        }
                        ++i;
                    }
                    while (i == 2) {
                        System.out.println("Введите координату X");
                        String s = sc.nextLine();
                        x = Double.parseDouble(s);
                        ++i;

                    }
                    while (i == 3) {
                        try {
                            System.out.println("Введите координату Y");
                            String s = sc.nextLine();
                            y = Long.parseLong(s);
                            if (y > 746) {
                                throw new IllegalValueOfYException();
                            }
                            ++i;
                        } catch (IllegalValueOfYException illegalValueOfXException) {
                            System.out.println(illegalValueOfXException.getMessage());
                        }
                    }
                    while (i == 4) {
                        System.out.println("Введите мощность двигателя");
                        String s = sc.nextLine();
                        enginePower = Double.parseDouble(s);
                        ++i;
                    }
                    while (i == 5) {
                        System.out.println("Введите вместимость");
                        String s = sc.nextLine();
                        capacity = Long.parseLong(s);
                        ++i;
                    }
                    while (i == 6) {
                        System.out.println("Введите расход топлива");
                        String s = sc.nextLine();
                        fuelConsumption = Long.parseLong(s);
                        ++i;
                    }
                    while (i == 7) {
                        System.out.println("Введите тип транспорта (Цифру или название) 1 - PLANE, 2 - HELICOPTER, 3 - SUBMARINE");
                        String vehicleType = sc.nextLine();
                        if (!(vehicleType.matches("[1-3]")||vehicleType.equals("PLANE")||vehicleType.equals("HELICOPTER")||vehicleType.equals("SUBMARINE"))) {
                            throw new InputMismatchException();
                        }
                        switch (vehicleType) {
                            case "1", "PLANE" -> type = VehicleType.PLANE;
                            case "2", "WISE" -> type = VehicleType.HELICOPTER;
                            case "3", "CHAOTIC_EVIL" -> type = VehicleType.SUBMARINE;
                        }
                        ++i;
                    }

                    i = 0;
                } catch (InputMismatchException | NumberFormatException inputMismatchException) {
                    System.out.println("Неверный тип данных");
                }
            }
            return new Vehicle(name, new Coordinates(x, y), enginePower, capacity, fuelConsumption, type);
        }

    public static Vehicle fromFileAdder(Scanner scanner) {
            if (Invoker.getSplit().length != 1) throw new InputMismatchException();
            String[] fields = fieldsReader(scanner);
            return new Vehicle(nameFromFileChecker(fields[0]), new Coordinates(XCoordinateFromFileChecker(fields[1]), YCoordinateFromFileChecker(fields[2])), enginePowerFromFileChecker(fields[3]), capacityFromFileChecker(fields[4]), fuelConsumptionFromFileChecker(fields[5]), typeFromFileChecker(fields[6]));
        }
        private static String[] fieldsReader(Scanner sc) {
            String[] fields = new String[8];
            for (int i = 0; i < fields.length; ++i) {
                try {
                    fields[i] = sc.nextLine();
                    if (fields[i].trim().isEmpty()) fields[i] = null;
                } catch (NoSuchElementException noSuchElementException) {
                    fields[i] = null;
                }
            }
            return fields;
        }
        private static String nameFromFileChecker(String name) {
            if (name.trim().isEmpty()) {
                throw new InputMismatchException();
            }
            return name;
        }
        private static double XCoordinateFromFileChecker(String xString) {
            try {
                Double.parseDouble(xString);
            } catch (NumberFormatException numberFormatException) {
                throw new InputMismatchException();
            }
            return Double.parseDouble(xString);
        }
        private static long YCoordinateFromFileChecker(String yString) {
            try {
                Long.parseLong(yString);
            } catch (NumberFormatException numberFormatException) {
                throw new InputMismatchException();
            }
            long y = Long.parseLong(yString);
            if (y > 746) {
                throw new InputMismatchException();
            }
            return y;
        }
        private static Double enginePowerFromFileChecker (String enginePowerString) {
            try {
                Long.parseLong(enginePowerString);
            } catch (NumberFormatException numberFormatException) {
                throw new InputMismatchException();
            }
            return Double.parseDouble(enginePowerString);
        }
        private static long capacityFromFileChecker(String capacityString) {
            try {
                Long.parseLong(capacityString);
            } catch (NumberFormatException numberFormatException) {
                throw new InputMismatchException();
            }
            return Long.parseLong(capacityString);
        }
        private static long fuelConsumptionFromFileChecker(String fuelConsumptionString) {
            try {
                Long.parseLong(fuelConsumptionString);
            } catch (NumberFormatException numberFormatException) {
                throw new InputMismatchException();
            }
            return Long.parseLong(fuelConsumptionString);
        }
        private static VehicleType typeFromFileChecker(String vehicleType) {
            if (!(vehicleType.matches("[1-3]")||vehicleType.equals("PLANE")||vehicleType.equals("HELICOPTER")||vehicleType.equals("SUBMARINE"))) {
                throw new InputMismatchException();
            }
            VehicleType type = null;
            switch (vehicleType) {
                case "1", "PLANE" -> type = VehicleType.PLANE;
                case "2", "HELICOPTER" -> type = VehicleType.HELICOPTER;
                case "3", "SUBMARINE" -> type = VehicleType.SUBMARINE;
            }
            return type;
        }

    public static void vehicleAdderToDB(Vehicle vehicle) {
        DatabaseConnection.executeStatement("insert into vehicle (id, creator, creationDate, name, enginePower, type, capacity, fuelConsumption, x, y) values ('" + vehicle.getId() + "', '" + vehicle.getCreator() + "', '" + vehicle.getCreationTime() + "', '" + vehicle.getName() + "', '" + vehicle.getEnginePower() + "', '" + vehicle.getType() + "', '" + vehicle.getCapacity() + "', '" + vehicle.getFuelConsumption() + "', '"  + vehicle.getCoordinates().getX() + "', '" + vehicle.getCoordinates().getY() + "')");
        VehicleCollection.updateFromDB();
    }

}
