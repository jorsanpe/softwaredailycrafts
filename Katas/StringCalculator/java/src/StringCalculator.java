import java.util.Arrays;

public class StringCalculator {
    private String delimiter_expr = null;

    public StringCalculator(String delimiter) {
        delimiter_expr = "[" + delimiter + "\n]";
    }

    public int add(String s) {
        return sum(s);
    }

    private int sum(String s) {
        String.
        return Arrays.asList(s.split(delimiter_expr)).stream()
                .filter(t -> !t.isEmpty())
                .map(Integer::parseInt)
                .reduce(0, (acc, e) -> acc + e);
    }
}
