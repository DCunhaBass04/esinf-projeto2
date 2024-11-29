package exercises;

import domain.Identifiable;
import domain.Trip;
import domain.Vehicle;
import trees.AVL;
import utils.Utils;

import java.util.AbstractMap;
import java.util.List;

public class Ex1 {

    public static Vehicle getVehicleById(int vehicleId, AVL<Vehicle> vehicleAVL) {
        return (Vehicle) Utils.getIdentifiableById(vehicleId, vehicleAVL);
    }

    public static AbstractMap.SimpleEntry<Trip, Vehicle> getTripAndVehicle(AVL<Vehicle> vehicles, AVL<Trip> trips, int tripId) {
        Trip trip = (Trip) Utils.getIdentifiableById(tripId, trips);
        Vehicle responsibleVehicle = (Vehicle) Utils.getIdentifiableById(trip.getVehicleId(), vehicles);
        return new AbstractMap.SimpleEntry<>(trip, responsibleVehicle);
    }


}
