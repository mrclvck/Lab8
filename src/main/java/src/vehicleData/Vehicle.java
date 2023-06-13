package src.vehicleData;

import src.database.DatabaseConnection;
import src.database.UserAuthentication;
import src.gui.GUI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Vehicle implements Comparable<Vehicle> {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double enginePower; //Поле может быть null, Значение поля должно быть больше 0
    private long capacity; //Значение поля должно быть больше 0
    private long fuelConsumption; //Значение поля должно быть больше 0
    private VehicleType type; //Поле может быть null
    private final String creator;

    public Vehicle (String name, Coordinates coordinates, Double enginePower, long capacity, long fuelConsumption, VehicleType type){
        setId();
        creationDate = new Date();
        this.name = name;
        this.coordinates = coordinates;
        this.enginePower = enginePower;
        this.capacity = capacity;
        this.fuelConsumption = fuelConsumption;
        this.type = type;
        creator = UserAuthentication.getCurrentUser();
    }

    public Vehicle(long id, String name, Coordinates coordinates, Date creationDate, Double enginePower, long capacity, long fuelConsumption, VehicleType type, String creator) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.enginePower = enginePower;
        this.capacity = capacity;
        this.fuelConsumption = fuelConsumption;
        this.type = type;
        this.creator = creator;
    }
    private void setId() {
        long newId = 0;
        try {
            ResultSet resultSet = DatabaseConnection.executePreparedStatement("SELECT nextval(?)", "id");
            resultSet.next();
            newId = resultSet.getLong(1);
            resultSet.close();
        } catch (SQLException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        id = newId;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Coordinates getCoordinates(){
        return coordinates;
    }

    public Double getEnginePower(){
        return enginePower;
    }

    public long getCapacity(){
        return capacity;
    }

    public long getFuelConsumption(){
        return fuelConsumption;
    }

    public VehicleType getType() {return type;}

    public long getCreationTime() {
        return creationDate.getTime();
    }

    public String getCreationDate() {
        return new SimpleDateFormat(GUI.getAppLanguage().getString("date_format")).format(creationDate);
    }

    public String getCreator() {
        return creator;
    }
    @Override
    public String toString() {
        return GUI.getAppLanguage().getString("vehicle") + " " + id + "\n" +
                GUI.getAppLanguage().getString("name") + ": " + name + "\n" +
                GUI.getAppLanguage().getString("coords") + ": (" + (coordinates.getX() + "").replace(".", GUI.getAppLanguage().getString("separator")) + "; " + coordinates.getY() + ")\n" +
                GUI.getAppLanguage().getString("creation_date") + ": " + getCreationDate() + "\n" +
                GUI.getAppLanguage().getString("ep") + ": " + (enginePower + "").replace(".", GUI.getAppLanguage().getString("separator")) + "\n" +
                GUI.getAppLanguage().getString("capacity") + ": " + capacity + "\n" +
                GUI.getAppLanguage().getString("fc") + ": " + fuelConsumption + "\n" +
                GUI.getAppLanguage().getString("type") + ": " + type + "\n" +
                GUI.getAppLanguage().getString("creator") + ": " + creator + "\n";
    }
    public int compareTo(Vehicle vehicle) {
        return (int)Math.signum(this.enginePower - vehicle.getEnginePower());
    }

}
