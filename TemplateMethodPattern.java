// TemplateMethodPattern.java
// The algorithm skeleton lives in the base class; subclasses implement steps.

abstract class DataParser {
    // template method - final so subclasses keep the algorithm order
    public final void parseAndSave(String input) {
        String cleaned = read(input);
        Object parsed = parse(cleaned);
        save(parsed);
    }

    protected String read(String input) {
        // default: trim whitespace
        return input == null ? "" : input.trim();
    }

    protected abstract Object parse(String cleaned);
    protected void save(Object parsed) {
        // default save (could be overridden)
        System.out.println("Saved: " + parsed);
    }
}

class CSVParser extends DataParser {
    @Override
    protected Object parse(String cleaned) {
        String[] parts = cleaned.split(",");
        return java.util.Arrays.asList(parts);
    }
}

class UppercaseParser extends DataParser {
    @Override
    protected Object parse(String cleaned) {
        return cleaned.toUpperCase();
    }

    @Override
    protected void save(Object parsed) {
        System.out.println("Custom save -> " + parsed);
    }
}

public class TemplateMethodPattern {
    public static void main(String[] args) {
        DataParser csv = new CSVParser();
        csv.parseAndSave("a,b,c");

        DataParser up = new UppercaseParser();
        up.parseAndSave(" hello world ");
    }
}
