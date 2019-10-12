package katas.business_rules.rules;

import katas.business_rules.Order;
import katas.business_rules.PhysicalType;
import katas.business_rules.ProcessingContext;
import katas.business_rules.Rule;

import java.util.EnumSet;
import java.util.List;

public class GenerateDuplicatePackageForProductsRule implements Rule {

    private final EnumSet<PhysicalType> productTypes;

    public GenerateDuplicatePackageForProductsRule(PhysicalType... productTypes) {
        this.productTypes = EnumSet.noneOf(PhysicalType.class);
        this.productTypes.addAll(List.of(productTypes));
    }

    @Override
    public void process(ProcessingContext context) {
        Order order = context.getOrder();
        if (order.isPhysical && productTypes.contains(order.getPhysicalType())) {
            //GENERATE
        }
    }
}
