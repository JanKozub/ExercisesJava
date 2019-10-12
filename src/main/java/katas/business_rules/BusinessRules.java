package katas.business_rules;

public class BusinessRules {
    public static void main(String[] args) {
        Customer customer = new Customer();
        Order order = new Order(customer, PhysicalType.BOOK);
        // order.addProduct()
        // order.setShipmentMethod
        // ... etc

        Payment payment = new Payment(0);
        // payment.setType("VISA");
        // payment.setAmount(123.45);

        OrderProcessor processor = new OrderProcessor();
        try {
            processor.process(order, payment);
        } catch (PaymentException ex) {
            //SHOW ERROR
        }

    }
}
