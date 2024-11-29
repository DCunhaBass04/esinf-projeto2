package trees;

import domain.Identifiable;
import domain.Trip;

import java.util.ArrayList;
import java.util.List;

public class AVL<E extends Comparable<E>> extends BST<E> {

    public AVL(E rootElement) {
        super(rootElement);
        size = 1;
    }

    @Override
    public void insert(E element) {
        root = insert(element, root);
        size++;
    }

    private Node<E> insert(E element, Node<E> node) {
        if (node == null) {
            return new Node<>(element);
        }
        if (element.compareTo(node.getElement()) < 0) {
            node.setLeft(insert(element, node.getLeft()));
        } else if (element.compareTo(node.getElement()) > 0) {
            node.setRight(insert(element, node.getRight()));
        } else {
            return node;
        }
        updateHeight(node);
        return applyRotation(node);
    }

    @Override
    public void remove(E element) {
        root = remove(element, root);
        size--;
    }

    private Node<E> remove(E element, Node<E> node) {
        if (node == null) {
            return null;
        }
        if (element.compareTo(node.getElement()) < 0) {
            node.setLeft(remove(element, node.getLeft()));
        } else if (element.compareTo(node.getElement()) > 0) {
            node.setRight(remove(element, node.getRight()));
        } else {
            // One Child or Leaf Node (no children)
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            // Two Children
            node.setElement(getMax(node.getLeft()));
            node.setLeft(remove(node.getElement(), node.getLeft()));
        }
        updateHeight(node);
        return applyRotation(node);
    }

    public E getMax() {
        if (isEmpty()) {
            return null;
        }
        return getMax(root);
    }

    private E getMax(Node<E> node) {
        if (node.getRight() != null) {
            return getMax(node.getRight());
        }
        return node.getElement();
    }

    private Node<E> applyRotation(Node<E> node) {
        int balance = balance(node);
        if (balance > 1) {
            if (balance(node.getLeft()) < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            if (balance(node.getRight()) > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node<E> rotateRight(Node<E> node) {
        Node<E> leftNode = node.getLeft();
        Node<E> centerNode = leftNode.getRight();
        leftNode.setRight(node);
        node.setLeft(centerNode);
        updateHeight(node);
        updateHeight(leftNode);
        return leftNode;
    }

    private Node<E> rotateLeft(Node<E> node) {
        Node<E> rightNode = node.getRight();
        Node<E> centerNode = rightNode.getLeft();
        rightNode.setLeft(node);
        node.setRight(centerNode);
        updateHeight(node);
        updateHeight(rightNode);
        return rightNode;
    }

    private void updateHeight(Node<E> node) {
        int maxHeight = Math.max(height(node.getLeft()), height(node.getRight()));
        node.setHeight(maxHeight + 1);
    }

    public E exactFind(E object){
        return exactFind(root, object);
    }

    private E exactFind(Node<E> node, E object){
        if(node == null) return null;
        if(node.getElement().equals(object)) return node.getElement();
        else if(node.getElement().compareTo(object) < 0) return exactFind(node.getRight(), object);
        else return exactFind(node.getLeft(), object);
    }

    private int balance(Node<E> node) {
        return node != null ? height(node.getLeft()) - height(node.getRight()) : 0;
    }

    private int height(Node<E> node) {
        return node != null ? node.getHeight() : 0;
    }

}
