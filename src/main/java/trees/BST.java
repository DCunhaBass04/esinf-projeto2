package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class BST<E extends Comparable<E>> implements BSTInterface<E> {

    protected Node<E> root;
    protected int size;

    public BST(E rootElement) {
        root = new Node<>(rootElement, null, null);
        size = 1;
    }

    @Override
    public void insert(E element) {
        insert(element, root);
        size++;
    }

    private void insert(E element, Node<E> node) {
        if (element.compareTo(node.getElement()) < 0) {
            if (node.getLeft() == null) {
                Node<E> newNode = new Node<>(element, null, null);
                node.setLeft(newNode);
            } else {
                insert(element, node.getLeft());
            }
        } else if (element.compareTo(node.getElement()) > 0) {
            if (node.getRight() == null) {
                Node<E> newNode = new Node<>(element, null, null);
                node.setRight(newNode);
            } else {
                insert(element, node.getRight());
            }
        }
    }

    @Override
    public void remove(E element) {
        remove(element, root);
        size--;
    }

    private Node<E> remove(E element, Node<E> node) {
        if (node == null) {
            return null;    //throw new IllegalArgumentException("Element does not exist");
        }
        if (element.compareTo(node.getElement())==0) {
            // node is the Node to be removed
            if (node.getLeft() == null && node.getRight() == null) { //node is a leaf (has no childs)
                return null;
            }
            if (node.getLeft() == null) {   //has only right child
                return node.getRight();
            }
            if (node.getRight() == null) {  //has only left child
                return node.getLeft();
            }
            E min = smallestElement(node.getRight());
            node.setElement(min);
            node.setRight(remove(min, node.getRight()));
        }
        else if (element.compareTo(node.getElement()) < 0)
            node.setLeft( remove(element, node.getLeft()) );
        else
            node.setRight( remove(element, node.getRight()) );
        return node;
    }

    @Override
    public int height() {
        return height(root);
    }
    private int height(Node<E> node) {
        int height = 0;
        for (Node<E> p : children(node)) {
            height = Math.max(height, 1 + height(p));
        }
        return height;
    }

    private Iterable<Node<E>> children(Node<E> node) {
        List<Node<E>> children = new ArrayList<>(2);
        if (node.getLeft() != null) {
            children.add(node.getLeft());
        }
        if (node.getRight() != null) {
            children.add(node.getRight());
        }
        return children;
    }

    @Override
    public E smallestElement() {
        return smallestElement(root);
    }

    private E smallestElement(Node<E> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getElement();
    }

    @Override
    public Iterable<E> inOrder() {
        List<E> elements = new ArrayList<>();
        return inOrder(root, elements);
    }

    private List<E> inOrder(Node<E> node, List<E> elements) {
        if (node != null) {
            inOrder(node.getLeft(), elements);
            elements.add(node.getElement());
            inOrder(node.getRight(), elements);
        }
        return elements;
    }

    @Override
    public Iterable<E> preOrder() {
        List<E> elements = new ArrayList<>();
        if (!isEmpty()) {
            preorderSubtree(root, elements);
        }
        return elements;
    }

    private void preorderSubtree(Node<E> node, List<E> elements) {
        elements.add(node.getElement());
        for (Node<E> currentNode : children(node)) {
            preorderSubtree(currentNode, elements);
        }
    }

    @Override
    public Iterable<E> postOrder() {
        List<E> elements = new ArrayList<>();
        if (!isEmpty()) {
            postorderSubtree(root, elements);
        }
        return elements;
    }

    private void postorderSubtree(Node<E> node, List<E> elements) {
        for (Node<E> currentNode : children(node)) {
            preorderSubtree(currentNode, elements);
        }
        elements.add(node.getElement());
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public E getRoot() {
        return root.getElement();
    }

    public void setRoot(E newRoot) {
        this.root.setElement(newRoot);
    }

    @Override
    public Map<Integer, List<E>> nodesByLevel() {
        return null;
    }

}
