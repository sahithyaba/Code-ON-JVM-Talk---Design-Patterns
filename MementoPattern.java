// MementoPattern.java
// Capture and restore state without exposing internals.

import java.util.*;

class Editor {
    private String content = "";

    public void type(String text) { content += text; }
    public String getContent() { return content; }

    public Memento save() { return new Memento(content); }
    public void restore(Memento m) { this.content = m.getState(); }

    // inner memento class (immutable)
    public static class Memento {
        private final String state;
        private Memento(String state) { this.state = state; }
        private String getState() { return state; }
    }
}

class Caretaker {
    private final Deque<Editor.Memento> history = new ArrayDeque<>();
    public void backup(Editor e) { history.push(e.save()); }
    public void undo(Editor e) {
        if (!history.isEmpty()) e.restore(history.pop());
        else System.out.println("Nothing to undo");
    }
}

public class MementoPattern {
    public static void main(String[] args) {
        Editor editor = new Editor();
        Caretaker caretaker = new Caretaker();

        editor.type("Hello ");
        caretaker.backup(editor);

        editor.type("World!");
        System.out.println("Current: " + editor.getContent()); // Hello World!

        caretaker.undo(editor);
        System.out.println("After undo: " + editor.getContent()); // Hello 
    }
}
