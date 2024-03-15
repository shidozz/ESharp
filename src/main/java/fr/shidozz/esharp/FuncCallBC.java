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
    private String funcName;
    public FuncCallBC(String funcName){
        super(BytecodeType.CALL_FUNCTION);
        this.funcName = funcName;
    }
    
    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeByte(this.type.ordinal());
        dos.writeBytes(funcName);
    }
    
}
