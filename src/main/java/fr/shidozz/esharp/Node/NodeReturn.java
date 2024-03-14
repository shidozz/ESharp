package fr.shidozz.esharp.Node;

/**
 *
 * @author shidozz
 */
public class NodeReturn extends Node {
    Node expression;

    public NodeReturn(Node expression) {
        this.expression = expression;
    }

    @Override
    public void print() {
        System.out.println("Return:");
        if (expression != null) {
            System.out.print("  Expression: ");
            expression.print();
        } else {
            System.out.println("  No expression");
        }
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

