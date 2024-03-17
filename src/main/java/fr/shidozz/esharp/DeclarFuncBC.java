package fr.shidozz.esharp;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author shidozz
 */
public class DeclarFuncBC extends Bytecode { 
    private int funcId;
    
    public DeclarFuncBC(int funcId) {
        super(BytecodeType.DECLARE_FUNCTION);
        this.funcId = funcId;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeByte(this.type.ordinal());
        Compiler.writeIntLittleEndian(dos, funcId);
    }
    
    @Override
    public void print(){
        System.out.println("DECLARE_FUNC, " + funcId);
    }
    
}
