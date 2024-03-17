package fr.shidozz.esharp;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author shidozz
 */
public class AddBC extends Bytecode {
    
    public AddBC() {
        super(BytecodeType.ADD);
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeByte(this.type.ordinal());
    }
    
    @Override
    public void print(){
        System.out.println("ADD");
    }
}
