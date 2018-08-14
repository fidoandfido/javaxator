package com.javaxator.patterns.visitor;

public class DriveComponent extends AbstractComponent {
    @Override
    public String getName() {
        return "Drive component";
    }

    @Override
    public void acceptVisitor(ShipComponentVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void doAction() {
        System.out.println("Accelerating craft");
    }
}

