package fr.shidozz.esharp.Node;

/**
 *
 * @author shidozz
 */

public class NodeAssignment extends Node {
    String identifier;
    Node expression;

    public NodeAssignment(String identifier, Node expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public void print() {
        System.out.println("Assignment: identifier=" + identifier);
        System.out.print("  Expression: ");
        expression.print();
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}