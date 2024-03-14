package fr.shidozz.esharp;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author shidozz
 */
public class DivBC extends Bytecode {
    private int left = 0;
    private int right = 0;
    
    public DivBC(int left, int right) {
        super(BytecodeType.DIV);
        this.left = left;
        this.right = right;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeByte(this.type.ordinal());
        Compiler.writeIntLittleEndian(dos, this.left);
        Compiler.writeIntLittleEndian(dos, this.right);
    }
}
