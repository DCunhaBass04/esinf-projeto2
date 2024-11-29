package exercises;

import domain.Trip;
import domain.TripPoint;
import trees.KDTree;
import utils.Utils;

import java.awt.geom.Point2D;
import java.util.List;

public class Ex5 {
    public static Trip getTripCloseToStartAndEndPoint(KDTree<Trip> allTrips, Point2D.Double start, Point2D.Double end){
        Trip closestTrip = null;
        double closestDistance = Double.MAX_VALUE;
        for(Trip trip : allTrips.inOrder()){
            double distance = Utils.haversineDistance(((List<TripPoint>)trip.getTripPoints().inOrder()).getFirst().getLatitudeLongitude(), start)
                    + Utils.haversineDistance(((List<TripPoint>)trip.getTripPoints().inOrder()).getLast().getLatitudeLongitude(), end);
            if(distance < closestDistance){
                closestTrip = trip;
                closestDistance = distance;
            }
        }
        return closestTrip;
    }
}
