package fr.shidozz.esharp.Node;

/**
 *
 * @author shidozz
 */
public class NodeVariableDeclaration extends Node {
    String type;
    String identifier;
    Node expression;

    public NodeVariableDeclaration(String type, String identifier, Node expression) {
        this.type = type;
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public void print() {
        System.out.println("VariableDeclaration: type=" + type + ", identifier=" + identifier);
        if (expression != null) {
            System.out.print("  Expression: ");
            expression.print();
        }
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
