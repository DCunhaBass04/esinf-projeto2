package exercises;

import domain.Trip;
import domain.Vehicle;
import filereader.Filereader;
import org.junit.jupiter.api.Test;
import trees.AVL;

import java.io.FileNotFoundException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Ex1Test {
    private final String STATIC_FILE1 = "VED_Static_Data_ICE&HEV.csv", STATIC_FILE2 = "VED_Static_Data_PHEV&EV.csv";
    private final String DYNAMIC_FILE = "VED_180404_week.csv";
    private AVL<Vehicle> vehicles = Filereader.loadStaticData(STATIC_FILE1, STATIC_FILE2);
    private AVL<Trip> trips = Filereader.loadDynamicData(DYNAMIC_FILE, (List<Vehicle>) vehicles.inOrder());


    public Ex1Test() throws FileNotFoundException {
    }
    @Test void assertTripAndVehicleInformationForTrip2213() {
        AbstractMap.SimpleEntry<Trip, Vehicle> entry = Ex1.getTripAndVehicle(vehicles, trips, 2213);
        //System.out.printf("Trip Data:%n-DayNum: %.2f%n-TripID: %d%n%nVehicle Data:%n-VehID: %d", entry.getKey().getDayNum(), entry.getKey().getId(), entry.getValue().getId());
        assertEquals(entry.getValue().getId(), 10);
        assertEquals(entry.getKey().getDayNum(), 155.5329374);
    }
    @Test void assertTripAndVehicleInformationForTrip2294() {
        AbstractMap.SimpleEntry<Trip, Vehicle> entry = Ex1.getTripAndVehicle(vehicles, trips, 2294);
        //System.out.printf("Trip Data:%n-DayNum: %.2f%n-TripID: %d%n%nVehicle Data:%n-VehID: %d", entry.getKey().getDayNum(), entry.getKey().getId(), entry.getValue().getId());
        assertEquals(entry.getValue().getId(), 11);
        assertEquals(entry.getKey().getDayNum(), 155.8144791);
    }
    @Test void assertTripAndVehicleInformationForTrip2003() {
        AbstractMap.SimpleEntry<Trip, Vehicle> entry = Ex1.getTripAndVehicle(vehicles, trips, 2003);
        //System.out.printf("Trip Data:%n-DayNum: %.2f%n-TripID: %d%n%nVehicle Data:%n-VehID: %d", entry.getKey().getDayNum(), entry.getKey().getId(), entry.getValue().getId());
        assertEquals(entry.getValue().getId(), 140);
        assertEquals(entry.getKey().getDayNum(), 155.026471);
    }
    @Test void assertTripAndVehicleInformationForTrip1431() {
        AbstractMap.SimpleEntry<Trip, Vehicle> entry = Ex1.getTripAndVehicle(vehicles, trips, 1431);
        //System.out.printf("Trip Data:%n-DayNum: %.2f%n-TripID: %d%n%nVehicle Data:%n-VehID: %d", entry.getKey().getDayNum(), entry.getKey().getId(), entry.getValue().getId());
        assertEquals(entry.getValue().getId(), 484);
        assertEquals(entry.getKey().getDayNum(), 155.9464657);
    }
    @Test void assertTripAndVehicleInformationForTrip1131() {
        AbstractMap.SimpleEntry<Trip, Vehicle> entry = Ex1.getTripAndVehicle(vehicles, trips, 1131);
        //System.out.printf("Trip Data:%n-DayNum: %.2f%n-TripID: %d%n%nVehicle Data:%n-VehID: %d", entry.getKey().getDayNum(), entry.getKey().getId(), entry.getValue().getId());
        assertEquals(entry.getValue().getId(), 488);
        assertEquals(entry.getKey().getDayNum(), 158.0293321);
    }
    @Test void assertTripAndVehicleInformationForTrip1262() {
        AbstractMap.SimpleEntry<Trip, Vehicle> entry = Ex1.getTripAndVehicle(vehicles, trips, 1262);
        //System.out.printf("Trip Data:%n-DayNum: %.2f%n-TripID: %d%n%nVehicle Data:%n-VehID: %d", entry.getKey().getDayNum(), entry.getKey().getId(), entry.getValue().getId());
        assertEquals(entry.getValue().getId(), 303);
        assertEquals(entry.getKey().getDayNum(), 155.862083);
    }
    @Test void assertVehicleInformationForVehID10() {
        Vehicle vehicle = Ex1.getVehicleById(10, vehicles);
        assertTrue(vehicle.getVehicleType().equalsIgnoreCase("EV"));
        assertTrue(vehicle.getVehicleClass().equalsIgnoreCase("Car"));
        assertTrue(vehicle.getEngineConfig().equalsIgnoreCase("ELECTRIC"));
        assertTrue(vehicle.getTransmission().equalsIgnoreCase("NO DATA"));
        assertTrue(vehicle.getDriveWheels().equalsIgnoreCase("FWD"));
        assertEquals(vehicle.getWeight(), 3500);
        List<Integer> idList = new ArrayList<>(Arrays.asList(2213, 2219));
        List<Trip> tripList = (List<Trip>) vehicle.getVehicleTrips().inOrder();
        assertEquals(tripList.size(), idList.size());
        for(int i = 0; i < tripList.size(); i++)
            assertEquals(tripList.get(i).getId(), idList.get(i));
    }
    @Test void assertVehicleInformationForVehID398() {
        Vehicle vehicle = Ex1.getVehicleById(398, vehicles);
        assertTrue(vehicle.getVehicleType().equalsIgnoreCase("PHEV"));
        assertTrue(vehicle.getVehicleClass().equalsIgnoreCase("Car"));
        assertTrue(vehicle.getEngineConfig().equalsIgnoreCase("4-GAS/ELECTRIC 1.8L"));
        assertTrue(vehicle.getTransmission().equalsIgnoreCase("CVT"));
        assertTrue(vehicle.getDriveWheels().equalsIgnoreCase("FWD"));
        assertEquals(vehicle.getWeight(), 3000);
        List<Integer> idList = new ArrayList<>(Arrays.asList(1151, 1164));
        List<Trip> tripList = (List<Trip>) vehicle.getVehicleTrips().inOrder();
        for(int i = 0; i < tripList.size(); i++) {
            //System.out.println(tripList.get(i).getId());
            assertEquals(tripList.get(i).getId(), idList.get(i));
        }
    }
}
