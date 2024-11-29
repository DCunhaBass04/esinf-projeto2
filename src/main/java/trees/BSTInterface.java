package trees;

import java.util.List;
import java.util.Map;

public interface BSTInterface<E> {
    public void insert(E element);
    public void remove(E element);
    public int height();
    public E smallestElement();
    public Iterable<E> inOrder();
    public Iterable<E> preOrder();
    public Iterable<E> postOrder();
    public boolean isEmpty();
    public int size();
    public Map<Integer, List<E>> nodesByLevel();

}
