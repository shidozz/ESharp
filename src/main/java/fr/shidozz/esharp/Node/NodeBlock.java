package fr.shidozz.esharp.Node;

/**
 *
 * @author shidozz
 */
import java.util.*;

public class NodeBlock extends Node {
    List<Node> statements;

    public NodeBlock(List<Node> statements) {
        this.statements = statements;
    }

    @Override
    public void print() {
        System.out.println("Block:");
        for (Node statement : statements) {
            statement.print();
        }
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
