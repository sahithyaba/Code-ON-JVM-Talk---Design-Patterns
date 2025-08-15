// FacadePattern.java
class CPU {
    void start() { System.out.println("CPU starting..."); }
}

class Memory {
    void load() { System.out.println("Memory loading..."); }
}

class Disk {
    void read() { System.out.println("Disk reading..."); }
}

class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private Disk disk;

    public ComputerFacade() {
        cpu = new CPU();
        memory = new Memory();
        disk = new Disk();
    }

    public void startComputer() {
        cpu.start();
        memory.load();
        disk.read();
        System.out.println("Computer started!");
    }
}

public class FacadePattern {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.startComputer();
    }
}
