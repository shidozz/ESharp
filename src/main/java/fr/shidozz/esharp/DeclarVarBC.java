package fr.shidozz.esharp;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author shidozz
 */
public class DeclarVarBC extends Bytecode { 
    private int varId;
    
    public DeclarVarBC(int varId) {
        super(BytecodeType.DECLARE_VAR);
        this.varId = varId;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeByte(this.type.ordinal());
        Compiler.writeIntLittleEndian(dos, varId);
    }
    
    @Override
    public void print(){
        System.out.println("DECLARE_VAR, " + varId);
    }
    
}
