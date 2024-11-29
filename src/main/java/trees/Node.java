package trees;

public class Node<E> {
    private E element;
    private int height = 0;
    private Node<E> left;
    private Node<E> right;

    public Node(E element, Node<E> left, Node<E> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    public Node(E element) {
        this.element = element;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
