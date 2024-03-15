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
        node.expression.accept(this);
        System.out.println("STORE_VAR, " + node.identifier);
        bytecodes.add(new StoreVarBC(node.identifier));
    }
    
    @Override
    public void visit(NodeBlock node) {
        System.out.println("Block:");

        for (Node n : node.statements) {
            n.accept(this);
        }
    }

    @Override
    public void visit(NodeExpression node) {
        node.leftOperand.accept(this);
        
        if(node.rightOperand != null){
            node.rightOperand.accept(this);
            
            switch (node.operator) {
                case MULTIPLY:
                    System.out.println("MUL");
                    bytecodes.add(new MulBC());
                    break;
                case DIVID:
                    System.out.println("DIV");
                    bytecodes.add(new DivBC());
                    break;
                case MINUS:
                    System.out.println("SUB");
                    bytecodes.add(new SubBC());
                    break;
                case PLUS:
                    System.out.println("ADD");
                    bytecodes.add(new AddBC());
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    
    @Override
    public void visit(NodeLiteral node) {
        System.out.println("PUSH, " + node.getValue());
        bytecodes.add(new PushBC((int)node.getValue()));
    }
    
    @Override
    public void visit(NodeVariableDeclaration node) {
        System.out.println("DECLARE_VAR, " + node.identifier);
        
        bytecodes.add(new DeclarVarBC(node.identifier));
        
        if(node.expression != null){
            node.expression.accept(this);
            
            System.out.println("STORE_VAR, " + node.identifier);
            bytecodes.add(new StoreVarBC(node.identifier));
        }
    }
}
