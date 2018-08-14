package com.javaxator.patterns.visitor;

public interface ShipComponentVisitor {

    void visit(DriveComponent component);
    void visit(EngineComponent component);
    void visit(HabitatComponent component);
    void visit(SteeringComponent component);

}
