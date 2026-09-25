package daa.assignment2;

public final class LinkedList implements IntSequence {
    private static final class Node {
        int value;
        Node next;
        Node prev;
        Node(int value) { this.value = value; }
    }
    private Node head;
    private Node tail;
    private int size;
    private final Metrics metrics;

    public LinkedList(Metrics metrics) { this.metrics = metrics; }
    public LinkedList() { this(new Metrics()); }
    public int size() { return size; }
    public void add(int value) {
        Node node = new Node(value);
        if (tail == null) head = node;
        else { tail.next = node; node.prev = tail; }
        tail = node;
        size++;
        metrics.movements++;
    }
    public void add(int index, int value) {
        checkPosition(index);
        if (index == size) { add(value); return; }
        Node next = nodeAt(index);
        Node node = new Node(value);
        node.next = next;
        node.prev = next.prev;
        if (next.prev == null) head = node;
        else next.prev.next = node;
        next.prev = node;
        size++;
        metrics.movements++;
    }
    public int remove(int index) {
        Node node = nodeAt(index);
        if (node.prev == null) head = node.next;
        else node.prev.next = node.next;
        if (node.next == null) tail = node.prev;
        else node.next.prev = node.prev;
        size--;
        metrics.movements++;
        return node.value;
    }
    public int get(int index) { return nodeAt(index).value; }
    public boolean contains(int value) {
        for (Node p = head; p != null; p = p.next) {
            metrics.accesses++;
            metrics.comparisons++;
            if (p.value == value) return true;
        }
        return false;
    }
    private Node nodeAt(int index) {
        checkElement(index);
        Node p;
        if (index < size / 2) {
            p = head;
            metrics.accesses++;
            for (int i = 0; i < index; i++) { p = p.next; metrics.accesses++; }
        } else {
            p = tail;
            metrics.accesses++;
            for (int i = size - 1; i > index; i--) { p = p.prev; metrics.accesses++; }
        }
        return p;
    }
    private void checkElement(int i) { if (i < 0 || i >= size) throw new IndexOutOfBoundsException(i); }
    private void checkPosition(int i) { if (i < 0 || i > size) throw new IndexOutOfBoundsException(i); }
}
