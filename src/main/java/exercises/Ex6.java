package exercises;

import domain.Trip;
import trees.KDTree;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ex6 {
    /**
     * Method developed to do Exercise 6
     * @param tripKDTree already correctly filled Kd Tree with Trips
     * @param n number of trips the user wants in their top-N longest trips
     * @param coords1 point that contains the left and bottom limits of the range
     * @param coords2 point that contains the right and upper limits of the range
     * @return a list containing n trips that belong in the desired range, sorted in descending order of their length (dayNum)
     */
    public static List<Trip> getTopNTrips(KDTree<Trip> tripKDTree, int n, Point2D.Double coords1, Point2D.Double coords2){
        Comparator<Trip> cmpDistance = (p1, p2) -> Double.compare(p2.getTotalTripDistance(), p1.getTotalTripDistance());
        List<Trip> topNTrips = new ArrayList<>();
        List<Trip> allTripsInRange = tripKDTree.topTrips(coords1, coords2);
        allTripsInRange.sort(cmpDistance);
        if(allTripsInRange.size() < n)
            n = allTripsInRange.size();
        for(int i = 0; i < n; i++)
            topNTrips.add(allTripsInRange.get(i));
        return topNTrips;
    }
}
