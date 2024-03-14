package fr.shidozz.esharp;

import java.io.*;
import fr.shidozz.esharp.Node.*;
import static fr.shidozz.esharp.ModuleHeader.*;
import java.util.*;

/**
 *
 * @author shidozz
 */
public class Esharp {

    public static Compiler compiler;
    
    public static void main(String[] args) {
        if (args.length < 3) {
            for(String arg : args){
                System.out.println(arg);
            }
            System.out.println("Usage: java Esharp <input> <option> <output>");
            return;
        }
        Lexer lexer = new Lexer(args[0]);

        lexer.tokenize();
        Parser parser = new Parser(lexer.tokens);
        List<Node> nodes = parser.parse();
        EVisitor visitor = new EVisitor();
        
        Header header = new Header(new byte[]{'E', 'M', 'O', 'D'}, (short) 1, (byte) 0, (byte) 0, 1024, 512);
        List<Bytecode> bc = visitor.getByteCodes();
        try{
            compiler = new Compiler(args[2]);
            compiler.compile(header, bc);
        } catch(IOException e){
            e.printStackTrace();
        }
        for(Node node : nodes){
            node.accept(visitor);
        }
    }
}
