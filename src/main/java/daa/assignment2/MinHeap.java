package daa.assignment2;

import java.util.Arrays;
import java.util.NoSuchElementException;

public final class MinHeap {
    private int[] data = new int[10];
    private int size;
    private final Metrics metrics;
    public MinHeap(Metrics metrics) { this.metrics = metrics; }
    public MinHeap() { this(new Metrics()); }
    public int size() { return size; }
    public void insert(int value) {
        if (size == data.length) { data = Arrays.copyOf(data, data.length * 2); metrics.movements += size; }
        int i = size++;
        while (i > 0) {
            int parent = (i - 1) / 2;
            metrics.comparisons++;
            if (data[parent] <= value) break;
            data[i] = data[parent];
            metrics.movements++;
            i = parent;
        }
        data[i] = value;
        metrics.movements++;
    }
    public int peekMin() { if (size == 0) throw new NoSuchElementException(); return data[0]; }
    public int extractMin() {
        if (size == 0) throw new NoSuchElementException();
        int result = data[0];
        int value = data[--size];
        if (size == 0) return result;
        int i = 0;
        while (2 * i + 1 < size) {
            int child = 2 * i + 1;
            if (child + 1 < size) {
                metrics.comparisons++;
                if (data[child + 1] < data[child]) child++;
            }
            metrics.comparisons++;
            if (value <= data[child]) break;
            data[i] = data[child];
            metrics.movements++;
            i = child;
        }
        data[i] = value;
        metrics.movements++;
        return result;
    }
    public boolean validHeap() {
        for (int i = 1; i < size; i++) if (data[(i - 1) / 2] > data[i]) return false;
        return true;
    }
}
