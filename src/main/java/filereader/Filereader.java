package filereader;

import domain.Trip;
import domain.TripPoint;
import domain.Vehicle;
import trees.AVL;
import trees.KDTree;
import utils.Utils;

import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class created for the purpose of reading files and returning useful items
 */


public final class Filereader {

    private final static double NAN_REPLACER = 0; // Must be Double

    private Filereader() {

    }

    /**
     * Este método combina os dois ficheiros de dados estáticos, isto é, os ficheiros com os dados dos veículos e
     * carrega toda a informação para uma AVL com objetos Vehicle.
     * @param filenameA Ficheiro com os dados dos veículos HEV ou PHEV
     * @param filenameB Ficheiro com os dados dos veículos HEV ou PHEV
     * @return Avl com os dados dos veículos
     * @throws FileNotFoundException Caso os ficheiros não existam.
     */
    public static AVL<Vehicle> loadStaticData(String filenameA, String filenameB) throws FileNotFoundException {
        File file = mergeFiles(filenameA, filenameB);
        Scanner fileScanner = new Scanner(file);
        fileScanner.nextLine(); // Ignore header line
        String[] currentLine = fileScanner.nextLine().split(",");
        AVL<Vehicle> vehicleAVL = new AVL<>(createVehicle(currentLine));
        while (fileScanner.hasNext()) {
            currentLine = fileScanner.nextLine().split(",");
            Vehicle currentVehicle = createVehicle(currentLine);
            vehicleAVL.insert(currentVehicle);
        }
        return vehicleAVL;
    }

    /**
     * Este método combina os dois ficheiros com os dados dos veículos num só.
     * Foi criado para facilitar o carregamento de dados no método "loadStaticData".
     * @param filenameA Ficheiro com os dados dos veículos HEV ou PHEV.
     * @param filenameB Ficheiro com os dados dos veículos HEV ou PHEV.
     * @return Um ficheiro com os dados dos dois ficheiros fornecidos como parâmetro.
     */

    private static File mergeFiles(String filenameA, String filenameB) {
        try {
            BufferedReader readerA = new BufferedReader(new FileReader(filenameA));
            BufferedReader readerB = new BufferedReader(new FileReader(filenameB));
            File mergedFile = new File("merged.csv");
            FileWriter writer = new FileWriter(mergedFile);
            readerA.readLine();
            readerB.readLine();
            String line;
            while ((line = readerA.readLine()) != null) {
                writer.write(line + "\n");
            }
            while ((line = readerB.readLine()) != null) {
                writer.write(line + "\n");
            }
            readerA.close();
            readerB.close();
            writer.close();
            return mergedFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null in case of an error
        }
    }

    /**
     * Este método instancia um veículo com os dados da linha analisada no ficheiro Excel.
     * Foi criado para facilitar a leitura dos métodos que usufruem do mesmo.
     * @param currentLine A linha apontada pelo scanner.
     * @return Objeto veículo com os dados da linha.
     */

    private static Vehicle createVehicle(String[] currentLine) {
        int vehicleId = Integer.parseInt(currentLine[0]);
        String vehicleType = currentLine[1];
        String vehicleClass = currentLine[2];
        String engineConfig = currentLine[3];
        String transmission = currentLine[4];
        String driveWheels = currentLine[5];
        int weight;
        if (!currentLine[6].equalsIgnoreCase("NO DATA")) {
            weight = Integer.parseInt(currentLine[6]);
        } else {
            weight = 0;
        }
        return new Vehicle(vehicleId, vehicleType, vehicleClass, engineConfig, transmission, driveWheels, weight);
    }

