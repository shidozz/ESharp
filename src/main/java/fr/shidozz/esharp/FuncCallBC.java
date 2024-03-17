/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.shidozz.esharp;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author shidozz
 */
public class FuncCallBC extends Bytecode{
    private int funcId;
    public FuncCallBC(int funcId){
        super(BytecodeType.CALL_FUNCTION);
        this.funcId = funcId;
    }
    
    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeByte(this.type.ordinal());
        Compiler.writeIntLittleEndian(dos, funcId);
    }
    
    @Override
    public void print(){
        System.out.println("CALL_FUNCTION, " + funcId);
    }
}
