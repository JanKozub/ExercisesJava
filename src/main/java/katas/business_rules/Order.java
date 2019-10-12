package katas.business_rules;

public class Order {
    public boolean isPhysical;
    private boolean physical;
    private OrderType type;
    private PhysicalType physicalType;

    public Order(Customer customer, PhysicalType physicalType) {

    }

    public Order(Customer customer, AbstractType abstractType) {

    }


    public double getCost() {
        return 0;
    }

    public PhysicalType getPhysicalType() {
        return physicalType;
    }

    public AbstractType getAbstractType() {
        return null;
    }
}
