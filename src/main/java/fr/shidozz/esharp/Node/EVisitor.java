package fr.shidozz.esharp.Node;

import fr.shidozz.esharp.*;
import java.util.*;

/**
 *
 * @author shidozz
 */
public class EVisitor implements Visitor {
    private List<Bytecode> bytecodes = new ArrayList<>();
    
    public List<Bytecode> getByteCodes(){
        return this.bytecodes;
    }
    
    @Override
    public void visit(NodeFunctionDeclaration node) {
        String functionName = node.functionName;
        List<String> parameters = node.parameters;

        System.out.println("Function Declaration:");
        System.out.println("Name: " + functionName);
        System.out.println("Parameters: " + parameters);
        node.block.accept(this);
    }

    @Override
    public void visit(NodeFunctionCall node) {
        // TODO: Implementation du visitor pour NodeFunctionCall
    }

    @Override
    public void visit(NodeReturn node) {
        // TODO: Implementation du visitor pour NodeReturn
    }
    
    @Override
    public void visit(NodeAssignment node) {
        System.out.println("Identifier: " + node.identifier);
        node.expression.print();
    }
    
    @Override
    public void visit(NodeBlock node) {
        System.out.println("Block:");

        for (Node n : node.statements) {
            if (n instanceof NodeVariableDeclaration) {
                System.out.println("Variable Declaration:");
                n.accept(this);
            } else if (n instanceof NodeFunctionCall) {
                System.out.println("Function Call:");
                n.accept(this);
            } else if (n instanceof NodeFunctionDeclaration) {
                System.out.println("Function Declaration:");
                n.accept(this);
            } else if (n instanceof NodeAssignment) {
                System.out.println("Assignment");
                n.accept(this);
            } else if (n instanceof NodeReturn) {
                System.out.println("Return:");
                n.accept(this);
            } else {
                System.out.println("Unknown statement:");
                n.accept(this);
            }
        }
    }

    @Override
    public void visit(NodeExpression node) {
        node.leftOperand.accept(this);
        NodeLiteral leftOP = (NodeLiteral) node.leftOperand;
        if(node.rightOperand != null){
            node.rightOperand.accept(this);
            NodeLiteral rightOP = (NodeLiteral) node.rightOperand;
        }
    }
    
    @Override
    public void visit(NodeLiteral node) {
        // TODO: Implementation du visitor pour NodeLiteral
    }
    
    @Override
    public void visit(NodeVariableDeclaration node) {
        if(node.expression != null)
            node.expression.accept(this);
    }
}
