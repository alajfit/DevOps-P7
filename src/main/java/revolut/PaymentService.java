package revolut;

public class PaymentService {
    private String serviceName;

    public PaymentService(String name){
        this.serviceName = name;
    }

    public String getType() {
        return serviceName;
    }

    public void sendPayment(Person sender, Person receiver, Double payment) {
        if (sender.getAccount("EUR").getBalance() > payment && payment > 0) {
            sender.getAccount("EUR").removeFunds(payment);
            receiver.getAccount("EUR").addFunds(payment);
        } else {
            System.out.println("Failed send balance too low");
        }
    }

    public void splitBill(Person firstHalf, Person secondHalf, Double billAmount) {
        double splitAmount = billAmount / 2;
        if (
            firstHalf.getAccount("EUR").getBalance() > splitAmount &&
            secondHalf.getAccount("EUR").getBalance() > splitAmount
        ) {
            firstHalf.getAccount("EUR").removeFunds(splitAmount);
            secondHalf.getAccount("EUR").removeFunds(splitAmount);
        } else {
            System.out.println("Insufficient Funds");
        }
    }
}
