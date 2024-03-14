package fr.shidozz.esharp.Node;

import fr.shidozz.esharp.*;

/**
 *
 * @author shidozz
 */

public class NodeExpression extends Node {
    Node leftOperand;
    TokenType operator;
    Node rightOperand;

    public NodeExpression(Node leftOperand, TokenType operator, Node rightOperand) {
        this.leftOperand = leftOperand;
        this.operator = operator;
        this.rightOperand = rightOperand;
    }

    @Override
    public void print() {
        System.out.println("Expression:");
        System.out.print("  Left Operand: ");
        leftOperand.print();
        System.out.println("  Operator: " + operator);
        System.out.print("  Right Operand: ");
        rightOperand.print();
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
