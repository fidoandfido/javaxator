package com.javaxator.patterns.visitor;

public class EngineComponent extends AbstractComponent {

    @Override
    public String getName() {
        return "Engine component";
    }

    @Override
    public void acceptVisitor(ShipComponentVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void doAction() {
        System.out.println("Engine activating.");
    }
}
