package exercises;

import domain.Trip;
import domain.TripPoint;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class Ex3 {

    private Ex3() {

    }

    /**
     * Maps the trip number to the origin and destination point hashmap
     */
    public static TreeMap<Integer, HashMap<Point2D.Double, Point2D.Double>> getOriginAndDestination(List<Trip> trips) {
        TreeMap<Integer, HashMap<Point2D.Double, Point2D.Double>> originDestinationMap = new TreeMap<>();
        for (Trip currentTrip : trips) {
            HashMap<Point2D.Double, Point2D.Double> originAndDestinationPoints = new HashMap<>();
            Point2D.Double origin = ((List<TripPoint>) currentTrip.getTripPoints().inOrder()).getFirst().getLatitudeLongitude();
            Point2D.Double destination = ((List<TripPoint>) currentTrip.getTripPoints().inOrder()).getLast().getLatitudeLongitude();
            originAndDestinationPoints.put(origin, destination);
            originDestinationMap.put(currentTrip.getId(), originAndDestinationPoints);
        }
        return originDestinationMap;
    }
}






