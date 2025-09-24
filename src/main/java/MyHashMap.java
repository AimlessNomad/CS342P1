import java.util.ArrayList;
import java.util.Iterator;

public class MyHashMap<T> implements Iterable<T> {

    private final ArrayList<GenericQueue<T>> map;
    private final ArrayList<Integer> hashes;
    private int sz;

    public MyHashMap(String key, T value) {
        map = new ArrayList<>(10);
        hashes = new ArrayList<>();
        this.sz = 0;
        this.put(key, value);
    }

    public void put(String key, T value) {
        int hash = key.hashCode();
        if(hashes.contains(hash)) {
            this.replace(key, value);
        } else {
            hashes.add(hash);
            GenericQueue<T> queue = map.get(hash % 10);
            if(queue == null) {
                map.set(hash % 10, new GenericQueue<>(value));
            } else {
                queue.add(value);
            }
            sz++;
        }
    }

    public void replace(String key, T value) {
        int hash = key.hashCode();
        GenericQueue<T> queue = map.get(hash % 10);
        GenericList.Node<T> curr = queue.getHead();
        for(int i = 0; i < queue.getLength(); i++) {
            if(curr.code == hash) {
                curr.data = value;
                sz++;
                break;
            }
            curr = curr.next;
        }
    }

    public boolean contains(String key) {
        return this.get(key) != null;
    }

    public T get(String key) {
        int hash = key.hashCode();
        GenericQueue<T> queue = map.get(hash % 10);
        GenericList.Node<T> curr = queue.getHead();

        for(int i = 0; i < queue.getLength(); i++) {
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
