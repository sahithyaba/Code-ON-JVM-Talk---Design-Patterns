// BridgePattern.java
interface Device {
    void turnOn();
    void turnOff();
}

class TV implements Device {
    public void turnOn() { System.out.println("TV on"); }
    public void turnOff() { System.out.println("TV off"); }
}

class Radio implements Device {
    public void turnOn() { System.out.println("Radio on"); }
    public void turnOff() { System.out.println("Radio off"); }
}

abstract class RemoteControl {
    protected Device device;
    public RemoteControl(Device device) { this.device = device; }
    public abstract void togglePower();
}

class BasicRemote extends RemoteControl {
    private boolean on = false;
    public BasicRemote(Device device) { super(device); }
    public void togglePower() {
        if (on) { device.turnOff(); } else { device.turnOn(); }
        on = !on;
    }
}

public class BridgePattern {
    public static void main(String[] args) {
        RemoteControl remote = new BasicRemote(new TV());
        remote.togglePower();
        remote.togglePower();
    }
}
