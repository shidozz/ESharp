package fr.shidozz.esharp;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author shidozz
 */
public class PushBC extends Bytecode {
    private int value;
    
    public PushBC(int value){
        super(BytecodeType.PUSH);
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeByte(this.type.ordinal());
        Compiler.writeIntLittleEndian(dos, this.value);
    }
    
    @Override
    public void print(){
        System.out.println("PUSH, " + value);
    }
}
