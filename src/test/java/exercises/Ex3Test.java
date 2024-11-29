package exercises;

import domain.Trip;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.awt.geom.Point2D;
import java.util.TreeMap;

import static filereader.Filereader.loadTrips;

public class Ex3Test {

    @Test
    void assertEmptyListEqualsNull() throws FileNotFoundException {
        List<Trip> trips = loadTrips("VED_180404_week.csv", "VED_Static_Data_ICE&HEV.csv", "VED_Static_Data_PHEV&EV.csv");
        TreeMap<Integer, HashMap<Point2D.Double, Point2D.Double>> map = Ex3.getOriginAndDestination(trips);
        HashMap<Point2D.Double, Point2D.Double> originAndDestinationPoints = new HashMap<>();
        Point2D.Double origin = new Point2D.Double(42.26920056, -83.73446917);
        Point2D.Double destination = new Point2D.Double(42.27401139, -83.74820694);
        originAndDestinationPoints.put(origin, destination);
        Assertions.assertEquals(originAndDestinationPoints, map.get(2003));
    }

}
