package exercises;
import domain.Trip;
import domain.TripPoint;
import domain.Vehicle;
import trees.AVL;
import utils.Utils;
import java.awt.geom.Point2D;
import java.util.*;


/**
 * The type Ex 4.
 * Construtor vazio para bloquear a instanciação
 */
public class Ex4 {

    private Ex4() {

    }


    /**
     * Este é o método principal do Exercício 4. Ele acha a viagem com a maior distância para cada veículo
     * e retorna um SortedMap com os ids dos veículos como chave e à sua viagem (Trip) que contem a maior distância
     * como valor.
     *
     * @param vehicleIds Uma lista de ids que idenfica o conjunto de veículos.
     * @param vehicles Uma árvore AVL contendo informações sobre os veículos.
     * @return Um SortedMap mapeando os IDs dos veículos às suas respetivas viagens com maior distância.
     */
    public static SortedMap<Integer, Trip> getAllTripsWithHighestDistanceForEachVehicle(List<Integer> vehicleIds, AVL<Vehicle> vehicles) {                            // for each id distance
        SortedMap<Integer, Trip> tripWithBiggestDistanceMap = new TreeMap<>();
        if (vehicles.isEmpty() || vehicleIds.isEmpty()) {
            return null;
        }
        for (Integer id : vehicleIds) {
            Vehicle vehicle = (Vehicle) Utils.getIdentifiableById(id, vehicles);
            Trip highestTrip = getTripWithMaxDistanceForOneVehicle(vehicle);
            tripWithBiggestDistanceMap.put(id, highestTrip);

        }
        return tripWithBiggestDistanceMap;
    }


    /**

     * Este método auxiliar do método a cima apresentado, calcula e retorna a viagem com a maior distância das viagens associadas ao veículo.
     * O método percorre as viagens do veículo, calcula a distância entre o ponto de origem e destino de cada viagem
     * e guarda aviagem com a maior distância encontrada.
     *
     * @param vehicle Veículo para o qual deseja-se encontrar a viagem com a maior distância.
     * @return A viagem com a maior distância, ou null se não houver viagens associadas ao veículo.
     */


    private static Trip getTripWithMaxDistanceForOneVehicle(Vehicle vehicle) {

        AVL<Trip> trips = vehicle.getVehicleTrips();
        if (trips.isEmpty()) {
            return null;
        }
        Trip highestTrip = null;
        double highestDistance = -1;

        for (Trip trip : trips.inOrder()) {
            double originX = ((List<TripPoint>) trip.getTripPoints().inOrder()).getFirst().getLatitudeLongitude().getX();
            double originY = ((List<TripPoint>) trip.getTripPoints().inOrder()).getFirst().getLatitudeLongitude().getY();
            double destinationX = ((List<TripPoint>) trip.getTripPoints().inOrder()).getLast().getLatitudeLongitude().getX();
            double destinationY = ((List<TripPoint>) trip.getTripPoints().inOrder()).getLast().getLatitudeLongitude().getY();
            double distance = Point2D.distance(originX, originY, destinationX, destinationY);

            if (distance > highestDistance) {
                highestDistance = distance;
                highestTrip = trip;
            }
        }
        return highestTrip;
    }

}