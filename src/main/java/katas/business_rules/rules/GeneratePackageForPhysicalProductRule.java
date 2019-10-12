package katas.business_rules.rules;

import katas.business_rules.Order;
import katas.business_rules.ProcessingContext;
import katas.business_rules.Rule;

public class GeneratePackageForPhysicalProductRule implements Rule {

    @Override
    public void process(ProcessingContext context) {
        Order order = context.getOrder();
        if (order.isPhysical) {
            //GENERATE PACKAGE
        }
    }
}
