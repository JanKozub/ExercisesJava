package katas.business_rules;

public class OrderProcessor {
    private final Rules rules = new Rules();

    public void process(Order order, Payment payment) throws PaymentException {
        ProcessingContext context = new ProcessingContext(order, payment, null);

        for (Rule rule : rules.getRules()) {
            rule.process(context);
            if (context.isStopProcessing())
                break;
        }


    }
}
