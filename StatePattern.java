// StatePattern.java
// Object behavior changes based on internal state (vending machine demo).

interface MachineState {
    void insertCoin(VendingMachine vm);
    void select(VendingMachine vm);
    void dispense(VendingMachine vm);
}

class NoCoinState implements MachineState {
    public void insertCoin(VendingMachine vm) {
        System.out.println("Coin inserted.");
        vm.setState(new HasCoinState());
    }
    public void select(VendingMachine vm) { System.out.println("Insert coin first."); }
    public void dispense(VendingMachine vm) { System.out.println("No coin - cannot dispense."); }
}

class HasCoinState implements MachineState {
    public void insertCoin(VendingMachine vm) { System.out.println("Coin already inserted."); }
    public void select(VendingMachine vm) {
        System.out.println("Item selected.");
        vm.setState(new DispensingState());
    }
    public void dispense(VendingMachine vm) { System.out.println("Select item first."); }
}

class DispensingState implements MachineState {
    public void insertCoin(VendingMachine vm) { System.out.println("Please wait - dispensing."); }
    public void select(VendingMachine vm) { System.out.println("Already selected."); }
    public void dispense(VendingMachine vm) {
        System.out.println("Dispensing item... done.");
        vm.setState(new NoCoinState());
    }
}

class VendingMachine {
    private MachineState state = new NoCoinState();
    public void setState(MachineState s) { this.state = s; }
    public void insertCoin() { state.insertCoin(this); }
    public void select() { state.select(this); }
    public void dispense() { state.dispense(this); }
}

public class StatePattern {
    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();
        vm.select();      // Insert coin first.
        vm.insertCoin();  // Coin inserted.
        vm.insertCoin();  // Coin already inserted.
        vm.select();      // Item selected.
        vm.dispense();    // Dispensing item... done.
        vm.dispense();    // No coin - cannot dispense.
    }
}
