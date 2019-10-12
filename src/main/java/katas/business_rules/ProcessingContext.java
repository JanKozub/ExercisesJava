package katas.business_rules;

import java.util.ArrayList;
import java.util.List;

public class ProcessingContext {
    private final Order order;
    private final Payment payment;
    private final Package aPackage;
    private final List<String> warnings = new ArrayList<>();
    private volatile boolean stopProcessing;

    public ProcessingContext(Order order, Payment payment, Package aPackage) {
        this.order = order;
        this.payment = payment;
        this.aPackage = aPackage;
    }

    public Order getOrder() {
        return order;
    }

    public Payment getPayment() {
        return payment;
    }

    public void addWarning(String message) {
        warnings.add(message);
    }

    public void setStopProcessing() {
        this.stopProcessing = true;
    }

    public boolean isStopProcessing() {
        return stopProcessing;
    }
}
