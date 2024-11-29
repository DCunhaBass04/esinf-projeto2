package exercises;

import domain.Trip;
import filereader.Filereader;
import org.junit.jupiter.api.Test;
import trees.KDTree;

import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ex5Test {
    private final KDTree<Trip> allTrips = Filereader.loadDynamicDataInKDtree("VED_180404_week.csv");

    public Ex5Test() throws FileNotFoundException {
    }

    @Test void assertNearestPointsIsCorrectForTrip2294() {
        Trip trip = Ex5.getTripCloseToStartAndEndPoint(allTrips, new Point2D.Double(42.2903977, -83.7183594), new Point2D.Double(42.2782272, -83.7460972));
        assertEquals(trip.getId(), 2294);
    }
    @Test void assertNearestPointsIsCorrectForTrip537() {
        Trip trip = Ex5.getTripCloseToStartAndEndPoint(allTrips, new Point2D.Double(42.274516111, -83.67427749), new Point2D.Double(42.274336669, -83.674655831));
        assertEquals(trip.getId(), 537);
    }
}
