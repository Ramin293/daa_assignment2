package daa.assignment2;

import java.util.Arrays;

public final class DynamicArray implements IntSequence {
    private int[] data = new int[10];
    private int size;
    private final Metrics metrics;

    public DynamicArray(Metrics metrics) { this.metrics = metrics; }
    public DynamicArray() { this(new Metrics()); }
    public int size() { return size; }
    public void add(int value) { ensureCapacity(size + 1); data[size++] = value; metrics.movements++; }
    public void add(int index, int value) {
        checkPosition(index);
        ensureCapacity(size + 1);
        for (int i = size; i > index; i--) { data[i] = data[i - 1]; metrics.movements++; }
        data[index] = value;
        metrics.movements++;
        size++;
    }
    public int remove(int index) {
        checkElement(index);
        int removed = data[index];
        metrics.accesses++;
        for (int i = index; i < size - 1; i++) { data[i] = data[i + 1]; metrics.movements++; }
        size--;
        return removed;
    }
    public int get(int index) { checkElement(index); metrics.accesses++; return data[index]; }
    public boolean contains(int value) {
        for (int i = 0; i < size; i++) {
            metrics.accesses++;
            metrics.comparisons++;
            if (data[i] == value) return true;
        }
        return false;
    }
    private void ensureCapacity(int needed) {
        if (needed > data.length) {
            int oldSize = data.length;
            data = Arrays.copyOf(data, Math.max(needed, oldSize * 2));
            metrics.movements += size;
        }
    }
    private void checkElement(int i) { if (i < 0 || i >= size) throw new IndexOutOfBoundsException(i); }
    private void checkPosition(int i) { if (i < 0 || i > size) throw new IndexOutOfBoundsException(i); }
}
