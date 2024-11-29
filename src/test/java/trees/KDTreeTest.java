package trees;

import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The tests in this class use values and answers from the official lecture slides for trees
 * https://moodle.isep.ipp.pt/pluginfile.php/303546/mod_resource/content/17/ESINF04-Trees.pdf
 */
public class KDTreeTest {
    private final String POINT1 = "A", POINT2 = "B", POINT3 = "C", POINT4 = "D", POINT5 = "E", POINT6 = "F",
            POINT7 = "G", POINT8 = "H", POINT9 = "I", POINT10 = "J", POINT11 = "K";

    /**
     * Test if the findNearestNeighbor is correct
     */
    @Test void assertIfFindNearestNeighborIsCorrect(){
        KDTree<String> kdTree = new KDTree<>(POINT1, new Point2D.Double(3,9));
        kdTree.insert(POINT2, new Point2D.Double(1,7));
        kdTree.insert(POINT3, new Point2D.Double(7,9));
        kdTree.insert(POINT4, new Point2D.Double(2,1));
        kdTree.insert(POINT5, new Point2D.Double(9,6));
        kdTree.insert(POINT6, new Point2D.Double(5,9));
        kdTree.insert(POINT7, new Point2D.Double(1,4));
        kdTree.insert(POINT8, new Point2D.Double(7,3));
        kdTree.insert(POINT9, new Point2D.Double(11,6));
        kdTree.insert(POINT10, new Point2D.Double(6,1));
        kdTree.insert(POINT11, new Point2D.Double(8,2));
        String nearestNeighbor = kdTree.findNearestNeighbor(new Point2D.Double(4,5));
        assertEquals(nearestNeighbor, POINT7);
    }
    @Test void assertIfRangeSearchIsCorrect(){
        KDTree<String> kdTree = new KDTree<>(POINT1, new Point2D.Double(30,40));
        kdTree.insert(POINT2, new Point2D.Double(5,25));
        kdTree.insert(POINT3, new Point2D.Double(70,70));
        kdTree.insert(POINT4, new Point2D.Double(10,12));
        kdTree.insert(POINT5, new Point2D.Double(50,30));
        kdTree.insert(POINT6, new Point2D.Double(35,45));
        kdTree.insert(POINT7, new Point2D.Double(60,40));
        List<String> pointsInRange = kdTree.topEObjects(new Point2D.Double(32,27), new Point2D.Double(75, 50));
        Comparator<String> cmpString = CharSequence::compare;
        List<String> supposedPointsInRange = new ArrayList<>();
        supposedPointsInRange.add(POINT5);
        supposedPointsInRange.add(POINT6);
        supposedPointsInRange.add(POINT7);
        pointsInRange.sort(cmpString);
        supposedPointsInRange.sort(cmpString);
        assertEquals(pointsInRange, supposedPointsInRange);
    }
}
