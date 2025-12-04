import java.util.ArrayList;

// VendingMachineState interface
interface VendingMachineState {
    void handleRequest();
}

// ReadyState class
class ReadyState implements VendingMachineState {
    @Override
    public void handleRequest() {
        System.out.println("Ready state: Please select a product.");
    }
}

// ProductSelectedState class
class ProductSelectedState implements VendingMachineState {
    @Override
    public void handleRequest() {
        System.out.println("Product selected state: Processing payment.");
    }
}

// PaymentPendingState class
class PaymentPendingState implements VendingMachineState {
    @Override
    public void handleRequest() {
        System.out.println("Payment pending state: Dispensing product.");
    }
}

// OutOfStockState class
class OutOfStockState implements VendingMachineState {
    @Override
    public void handleRequest() {
        System.out.println("Out of stock state: Product unavailable. Please select another product.");
    }
}

// VendingMachine class
class VendingMachine {
    private VendingMachineState currentState;

    public VendingMachine() {
        this.currentState = new ReadyState();
    }

    public void setState(VendingMachineState state) {
        this.currentState = state;
    }

    public void handleRequest() {
        currentState.handleRequest();
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.handleRequest();
        vendingMachine.setState(new ProductSelectedState());
        vendingMachine.handleRequest();
        vendingMachine.setState(new PaymentPendingState());
        vendingMachine.handleRequest();
        vendingMachine.setState(new OutOfStockState());
        vendingMachine.handleRequest();
    }
}