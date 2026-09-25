package daa.assignment2;

import java.nio.file.Path;

public final class Main {
    private Main() { }
    public static void main(String[] args) throws Exception {
        String command = args.length == 0 ? "--help" : args[0];
        switch (command) {
            case "--test" -> System.out.println(Tests.run());
            case "--benchmark" -> Benchmark.run(Path.of(args.length > 1 ? args[1] : "results/results.csv"));
            case "--help" -> System.out.println("Usage: java -cp target/classes daa.assignment2.Main --test | --benchmark [output.csv]");
            default -> throw new IllegalArgumentException(command);
        }
    }
}
