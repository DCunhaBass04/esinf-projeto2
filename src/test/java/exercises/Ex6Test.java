package exercises;

import domain.Trip;
import filereader.Filereader;
import org.junit.jupiter.api.Test;
import trees.KDTree;

import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ex6Test {
    private final KDTree<Trip> kdTree = Filereader.loadDynamicDataInKDtree("VED_180404_week.csv");

    public Ex6Test() throws FileNotFoundException {
    }

    @Test void assertBiggestTripsOfAllTrips() {
        List<Trip> trips = Ex6.getTopNTrips(kdTree, 5, new Point2D.Double(0, -90), new Point2D.Double(50, 0));
        List<Integer> idList = new ArrayList<>(Arrays.asList(1304, 1379, 1221, 1395, 1126));
        printAndTestLists(trips, idList);
    }
    @Test void assertBiggestTripsWithinRange1() {
        List<Trip> trips = Ex6.getTopNTrips(kdTree, 3, new Point2D.Double(42.27, -83.72), new Point2D.Double(42.31, -82.68));
        List<Integer> idList = new ArrayList<>(Arrays.asList(1500, 1059, 1724));
        printAndTestLists(trips, idList);
    }
    @Test void assertBiggestTripsWithinRange2() {
        List<Trip> trips = Ex6.getTopNTrips(kdTree, 3, new Point2D.Double(42.275, -83.73), new Point2D.Double(42.305, -82.71));
        List<Integer> idList = new ArrayList<>(Arrays.asList(490, 931, 1806));
        printAndTestLists(trips, idList);
    }
    @Test void assertBiggestTripsWithinNullRange() {
        List<Trip> trips = Ex6.getTopNTrips(kdTree, 3, new Point2D.Double(42.15, -83.7), new Point2D.Double(42.2, -82.65));
        List<Integer> idList = new ArrayList<>();
        printAndTestLists(trips, idList);
    }
    private void printAndTestLists(List<Trip> trips, List<Integer> idList){
        assertEquals(trips.size(), idList.size());
        for(int i = 0; i < trips.size(); i++) {
            //System.out.printf("Trip no. %d%nDistance %.2f km%n%n",trips.get(i).getId(), trips.get(i).getTotalTripDistance());
            assertEquals(trips.get(i).getId(), idList.get(i));
        }
    }


}
