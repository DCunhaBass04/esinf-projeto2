package trees;

import java.awt.geom.Point2D;

public class KDNode<E> extends Node<E> {
    private Point2D.Double coords;
    public KDNode(Point2D.Double coords, E element, Node<E> left, Node<E> right) {
        super(element, left, right);
        this.coords = coords;
    }
    public Point2D.Double getCoords(){return coords;}
    public void setCoords(Point2D.Double coords){this.coords = coords;}
    @Override
    public KDNode<E> getLeft(){return (KDNode<E>) super.getLeft();}
    @Override
    public KDNode<E> getRight(){return (KDNode<E>) super.getRight();}
    public void setObject(KDNode<E> node){
        super.setLeft(node.getLeft());
        super.setRight(node.getRight());
        super.setHeight(node.getHeight());
        super.setElement(node.getElement());
        setCoords(node.getCoords());
    }
}
