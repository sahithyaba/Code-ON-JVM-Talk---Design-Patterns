// CommandPattern.java
// Commands encapsulate actions and support undo.

import java.util.*;

interface Command {
    void execute();
    void undo();
}

class Document {
    private final StringBuilder text = new StringBuilder();
    public void append(String s) { text.append(s); }
    public void deleteLast(int n) {
        int start = Math.max(0, text.length() - n);
        text.delete(start, text.length());
    }
    public String getText() { return text.toString(); }
}

class AppendCommand implements Command {
    private final Document doc;
    private final String text;
    public AppendCommand(Document doc, String text) { this.doc = doc; this.text = text; }
    public void execute() { doc.append(text); }
    public void undo() { doc.deleteLast(text.length()); }
}

class Invoker {
    private final Deque<Command> history = new ArrayDeque<>();
    public void run(Command c) {
        c.execute();
        history.push(c);
    }
    public void undo() {
        if (!history.isEmpty()) history.pop().undo();
    }
}

public class CommandPattern {
    public static void main(String[] args) {
        Document doc = new Document();
        Invoker inv = new Invoker();

        inv.run(new AppendCommand(doc, "Hello"));
        inv.run(new AppendCommand(doc, " World"));
        System.out.println("Doc: " + doc.getText()); // Hello World

        inv.undo();
        System.out.println("After undo: " + doc.getText()); // Hello

        inv.undo();
        System.out.println("After undo: " + doc.getText()); // (empty)
    }
}
