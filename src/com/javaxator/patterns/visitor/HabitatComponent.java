package com.javaxator.patterns.visitor;

public class HabitatComponent extends AbstractComponent {
    @Override
    public String getName() {
        return "Habitat component";
    }

    @Override
    public void acceptVisitor(ShipComponentVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void doAction() {
        System.out.println("Keeping lifeforms alive");
    }
}
