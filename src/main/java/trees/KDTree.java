package trees;

import domain.Trip;
import domain.TripPoint;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * KD tree created with the methods necessary to develop ESINF's second project
 * This tree is a 2D tree, as it only supports points with two coordinates (x and y)
 * @param <E> generic class that can be compared
 */
public class KDTree <E extends Comparable<E>> {
    protected KDNode<E> root;
    public KDTree(E rootElement, Point2D.Double coords) {
        root = new KDNode<>(coords, rootElement, null, null);
    }
    private final Comparator<KDNode<E>> cmpX = Comparator.comparingDouble(p -> p.getCoords().getX());
    private final Comparator<KDNode<E>> cmpY = Comparator.comparingDouble(p -> p.getCoords().getY());

    /**
     * Public method accessible from outside that leads to the private "insert" method
     * @param element element to be associated with the given coordinates
     * @param coords coordinates that will decide where the tree is placed
     */
    public void insert(E element, Point2D.Double coords){
        insert(root, new KDNode<>(coords, element, null, null), true);
    }

    /**
     * This recursive method is used to insert the new object and its coordinates in this KD tree
     * Best-Case Time Complexity (in big-Oh notation) is O(log n) (n being the number of nodes in the tree)
     * Worst-Case Time Complexity (in big-Oh notation) is O(n) (n being the total number of nodes in the tree)
     * @param currentNode node that is being currently analyzed, starts as the root
     * @param node node that holds the new point's data, left and right child start as null and are changed inside the method to properly fit it in the tree
     * @param divX true if the compared dimension is X, false if it is Y
     */
    private void insert(KDNode<E> currentNode, KDNode<E> node, boolean divX){
        if(!node.getCoords().equals(currentNode.getCoords())) {
            int cmpResult = (divX ? cmpX : cmpY).compare(node, currentNode);
            if (cmpResult < 0)
                if (currentNode.getLeft() == null)
                    currentNode.setLeft(node);
                else
                    insert(currentNode.getLeft(), node, !divX);
            else if (currentNode.getRight() == null)
                    currentNode.setRight(node);
                else
                    insert(currentNode.getRight(), node, !divX);
        }
    }

    /**
     * Public method accessible from outside that leads to the private "findNearestNeighbor" method
     * @param coords coordinates to be searched
     * @return nearest point to the desired coordinate
     */
    public E findNearestNeighbor(Point2D.Double coords){
        return findNearestNeighbor(root, coords.getX(), coords.getY(), root, true);
    }

    /**
     * This recursive method is used to obtain the nearest node to a certain point not belonging in the tree
     * Best-Case Time Complexity (in big-Oh notation) is O(log n) (n being the number of nodes in the tree)
     * Worst-Case Time Complexity (in big-Oh notation) is O(n) (n being the total number of nodes in the tree)
     * @param node current node to be compared to the given point, starts as the root
     * @param x x coordinate of the searched point
     * @param y y coordinate of the search point
     * @param closestNode node currently regarded as the closest to the searched point, also starts as the root
     * @param divX true if the compared dimension is X, false if it is Y
     * @return the closest element to the desired point in the entire tree
     */
    private E findNearestNeighbor(KDNode<E> node, double x, double y, KDNode<E> closestNode, boolean divX){
        if (node == null) return null;

        double d = Point2D.distance(node.getCoords().getX(), node.getCoords().getY(), x, y);
        double closestDist = Point2D.distance(closestNode.getCoords().getX(), closestNode.getCoords().getY(), x, y);
        if(closestDist > d) closestNode.setObject(node);

        double delta = divX ? x - node.getCoords().getX() : y - node.getCoords().getY();
        double delta2 = Math.pow(delta, 2);

        KDNode<E> node1 = delta < 0 ? node.getLeft() : node.getRight();
        KDNode<E> node2 = delta < 0 ? node.getRight() : node.getLeft();

        findNearestNeighbor(node1, x, y, closestNode, !divX);

        if(delta2 < closestDist) findNearestNeighbor(node2, x, y, closestNode, !divX);

        return closestNode.getElement();
    }

    /**
     * Public method accessible from outside that leads to the private "getObjectInInterval" method
     * @param coords1 point that contains the left and bottom limits of the range
     * @param coords2 point that contains the right and upper limits of the range
     * @return list containing all the E objects in the tree that belong to that range
     */
    public List<E> topEObjects(Point2D.Double coords1, Point2D.Double coords2){
        List<E> topObjects = new ArrayList<>();
        getObjectInInterval(root, coords1.getX(), coords1.getY(), coords2.getX(), coords2.getY(), topObjects, true);
        return topObjects;
    }

