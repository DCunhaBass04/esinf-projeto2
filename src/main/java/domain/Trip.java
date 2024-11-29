package domain;

import trees.AVL;
import utils.Utils;

import java.util.Comparator;
import java.util.List;

public class Trip implements Comparable<Trip>, Identifiable {

    private double dayNum; // Number of days passed since a reference date
    private int vehicleId;
    private int id;
    private AVL<TripPoint> tripPoints;
    public Trip(double dayNum, int vehicleId, int id, AVL<TripPoint> tripPoints) {
        this.id = id;
        this.dayNum = dayNum;
        this.vehicleId = vehicleId;
        this.tripPoints = tripPoints;
    }

    @Override
    public int getId() {return id;}
    @Override
    public void setId(int id) {this.id = id;}

    public double getDayNum() {
        return dayNum;
    }

    public void setDayNum(double dayNum) {
        this.dayNum = dayNum;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public AVL<TripPoint> getTripPoints() {
        return tripPoints;
    }

    public void setTripPoints(AVL<TripPoint> tripPoints) {
        this.tripPoints = tripPoints;
    }

    // TODO: Rever com professor!
    public double getMaxVehicleSpeed() {
        List<TripPoint> tripPointList = (List<TripPoint>) tripPoints.inOrder();
        tripPointList.sort(Comparator.comparingDouble(TripPoint::getVehicleSpeed));
        return tripPointList.getLast().getVehicleSpeed();
    }

    public double getMaxAbsoluteLoad() {
        List<TripPoint> tripPointList = (List<TripPoint>) tripPoints.inOrder();
        tripPointList.sort(Comparator.comparingDouble(TripPoint::getAbsoluteLOad));
        return tripPointList.getLast().getAbsoluteLOad();
    }

    public double getMaxOutsideAirTemperature() {
        List<TripPoint> tripPointList = (List<TripPoint>) tripPoints.inOrder();
        tripPointList.sort(Comparator.comparingDouble(TripPoint::getOutsideAirTemperature));
        return tripPointList.getLast().getOutsideAirTemperature();
    }



    // TODO

    public double getSpeedAverage() {
        List<TripPoint> tripPointList = (List<TripPoint>) tripPoints.inOrder();
        double sum = 0;
        for (TripPoint currentTripPoint : tripPointList) {
            sum += currentTripPoint.getVehicleSpeed();
        }
        return sum / tripPoints.size();
    }

    public double getAbsoluteLoadAverage() {
        List<TripPoint> tripPointList = (List<TripPoint>) tripPoints.inOrder();
        double sum = 0;
        for (TripPoint currentTripPoint : tripPointList) {
            sum += currentTripPoint.getAbsoluteLOad();
        }
        return sum / tripPoints.size();
    }

    public double getOutsideAirTemperatureAverage() {
        List<TripPoint> tripPointList = (List<TripPoint>) tripPoints.inOrder();
        double sum = 0;
        for (TripPoint currentTripPoint : tripPointList) {
            sum += currentTripPoint.getOutsideAirTemperature();
        }
        return sum / tripPoints.size();
    }
    public double getTotalTripDistance(){
        double distance = 0;
        List<TripPoint> allPoints = (List<TripPoint>) tripPoints.inOrder();
        for(int i = 1; i < allPoints.size(); i++)
            distance += Utils.haversineDistance(allPoints.get(i).getLatitudeLongitude(), allPoints.get(i - 1).getLatitudeLongitude());
        return distance;
    }
    @Override
    public int compareTo(Trip otherTrip) {
        return id - otherTrip.id;
    }

}
