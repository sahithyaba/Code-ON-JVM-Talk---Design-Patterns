// ObserverPattern.java
import java.util.*;

interface Observer {
    void update(String event);
}

class Subject {
    private final List<Observer> observers = new ArrayList<>();
    private String state;

    public void attach(Observer o) { observers.add(o); }
    public void detach(Observer o) { observers.remove(o); }

    public void setState(String s) {
        this.state = s;
        notifyAllObservers();
    }

    private void notifyAllObservers() {
        for (Observer o : observers) o.update(state);
    }
}

class LoggerObserver implements Observer {
    public void update(String event) {
        System.out.println("[Logger] event: " + event);
    }
}

class UIObserver implements Observer {
    public void update(String event) {
        System.out.println("[UI] refresh after: " + event);
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        Subject subject = new Subject();
        LoggerObserver logger = new LoggerObserver();
        UIObserver ui = new UIObserver();

        subject.attach(logger);
        subject.attach(ui);

        subject.setState("DataLoaded");
        subject.setState("UserUpdated");

        subject.detach(ui);
        subject.setState("AnotherEvent");
    }
}
