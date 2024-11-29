package domain;

import trees.AVL;

public class Vehicle implements Comparable<Vehicle>, Identifiable {
    private int id;
    private String vehicleType;
    private String vehicleClass;
    private String engineConfig;
    private String transmission;
    private String driveWheels;
    private int weight;
    private AVL<Trip> vehicleTrips;

    public Vehicle(int id, String vehicleType, String vehicleClass, String engineConfig, String transmission, String driveWheels, int weight) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.vehicleClass = vehicleClass;
        this.engineConfig = engineConfig;
        this.transmission = transmission;
        this.driveWheels = driveWheels;
        this.weight = weight;
        this.vehicleTrips = new AVL<>(null);
    }
    @Override
    public int getId() {return id;}
    @Override
    public void setId(int id) {this.id = id;}
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public String getEngineConfig() {
        return engineConfig;
    }

    public void setEngineConfig(String engineConfig) {
        this.engineConfig = engineConfig;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getDriveWheels() {
        return driveWheels;
    }

    public void setDriveWheels(String driveWheels) {
        this.driveWheels = driveWheels;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public AVL<Trip> getVehicleTrips() {
        return vehicleTrips;
    }

    public void setVehicleTrips(AVL<Trip> vehicleTrips) {
        this.vehicleTrips = vehicleTrips;
    }

    @Override
    public int compareTo(Vehicle otherVehicle) {
        return id - otherVehicle.id;
    }
}
