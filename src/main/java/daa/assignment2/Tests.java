package daa.assignment2;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Random;

public final class Tests {
    private static int checks;
    private Tests() { }
    public static String run() {
        checks = 0;
        testSequence(false);
        testSequence(true);
        testHeap();
        return "PASS: " + checks + " checks";
    }
    private static void testSequence(boolean linked) {
        IntSequence actual = linked ? new LinkedList() : new DynamicArray();
        ArrayList<Integer> expected = new ArrayList<>();
        eq(actual.size(), 0);
        eq(actual.contains(7), false);
        fails(IndexOutOfBoundsException.class, () -> actual.get(0));
        fails(IndexOutOfBoundsException.class, () -> actual.remove(0));
        fails(IndexOutOfBoundsException.class, () -> actual.add(-1, 5));
        actual.add(4); expected.add(4); compare(actual, expected);
        actual.add(0, 4); expected.add(0, 4); compare(actual, expected);
        actual.add(actual.size(), -3); expected.add(-3); compare(actual, expected);
        eq(actual.remove(1), expected.remove(1)); compare(actual, expected);
        fails(IndexOutOfBoundsException.class, () -> actual.add(actual.size() + 1, 0));
        fails(IndexOutOfBoundsException.class, () -> actual.get(-1));
        fails(IndexOutOfBoundsException.class, () -> actual.remove(actual.size()));
        Random r = new Random(42);
        for (int k = 0; k < 5_000; k++) {
            int op = r.nextInt(5);
            if (op == 0 || expected.isEmpty()) {
                int value = r.nextInt(30); actual.add(value); expected.add(value);
            } else if (op == 1) {
                int index = r.nextInt(expected.size() + 1), value = r.nextInt(30);
                actual.add(index, value); expected.add(index, value);
            } else if (op == 2) {
                int index = r.nextInt(expected.size()); eq(actual.remove(index), expected.remove(index));
            } else if (op == 3) {
                int index = r.nextInt(expected.size()); eq(actual.get(index), expected.get(index));
            } else {
                int value = r.nextInt(30); eq(actual.contains(value), expected.contains(value));
            }
            if (k % 100 == 0) compare(actual, expected);
        }
        while (!expected.isEmpty()) eq(actual.remove(0), expected.remove(0));
        eq(actual.size(), 0);
        for (int i = 0; i < 100_000; i++) actual.add(i);
        eq(actual.size(), 100_000); eq(actual.get(99_999), 99_999);
    }
    private static void compare(IntSequence actual, ArrayList<Integer> expected) {
        eq(actual.size(), expected.size());
        for (int i = 0; i < expected.size(); i++) eq(actual.get(i), expected.get(i));
    }
    private static void testHeap() {
        MinHeap heap = new MinHeap(); PriorityQueue<Integer> expected = new PriorityQueue<>();
        fails(NoSuchElementException.class, heap::peekMin);
        fails(NoSuchElementException.class, heap::extractMin);
        Random r = new Random(42);
        for (int i = 0; i < 100_000; i++) {
            if (expected.isEmpty() || r.nextBoolean()) {
                int value = r.nextInt(1_000);
                heap.insert(value); expected.add(value);
            } else eq(heap.extractMin(), expected.remove());
            eq(heap.validHeap(), true);
            eq(heap.size(), expected.size());
            if (!expected.isEmpty()) eq(heap.peekMin(), expected.peek());
        }
        while (!expected.isEmpty()) { eq(heap.extractMin(), expected.remove()); eq(heap.validHeap(), true); }
        eq(heap.size(), 0);
    }
    private static void eq(Object actual, Object expected) {
        checks++;
        if (!actual.equals(expected)) throw new AssertionError("Expected " + expected + ", got " + actual);
    }
    private static void fails(Class<? extends Throwable> kind, Runnable action) {
        checks++;
        try { action.run(); } catch (Throwable e) { if (kind.isInstance(e)) return; throw new AssertionError(e); }
        throw new AssertionError("Expected " + kind.getSimpleName());
    }
}
