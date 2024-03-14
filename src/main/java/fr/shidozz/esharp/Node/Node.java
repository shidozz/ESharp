package fr.shidozz.esharp.Node;

/**
 *
 * @author shidozz
 */
public abstract class Node {
    public abstract void print();
    public abstract void accept(Visitor visitor);
}

