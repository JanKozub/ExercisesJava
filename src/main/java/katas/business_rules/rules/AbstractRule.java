package katas.business_rules.rules;

import katas.business_rules.ProcessingContext;
import katas.business_rules.Rule;

public class AbstractRule implements Rule {

    private AbstractRule nextRule;
    private AbstractRule nextAlternateRule;

    public AbstractRule(AbstractRule nextRule, AbstractRule nextAlternateRule) {
        this.nextRule = nextRule;
        this.nextAlternateRule = nextAlternateRule;
    }

    @Override
    public void process(ProcessingContext context) {
        // do my stuff

        if (nextRule != null)
            nextRule.process(context);
        else
            nextAlternateRule.process(context);

        // finalize
    }
}
