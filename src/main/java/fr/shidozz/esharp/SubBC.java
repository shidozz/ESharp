package fr.shidozz.esharp;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author shidozz
 */
public class SubBC extends Bytecode {
    private int left = 0;
    private int right = 0;
    
    public SubBC(int left, int right) {
        super(BytecodeType.SUB);
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
