package fr.shidozz.esharp;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author shidozz
 */
public class StoreVarBC extends Bytecode { 
    private int varId;
    public StoreVarBC(int varId) {
        super(BytecodeType.STORE_VAR);
        this.varId = varId;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeByte(this.type.ordinal());
        Compiler.writeIntLittleEndian(dos, varId);
    }
    
    @Override
    public void print(){
        System.out.println("STORE_VAR, " + varId);
    }
}
