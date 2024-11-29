package filereader;

import domain.Trip;
import domain.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import trees.AVL;
import utils.Utils;

import java.io.FileNotFoundException;
import java.util.List;

class FilereaderTest {

    @Test
    void loadStaticData() throws FileNotFoundException {
        AVL<Vehicle> vehicleAVL = Filereader.loadStaticData(Utils.STATIC_DATA_FILENAME_A, Utils.STATIC_DATA_FILENAME_B);
        List<Vehicle> vehiclesInOrder = (List<Vehicle>) vehicleAVL.inOrder();
    }

    @Test
    void loadDynamicData() throws FileNotFoundException {
        AVL<Vehicle> vehicleAVL = Filereader.loadStaticData(Utils.STATIC_DATA_FILENAME_A, Utils.STATIC_DATA_FILENAME_B);
        List<Vehicle> vehiclesInOrder = (List<Vehicle>) vehicleAVL.inOrder();
        AVL<Trip> tripAVL = Filereader.loadDynamicData(Utils.DYNAMIC_DATA_FILENAME, vehiclesInOrder);
        List<Trip> tripsInOrder = (List<Trip>) tripAVL.inOrder();
    }

    @Test
    void verifySomeTripsAreLoadedAfterLoadingDynamicData() throws FileNotFoundException {
        AVL<Vehicle> vehicleAVL = Filereader.loadStaticData(Utils.STATIC_DATA_FILENAME_A, Utils.STATIC_DATA_FILENAME_B);
        List<Vehicle> vehiclesInOrder = (List<Vehicle>) vehicleAVL.inOrder();
        System.out.println("Before loading trips: ");
        for (Vehicle vehicle : vehiclesInOrder) {
            System.out.println(vehicle.getVehicleTrips().isEmpty());
        }
        AVL<Trip> tripAVL = Filereader.loadDynamicData(Utils.DYNAMIC_DATA_FILENAME, vehiclesInOrder);
        System.out.println("After loading trips: ");
        for (Vehicle vehicle : vehiclesInOrder) {
            System.out.println(vehicle.getVehicleTrips().isEmpty());
        }
    }

    @Test
    void assertTripsIdEqualsVehicleId() throws FileNotFoundException {
        AVL<Vehicle> vehicleAVL = Filereader.loadStaticData(Utils.STATIC_DATA_FILENAME_A, Utils.STATIC_DATA_FILENAME_B);
        List<Vehicle> vehiclesInOrder = (List<Vehicle>) vehicleAVL.inOrder();
        AVL<Trip> tripAVL = Filereader.loadDynamicData(Utils.DYNAMIC_DATA_FILENAME, vehiclesInOrder);
        List<Trip> tripsInOrder = (List<Trip>) tripAVL.inOrder();
        boolean passed = true;
        for (Vehicle currentVehicle : vehiclesInOrder) {
            if (!currentVehicle.getVehicleTrips().isEmpty()) {
                for (Trip currentTrip : currentVehicle.getVehicleTrips().inOrder()) {
                    if (currentTrip.getVehicleId() != currentVehicle.getId()) {
                        passed = false;
                        break;
                    }
                }
            }
        }
        Assertions.assertTrue(passed);
    }

}