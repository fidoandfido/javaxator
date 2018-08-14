package com.javaxator.patterns.visitor;

public class SteeringComponent extends AbstractComponent {

    @Override
    public String getName() {
        return "Steering component";
    }

    @Override
    public void acceptVisitor(ShipComponentVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void doAction() {
        System.out.println("Turning craft");
    }
}
