package fr.shidozz.esharp.Node;

import java.util.*;

/**
 *
 * @author shidozz
 */
public class NodeFunctionCall extends Node {
    String functionName;
    List<Node> arguments;

    public NodeFunctionCall(String functionName, List<Node> arguments) {
        this.functionName = functionName;
        this.arguments = arguments;
    }

    @Override
    public void print() {
        System.out.println("FunctionCall: functionName=" + functionName);
        System.out.println("  Arguments:");
        for (Node argument : arguments) {
            argument.print();
        }
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
