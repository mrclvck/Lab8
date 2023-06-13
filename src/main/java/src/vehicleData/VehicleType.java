package src.vehicleData;

public enum VehicleType {
    PLANE("PLANE","1",1),
    HELICOPTER("HELICOPTER","2",10),
    SUBMARINE("SUBMARINE","3",100);
    private final String name;
    private final String number;
    private final int power;
    VehicleType(String name, String number, int power) {
        this.name = name;
        this.number = number;
        this.power = power;
    }

    public static VehicleType getType(String string) {
        for (VehicleType vehicleType : VehicleType.values()) {
            if (vehicleType.number.equals(string)|vehicleType.name.equals(string.toUpperCase())) return vehicleType;
        }
        return null;
    }

    public static int getPower(VehicleType vehicleType) {
        if (vehicleType == null) {
            return 0;
        } else {
            return vehicleType.power;
        }
    }
}
