// AdapterPattern.java
interface NewPaymentGateway {
    void pay(String account, double amount);
}

class OldPaymentSystem {
    public void makePayment(String acc, double amt) {
        System.out.println("Old Payment: " + amt + " to " + acc);
    }
}

class PaymentAdapter implements NewPaymentGateway {
    private OldPaymentSystem oldSystem;
    public PaymentAdapter(OldPaymentSystem oldSystem) { this.oldSystem = oldSystem; }
    public void pay(String account, double amount) {
        oldSystem.makePayment(account, amount);
    }
}

public class AdapterPattern {
    public static void main(String[] args) {
        OldPaymentSystem oldSystem = new OldPaymentSystem();
        NewPaymentGateway adapter = new PaymentAdapter(oldSystem);
        adapter.pay("ACC123", 200);
    }
}
