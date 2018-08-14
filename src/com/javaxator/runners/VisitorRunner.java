package com.javaxator.runners;

import com.javaxator.patterns.visitor.*;

import java.util.Arrays;
import java.util.List;

public class VisitorRunner implements ShipComponentVisitor {

    public static void main(String[] argv) {

        List<AbstractComponent> abstractComponentList = Arrays.asList(
                new DriveComponent(),
                new EngineComponent(),
                new HabitatComponent(),
                new SteeringComponent()
        );
        VisitorRunner runner = new VisitorRunner();
        System.out.println("----------------");
        runner.noDoubleDispatch(abstractComponentList);
        System.out.println("----------------");
        runner.withVisitor(abstractComponentList);
    }


    public void noDoubleDispatch(List<AbstractComponent> components) {
        System.out.println("No double dispatch:");
        for (AbstractComponent component: components) {
            checkComponent(component);
        }
    }

    public void withVisitor(List<AbstractComponent> components) {
        System.out.println("Visitor pattern:");
        for (AbstractComponent component: components) {
            component.acceptVisitor(this);
        }
    }

    public void checkComponent(AbstractComponent component) {
        System.out.println("Component name: " + component.getName());
        System.out.println("Checking abstract component.");
    }

    public void checkComponent(DriveComponent component) {
        System.out.println("Component name: " + component.getName());
        System.out.println("Checking drive component.");
    }

    public void checkComponent(EngineComponent component) {
        System.out.println("Component name: " + component.getName());
        System.out.println("Checking engine component.");
    }

    public void checkComponent(HabitatComponent component) {
        System.out.println("Component name: " + component.getName());
        System.out.println("Checking habitat component.");
    }

    public void checkComponent(SteeringComponent component) {
        System.out.println("Component name: " + component.getName());
        System.out.println("Checking steering component.");
    }

    @Override
    public void visit(DriveComponent component) {
        checkComponent(component);
    }

    @Override
    public void visit(EngineComponent component) {
        checkComponent(component);
    }

    @Override
    public void visit(HabitatComponent component) {
        checkComponent(component);
    }

    @Override
    public void visit(SteeringComponent component) {
        checkComponent(component);
    }
}
