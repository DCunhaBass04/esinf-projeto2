package exercises;
import domain.Trip;
import domain.Vehicle;
import filereader.Filereader;
import org.junit.jupiter.api.Test;
import trees.AVL;
import utils.Utils;
import java.io.FileNotFoundException;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class Ex4Test {
    AVL<Vehicle> vehicleAVL = Filereader.loadStaticData(Utils.STATIC_DATA_FILENAME_A, Utils.STATIC_DATA_FILENAME_B);
    List<Vehicle> vehicles = (List<Vehicle>) vehicleAVL.inOrder();  //vehicles Avl built
    AVL<Trip> tripAVL = Filereader.loadDynamicData(Utils.DYNAMIC_DATA_FILENAME, vehicles); //inserting trips


    Ex4Test() throws FileNotFoundException {
    }

    @Test
    void assertTripWithHighestDistanceForVehicleIdEmpty() {
        List<Integer> vehiclesIDTests = new ArrayList<>() ;

        SortedMap<Integer, Trip> resultMap = Ex4.getAllTripsWithHighestDistanceForEachVehicle(vehiclesIDTests, vehicleAVL);

        assertNull(resultMap);

    }
    @Test
    void assertTripWithHighestDistanceForVehicleIdNull() {
        List<Integer> vehiclesIDTests = null;

        // You need to pass a lambda or method reference to assertThrows
        assertThrows(NullPointerException.class, () -> Ex4.getAllTripsWithHighestDistanceForEachVehicle(vehiclesIDTests, vehicleAVL));
    }
    @Test
    void assertTripWithHighestDistanceForNonexistentVehicleId() throws NullPointerException{
        List<Integer> vehiclesIDTests = new ArrayList<>();
        vehiclesIDTests.add(-10);

        // Check if accessing a specific operation on resultMap throws a NullPointerException
        assertThrows(NullPointerException.class, () -> Ex4.getAllTripsWithHighestDistanceForEachVehicle(vehiclesIDTests, vehicleAVL));
    }


    //multiple values
        @Test
        void assertTripWithHighestDistanceForMultipleVehicleIds() {
            List<Integer> vehiclesIDTests = new ArrayList<>();
            vehiclesIDTests.add(595);
            vehiclesIDTests.add(576);   //has more than one trip
            vehiclesIDTests.add(562);
            SortedMap<Integer, Trip> resultMap = Ex4.getAllTripsWithHighestDistanceForEachVehicle(vehiclesIDTests, vehicleAVL);

            assertNotNull(resultMap);

            // Define expected values
            List<Integer> expectedIds = Arrays.asList(527, 1016,411);

            for (Map.Entry<Integer, Trip> entry : resultMap.entrySet()) {
                Trip trip = entry.getValue();

                // Use assertTrue to check if the id is one of the expected values
                assertTrue(expectedIds.contains(trip.getId()), "Unexpected Trip Id: " + trip.getId());
            }
        }


    //Single Values

    @Test
    void assertTripWithHighestDistanceForVehicleId213() {
        List<Integer> vehiclesIDTests = new ArrayList<>();
        vehiclesIDTests.add(213);
        SortedMap<Integer, Trip> resultMap = Ex4.getAllTripsWithHighestDistanceForEachVehicle(vehiclesIDTests, vehicleAVL);

        assertNotNull(resultMap);
        for (Map.Entry<Integer, Trip> entry :  resultMap.entrySet()) {
            Trip trip = entry.getValue();
            assertEquals(1576, trip.getId());  //expected Trip Id value
        }
    }

    @Test
    void assertTripWithHighestDistanceForVehicleId561() {
        List<Integer> vehiclesIDTests = new ArrayList<>();
        vehiclesIDTests.add(561);
        SortedMap<Integer, Trip> resultMap = Ex4.getAllTripsWithHighestDistanceForEachVehicle(vehiclesIDTests, vehicleAVL);

        assertNotNull(resultMap);
        for (Map.Entry<Integer, Trip> entry :  resultMap.entrySet()) {
            Integer vehicleID = entry.getKey();
            Trip trip = entry.getValue();
            assertEquals(1361, trip.getId());  //expected Trip Id value
        }
    }

    //wrong values

    @Test
    void assertTripWithHighestDistanceForVehicleId562() {
        List<Integer> vehiclesIDTests = new ArrayList<>();
        vehiclesIDTests.add(562);
        SortedMap<Integer, Trip> resultMap = Ex4.getAllTripsWithHighestDistanceForEachVehicle(vehiclesIDTests, vehicleAVL);

        assertNotNull(resultMap);
        for (Map.Entry<Integer, Trip> entry :  resultMap.entrySet()) {
            Trip trip = entry.getValue();
            assertNotEquals(1017, trip.getId());  //other trip id with less distance
            assertEquals(1016, trip.getId());  //other trip id with less distance
        }
    }

}

