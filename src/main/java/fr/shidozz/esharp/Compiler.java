package fr.shidozz.esharp;

import java.io.*;
import java.util.*;
import static fr.shidozz.esharp.ModuleHeader.*;

/**
 *
 * @author shidozz
 */
public class Compiler {
    List<Bytecode> opcode;
    public DataOutputStream dos;
    String filename;
    
    public Compiler(String filename) throws IOException {
        this.filename = filename;
        this.dos = new DataOutputStream(new FileOutputStream(filename));
    }
    
    public void compile(Header header, List<Bytecode> opcode) throws IOException {
        addModule(header);
        addOpcode(opcode);
        dos.close();
    }
    
    private void addModule(Header header) throws IOException {
        dos.write(header.magic);
        writeShortLittleEndian(dos, header.version);
        dos.writeByte(header.module_type);
        dos.writeByte(header.architecture);
        writeIntLittleEndian(dos, header.code_size);
        writeIntLittleEndian(dos, header.data_size);
        writeIntLittleEndian(dos, header.checksum);
        System.out.println("Checksum: " + header.checksum);
    }
    
    private void addOpcode(List<Bytecode> opcode) throws IOException {
        for (Bytecode instruction : opcode) {
            instruction.write(dos);
        }
    }
    
    public static void writeShortLittleEndian(DataOutputStream dos, short value) throws IOException {
        dos.writeByte(value & 0xFF);
        dos.writeByte((value >> 8) & 0xFF);
    }
    
    public static void writeIntLittleEndian(DataOutputStream dos, int value) throws IOException {
        dos.writeByte(value & 0xFF);
        dos.writeByte((value >> 8) & 0xFF);
        dos.writeByte((value >> 16) & 0xFF);
        dos.writeByte((value >> 24) & 0xFF);
    }

}
