package fr.shidozz.esharp;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author shidozz
 */
public class MulBC extends Bytecode {
    
    public MulBC() {
        super(BytecodeType.MUL);
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeByte(this.type.ordinal());
    }
    
    @Override
    public void print(){
        System.out.println("MUL");
    }
}
