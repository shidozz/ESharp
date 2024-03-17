package fr.shidozz.esharp.Node;

import fr.shidozz.esharp.*;
import java.util.*;

/**
 *
 * @author shidozz
 */
public class EVisitor implements Visitor {
    private List<Bytecode> bytecodes = new ArrayList<>();
    private List<String> stackVar = new ArrayList<>();
    private List<String> stackFunc = new ArrayList<>();
    
    public List<Bytecode> getByteCodes(){
        return this.bytecodes;
    }
    
    @Override
    public void visit(NodeFunctionDeclaration node) {
        String functionName = node.functionName;
        List<String> parameters = node.parameters;
        
        stackFunc.add(node.functionName);
        int funcId = stackFunc.indexOf(node.functionName);
        bytecodes.add(new FuncCallBC(funcId));
        for(String param : parameters){
            stackVar.add(param);
            int paramId = stackVar.indexOf(param);
            bytecodes.add(new DeclarVarBC(paramId));
        }
        bytecodes.add(new ParamBC());
    
        node.block.accept(this);
    }

    @Override
    public void visit(NodeFunctionCall node) {
        for (Node n : node.arguments) {
            n.accept(this);
        }
        
        int funcId = stackFunc.indexOf(node.functionName);
        bytecodes.add(new FuncCallBC(funcId));
    }

    @Override
    public void visit(NodeReturn node) {
        bytecodes.add(new ReturnBC());
    }
    
    @Override
    public void visit(NodeAssignment node) {
        node.expression.accept(this);
        int varId = stackVar.indexOf(node.identifier);
        if (varId == -1) {
            System.err.println("Erreur : Variable '" + node.identifier + "' non déclarée");
            return;
        }
        bytecodes.add(new StoreVarBC(varId));
    }
    
    @Override
    public void visit(NodeBlock node) {
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
                    bytecodes.add(new MulBC());
                    break;
                case DIVID:
                    bytecodes.add(new DivBC());
                    break;
                case MINUS:
                    bytecodes.add(new SubBC());
                    break;
                case PLUS:
                    bytecodes.add(new AddBC());
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    
    @Override
    public void visit(NodeLiteral node) {
        if (node.getLitType() == LiteralType.IDENTIFIER) {
            String varName = (String) node.getValue();
            int varId = stackVar.indexOf(varName);
            if (varId == -1) {
                System.err.println("Erreur : Variable '" + varName + "' non déclarée");
                return;
            }
            bytecodes.add(new LoadVarBC(varId));
        } else if (node.getLitType() == LiteralType.NUMBER) {
            bytecodes.add(new PushBC((int) node.getValue()));
        }
    }
    
    @Override
    public void visit(NodeVariableDeclaration node) {     
        if(stackVar.contains(node.identifier)){
            System.err.println("'" + node.identifier + "' existe déjà");
            return;
        }
        
        stackVar.add(node.identifier);
        
        int varId = stackVar.indexOf(node.identifier);
        
        bytecodes.add(new DeclarVarBC(varId));
        
        if(node.expression != null){
            node.expression.accept(this);
            
            bytecodes.add(new StoreVarBC(varId));
        }
    }
}
