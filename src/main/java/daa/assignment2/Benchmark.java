package daa.assignment2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Random;

public final class Benchmark {
    private static final int[] SIZES = {100, 1_000, 10_000, 100_000};
    private static final int RUNS = 5;
    private static volatile long sink;
    private Benchmark() { }

    public static void run(Path output) throws IOException {
        StringBuilder csv = new StringBuilder("workload,structure,position,n,m,average_time_ns,average_accesses,average_movements,average_comparisons,theory\n");
        for (int n : SIZES) {
            int[] values = randomValues(n);
            int[] indices = randomIndices(n, 10_000);
            int[] queries = searchValues(values, 1_000);
            for (boolean linked : new boolean[]{false, true}) {
                String name = linked ? "LinkedList" : "DynamicArray";
                Result access = repeat(() -> {
                    Metrics m = new Metrics(); IntSequence s = sequence(linked, m);
                    fill(s, values);
                    m.accesses = m.movements = m.comparisons = 0;
                    long t = System.nanoTime(); long sum = 0;
                    for (int index : indices) sum += s.get(index);
                    long elapsed = System.nanoTime() - t; sink = sum;
                    return new Sample(elapsed, m);
                });
                append(csv, "random_access", name, "random", n, indices.length, access, linked ? "Theta(m*n)" : "Theta(m)");
                Result search = repeat(() -> {
                    Metrics m = new Metrics(); IntSequence s = sequence(linked, m);
                    fill(s, values);
                    m.accesses = m.movements = m.comparisons = 0;
                    long t = System.nanoTime(); int found = 0;
                    for (int query : queries) if (s.contains(query)) found++;
                    long elapsed = System.nanoTime() - t; sink = found;
                    return new Sample(elapsed, m);
                });
                append(csv, "search", name, "random", n, queries.length, search, "Theta(m*n) average");
                for (boolean middle : new boolean[]{false, true}) {
                    int index = middle ? n / 2 : 0;
                    String position = middle ? "middle" : "front";
                    Result insertion = repeat(() -> {
                        Metrics m = new Metrics(); IntSequence s = sequence(linked, m);
                        fill(s, values);
                        m.accesses = m.movements = m.comparisons = 0;
                        long t = System.nanoTime();
                        for (int k = 0; k < 1_000; k++) s.add(index, k);
                        return new Sample(System.nanoTime() - t, m);
                    });
                    append(csv, "insert", name, position, n, 1_000, insertion,
                            linked && !middle ? "Theta(m)" : "Theta(m*n)");
                    Result removal = repeat(() -> {
                        Metrics totals = new Metrics(); long elapsed = 0; long sum = 0;
                        int completed = 0;
                        int batch = middle ? Math.max(1, n - index) : n;
                        while (completed < 1_000) {
                            Metrics m = new Metrics(); IntSequence s = sequence(linked, m);
                            fill(s, values); // restoration is outside the timed section
                            m.accesses = m.movements = m.comparisons = 0;
                            int count = Math.min(batch, 1_000 - completed);
                            long t = System.nanoTime();
                            for (int k = 0; k < count; k++) sum += s.remove(index);
                            elapsed += System.nanoTime() - t;
                            totals.accesses += m.accesses;
                            totals.movements += m.movements;
                            totals.comparisons += m.comparisons;
                            completed += count;
                        }
                        sink = sum;
                        return new Sample(elapsed, totals);
                    });
                    append(csv, "remove", name, position, n, 1_000, removal,
                            linked && !middle ? "Theta(m)" : "Theta(m*n)");
                }
            }
            Result insertion = repeat(() -> {
                Metrics m = new Metrics(); MinHeap heap = new MinHeap(m);
                long t = System.nanoTime();
                for (int value : values) heap.insert(value);
                long elapsed = System.nanoTime() - t;
                if (!heap.validHeap()) throw new AssertionError("Heap property after insertion");
                return new Sample(elapsed, m);
            });
            append(csv, "heap_insert", "MinHeap", "na", n, n, insertion, "O(n*log n) worst");
            Result extraction = repeat(() -> {
                Metrics m = new Metrics(); MinHeap heap = new MinHeap(m);
                for (int value : values) heap.insert(value);
                m.accesses = m.movements = m.comparisons = 0;
                int previous = Integer.MIN_VALUE;
                long t = System.nanoTime();
                for (int k = 0; k < n; k++) {
                    int current = heap.extractMin();
                    if (current < previous) throw new AssertionError("Extraction order");
                    previous = current;
                }
                long elapsed = System.nanoTime() - t; sink = previous;
                return new Sample(elapsed, m);
            });
            append(csv, "heap_extract", "MinHeap", "na", n, n, extraction, "O(n*log n) worst");
            System.out.println("Completed n=" + n);
        }
        Files.createDirectories(output.toAbsolutePath().getParent());
        Files.writeString(output, csv.toString());
        System.out.println("Saved " + output);
    }

    private static int[] randomValues(int n) {
        Random r = new Random(42);
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = r.nextInt(2 * n + 1);
        return a;
    }
    private static int[] randomIndices(int n, int count) {
        Random r = new Random(43);
        int[] a = new int[count];
        for (int i = 0; i < count; i++) a[i] = r.nextInt(n);
        return a;
    }
    private static int[] searchValues(int[] values, int count) {
        Random r = new Random(44);
        int[] a = new int[count];
        for (int i = 0; i < count; i++) a[i] = i % 2 == 0 ? values[r.nextInt(values.length)] : -1 - i;
        return a;
    }
    private static IntSequence sequence(boolean linked, Metrics m) { return linked ? new LinkedList(m) : new DynamicArray(m); }
    private static void fill(IntSequence s, int[] values) { for (int v : values) s.add(v); }
    private interface Experiment { Sample run(); }
    private record Sample(long nanos, Metrics metrics) { }
    private record Result(double nanos, double accesses, double movements, double comparisons) { }
    private static Result repeat(Experiment experiment) {
        double t = 0, a = 0, v = 0, c = 0;
        for (int run = 0; run < RUNS; run++) {
            Sample sample = experiment.run();
            t += sample.nanos;
            a += sample.metrics.accesses;
            v += sample.metrics.movements;
            c += sample.metrics.comparisons;
        }
        return new Result(t / RUNS, a / RUNS, v / RUNS, c / RUNS);
    }
    private static void append(StringBuilder csv, String workload, String structure, String position,
                               int n, int count, Result r, String theory) {
        csv.append(String.format(Locale.ROOT, "%s,%s,%s,%d,%d,%.1f,%.1f,%.1f,%.1f,%s%n",
                workload, structure, position, n, count, r.nanos, r.accesses, r.movements, r.comparisons, theory));
    }
}
