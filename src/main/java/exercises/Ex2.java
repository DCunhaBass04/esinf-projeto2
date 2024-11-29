package exercises;

import domain.Trip;
import domain.Vehicle;
import trees.AVL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Ex2 {

    private Ex2() {

    }

    /**
     * Este é o método principal do exercício 2. Devolve um HashMap que mapeia os números de cada veículo a uma lista
     * de doubles que contêm as estatísticas pedidas: máximo, mínimo, e média dos parâmetros: velocidade, carga, e temperatura.
     * Também recebe como parâmetros um intervalo de dias para calcular o pedido.
     * @param vehicleList A lista de veículos a calcular as estatísticas.
     * @param lowerDayNum O dia mais baixo do intervalo a calcular as estatísticas.
     * @param higherDayNum O dia mais alto do intervalo a calcular as estatísticas.
     * @return Um HashMap que mapeia o número de cada veículo do intervalo às estatísticas pedidas.
     */

    public static HashMap<Integer, List<Double>> getStatistics(List<Vehicle> vehicleList, double lowerDayNum, double higherDayNum) {
        if (vehicleList.isEmpty() || !verifyDayInterval(lowerDayNum, higherDayNum)) {
            return null;
        } else {
            HashMap<Integer, List<Double>> statisticsMap = new HashMap<>();
            for (Vehicle currentVehicle : vehicleList) {
                AVL<Trip> vehicleTrips = currentVehicle.getVehicleTrips();
                calcTripStatistics(statisticsMap, currentVehicle.getId(), vehicleTrips, lowerDayNum, higherDayNum);
            }
            return statisticsMap;
        }
    }

    /**
     * Calcula as estatisticas para uma determinada viagem e adiciona as mesmas a um HashMap.
     * @param statisticsMap O mapa que mapeia o número do veículo às suas estatísticas.
     * @param currentVehicleId O id do veículo a calcular as estatísticas.
     * @param currentVehicleTrips As viagens do veículo a calcular as estatísticas.
     * @param lowerDayNum O dia mais baixo no intervalo a calcular as estatísticas.
     * @param higherDayNum O dia mais alto no intervalo a calcular as estatísticas.
     */

    private static void calcTripStatistics(HashMap<Integer, List<Double>> statisticsMap, int currentVehicleId,
                                           AVL<Trip> currentVehicleTrips, double lowerDayNum, double higherDayNum) {
        if (currentVehicleTrips.getRoot() != null) {
            for (Trip vehicleTrip : currentVehicleTrips.inOrder()) {
                if (verifyDayInsideInterval(vehicleTrip.getDayNum(), lowerDayNum, higherDayNum)) {
                    List<Double> statistics = new ArrayList<>();
                    double maxSpeed = vehicleTrip.getMaxVehicleSpeed();
                    statistics.add(maxSpeed);
                    double maxLoad = vehicleTrip.getMaxAbsoluteLoad();
                    statistics.add(maxLoad);
                    double maxOutsideAirTemperature = vehicleTrip.getMaxOutsideAirTemperature();
                    statistics.add(maxOutsideAirTemperature);
                    double avgSpeed = vehicleTrip.getSpeedAverage();
                    statistics.add(avgSpeed);
                    double avgLoad = vehicleTrip.getAbsoluteLoadAverage();
                    statistics.add(avgLoad);
                    double avgOutsideAirTemperature = vehicleTrip.getOutsideAirTemperatureAverage();
                    statistics.add(avgOutsideAirTemperature);
                    statisticsMap.put(currentVehicleId, statistics);
                }
            }
        }
    }

    /**
     * Verifica que um dado dia está num intervalo de dias.
     * @param dayNum O dia a verficar.
     * @param lower O dia mais baixo do intervalo.
     * @param higher O dia mais alto do intervalo.
     * @return Verdadeiro ou falso.
     */

    private static boolean verifyDayInsideInterval(double dayNum, double lower, double higher) {
        if (dayNum > lower && dayNum < higher) {
            return true;
        } else return dayNum == lower || dayNum == higher;
    }

    /**
     * Verifica que o intervalo de dias fornecido é válido.
     * @param lowerDayNum Dia mais baixo do intervalo.
     * @param higherDayNum Dia mais alto do intervalo.
     * @return Verdadeiro ou falso.
     */

    private static boolean verifyDayInterval(double lowerDayNum, double higherDayNum) {
        if (lowerDayNum > higherDayNum) {
            return false;
        } else if (lowerDayNum * higherDayNum < 0) {
            return false;
        } else if (lowerDayNum == higherDayNum){
            return true;
        } else {
            return true;
        }
    }
}
