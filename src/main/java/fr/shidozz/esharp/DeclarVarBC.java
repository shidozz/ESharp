package fr.shidozz.esharp;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author shidozz
 */
public class DeclarVarBC extends Bytecode { 
    private String name;
    
    public DeclarVarBC(String name) {
        super(BytecodeType.DECLARE_VAR);
        this.name = name;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeByte(this.type.ordinal());
        dos.writeBytes(name);
    }
    
}
