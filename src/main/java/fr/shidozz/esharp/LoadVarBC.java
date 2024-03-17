package fr.shidozz.esharp;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author shidozz
 */
public class LoadVarBC extends Bytecode { 
    private int varId;
    public LoadVarBC(int varId) {
        super(BytecodeType.LOAD_VAR);
        this.varId = varId;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeByte(this.type.ordinal());
        Compiler.writeIntLittleEndian(dos, varId);
    }
    
    @Override
    public void print(){
        System.out.println("LOAD_VAR, " + varId);
    }
}
