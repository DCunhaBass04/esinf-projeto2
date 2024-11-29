package exercises;

import domain.Trip;
import domain.Vehicle;
import filereader.Filereader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import trees.AVL;
import utils.Utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class Ex2Test {

    private static final String DYNAMIC_DATA_FILENAME = "VED_180404_week.csv";
    private static final String STATIC_DATA_FILENAME_A = "VED_Static_Data_ICE&HEV.csv";
    private static final String STATIC_DATA_FILENAME_B = "VED_Static_Data_PHEV&EV.csv";

    @Test
    void getStatistics() throws FileNotFoundException {
        AVL<Vehicle> vehicleAVL = Filereader.loadStaticData(Utils.STATIC_DATA_FILENAME_A, Utils.STATIC_DATA_FILENAME_B);
        List<Vehicle> vehiclesInOrder = (List<Vehicle>) vehicleAVL.inOrder();
        AVL<Trip> tripAVL = Filereader.loadDynamicData(Utils.DYNAMIC_DATA_FILENAME, vehiclesInOrder);
        List<Trip> tripsInOrder = (List<Trip>) tripAVL.inOrder();
        double lowerDay = 155.5229374;
        double higherDay = 156.5229374;
        HashMap<Integer, List<Double>> statisticsMap = Ex2.getStatistics(vehiclesInOrder, lowerDay, higherDay);
        for (Integer vehId : statisticsMap.keySet()) {
            List<Double> currentStats = statisticsMap.get(vehId);
            currentStats.forEach(System.out::println);
        }
    }

    @Test
    void assertEmptyListEqualsNull() {
        List<Vehicle> vehicleList = new ArrayList<>();
        double lowerDay = 155.479425;
        double higherDay = 155.8903352;
        HashMap<Integer, List<Double>> map = Ex2.getStatistics(vehicleList, lowerDay, higherDay);
        Assertions.assertNull(map);
    }

    @Test
    void assertInvalidDayIntervalEqualsNull() {
        List<Vehicle> vehicleList = new ArrayList<>();
        double lowerDay = 155.479425;
        double higherDay = -155.8903352;
        HashMap<Integer, List<Double>> map = Ex2.getStatistics(vehicleList, lowerDay, higherDay);
        Assertions.assertNull(map);
    }
}