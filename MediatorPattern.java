// MediatorPattern.java
// A chatroom mediator routes messages between users.

import java.util.*;

interface ChatMediator {
    void register(User user);
    void send(String from, String to, String message); // to=null => broadcast
}

class SimpleChatMediator implements ChatMediator {
    private final Map<String, User> users = new HashMap<>();
    public void register(User user) { users.put(user.getName(), user); user.setMediator(this); }
    public void send(String from, String to, String message) {
        if (to == null) { // broadcast
            for (User u : users.values()) {
                if (!u.getName().equals(from)) u.receive(from, message);
            }
        } else {
            User receiver = users.get(to);
            if (receiver != null) receiver.receive(from, message);
        }
    }
}

class User {
    private final String name;
    private ChatMediator mediator;
    public User(String name) { this.name = name; }
    public String getName() { return name; }
    public void setMediator(ChatMediator m) { this.mediator = m; }
    public void send(String to, String msg) {
        mediator.send(name, to, msg);
    }
    public void receive(String from, String msg) {
        System.out.println(from + " -> " + name + ": " + msg);
    }
}

public class MediatorPattern {
    public static void main(String[] args) {
        SimpleChatMediator room = new SimpleChatMediator();
        User alice = new User("Alice");
        User bob = new User("Bob");
        User carol = new User("Carol");

        room.register(alice);
        room.register(bob);
        room.register(carol);

        alice.send(null, "Hello everyone!"); // broadcast
        bob.send("Alice", "Hi Alice");       // direct
    }
}
