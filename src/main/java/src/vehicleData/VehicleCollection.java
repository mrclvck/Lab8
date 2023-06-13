package src.vehicleData;

import src.database.DatabaseConnection;
import src.gui.GUI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

public class VehicleCollection {
    private static final Stack<Vehicle> vehicles = new Stack<>();
    private static final Date dateOfInitialization = new Date();

    public static Stack<Vehicle> getVehicle() {
        return vehicles;
    }

    public static String getInfo() {
        return GUI.getAppLanguage().getString("col_type") + ": " + vehicles.getClass().getTypeName().split(".util.")[1] + "\n" +
                GUI.getAppLanguage().getString("init_date") + ": " + new SimpleDateFormat(GUI.getAppLanguage().getString("date_format")).format(dateOfInitialization) + "\n" +
                GUI.getAppLanguage().getString("amount") + ": " + vehicles.size();
    }

    public static void putVehiclesFromDB() {
        try {
            ResultSet resultSet = DatabaseConnection.executePreparedStatement("select * from vehicle");
            while (resultSet.next()) {
                try {
                    vehicles.add(new Vehicle(
                                    resultSet.getLong(1),
                                    resultSet.getString(4),
                                    new Coordinates(resultSet.getDouble(9), resultSet.getLong(10)),
                                    new Date(resultSet.getLong(3)),
                                    resultSet.getDouble(5),
                                    resultSet.getLong(7),
                                    resultSet.getLong(8),
                                    VehicleType.getType(resultSet.getString(6)),
                                    resultSet.getString(2)));
                } catch (Exception ignored) {}
            }
            resultSet.close();
        } catch (SQLException | IllegalArgumentException ignored) {}
    }

    public static void updateFromDB() {
        vehicles.clear();
        putVehiclesFromDB();
    }
}
