import java.util.ArrayList;
import java.util.Iterator;

public class MyHashMap<T> implements Iterable<T> {

    private final ArrayList<GenericQueue<T>> map;
    private int sz;

    public MyHashMap(String key, T value) {
        map = new ArrayList<>(10);
        while(map.size() < 10) map.add(null);
        this.sz = 0;
        this.put(key, value);
    }

    public void put(String key, T value) {
        int hash = key.hashCode();
        GenericQueue<T> queue = map.get(hash % 10);
        if(queue == null) {
            map.set(hash % 10, new GenericQueue<>(value, hash));
        } else {
            GenericList.Node<T> curr = queue.getHead();
            for(int i = 0; i < queue.getLength(); i++) {
                if (curr.code == hash) {
                    curr.data = value;
                    return;
                }
                curr = curr.next;
            }
            queue.add(value, hash);
        }
        sz++;
    }

    public T replace(String key, T value) {
        int hash = key.hashCode();
        GenericQueue<T> queue = map.get(Math.abs(hash % 10));
        if(queue == null) return null;

        GenericList.Node<T> curr = queue.getHead();
        for(int i = 0; i < queue.getLength(); i++) {
            if(curr.code == hash) {
                T temp = curr.data;
                curr.data = value;
                return temp;
            }
            curr = curr.next;
        }
        return null;
    }

    public boolean contains(String key) {
        return this.get(key) != null;
    }

    public T get(String key) {
        int hash = key.hashCode();
        GenericQueue<T> queue = map.get(Math.abs(hash % 10));
        if(queue == null) return null;
        GenericList.Node<T> curr = queue.getHead();

        while(curr != null) {
            if(curr.code == hash) {
                return curr.data;
            }
            curr = curr.next;
        }
        return null;
    }

    public int size() {
        return sz;
    }

    public boolean isEmpty() {
        return sz == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
