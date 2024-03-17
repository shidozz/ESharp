package fr.shidozz.esharp;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author shidozz
 */
public class HaltBC extends Bytecode {
    
    public HaltBC() {
        super(BytecodeType.HALT);
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeByte(this.type.ordinal());
    }
    
    @Override
    public void print(){
        System.out.println("HALT");
    }
}
