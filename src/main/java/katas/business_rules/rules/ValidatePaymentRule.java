package katas.business_rules.rules;

import katas.business_rules.*;

public class ValidatePaymentRule implements Rule {
    @Override
    public void process(ProcessingContext context) {
        Order order = context.getOrder();
        Payment payment = context.getPayment();
        if (payment.getAmount() != order.getCost()) {
            throw new PaymentException("Payment error: Invalid amount");
        }
    }
}
