package fr.shidozz.esharp;

/**
 *
 * @author shidozz
 */
public class ModuleHeader {    
    enum emod_arch {
        EMOD_ARCH_BYTECODE,
        EMOD_ARCH_x86,
        EMOD_ARCH_x64,
        EMOD_ARCH_ARM32,
        EMOD_ARCH_ARM64,
        EMOD_ARCH_UNKOWN,
    }

    enum emod_type {
        EMOD_TYPE_KERNEL,
        EMOD_TYPE_USER,
        EMOD_TYPE_UNKOWN,
    }

    static class Header {
        byte[] magic = new byte[4];
        short version;
        byte module_type;
        byte architecture;
        int code_size;
        int data_size;
        int checksum;
        
        public Header(byte[] magic, short version, byte moduleType, byte architecture, int codeSize, int dataSize){
            this.magic = magic;
            this.version = version;
            this.module_type = moduleType;
            this.architecture = architecture;
            this.code_size = codeSize;
            this.data_size = dataSize;
            this.checksum = calcChecksum();
        }
        
        private int calcChecksum(){
            int value = 0;
            for (int i = 0; i < magic.length; i++) {
                value += magic[i];
            }
            return value;
        }
    }
    
    
}