    /**
     * This recursive method is used to obtain the points that belong to a certain range, adding them to a list
     * Best-Case Time Complexity (in big-Oh notation) is O(log n) (n being the number of nodes in the tree)
     * Worst-Case Time Complexity (in big-Oh notation) is O(n) (n being the number of nodes in the tree)
     * @param node current node to be analyzed. Starts off as the root of the tree
     * @param x1 range's left limit
     * @param y1 range's bottom limit
     * @param x2 range's right limit
     * @param y2 range's upper limit
     * @param topObjects list of objects that belong in that range. Is updated through the method
     * @param divX true if the compared dimension is X, false if it is Y
     */
    private void getObjectInInterval(KDNode<E> node, double x1, double y1, double x2, double y2, List<E> topObjects, boolean divX){
        if (node == null) return;
        if (node.getCoords().getX() >= x1 && node.getCoords().getX() <= x2 && node.getCoords().getY() >= y1 && node.getCoords().getY() <= y2)
            topObjects.add(node.getElement());
        if ((node.getCoords().getX() >= x1 && divX) || (node.getCoords().getY() >= y1 && !divX))
            getObjectInInterval(node.getLeft(), x1, y1, x2, y2, topObjects, !divX);
        if ((node.getCoords().getX() <= x2 && divX) || (node.getCoords().getY() <= y2 && !divX))
            getObjectInInterval(node.getRight(), x1, y1, x2, y2, topObjects, !divX);
    }
    public Iterable<E> inOrder() {
        List<E> elements = new ArrayList<>();
        return inOrder(root, elements);
    }
    private List<E> inOrder(Node<E> node, List<E> elements) {
        if (node != null) {
            inOrder(node.getLeft(), elements);
            if(!elements.contains(node.getElement()))
                elements.add(node.getElement());
            inOrder(node.getRight(), elements);
        }
        return elements;
    }
    /**
     * Public method accessible from outside that leads to the private "getTripInInterval" method, only usable for trips
     * @param coords1 point that contains the left and bottom limits of the range
     * @param coords2 point that contains the right and upper limits of the range
     * @return list containing all trips in the tree where all of its points belong to that range
     */
    public List<Trip> topTrips(Point2D.Double coords1, Point2D.Double coords2){
        List<Trip> topObjects = new ArrayList<>();
        getTripInInterval((KDNode<Trip>) root, coords1.getX(), coords1.getY(), coords2.getX(), coords2.getY(), topObjects, true);
        return topObjects;
    }

    /**
     * Similar to the getObjectInInterval method, only for trips
     * Complexities are multiplied by the size of the trip with more registered points
     * @param node current node to be analyzed. Starts off as the root of the tree
     * @param x1 range's left limit
     * @param y1 range's bottom limit
     * @param x2 range's right limit
     * @param y2 range's upper limit
     * @param topTrips list of trips that belong in that range. Is updated through the method
     * @param divX true if the compared dimension is X, false if it is Y
     */
    private void getTripInInterval(KDNode<Trip> node, double x1, double y1, double x2, double y2, List<Trip> topTrips, boolean divX){
        if (node == null) return;
        if (isEntireTripInRange(node.getElement(), x1, y1, x2, y2) && !topTrips.contains(node.getElement()))
            topTrips.add(node.getElement());
        if ((node.getCoords().getX() >= x1 && divX) || (node.getCoords().getY() >= y1 && !divX))
            getTripInInterval(node.getLeft(), x1, y1, x2, y2, topTrips, !divX);
        if ((node.getCoords().getX() <= x2 && divX) || (node.getCoords().getY() <= y2 && !divX))
            getTripInInterval(node.getRight(), x1, y1, x2, y2, topTrips, !divX);
    }

    /**
     * Checks if all registered points of a certain trip belong to the desired range
     * @param trip trip to be analyzed
     * @param x1 range's left limit
     * @param y1 range's bottom limit
     * @param x2 range's right limit
     * @param y2 range's upper limit
     * @return true if all the trip's points are in the range, false otherwise
     */
    private boolean isEntireTripInRange(Trip trip, double x1, double y1, double x2, double y2){
        for(TripPoint point : trip.getTripPoints().inOrder()) {
            Point2D.Double coords = point.getLatitudeLongitude();
            if (coords.getX() < x1 || coords.getX() > x2 || coords.getY() < y1 || coords.getY() > y2)
                return false;
        }
        return true;
    }
}
