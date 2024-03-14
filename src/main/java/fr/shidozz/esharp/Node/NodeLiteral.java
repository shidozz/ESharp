package fr.shidozz.esharp.Node;

/**
 *
 * @author shidozz
 */

public class NodeLiteral extends Node {
    private Object value;
    private LiteralType litType;

    public NodeLiteral(Object value, LiteralType litType) {
        this.value = value;
        this.litType = litType;
    }

    @Override
    public void print() {
        System.out.println("Literal: " + value + " Type: " + litType);
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
    public Object getValue(){
        return value;
    }
    
    public Object getLitType(){
        return litType;
    }
}