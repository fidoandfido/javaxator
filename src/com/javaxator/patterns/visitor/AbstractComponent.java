package com.javaxator.patterns.visitor;

public abstract class AbstractComponent {

    public String getName() {
        return "Abstract Component";
    }

    public abstract void acceptVisitor(ShipComponentVisitor visitor);

    public abstract void doAction();

}
