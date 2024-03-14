package fr.shidozz.esharp.Node;

import java.util.*;

/**
 *
 * @author shidozz
 */
public class NodeFunctionDeclaration extends Node {
    String returnType;
    String functionName;
    List<String> parameters;
    Node block;

    public NodeFunctionDeclaration(String returnType, String functionName, List<String> parameters, Node block) {
        this.returnType = returnType;
        this.functionName = functionName;
        this.parameters = parameters;
        this.block = block;
    }

    @Override
    public void print() {
        System.out.println("FunctionDeclaration: returnType=" + returnType + ", functionName=" + functionName);
        System.out.println("  Parameters:");
        for (String parameter : parameters) {
            System.out.println("    " + parameter);
        }
        System.out.println("  Block:");
        block.print();
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