    /**
     * Este método carrega os dados dinâmicos para uma AVL. Por outras palavras, lê o ficheiro csv
     * e cria objetos Trip conforme os dados de cada linha do ficheiro, carregando os mesmos para uma AVL.
     * Simultaneamente, preenche o campo privado Trip em cada veículo. Assim, ao chamar este método cada veículo fica
     * automaticamente com as suas viagens preenchidas.
     * @param filename O ficheiro que contêm os dados dinâmicos.
     * @param vehicles A lista de veículos cujas viagens queremos preencher
     * @return Uma AVL com todas as viagens.
     * @throws FileNotFoundException Caso o ficheiro não exista.
     */
    public static AVL<Trip> loadDynamicData(String filename, List<Vehicle> vehicles) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(filename));
        fileScanner.nextLine();     // Exclui o header da leitura.
        Trip currentTrip = createTrip(fileScanner.nextLine().split(","));   // Cria viagem com dados da primeira linha.
        AVL<Trip> tripAVL = new AVL<>(currentTrip);
        while (fileScanner.hasNext()) {
            String[] currentLine = fileScanner.nextLine().split(",");
            while (fileScanner.hasNext() && Integer.parseInt(currentLine[1]) == currentTrip.getVehicleId()) {
                addTripPoint(currentLine, currentTrip.getTripPoints());
                currentLine = fileScanner.nextLine().split(",");
            }
            addVehicleTrip(vehicles, currentTrip.getVehicleId(), currentTrip);
            tripAVL.insert(currentTrip);
            currentTrip = createTrip(currentLine);
        }
        return tripAVL;
    }

    private static Trip createTrip(String[] currentLine) {
        double dayNum = Double.parseDouble(currentLine[0]);
        int vehicleId = Integer.parseInt(currentLine[1]);
        int trip = Integer.parseInt(currentLine[2]);
        AVL<TripPoint> tripPoints = new AVL<>(null);
        addTripPoint(currentLine, tripPoints);
        return new Trip(dayNum, vehicleId, trip, tripPoints);
    }

    private static void addTripPoint(String[] currentLine, AVL<TripPoint> tripPoints) {
        int timestamp = Integer.parseInt(currentLine[3]);
        Point2D.Double latitudeLongitude = new Point2D.Double(convertWithoutNan(currentLine[4]), convertWithoutNan(currentLine[5]));
        double vehicleSpeed = convertWithoutNan(currentLine[6]);
        double maf = convertWithoutNan(currentLine[7]);
        double engineRpm = convertWithoutNan(currentLine[8]);
        double absoluteLOad = convertWithoutNan(currentLine[9]);
        double outsideAirTemperature = convertWithoutNan(currentLine[10]);
        double fuelRate = convertWithoutNan(currentLine[11]);
        double airConditioningPowerKw = convertWithoutNan(currentLine[12]);
        double airConditioningPowerW = convertWithoutNan(currentLine[13]);
        double heaterPower = convertWithoutNan(currentLine[14]);
        double hvBatteryCurrent = convertWithoutNan(currentLine[15]);
        double hvBatterySoc = convertWithoutNan(currentLine[16]);
        double hvBatteryVoltage = convertWithoutNan(currentLine[17]);
        double shortTermFuelBank1 = convertWithoutNan(currentLine[18]);
        double shortTermFuelBank2 = convertWithoutNan(currentLine[19]);
        double longTermFuelBank1 = convertWithoutNan(currentLine[20]);
        double longTermFuelBank2 = convertWithoutNan(currentLine[21]);
        TripPoint tripPoint = new TripPoint(timestamp, latitudeLongitude, vehicleSpeed, maf,
                engineRpm, absoluteLOad, outsideAirTemperature, fuelRate, airConditioningPowerKw,
                airConditioningPowerW, heaterPower, hvBatteryCurrent, hvBatterySoc, hvBatteryVoltage,
                shortTermFuelBank1, shortTermFuelBank2, longTermFuelBank1, longTermFuelBank2);
        if (tripPoints.getRoot() == null) {
            tripPoints.setRoot(tripPoint);
        } else {
            tripPoints.insert(tripPoint);
        }
    }

    private static double convertWithoutNan(String data) {
        if (data.equals("NaN")) {
            return NAN_REPLACER;
        } else {
            return Double.parseDouble(data);
        }
    }

    private static void addVehicleTrip(List<Vehicle> vehicles, int vehicleId, Trip trip) {
        for (Vehicle current : vehicles) {
            if (current.getId() == vehicleId) {
                if (current.getVehicleTrips().getRoot() == null) {
                    current.getVehicleTrips().setRoot(trip);
                    break;
                } else {
                    current.getVehicleTrips().insert(trip);
                    break;
                }
            }
        }
    }

    public static List<Trip> loadTrips(String dynamic_data_filename, String static_data_filename_a, String static_data_filename_b) throws FileNotFoundException {
        AVL<Vehicle> vehicleAVL = Filereader.loadStaticData(static_data_filename_a, static_data_filename_b);
        List<Vehicle> vehicles = (List<Vehicle>) vehicleAVL.inOrder();
        AVL<Trip> tripAVL = Filereader.loadDynamicData(dynamic_data_filename, vehicles);
        List<Trip> trips = (List<Trip>) tripAVL.inOrder();
        return trips;
    }

//--------------------------------------------------KD Tree-------------------------------------------------------------
    public static KDTree<Trip> loadDynamicDataInKDtree(String filename) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(filename));
        fileScanner.nextLine(); // Ignore header line
        String[] currentLine = fileScanner.nextLine().split(",");
        Trip currentTrip = createTrip(currentLine);
        KDTree<Trip> kdTree = new KDTree<>(currentTrip, ((List<TripPoint>) currentTrip.getTripPoints().inOrder()).getFirst().getLatitudeLongitude());
        while (fileScanner.hasNext()) {
            int currentVehicleId = currentTrip.getVehicleId();
            currentLine = fileScanner.nextLine().split(",");
            while (Integer.parseInt(currentLine[1]) == currentVehicleId && fileScanner.hasNext()) {
                addTripPoint(currentLine, currentTrip.getTripPoints());
                kdTree.insert(currentTrip, ((List<TripPoint>) currentTrip.getTripPoints().inOrder()).getLast().getLatitudeLongitude());
                currentLine = fileScanner.nextLine().split(",");
            }
            currentTrip = createTrip(currentLine);
        }
        return kdTree;
    }
//--------------------------------------------------KD Tree-------------------------------------------------------------

}
