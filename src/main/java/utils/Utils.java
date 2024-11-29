package utils;

import domain.Identifiable;
import trees.AVL;

import java.awt.geom.Point2D;
import java.util.List;

public final class Utils<T extends Number> {

    public static final String DYNAMIC_DATA_FILENAME = "VED_180404_week.csv";
    public static final String STATIC_DATA_FILENAME_A = "VED_Static_Data_ICE&HEV.csv";
    public static final String STATIC_DATA_FILENAME_B = "VED_Static_Data_PHEV&EV.csv";

    private static final double NAN_REPLACER = 0;

    private Utils() {

    }

    public static <T extends Number> double calcAverage(List<T> list) {
        double sum = 0;
        for (T currentElement : list) {
            sum += currentElement.doubleValue();
        }
        return sum / list.size();
    }

    public static double calcMaxDouble(List<Double> list) {
        double max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > max) {
                max = list.get(i);
            }
        }
        return max;
    }

    public static double calcMinDouble(List<Double> list) {
        double max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > max) {
                max = list.get(i);
            }
        }
        return max;
    }

    public static int calcMaxInt(List<Integer> list) {
        int max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > max) {
                max = list.get(i);
            }
        }
        return max;
    }

    public static int calcMinInt(List<Integer> list) {
        int max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < max) {
                max = list.get(i);
            }
        }
        return max;
    }
    public static Identifiable getIdentifiableById(int id, AVL<? extends Identifiable> identifiableAVL) {
        List<Identifiable> objects = (List<Identifiable>) identifiableAVL.inOrder();
        for (Identifiable current : objects) {
            if (current.getId() == id) {
                return current;
            }
        }
        return null;
    }
    public static double haversineDistance(Point2D.Double coords1, Point2D.Double coords2){
        final int earthRadius = 6371; //kilometers
        double x1Rad = Math.toRadians(coords1.getX()), x2Rad = Math.toRadians(coords2.getX()),
                y1Rad = Math.toRadians(coords1.getY()), y2Rad = Math.toRadians(coords2.getY());
        double xDistance = (y2Rad - y1Rad) * Math.cos((x1Rad + x2Rad)/2),
                yDistance = (x2Rad - x1Rad);
        return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance,2)*earthRadius);
    }
}
