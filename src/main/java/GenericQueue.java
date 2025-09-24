public class GenericQueue<T> extends GenericList<T>{

    private Node<T> tail;

    public GenericQueue(T val) {
        Node<T> head = new Node<>(val);
        this.setHead(head);
        this.setLength(1);
        tail = head;
    }

    @Override
    public void add(T data) {
        Node<T> elem = new Node<>(data);
        if(this.getLength() == 0) {
            this.setHead(elem);
        } else {
            tail.next = elem;
        }

        tail = elem;
        this.setLength(this.getLength() + 1);
    }

    public void add(T data, int code) {
        Node<T> elem = new Node<>(data, code);
        if(this.getLength() == 0) {
            this.setHead(elem);
        } else {
            tail.next = elem;
        }

        tail = elem;
        this.setLength(this.getLength() + 1);
    }

    public void enqueue(T data) {
        this.add(data);
    }

    @Override
    public T delete() {
        int len = this.getLength();
        if(len == 0) return null;
        else if(len == 1) {
            T temp = tail.data;
            tail = null;
            this.setHead(null);
            this.setLength(0);
            return temp;
        }

        T temp = tail.data;
        this.setLength(len - 1);
        Node<T> curr = this.getHead();
        for(int i = 0; i < len - 2; i++) {
            curr = curr.next;
        }
        tail = curr;
        tail.next = null;
        if(len == 2) this.setHead(tail);
        return temp;
    }

    public T dequeue() {
        return this.delete();
    }

    public Node<T> getTail() {
        return tail;
    }
}
