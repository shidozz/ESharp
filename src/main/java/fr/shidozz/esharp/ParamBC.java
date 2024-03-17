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
public class ParamBC extends Bytecode{
    
    public ParamBC(){
        super(BytecodeType.SEPARE_PARAM);
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeByte(this.type.ordinal());
    }

    @Override
    public void print() {
        System.out.println("SEPARE_PARAM");
    }
    
}
