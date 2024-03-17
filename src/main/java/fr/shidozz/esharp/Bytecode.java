package fr.shidozz.esharp;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author shidozz
 */
public abstract class Bytecode {
    BytecodeType type;
    
    public Bytecode(BytecodeType type){
        this.type = type;
    }
    
    public abstract void write(DataOutputStream dos) throws IOException;
    public abstract void print();
}
