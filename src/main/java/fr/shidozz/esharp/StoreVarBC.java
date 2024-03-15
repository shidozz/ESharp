package fr.shidozz.esharp;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author shidozz
 */
public class StoreVarBC extends Bytecode { 
    private String name;
    public StoreVarBC(String name) {
        super(BytecodeType.STORE_VAR);
        this.name = name;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeByte(this.type.ordinal());
        dos.writeBytes(name);
    }
}
