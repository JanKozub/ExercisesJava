package katas.business_rules;

import katas.business_rules.rules.GenerateDuplicatePackageForProductsRule;
import katas.business_rules.rules.GeneratePackageForPhysicalProductRule;
import katas.business_rules.rules.ValidatePaymentRule;

import java.util.List;

public class Rules {

    public Rules() {
    }

    public List<Rule> getRules() {
        return List.of(
                new ValidatePaymentRule(),
                new GenerateDuplicatePackageForProductsRule(PhysicalType.BOOK),
                new GeneratePackageForPhysicalProductRule()
        );
    }
}
