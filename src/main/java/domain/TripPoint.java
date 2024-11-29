package domain;

import java.awt.geom.Point2D;

public class TripPoint implements Comparable<TripPoint> {
    private int timestamp;
    private Point2D.Double latitudeLongitude;
    private double vehicleSpeed;
    private double maf;
    private double engineRpm;
    private double absoluteLOad;
    private double outsideAirTemperature; //OAT
    private double fuelRate;
    private double airConditioningPowerKw;
    private double airConditioningPowerW;
    private double heaterPower;
    private double hvBatteryCurrent;
    private double hvBatterySoc;
    private double hvBatteryVoltage;
    private double shortTermFuelBank1;
    private double shortTermFuelBank2;
    private double longTermFuelBank1;
    private double longTermFuelBank2;

    public TripPoint(int timestamp, Point2D.Double latitudeLongitude, double vehicleSpeed, double maf, double engineRpm, double absoluteLOad, double outsideAirTemperature, double fuelRate, double airConditioningPowerKw, double airConditioningPowerW, double heaterPower, double hvBatteryCurrent, double hvBatterySoc, double hvBatteryVoltage, double shortTermFuelBank1, double shortTermFuelBank2, double longTermFuelBank1, double longTermFuelBank2) {
        this.timestamp = timestamp;
        this.latitudeLongitude = latitudeLongitude;
        this.vehicleSpeed = vehicleSpeed;
        this.maf = maf;
        this.engineRpm = engineRpm;
        this.absoluteLOad = absoluteLOad;
        this.outsideAirTemperature = outsideAirTemperature;
        this.fuelRate = fuelRate;
        this.airConditioningPowerKw = airConditioningPowerKw;
        this.airConditioningPowerW = airConditioningPowerW;
        this.heaterPower = heaterPower;
        this.hvBatteryCurrent = hvBatteryCurrent;
        this.hvBatterySoc = hvBatterySoc;
        this.hvBatteryVoltage = hvBatteryVoltage;
        this.shortTermFuelBank1 = shortTermFuelBank1;
        this.shortTermFuelBank2 = shortTermFuelBank2;
        this.longTermFuelBank1 = longTermFuelBank1;
        this.longTermFuelBank2 = longTermFuelBank2;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public Point2D.Double getLatitudeLongitude() {
        return latitudeLongitude;
    }

    public void setLatitudeLongitude(Point2D.Double latitudeLongitude) {
        this.latitudeLongitude = latitudeLongitude;
    }

    public double getVehicleSpeed() {
        return vehicleSpeed;
    }

    public void setVehicleSpeed(double vehicleSpeed) {
        this.vehicleSpeed = vehicleSpeed;
    }

    public double getMaf() {
        return maf;
    }

    public void setMaf(double maf) {
        this.maf = maf;
    }

    public double getEngineRpm() {
        return engineRpm;
    }

    public void setEngineRpm(double engineRpm) {
        this.engineRpm = engineRpm;
    }

    public double getAbsoluteLOad() {
        return absoluteLOad;
    }

    public void setAbsoluteLOad(double absoluteLOad) {
        this.absoluteLOad = absoluteLOad;
    }

    public double getOutsideAirTemperature() {
        return outsideAirTemperature;
    }

    public void setOutsideAirTemperature(double outsideAirTemperature) {
        this.outsideAirTemperature = outsideAirTemperature;
    }

    public double getFuelRate() {
        return fuelRate;
    }

    public void setFuelRate(double fuelRate) {
        this.fuelRate = fuelRate;
    }

    public double getAirConditioningPowerKw() {
        return airConditioningPowerKw;
    }

    public void setAirConditioningPowerKw(double airConditioningPowerKw) {
        this.airConditioningPowerKw = airConditioningPowerKw;
    }

    public double getAirConditioningPowerW() {
        return airConditioningPowerW;
    }

    public void setAirConditioningPowerW(double airConditioningPowerW) {
        this.airConditioningPowerW = airConditioningPowerW;
    }

    public double getHeaterPower() {
        return heaterPower;
    }

    public void setHeaterPower(double heaterPower) {
        this.heaterPower = heaterPower;
    }

    public double getHvBatteryCurrent() {
        return hvBatteryCurrent;
    }

    public void setHvBatteryCurrent(double hvBatteryCurrent) {
        this.hvBatteryCurrent = hvBatteryCurrent;
    }

    public double getHvBatterySoc() {
        return hvBatterySoc;
    }

    public void setHvBatterySoc(double hvBatterySoc) {
        this.hvBatterySoc = hvBatterySoc;
    }

    public double getHvBatteryVoltage() {
        return hvBatteryVoltage;
    }

    public void setHvBatteryVoltage(double hvBatteryVoltage) {
        this.hvBatteryVoltage = hvBatteryVoltage;
    }

    public double getShortTermFuelBank1() {
        return shortTermFuelBank1;
    }

    public void setShortTermFuelBank1(double shortTermFuelBank1) {
        this.shortTermFuelBank1 = shortTermFuelBank1;
    }

    public double getShortTermFuelBank2() {
        return shortTermFuelBank2;
    }

    public void setShortTermFuelBank2(double shortTermFuelBank2) {
        this.shortTermFuelBank2 = shortTermFuelBank2;
    }

    public double getLongTermFuelBank1() {
        return longTermFuelBank1;
    }

    public void setLongTermFuelBank1(double longTermFuelBank1) {
        this.longTermFuelBank1 = longTermFuelBank1;
    }

    public double getLongTermFuelBank2() {
        return longTermFuelBank2;
    }

    public void setLongTermFuelBank2(double longTermFuelBank2) {
        this.longTermFuelBank2 = longTermFuelBank2;
    }

    @Override
    public int compareTo(TripPoint otherTripPoint) {
        return timestamp - otherTripPoint.timestamp;
    }
}
