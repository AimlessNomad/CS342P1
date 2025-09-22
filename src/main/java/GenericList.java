import java.util.ArrayList;
import java.util.Iterator;

public abstract class GenericList<T> implements Iterable<T> {

    private Node<T> head;
    private int length;

    public static class Node<T> {
        T data;
        int code;
        Node<T> next;
    }

    public void print() {
        if(length == 0) {
            System.out.println("Empty List");
        }
        else {
            Node<T> curr = head;
            for(int i = 0; i < length; i++) {
                System.out.println(curr.data);
                curr = curr.next;
            }
        }
    }

    public ArrayList<T> dumpList() {
        return new ArrayList<T>();
    }

    public T get(int index) {
        if(index >= length) return null;
        Node<T> curr = head;
        for(int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.data;
    }

    /*
    Replace the element at specified position in the list
    with the specified element and return the element previously at the specified position.
    Return null if index is out of bounds
     */
    public T set(int index, T element) {
        if(index >= length) return null;
        Node<T> curr = head;
        for(int i = 0; i < index; i++) {
            curr = curr.next;
        }
        T temp = curr.data;
        curr.data = element;
        return temp;
    }

    public Iterator<T> descendingIterator() {
        return new ReverseGLLIterator<T>();
    }

    public Iterator<T> Iterator() {
        return new GLLIterator<T>();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public abstract void add(T data);
    public abstract T delete();
}
