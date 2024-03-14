package fr.shidozz.esharp.Node;

/**
 *
 * @author shidozz
 */
public interface Visitor {
    public void visit(NodeVariableDeclaration node);
    public void visit(NodeFunctionDeclaration node);
    public void visit(NodeFunctionCall node);
    public void visit(NodeExpression node);
    public void visit(NodeAssignment node);
    public void visit(NodeLiteral node);
    public void visit(NodeReturn node);
    public void visit(NodeBlock node);
}

